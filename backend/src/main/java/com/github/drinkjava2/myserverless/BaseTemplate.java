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

import static com.github.drinkjava2.myserverless.util.JsonUtil.getAsText;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;

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

    protected String myToken; // current myToken 
    
    protected  Map<String, Object> params; //root node of json input

    protected String $0; //shortcut of params, only give first 10
    protected Object $1;
    protected Object $2;
    protected Object $3;
    protected Object $4;
    protected Object $5;
    protected Object $6;
    protected Object $7;
    protected Object $8;
    protected Object $9;
    protected Object $10;

    public void initParams(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params, String myToken) {
        this.request = request;
        this.response = response;
        this.myToken = myToken;
        this.params =params; 
        $0 = (String) params.get("$0");
        $1 =  params.get("$1");  
        $2 =  params.get("$2"); 
        $3 =  params.get("$3"); 
        $4 =  params.get("$4"); 
        $5 =  params.get("$5"); 
        $6 =  params.get("$6"); 
        $7 =  params.get("$7"); 
        $8 =  params.get("$8"); 
        $9 =  params.get("$9"); 
        $10 =  params.get("$10");  
    }

    /** Pack all $1 , $2,... parameters into a Object[], usually used for SQL parameters */
    public Object[] getParamArray() { 
        if(params.size()<=3) //$0, token, remoteMethod is always existing
            return new Object[] {};
        Object[] objs=new Object[params.size()-3];
        for (int i = 1; i <= params.size()-3; i++)  
            objs[i-1]=params.get("$"+i);  
        return objs;
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

}
