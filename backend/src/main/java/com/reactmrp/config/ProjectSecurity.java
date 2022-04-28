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

import java.util.List;

import com.github.drinkjava2.jdbpro.handler.SimpleCacheHandler;
import com.github.drinkjava2.jdialects.StrUtils;
import com.github.drinkjava2.jsqlbox.DB;
import com.github.drinkjava2.myserverless.TokenSecurity;
import com.github.drinkjava2.myserverless.util.MD5Util;
import com.github.drinkjava2.myserverless.util.MyStrUtils;
import com.reactmrp.entity.User;

/**
 * MyServerless的TokenSecurity接口两个方法必须实现，以实现登录和token检查功能
 * 
 * @author Yong Zhu
 * @since 1.0.0
 */
public class ProjectSecurity implements TokenSecurity {

    public static String encodePassword(String password) {
        return MD5Util.encryptMD5("saltForMD5" + password);
    }

    @Override
    public String login(String userName, String password) {
        if (MyStrUtils.isEmpty(userName) || MyStrUtils.isEmpty(password))
            return null; 
        List<User> users = DB.entityFindBySample(new User().setUserName(userName).setPassword(encodePassword(password)));
        if (users.size() != 1)
            return null;
        String token = userName + "_" + StrUtils.getRandomString(50);
        new User().setUserName(userName).setToken(token).update(DB.IGNORE_EMPTY);
        return token;
    }

    public static SimpleCacheHandler loginTokenCache = new SimpleCacheHandler(3000, 10 * 24 * 60 * 60);//缺省最多同时保存3000个token, 10天过期 

    @Override
    public boolean allow(String token, String methodId) {
        if (MyStrUtils.containsIgnoreCase(methodId, "public")) //只要方法id里包含public都允许执行，通常是固定放在后端的方法，即BackendPublicxxx之类的
            return true;
        int i = DB.qryIntValue(loginTokenCache, "select count(*) from users where token=", DB.que(token));
        if (i == 1)
            return true;
        else
            return false;
    }

}
