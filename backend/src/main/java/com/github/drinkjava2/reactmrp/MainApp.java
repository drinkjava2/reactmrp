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
package com.github.drinkjava2.reactmrp;

import java.io.File;

import com.github.drinkjava2.myserverless.MyServerlessServlet;

import io.undertow.Undertow;
import io.undertow.server.handlers.resource.FileResourceManager;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ErrorPage;

/**
 * The application
 * 
 * @author Yong Zhu
 * @since 1.0.0
 */
public class MainApp {

    public static void main(String[] args) throws Exception {
        String projectFolder = new File("").getAbsolutePath();
        String webAppFolder = projectFolder + "/src/main/webapp";
        System.out.println("projectFolder=" + projectFolder);
        System.out.println("classAbsPath=" + Class.class.getClass().getResource("/").getPath());
        System.out.println("webAppFolder=" + webAppFolder);

        //注意这个server演示并没有用到web.xml,而是用Undertow的Java方法来进行服务端配置
        //以下为Undertow利用Java方法的配置，参见 http://undertow.io/undertow-docs/undertow-docs-2.0.0/index.html#creating-a-servlet-deployment
        DeploymentInfo info = Servlets.deployment().setClassLoader(MainApp.class.getClassLoader()).setContextPath("/").setDeploymentName("myserverlessServer");

        //InitConfig 进行了演示数据库的创建和MyServerless自定义模板方法的登记
        info.addServlet(Servlets.servlet("initConfig", InitConfig.class).setLoadOnStartup(0));

        //undertow添加MyServerlessServlet处理所有myserverless.do访问
        info.addServlet(Servlets.servlet("dispatch", MyServerlessServlet.class).addMapping("*.do"));
        info.setResourceManager(new FileResourceManager(new File(webAppFolder), 0))
                .addWelcomePage("/page/home.html")//指定缺省页
                .addErrorPage(new ErrorPage("/page/404.html")); //指定404页
        DeploymentManager manager = Servlets.defaultContainer().addDeployment(info);
        manager.deploy();
        Undertow server = Undertow.builder().addHttpListener(80, "localhost").setHandler(manager.start()).build();
        server.start();

        try {
            Runtime.getRuntime().exec("cmd /c start http://127.0.0.1"); //如果在windows下调用缺省browser
        } catch (Exception e) {
        }
        System.out.println("Undertow server started at port 80");
    }

}
