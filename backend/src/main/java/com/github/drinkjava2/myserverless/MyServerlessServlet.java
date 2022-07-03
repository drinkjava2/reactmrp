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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.drinkjava2.myserverless.compile.DynamicCompileEngine;
import com.github.drinkjava2.myserverless.util.JsonUtil;
import com.github.drinkjava2.myserverless.util.MyStrUtils;

/**
 * Dispatch call to local java classes and return a JSON
 * 
 * @author Yong Zhu
 * @since 1.0.0
 */
@SuppressWarnings("all")
public class MyServerlessServlet extends HttpServlet {

    private static void setResponseXhrHeaders(HttpServletResponse resp) {
        resp.addHeader("Access-Control-Allow-Origin", MyServerlessEnv.Access_Control_Allow_Origin);
        resp.addHeader("Access-Control-Allow-Methods", MyServerlessEnv.Access_Control_Allow_Methods);
        resp.addHeader("Access-Control-Max-Age", MyServerlessEnv.Access_Control_Max_Age);
        resp.addHeader("Access-Control-Allow-Headers", MyServerlessEnv.Access_Control_Allow_Headers);
        resp.addHeader("Access-Control-Allow-Credentials", MyServerlessEnv.Access_Control_Allow_Credentials);
    };

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //CORS options request
        setResponseXhrHeaders(resp);
        resp.setCharacterEncoding("utf-8");
        resp.setStatus(200);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    public static void doAction(HttpServletRequest req, HttpServletResponse resp) {
        setResponseXhrHeaders(resp);
        resp.setCharacterEncoding("utf-8");

        JsonResult jsonResult = doActionBody(req, resp);
        Integer status = jsonResult.getStatus();
        if (status != null)
            resp.setStatus(status);
        else
            resp.setStatus(200);
        jsonResult.setStatus(null); //no need put status in json

        if (BaseTemplate.NONE.equals(jsonResult.getData())) // if return NONE, do nothing, user can write to req themself
            return;

        resp.setHeader("Content-Type", "application/json;charset:utf-8");
        String jsonStr = JsonUtil.toJSON(jsonResult);
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            out.println(jsonStr);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                out.close();
        }
    }

    /** Dispatch remote call to related classes, and return a json */
    public static JsonResult doActionBody(HttpServletRequest req, HttpServletResponse resp) {
        String jsonString = null;
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            return JsonResult.json206("Error: unsupported utf-8 encoding on server side.", req, null);
        }

        try {
            BufferedReader reader = req.getReader();
            jsonString = reader.readLine();
        } catch (IOException e1) {
            return JsonResult.json206("Error: can not read json on server side.", req, null);
        }

        Map<String, Object> params = null;
        try {
            params = (Map<String, Object>) new ObjectMapper().readValue(jsonString, Object.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (params == null)
            return JsonResult.json206("Error: unsupport json format on server side.", req, null);

        String sqlOrJavaPiece = (String) params.get("$0");
        String remoteMethod = (String) params.get("remoteMethod"); // like java/javaTx/qryMapList...
        if (remoteMethod == null)
            remoteMethod = "";
        String myToken = (String) params.get("myToken");

        if (MyStrUtils.isEmpty(myToken) || myToken.length() < 10) {//if myToken is empty or wrong, get from cookie
            Cookie[] cookies = req.getCookies();
            if (cookies != null && cookies.length > 0)
                for (Cookie cookie : cookies) {
                    if ("myToken".equals(cookie.getName()))
                        myToken = cookie.getValue();
                }
        }

        if (MyStrUtils.isEmpty(sqlOrJavaPiece))
            return JsonResult.json206("Error: request body is empty.", req, jsonString);

        Class<?> childClass = null;
        boolean hotCompile=false;
        try {
            childClass = MyServerlessEnv.findCachedClass(sqlOrJavaPiece); //先试着看是不是sqlOrJavaPiece只是一个类名，且这个类已在后端deploy目录下存在
            if (childClass != null) {
                String methodId = MyStrUtils.substringBefore(childClass.getName(), "_"); //com.xx.deploy.xxPublicx$xxx 
                methodId = MyStrUtils.substringAfterLast(methodId, "."); // xxPublicx$xxx
                String error=MyServerlessEnv.tokenSecurity.check(myToken, methodId, hotCompile);//重要，在这里调用系统配置的TokenSecurity进行权限检查
                if(!MyStrUtils.isEmpty(error))
                    return JsonResult.json206(error);
            } else {
                if (MyServerlessEnv.is_product_stage)
                    return JsonResult.json206("Error: hot compile is not allowed in product stage.");

                if (MyStrUtils.isEmpty(myToken) || myToken.length() < 10) //如果myToken没有，直接报错，不允许动态编译
                    return JsonResult.json206("Error: myToken not found.");

                PieceType pieceType = PieceType.byRemoteMethodName(remoteMethod);
                Class<?> templateClass = MyServerlessEnv.methodTemplates.get(remoteMethod);
                if (templateClass == null)
                    return JsonResult.json206("Error: server template '" + remoteMethod + "' not found.", req, jsonString);

                SqlJavaPiece piece = SqlJavaPiece.parseFromFrontText(remoteMethod, sqlOrJavaPiece);
                String methodId = piece.getClassName(); //admin_rxumbbmwww3r6k3fyp8i
                methodId = MyStrUtils.substringBefore(methodId, "_");//admin
                hotCompile=true;
                String error=MyServerlessEnv.tokenSecurity.check(myToken, methodId, hotCompile);//重要，在这里调用系统配置的TokenSecurity进行权限检查
                if(!MyStrUtils.isEmpty(error))
                    return JsonResult.json206(error); 

                String classSrc = SrcBuilder.createSourceCode(templateClass, pieceType, piece);
                //注意下面这个方法动态编译Java源码，但是它自带缓存，如果相同的内容则直接返回缓存中上次编译后获得的类
                childClass = DynamicCompileEngine.instance.javaCodeToClass(MyServerlessEnv.deploy_package + "." + piece.getClassName(), classSrc);
                if (childClass == null) //still is null
                    return JsonResult.json206("Error: hot compile failed.", req, jsonString);
            }

            BaseTemplate instance = null;
            if (BaseTemplate.class.isAssignableFrom(childClass)) {
                instance = (BaseTemplate) childClass.newInstance(); //这里只能用newInstance生成多例，如果要采用单例模式虽然可以节省一点内存，但是req、rep、json只能放在线程变量里传递太麻烦
            } else
                return JsonResult.json206("Error: incorrect base template.", req, jsonString);

            instance.initParams(req, resp, params, myToken);

            return instance.execute();
        } catch (Exception e) {
            e.printStackTrace();
            if (MyServerlessEnv.allow_debug_info) //if debugInfo is true, will put exception message and debug info in JSON
                return JsonResult.json206("Error: server internal error.").setDebugInfo(JsonResult.getDebugInfo(req, jsonString) + "\n" + e.getMessage());
            else
                return JsonResult.json206("Error: server internal error.");
        }
    } 
}
