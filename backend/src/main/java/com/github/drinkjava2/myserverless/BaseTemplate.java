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

import static com.github.drinkjava2.myserverless.util.JacksonUtil.getAsText;

import java.util.ArrayList;
import java.util.List;

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
    
    protected JsonNode jsonNode; //root node of json input

    protected String $0; //shortcut of paramArrays
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

    public void initParams(HttpServletRequest request, HttpServletResponse response, JsonNode jsonNode, String myToken) {
        this.request = request;
        this.response = response;
        this.myToken = myToken;
        this.jsonNode =jsonNode; 
        $0 = getAsText(jsonNode, "$0");
        $1 = getAsText(jsonNode, "$1"); 
        $2 = getAsText(jsonNode, "$2"); 
        $3 = getAsText(jsonNode, "$3"); 
        $4 = getAsText(jsonNode, "$4"); 
        $5 = getAsText(jsonNode, "$5"); 
        $6 = getAsText(jsonNode, "$6"); 
        $7 = getAsText(jsonNode, "$7"); 
        $8 = getAsText(jsonNode, "$8"); 
        $9 = getAsText(jsonNode, "$9"); 
        $10 = getAsText(jsonNode, "$10"); 
    }

    /** Pack all $1 , $2,... parameters into a String[] */
    public String[] getParamArray() {  
        List<String> paramList = new ArrayList<String>();
        for (int i = 1; i <= 100; i++) {
            String parameter = getAsText(jsonNode, "$" + i);
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

}
