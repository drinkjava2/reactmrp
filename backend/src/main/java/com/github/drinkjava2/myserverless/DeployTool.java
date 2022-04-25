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
import com.github.drinkjava2.myserverless.util.Systemout;

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
            Systemout.println("Error: Deploy option can only be 'goServer' or 'goFront'");
    }

    /**
     * Register a customized method template
     */
    public static void registerMethodTemplate(String method, Class<?> templateClass) {
        MyServerlessEnv.registerMethodTemplate(method, templateClass);
    }

    /**
     * Push back all Sql/Java pieces to front side, except file start with "back"
     */
    public static void goFront() {
        Systemout.println("Current deploy folder is: " + MyServerlessEnv.getSrcDeployFolder());
        Systemout.println("Current frontend folder is: "+MyServerlessEnv.getSrcWebappFolder());
        List<File> htmlJspfiles = searchSupportedWebFiles(MyServerlessEnv.getSrcWebappFolder(), null);
        Systemout.println("Found "+htmlJspfiles.size()+" files, start transfer...");
        List<String> toDeleteJavas = new ArrayList<String>();
        for (File file : htmlJspfiles) 
            DeployToolUtils.oneFileToFront(file, false, toDeleteJavas, true);
        for (String javaFile : toDeleteJavas) {
            Systemout.println("Delete file:"+javaFile);
            new File(javaFile).delete();
        }
        System.out.println("Done!");
    }

    /**
     * Extract all Sql/Java pieces from front to backend, except java/sql piece started with "front"
     */
    public static void goServer() {
        Systemout.println("Current deploy folder is: " + MyServerlessEnv.getSrcDeployFolder());
        Systemout.println("Current frontend folder is: "+MyServerlessEnv.getSrcWebappFolder());
        List<File> files = searchSupportedWebFiles(MyServerlessEnv.getSrcWebappFolder(), null);
        Systemout.println("Found "+files.size()+" files, start transfer...");
        List<SqlJavaPiece> sqlJavaPieces = new ArrayList<>();
        for (File file : files)
            DeployToolUtils.oneFileToServ(sqlJavaPieces, file, true);
        for (SqlJavaPiece sqlJavaPiece : sqlJavaPieces) {
            System.out.println(sqlJavaPiece.getDebugInfo());
        }
        exportApiDoc(sqlJavaPieces);
        System.out.println("Done!");
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
        //Systemout.println("API file export: " + apiFile);
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
