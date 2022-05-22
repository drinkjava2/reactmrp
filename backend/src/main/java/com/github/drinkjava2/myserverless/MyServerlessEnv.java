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
import com.github.drinkjava2.myserverless.util.MyStrUtils;

/**
 * MyServerlessEnv保存了从myserverless.properties中读取的所有配置
 * 
 * @author Yong Zhu
 * @since 1.0.0
 */
public class MyServerlessEnv {// NOSONAR
    static Properties prop = new Properties();
    static {
        InputStream is = DeployTool.class.getClassLoader().getResourceAsStream("myserverless.properties");
        if (is == null) {
            System.err.println("Error: Config file myserverless.properties not found.");
            System.exit(0);
        }
        try {
            prop.load(is);
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

    // product or develop. If set to true will return debug info to front end
    public static final boolean allow_debug_info = "true".equalsIgnoreCase(prop.getProperty("allow_debug_info"));

    // product or develop. If set to product, reject receive SQL and Java from front end
    public static final String stage = prop.getProperty("stage");

    public static final boolean is_product_stage = "product".equalsIgnoreCase(stage);

    // if export java class source file in classes/.../deploy folder, default is false
    public static final boolean allow_java_file_export = "true".equalsIgnoreCase(prop.getProperty("allow_java_file_export"));

    public static final TokenSecurity tokenSecurity; //TokenSecurity class name

    public static final List<String> web_files = new ArrayList<String>(); //html, htm, jsp, js, php 

    public static final Map<String, Class<?>> methodTemplates = new HashMap<String, Class<?>>();

    // deploy package name, store dynamic generated classed
    public static final String deploy_package = prop.getProperty("deploy_package");

    public static final String backend_folder; // absolute path of backend folder

    public static final String call_server_method = prop.getProperty("call_server_method"); //if change setting in properties file, also need change myserverless.js file

    public static final String api_export_file = prop.getProperty("api_export_file"); //API export file name, default is empty

    //http header setting
    public static final String Access_Control_Allow_Origin = prop.getProperty("Access_Control_Allow_Origin");
    public static final String Access_Control_Allow_Methods = prop.getProperty("Access_Control_Allow_Methods");
    public static final String Access_Control_Max_Age = prop.getProperty("Access_Control_Max_Age");
    public static final String Access_Control_Allow_Headers = prop.getProperty("Access_Control_Allow_Headers");
    public static final String Access_Control_Allow_Credentials = prop.getProperty("Access_Control_Allow_Credentials");

    static {
        if (!("product".equalsIgnoreCase(stage) || "develop".equalsIgnoreCase(stage)))
            throw new IllegalArgumentException("In myserverless.properties, stage can only be develop or product");

        try {
            String token_security = prop.getProperty("token_security");
            tokenSecurity = (TokenSecurity) Class.forName(token_security).newInstance();
        } catch (Exception e) {
            System.err.println("Error: token_security setting in myserverless.properties not right, should be a class name, like com.xx.Xxxx");
            throw new RuntimeException(e);
        }

        String web_files_str = prop.getProperty("web_files");
        if (MyStrUtils.isEmpty(web_files_str)) {
            throw new IllegalArgumentException("web_files configration missing, an example: web_files=html,htm,js");
        } else {
            String[] splited = MyStrUtils.split(",", web_files_str);
            for (String s : splited)
                web_files.add(MyStrUtils.trimAllWhitespace(s));
            if (web_files.isEmpty())
                throw new IllegalArgumentException("web_files configration missing, an example: web_files=html,htm,js");
        }

        if (MyStrUtils.isEmpty(call_server_method))
            throw new IllegalArgumentException("call_server_method configration missing");

        String newFilePath = new File("").getAbsolutePath();
        newFilePath = MyStrUtils.replace(newFilePath, "\\", "/");
        backend_folder = MyStrUtils.substringBefore(newFilePath, "/target");
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
        if (!MyStrUtils.isLegalClassName(sqlJavaPiece))
            return null;
        return ClassExistCacheUtils.checkClassExist(new StringBuilder(MyServerlessEnv.deploy_package).append(".").append(sqlJavaPiece).toString());
    }

    public static String getClassLoaderFolder() {
        String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
        path = MyStrUtils.replaceFirst(path, "file:/", "");
        path = MyStrUtils.replaceFirst(path, "file:", "");
        if (path.endsWith("/") || path.endsWith("\\"))
            path = path.substring(0, path.length() - 1);
        path = MyStrUtils.replace(path, "\\", "/");
        return path;
    }

    public static String getClassesDeployFolder() {
        return getClassLoaderFolder() + "/" + MyStrUtils.replace(deploy_package, ".", "/");
    }

    public static String getSrcDeployFolder() {
        return backend_folder + "/src/main/java/" + MyStrUtils.replace(deploy_package, ".", "/");
    }

    public static String getFrontendFolder() {
        String s = MyStrUtils.substringBeforeLast(backend_folder, "backend");
        return s + "frontend";
    }

    public static String[] getSrcWebappFolders() {
        return new String[]{backend_folder + "/src/main/webapp", getFrontendFolder() + "/src", getFrontendFolder() + "/public"};
    }

    public static boolean isWebFile(String filename) {
        for (String web_file : MyServerlessEnv.web_files)
            if (filename.endsWith("." + web_file)) {
                return true;
            }
        return false;
    }

}
