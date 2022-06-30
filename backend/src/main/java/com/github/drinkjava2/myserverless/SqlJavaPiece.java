/* Copyright 2018-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package com.github.drinkjava2.myserverless;

import static com.github.drinkjava2.myserverless.util.MyStrUtils.getRandomClassName;
import static com.github.drinkjava2.myserverless.util.MyStrUtils.isEmpty;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.drinkjava2.myserverless.util.MyFileUtils;
import com.github.drinkjava2.myserverless.util.MyStrUtils;

/**
 * SQL or Java source code piece, this is a virtual model represents the sql or
 * java piece extracted from html file or remote ajax call
 * 
 * @author Yong Zhu
 * @since 1.0.0
 */
@SuppressWarnings("all")
public class SqlJavaPiece {
    String id = null; // null: id is random, "#xxxxxxx":assign ID, "#":assign id by computer
    String imports = ""; // import abc.def; import abc.hij;...
    String body = ""; // the SQL or Java piece body text
    String originText = ""; // the origin text to be parsed
    String methodType = ""; // can only be SQL or JAVA
    String packageName = ""; // if is "FULL" type, will parse package name
    String className = ""; // class name, like "DemoUser"
    String location = ""; //the piece located in which file

//    public String getDebugInfo() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("=====SqlJavaPiece debugInfo====\r");
//        sb.append("id=(").append(id).append(")\r");
//        sb.append("imports=(").append(imports).append(")\r");
//        sb.append("body=(").append(body).append(")\r");
//        sb.append("originText=(").append(originText).append(")\r");
//        sb.append("methodType=(").append(methodType).append(")\r");
//        sb.append("packageName=(").append(packageName).append(")\r");
//        sb.append("className=(").append(className).append(")\r");
//        sb.append("location=(").append(location).append(")\r");
//        return sb.toString();
//    }

    public SqlJavaPiece trim() {
        if (this.body == null)
            this.body = "";
        this.body = this.body.trim();
        return this;
    }

    /**
     * parse a sql/java front text to SqlJavaPiece Object usage: <br/>
     * SqlJavaPiece p = SqlJavaPiece.parse("#234234 Front import a.b.C; import
     * b.d.e; import b.d.e; select * from users ");
     * 
     */
    public static SqlJavaPiece parseFromFrontText(String remoteMethod, String frontText) {
        SqlJavaPiece piece = doParse(frontText);
        if (!piece.getClassName().isEmpty())
            return piece;
        if (isEmpty(piece.getId()))
            piece.setClassName("Default_" + getRandomOrCachedClassName(remoteMethod, frontText));
        else
            piece.setClassName(MyStrUtils.replace(piece.getId(), "#", "") + "_" + getRandomOrCachedClassName(remoteMethod, frontText));
        return piece;
    }

    // Cache random IDs, for developing stage only
    private static final Map<String, String> cachedRandomIdMap = new ConcurrentHashMap<String, String>();

    private static String getRandomOrCachedClassName(String remoteMethod, String frontText) {
        String key = remoteMethod + ":" + frontText;
        String id = cachedRandomIdMap.get(key);
        if (MyStrUtils.isEmpty(id)) {
            id = getRandomClassName(20);
            cachedRandomIdMap.put(key, id);
        }
        return id;
    }

    /**
     * Parse front text to SqlJavaPiece object
     * 
     * @param frontText
     * @return SqlJavaPiece
     */
    private static SqlJavaPiece doParse(String frontText) {
        SqlJavaPiece piece = new SqlJavaPiece();
        piece.setOriginText(frontText);
        if (MyStrUtils.isEmpty(frontText))
            return piece.trim();
        String lastPiece = frontText;
        String trimed = MyStrUtils.trimLeadingWhitespace(lastPiece);
        String firstWord = MyStrUtils.findFirstWordNoWhiteChars(trimed);
        while (!MyStrUtils.isEmpty(firstWord)) {
            if (firstWord.startsWith("#"))
                piece.setId(firstWord);
            else if ("import".equals(firstWord)) { // NOSONAR
                // a.b; import b.c; select * from users; // NOSONAR
                StringBuilder imports = new StringBuilder();
                while ("import".equals(firstWord)) {
                    String importStr = MyStrUtils.substringBefore(lastPiece, ";");
                    String stmp = (importStr + "; // MYSERVERLESS IMPORT").trim();
                    imports.append(stmp).append("\n");
                    lastPiece = lastPiece.substring(importStr.length() + 1);// import a.b; import c.d
                    trimed = lastPiece.trim();
                    firstWord = MyStrUtils.findFirstWordNoWhiteChars(trimed);
                    if (!"import".equals(firstWord)) {
                        piece.setImports(imports.toString());
                        piece.setBody(lastPiece);
                        return piece.trim();
                    }
                }
            } else {// Not imports found
                piece.setBody(lastPiece);
                return piece.trim();
            }
            lastPiece = trimed.substring(firstWord.length());
            trimed = MyStrUtils.trimLeadingWhitespace(lastPiece);
            firstWord = MyStrUtils.findFirstWordNoWhiteChars(trimed);
        }
        piece.setBody(lastPiece);
        return piece.trim();
    }

    public static SqlJavaPiece parseFromJavaSrcFile(String fileFullPath) {
        SqlJavaPiece piece = new SqlJavaPiece();
        String src = MyFileUtils.readFile(fileFullPath, "UTF-8");
        piece.setOriginText(src);
        if (MyStrUtils.isEmpty(src))
            return piece.trim();
        String code = MyStrUtils.substringBetween(src, "/* MYSERVERLESS BODY BEGIN */", "/* MYSERVERLESS BODY END */");
        if (!MyStrUtils.isEmpty(code))
            piece.setMethodType("JAVA");
        if (MyStrUtils.isEmpty(code))
            return piece.trim();

        piece.setBody(code);

        piece.setId(null);
        String imports = "";
        while (src.contains("// MYSERVERLESS IMPORT")) {
            String st = MyStrUtils.substringBefore(src, "// MYSERVERLESS IMPORT");
            st = MyStrUtils.substringAfterLast(st, "import ");
            imports += "\n" + ("import " + st).trim();
            src = MyStrUtils.replaceFirst(src, "// MYSERVERLESS IMPORT", "");
        }
        piece.setImports(imports);
        return piece.trim();
    }
    
    public String getMethodId() {
        return MyStrUtils.substringBefore(className, "_");
    }

    //======garbage code====
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImports() {
        return imports;
    }

    public void setImports(String imports) {
        this.imports = imports;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOriginText() {
        return originText;
    }

    public void setOriginText(String originText) {
        this.originText = originText;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    } 

}
