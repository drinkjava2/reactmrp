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

import com.github.drinkjava2.myserverless.DeployTool;

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

}
