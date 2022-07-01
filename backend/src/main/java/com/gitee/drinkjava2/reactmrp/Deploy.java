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
package com.gitee.drinkjava2.reactmrp;

import java.io.File;

import org.junit.Test;

import com.gitee.drinkjava2.reactmrp.config.InitConfig;
import com.github.drinkjava2.myserverless.DeployTool;
import com.github.drinkjava2.myserverless.util.MyFileUtils;
import com.github.drinkjava2.myserverless.util.MyStrUtils;

/**
 *  Deploy工具必须先调用initMyServerlessTemplates方法才可以使用，用法： 
 *  <pre>
 *  
 *  将服务器的MyServerless片段移到前端HTML页面里
 *  java -classpath ".;*" com.xx.xx.Deploy goFront
 *  
 *  将MyServerless片段从HTML页面移到服务器，以实现安全
 *  java -classpath ".;*" com.xx.xx.Deploy goServer
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
    public void replaceAtToAbolutePath() {//切换@符号到绝对路径以方便IDE跳转，否则eclipse+typescript插件是不支持@符号的
        for (File file : DeployTool.searchSupportedWebFilesInOnePath("E:\\reactmrp\\frontend\\src", null)) {
            String fp = file.getAbsolutePath();
            String s = MyFileUtils.readFile(fp, "utf-8"); 
            if ((fp.endsWith(".js") || fp.endsWith(".jsx")) && (s.contains("import \"@/") || s.contains(" from \"@/") ||  s.contains(" from '@/")  || s.contains("'*/'@/") )) {
                s = MyStrUtils.replace(s, "import \"@/", "import \"E:/reactmrp/frontend/src/");
                s = MyStrUtils.replace(s, " from \"@/", " from \"E:/reactmrp/frontend/src/");
                s = MyStrUtils.replace(s, " from '@/", " from 'E:/reactmrp/frontend/src/");
                s = MyStrUtils.replace(s, "'*/'@/", "'*/'E:/reactmrp/frontend/src/");
                MyFileUtils.writeAndPrintFilename(file.getAbsolutePath(), s, "utf-8");
                System.out.println(file.getAbsolutePath());
            }
        }
    }

    @Test
    public void replaceAbolutePathToAt() {//正式发布或提交到git服务器上之前，切换回绝对路径为@符号
        for (File file : DeployTool.searchSupportedWebFilesInOnePath("E:\\reactmrp\\frontend\\src", null)) {
            String fp = file.getAbsolutePath();
            String s = MyFileUtils.readFile(fp, "utf-8");
            if ((fp.endsWith(".js") || fp.endsWith(".jsx")) && s.contains("E:/reactmrp/frontend/src/")) {
                s = MyStrUtils.replace(s, "E:/reactmrp/frontend/src/", "@/");
                MyFileUtils.writeAndPrintFilename(file.getAbsolutePath(), s, "utf-8");
                System.out.println(file.getAbsolutePath());
            }
        }
    }

}