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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.github.drinkjava2.myserverless.util.MyServerlessFileUtils;
import com.github.drinkjava2.myserverless.util.MyServerlessStrUtils;

/**
 * DeployTool extract all SQL and Java in html or .js files to server side, and
 * reverse.
 * 
 * @author Yong Zhu
 * @since 1.0.0
 */
public class DeployTool {

    public static void deploy(String option) {
        if ("goServer".equalsIgnoreCase(option))
            goServer();
        else if ("goFront".equalsIgnoreCase(option))
            goFront();
        else
            System.out.println("Error: Deploy option can only be 'goServer' or 'goFront'");
    }

    /**
     * Register a customized method template
     */
    public static void registerMethodTemplate(String method, Class<?> templateClass) {
        MyServerlessEnv.registerMethodTemplate(method, templateClass);
    }

    /**
     * Push back all Sql/Java pieces to front side, ignore "SERV" keyword
     */
    public static void goFront() {
        System.out.println("Current deploy folder is: " + MyServerlessEnv.getSrcDeployFolder());
        List<File> htmlJspfiles = searchSupportedWebFiles(MyServerlessEnv.getSrcWebappFolder(), null);
        List<String> toDeleteJavas = new ArrayList<String>();
        for (File file : htmlJspfiles)
            DeployToolUtils.oneFileToFront(file, false, toDeleteJavas, true);
        for (String javaFile : toDeleteJavas)
            new File(javaFile).delete();
    }

    /**
     * Extract all Sql/Java pieces from web files to server side java source code, no matter if it have "FRONT"
     * keyword or not
     */
    public static void goServer() {
        System.out.println("Current deploy folder is: " + MyServerlessEnv.getSrcDeployFolder());
        List<File> files = searchSupportedWebFiles(MyServerlessEnv.getSrcWebappFolder(), null);
        List<SqlJavaPiece> sqlJavaPieces = new ArrayList<>();
        for (File file : files)
            DeployToolUtils.oneFileToServ(sqlJavaPieces, file, true);
        exportApiDoc(sqlJavaPieces);
    }

    private static void exportApiDoc(List<SqlJavaPiece> pieces) {
        String apiFile = MyServerlessEnv.getApiExportFile();
        if (MyServerlessStrUtils.isEmpty(apiFile))
            return;
        StringBuilder apiHtml = new StringBuilder();
        apiHtml.insert(0, "<!DOCTYPE html>\n" + //
                "<html>\n" + //
                "<head> \n" + //
                "<meta charset=\"utf-8\"> \n" + //
                "<title>API List</title> \n" + //
                "<style> \n" + " td { word-break: break-all; }\n" + "</style> " + "</head>\n" + //
                "<body>\n" + //
                "<table width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"2\">\n" + //
                "<tr>\n" + //
                "  <th width=\"15%\">ID</th>\n" + //
                "  <th width=\"15%\">Location</th>\n" + //
                "  <th width=\"70%\">Source</th>\n" + //
                "</tr> ");
        for (SqlJavaPiece piece : pieces) {
            apiHtml.append("<tr>\n");
            apiHtml.append("<td>").append(piece.getClassName()).append("</td>\n");
            apiHtml.append("<td>").append(piece.getLocation()).append("</td>\n");
            apiHtml.append("<td><xmp>").append(piece.getBody()).append("</xmp></td>\n");
            apiHtml.append("</tr>\n");
        }
        apiHtml.append("</table>\n" + //
                "</body>\n" + //
                "</html>");
        MyServerlessFileUtils.writeFile(apiFile, apiHtml.toString(), "UTF-8");
        System.out.println("API file export: " + apiFile);
    }

    // ============static methods=============================

    private static List<File> searchSupportedWebFiles(String path, List<File> files) {
        if (files == null)
            files = new ArrayList<File>();
        File file = new File(path);
        File[] array = file.listFiles();
        if (array == null)
            return files;
        for (int i = 0; i < array.length; i++) {
            if (array[i].isFile()) {
                String fileName = array[i].getName();
                boolean isWebFile = false;
                for (String web_file : MyServerlessEnv.getWebFiles())
                    if (fileName.endsWith("." + web_file)) {
                        isWebFile = true;
                        break;
                    }

                if (isWebFile)
                    files.add(array[i]);
            } else if (array[i].isDirectory()) {
                searchSupportedWebFiles(array[i].getPath(), files);
            }
        }
        return files;
    }

}
