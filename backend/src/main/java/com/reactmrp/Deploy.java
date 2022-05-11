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
package com.reactmrp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.github.drinkjava2.myserverless.DeployTool;
import com.reactmrp.config.InitConfig;

/**
 *  Deploy工具必须先调用initMyServerlessTemplates方法才可以使用，每个项目的初始化方法内容可能不一样
 *  用法： 
 *  <pre>
 *  
 *  将服务器的MyServerless片段移到前端HTML页面里
 *  java -classpath ".;*" com.reactmrp.Deploy goFront
 *  
 *  将MyServerless片段从HTML页面移到服务器，以实现安全
 *  java -classpath ".;*" com.reactmrp.Deploy goServer
 *  
 *  </pre>
 */
public class Deploy {
    public static void main(String[] args) {
        InitConfig.initMyServerlessTemplates();
        DeployTool.deploy(args[0]);
    }

    @Test
    public void goServer() {
        InitConfig.initMyServerlessTemplates();
        DeployTool.deploy("goServer");
    }

    @Test
    public void goFront() {
        InitConfig.initMyServerlessTemplates();
        DeployTool.deploy("goFront");
    }

    @Test
    public void tempSearchFile() {//临时用一下
        List<File> files = searchFilesInFolder("E:\\gproj\\githubreactmrp\\react-mrp\\frontend\\src");
        for (File file : files) {
            System.out.println(file.getName());
        }
    }

    public static List<File> searchFilesInFolder(String fullPath) {
        List<File> files = new ArrayList<File>();
        File file = new File(fullPath);
        File[] array = file.listFiles();
        if (array == null)
            return files;
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i].getName());
            if (array[i].isFile()) {
                String fileName = array[i].getName();
                files.add(array[i]);
            } else if (array[i].isDirectory()) {
                searchFilesInFolder(array[i].getPath());
            }
        }
        return files;
    }

}
