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

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.drinkjava2.myserverless.compile.DynamicCompileEngine;
import com.github.drinkjava2.myserverless.util.MyStrUtils;

/**
 * Dispatch call to local java classes and return a JSON
 * 
 * @author Yong Zhu
 * @since 1.0.0
 */
@SuppressWarnings("all")
public class MyServerlessServlet extends HttpServlet {

    @Override 
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //CORS options request
        resp.setHeader("Access-Control-Allow-Origin", "*"); //allow cross origin access 
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Max-Age", "1728000");
        resp.addHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Credentials", "*");
        resp.setCharacterEncoding("utf-8");
        resp.setStatus(200); 
    };

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    public static void doAction(HttpServletRequest req, HttpServletResponse resp) {
        //1.if has login parameter, treat it as login method
        resp.setHeader("Access-Control-Allow-Origin", "*"); //allow cross origin access 
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Max-Age", "1728000");
        resp.addHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Credentials", "*");
        resp.setCharacterEncoding("utf-8");

        JsonResult jsonResult = doActionBody(req, resp);
        Integer status = jsonResult.getStatus();
        if (status != null)
            resp.setStatus(status);
        else
            resp.setStatus(200);
        jsonResult.setStatus(null); //no need put status in json

        if (BaseTemplate.NONE.equals(jsonResult.getData())) // if return NONE, do nothing
            return;

        resp.setHeader("Content-Type", "application/json;charset:utf-8");
        String json = JSON.toJSONString(jsonResult);
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            out.println(json);
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
        JSONObject json = null;
        String jsonString = null;
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            return JsonResult.json403("Error: Unsupported utf-8 encoding on server side.", req, null);
        }

        try {
            BufferedReader reader = req.getReader();
            jsonString = reader.readLine();
        } catch (IOException e1) {
            return JsonResult.json403("Error: can not read json on server side.", req, null);
        }
        json = JSON.parseObject(jsonString);
        if (json == null)
            return JsonResult.json403("Error: unknow request", req, null);

        String sqlOrJavaPiece = json.getString("$0");
        String remoteMethod = json.getString("remoteMethod");
        String myToken = json.getString("myToken");

        if (MyStrUtils.isEmpty(myToken) || myToken.length() < 10) {//if myToken is empty or wrong, get from cookie
            Cookie[] cookies = req.getCookies();
            if (cookies != null && cookies.length > 0)
                for (Cookie cookie : cookies) {
                    if ("myToken".equals(cookie.getName()))
                        myToken = cookie.getValue();
                }
        }

        if (MyStrUtils.isEmpty(sqlOrJavaPiece))
            return JsonResult.json403("Error: request body is empty.", req, json);

        Class<?> childClass = null;
        try {
            childClass = MyServerlessEnv.findCachedClass(sqlOrJavaPiece);
            if (childClass == null) {
                if (MyServerlessEnv.isProductStage())
                    return JsonResult.json403("Error: in product stage but not found class on server.", req, json);

                PieceType pieceType = PieceType.byRemoteMethodName(remoteMethod);
                Class<?> templateClass = MyServerlessEnv.getMethodTemplates().get(remoteMethod);
                if (templateClass == null)
                    return JsonResult.json403("Error: template class for remote method '" + remoteMethod + "' not found.", req, json);
                SqlJavaPiece piece = SqlJavaPiece.parseFromFrontText(remoteMethod, sqlOrJavaPiece);
                String classSrc = SrcBuilder.createSourceCode(templateClass, pieceType, piece);
                childClass = DynamicCompileEngine.instance.javaCodeToClass(MyServerlessEnv.getDeployPackage() + "." + piece.getClassName(), classSrc);
            }
            if (childClass == null) //still is null
                return JsonResult.json403("Error: compile failed on server side.", req, json);

            String methodId = MyStrUtils.substringBefore(childClass.getName(), "_");
            methodId = MyStrUtils.substringAfterLast(methodId, ".");

            if (!MyServerlessEnv.getTokenSecurity().allow(myToken, methodId)) //重要，在这里调用系统配置的TokenSecurity进行权限检查
                return JsonResult.json403("Error: no privilege to execute '" + methodId + "' method", req, json);

            BaseTemplate instance = null;
            if (BaseTemplate.class.isAssignableFrom(childClass)) {
                instance = (BaseTemplate) childClass.newInstance(); //这里只能用newInstance生成多例，如果要采用单例模式虽然可以节省一点内存，但是req、rep、json只能放在线程变量里传递太麻烦
            } else
                return JsonResult.json403("Error: incorrect MyServerless child template error.", req, json);

            instance.initParams(req, resp, json, myToken);

            return instance.execute();
        } catch (Exception e) {
            e.printStackTrace();
            if (MyServerlessEnv.isDebugInfo()) //if debugInfo is true, will put exception message and debug info in JSON
                return new JsonResult(403, "Error: server internal error.").setStatus(403).setDebugInfo(JsonResult.getDebugInfo(req, json) + "\n" + e.getMessage());
            else
                return JsonResult.json403("Error: server internal error.", req, json);
        }
    }

}
