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
package com.reactmrp.config;

import com.github.drinkjava2.jdbpro.handler.SimpleCacheHandler;
import com.github.drinkjava2.jdialects.StrUtils;
import com.github.drinkjava2.jsqlbox.DB;
import com.github.drinkjava2.myserverless.TokenSecurity;
import com.github.drinkjava2.myserverless.util.MD5Util;
import com.github.drinkjava2.myserverless.util.MyServerlessStrUtils;

/**
 * MyServerless的TokenSecurity接口两个方法必须实现，以实现登录和token检查功能
 * 
 * @author Yong Zhu
 * @since 1.0.0
 */
public class MyTokenSecurity implements TokenSecurity {

    public static String encodePassword(String password) {
        return MD5Util.encryptMD5("saltForMD5" + password);
    }

    @Override
    public String login(String username, String password) {
        int i = DB.qryIntValue("select count(*) from users where username=", DB.que(username), " and password=", DB.que(encodePassword(password)));
        if (i == 1) {
            String token = username + "_" + StrUtils.getRandomString(50);
            DB.exe("update users set token=", DB.que(token), " where username=", DB.que(username), " and password=", DB.que(encodePassword(password)));
            return token;
        } else {
            return null;
        }
    }

    static SimpleCacheHandler tokenCache = new SimpleCacheHandler(3000, 10 * 24 * 60 * 60);//缺省最多同时保存3000个token, 10天过期 

    @Override
    public boolean allowExecute(String token, String methodId) {
        if (MyServerlessStrUtils.startsWithIgnoreCase(methodId, "public")) //只要public开头的方法都允许执行
            return true;
        int i = DB.qryIntValue(tokenCache, "select count(*) from users where token=", DB.que(token));
        if (i == 1)
            return true;
        else
            return false;
    }

}
