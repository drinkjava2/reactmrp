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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.github.drinkjava2.myserverless.util.ClassExistCacheUtils;
import com.github.drinkjava2.myserverless.util.MyServerlessStrUtils;

/**
 * DeployTool extract all SQL and Java in html or .js files to server side, and
 * reverse.
 * 
 * @author Yong Zhu
 * @since 1.0.0
 */
public class MyServerlessEnv {// NOSONAR

    private static final Map<String, Class<?>> methodTemplates = new HashMap<String, Class<?>>();

    private static final String deploy_package; // deploy package name, store dynamic generated classed

    private static final String project_root_folder; // absolute path of deploy package

    private static final String stage; // product or develop. If set to product, reject receive SQL and Java from front end

    private static final boolean debug_info; // product or develop. If set to true will return debug info to front end

    private static final boolean is_product_stage;

    private static final boolean java_file_export; // if export java class source file in classes/.../deploy folder, default is false

    private static final TokenSecurity tokenSecurity; //TokenSecurity class name

    private static final List<String> web_files = new ArrayList<String>(); //html, htm, jsp, js, php 

    private static final String call_deployed_method_name; //default is "callDeployed", if change, also need change myserverless.js file.
    
    private static final String api_export_file; //API export file name, default is empty

    static {
        InputStream is = DeployTool.class.getClassLoader().getResourceAsStream("myserverless.properties");
        if (is == null) {
            System.err.println("Error: Config file myserverless.properties not found.");
            System.exit(0);
        }
        Properties prop = new Properties();
        try {
            prop.load(is);
            deploy_package = prop.getProperty("deploy_package");
            //TEMPLATE_PACKAGE = prop.getProperty("template_package");

            stage = prop.getProperty("stage");
            if ("product".equalsIgnoreCase(stage) || "develop".equalsIgnoreCase(stage)) {
            } else
                throw new IllegalArgumentException("In myserverless.properties, stage can only be develop or product");
            is_product_stage = "product".equalsIgnoreCase(stage);

            String token_security = prop.getProperty("token_security");
            tokenSecurity = (TokenSecurity) Class.forName(token_security).newInstance();

            String debug_info_str = prop.getProperty("debug_info");
            if ("true".equalsIgnoreCase(debug_info_str) || "false".equalsIgnoreCase(debug_info_str)) {
            } else
                throw new IllegalArgumentException("In myserverless.properties, debug_info can only be true or false");
            debug_info = "true".equalsIgnoreCase(prop.getProperty("debug_info"));

            if ("true".equalsIgnoreCase(prop.getProperty("java_file_export")))
                java_file_export = true;
            else
                java_file_export = false;

            String web_files_str = prop.getProperty("web_files");
            if (MyServerlessStrUtils.isEmpty(web_files_str)) {
                throw new IllegalArgumentException("web_files configration missing, an example: web_files=html,htm,js");
            } else {
                String[] splited = MyServerlessStrUtils.split(",", web_files_str);
                for (String s : splited)
                    web_files.add(MyServerlessStrUtils.trimAllWhitespace(s));
                if (web_files.isEmpty())
                    throw new IllegalArgumentException("web_files configration missing, an example: web_files=html,htm,js");
            }

            String call_deployed_method_name_str = prop.getProperty("call_deployed_method_name");
            if (MyServerlessStrUtils.isEmpty(call_deployed_method_name_str))
                call_deployed_method_name = "callDeployed";
            else
                call_deployed_method_name = call_deployed_method_name_str;

            api_export_file=prop.getProperty("api_export_file");
            
            String newFilePath = new File("").getAbsolutePath();
            newFilePath = MyServerlessStrUtils.substringBefore(newFilePath, "\\target");
            project_root_folder = MyServerlessStrUtils.substringBefore(newFilePath, "/target");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Register customized MyServerless template class
     * 
     * @param methodName
     * @param templateClass
     */
    public static void registerMethodTemplate(String methodName, Class<?> templateClass) {
        methodTemplates.put(methodName, templateClass);
    }

    /**
     * Return class stored in deploy package, if not found, return null
     */
    public static Class<?> findCachedClass(String sqlJavaPiece) {
        if (sqlJavaPiece == null)
            return null;
        if (!MyServerlessStrUtils.isLegalClassName(sqlJavaPiece))
            return null;
        return ClassExistCacheUtils.checkClassExist(new StringBuilder(MyServerlessEnv.getDeployPackage()).append(".").append(sqlJavaPiece).toString());
    }

    public static String getClassesDeployFolder() {
        return getClassLoaderFolder() + "/" + MyServerlessStrUtils.replace(deploy_package, ".", "/");
    }

    public static String getSrcDeployFolder() {
        return getProjectRootFolder() + "/src/main/java/" + MyServerlessStrUtils.replace(deploy_package, ".", "/");
    }

    public static String getSrcWebappFolder() {
        return getProjectRootFolder() + "/src/main/webapp";

    }

    public static String getClassLoaderFolder() {
        String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
        path = MyServerlessStrUtils.replaceFirst(path, "file:/", "");
        path = MyServerlessStrUtils.replaceFirst(path, "file:", "");
        if (path.endsWith("/") || path.endsWith("\\"))
            path = path.substring(0, path.length() - 1);
        return path;
    }

    // ==========getter & setter =============

    public static Map<String, Class<?>> getMethodTemplates() {
        return methodTemplates;
    }

    public static String getDeployPackage() {
        return deploy_package;
    }

    public static String getProjectRootFolder() {
        return project_root_folder;
    }

    public static boolean isProductStage() {
        return is_product_stage;
    }

    public static boolean isDevelopStage() {
        return !is_product_stage;
    }

    public static boolean isDebugInfo() {
        return debug_info;
    }

    public static boolean isJavaFileExport() {
        return java_file_export;
    }

    public static TokenSecurity getTokenSecurity() {
        return tokenSecurity;
    }

    public static List<String> getWebFiles() {
        return web_files;
    }

    public static String getCallDeployedMethodName() {
        return call_deployed_method_name;
    }
    
    public static String getApiExportFile() {
        return api_export_file;
    }
}
