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

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.github.drinkjava2.myserverless.util.MyFileUtils;

/**
 * This is the base environment servlet store environment info
 * 
 * @author Yong Zhu
 * @since 1.0.0
 */
@SuppressWarnings("all")
public abstract class BaseTemplate {
    public static final String NONE = "MyServerless NONE TAG"; //used to customize output  
    public int code = 200;
    public String message = "";

    protected HttpServletRequest request; // request instance

    protected HttpServletResponse response; // response instance

    protected JSONObject json; // json instance

    protected String $0;
    protected String $1;
    protected String $2;
    protected String $3;
    protected String $4;
    protected String $5;
    protected String $6;
    protected String $7;
    protected String $8;
    protected String $9;
    protected String $10;
    protected String $11;
    protected String $12;
    protected String $13;
    protected String $14;
    protected String $15;
    protected String $16;
    protected String $17;
    protected String $18;
    protected String $19;
    protected String $20;

    public void initParams(HttpServletRequest request, HttpServletResponse response, JSONObject json) {
        this.request = request;
        this.response = response;
        this.json = json;

        $0 = json.getString("$0");
        $1 = json.getString("$1");
        $2 = json.getString("$2");
        $3 = json.getString("$3");
        $4 = json.getString("$4");
        $5 = json.getString("$5");
        $6 = json.getString("$6");
        $7 = json.getString("$7");
        $8 = json.getString("$8");
        $9 = json.getString("$9");
        $10 = json.getString("$10"); 
    }

    /** Pack all $1 , $2,... to $10 parameters into a String[] */
    public String[] getParamArray() {
        List<String> paramList = new ArrayList<String>();
        for (int i = 1; i <= 100; i++) {
            String parameter = json.getString("$" + i);
            if (parameter != null)
                paramList.add(parameter);
            else
                break;
        }
        return paramList.toArray(new String[paramList.size()]);
    }
    
    /**
     * The body method for template
     * @return Object
     */
    public Object executeBody() {
        return null;
    }

    /**
     * Execute executeBody method and wrap result to a JsonResult result
     * @return JsonResult
     */
    public JsonResult execute() {
        Object data = executeBody();
        if (data instanceof JsonResult)
            return (JsonResult) data;
        return new JsonResult(code, message, data);
    }

    // getter & setters ========== 
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }

}
