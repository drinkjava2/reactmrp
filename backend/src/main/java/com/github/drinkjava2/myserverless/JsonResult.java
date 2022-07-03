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

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * JsonResult used to return a JSON to front end 
 */
public class JsonResult {
    public static final int CODE200 = 200;

    private int code = 0;

    private String msg;

    private Object data;

    private Integer status; //status only used for http status, front end should not use this field

    private Object debugInfo; //if in MyServerlessl.properties set debug_info=true will return detail debug info  

    public JsonResult() {
        //default constructor
    }

    /** this constructor to build normal return    */
    public JsonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /** this constructor to build normal return    */
    public JsonResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult json206(String msg, HttpServletRequest request, String json ) {
        JsonResult result = new JsonResult(500, msg).setStatus(500);
        if (MyServerlessEnv.allow_debug_info)
            result.setDebugInfo(getDebugInfo(request, json));
        return result;
    }
    
    public static JsonResult json206(String msg) {
        return new JsonResult(500, msg).setStatus(500);
    }
    
    /**  Get debug info of request   */
    public static String getDebugInfo(HttpServletRequest request, String json) {
        if (request == null || !MyServerlessEnv.allow_debug_info)
            return "";
        StringBuilder sb = new StringBuilder("\n");
        sb.append("Host: ").append(request.getHeader("Host")).append("\n");
        sb.append("URL: ").append(request.getRequestURL()).append("\n");
        sb.append("URI: ").append(request.getRequestURI()).append("\n");

        sb.append("Headers: ");
        Enumeration<String> headNames = request.getHeaderNames();
        while (headNames.hasMoreElements()) {
            String headName = headNames.nextElement();
            sb.append(headName).append(":").append(request.getHeader(headName)).append(";");
        }
        sb.append("\n");

        sb.append("Parameters: ");
        for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {
            Object o = e.nextElement();
            String name = o.toString();
            sb.append(name).append("=").append(request.getParameter(o.toString())).append(";");
        }
        sb.append("\n");
        
        sb.append("Cookies: ");
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
             for (Cookie cookie : cookies){  
                 sb.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
             }
         }     
        sb.append("\n");

        sb.append("JSON: ").append(json).append("\n");
        return sb.toString();
    }

    //============below are getter & setter ==============

    public int getCode() {
        return code;
    }

    public JsonResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public JsonResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public JsonResult setData(Object data) {
        this.data = data;
        return this;
    }

    public JsonResult setDataArray(Object... data) {
        this.data = data;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public JsonResult setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Object getDebugInfo() {
        return debugInfo;
    }

    public JsonResult setDebugInfo(Object debugInfo) {
        this.debugInfo = debugInfo;
        return this;
    }

}
