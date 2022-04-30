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
        return MD5Util.encryptMD5("theSalt" + password);
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

    //1.重要： 每当有人员、角色、权限变动时，都要调用clearCache清空缓存防止脏数据
    //2.MRP系统另起守护线程，在每天或每周5晚12点清空所有user的token，并清空这些cache，强制用户下次必须重新登录
    //3.缓存的大小取决于有多少个用户，MRP系统通常用户数不超过1000
    private static SimpleCacheHandler userPowerCache = new SimpleCacheHandler(300, 100 * 24 * 60 * 60);//缺省最多同时保存300个token, 100天过期

    /** clearCache  */
    public static void clearCache() {
        userPowerCache.clearCache();
    }

    @Override
    public boolean allow(String token, String methodId) {
        return ifAllow(token, methodId);
    }

    public static boolean ifLogin(String token) {
        token=MyStrUtils.trimAllWhitespace(token);
        if(MyStrUtils.isEmpty(token))
            return false;
        String userName = DB.qryString("select userName from users where token=", DB.que(token));
        return !(MyStrUtils.isEmpty(userName));
    }
    
    public static boolean ifAllow(String token, String methodId) {
        //只要方法id里包含public（不分大小写)都允许执行，通常是固定放在后端的方法，即BackendPublicxxx之类的。  在部署时要检查，所有的public方法都必须是允许不登录就允许执行的
        if (MyStrUtils.containsIgnoreCase(methodId, "public"))
            return true;

        //检查是否token存在
        String userName = DB.qryString("select userName from users where token=", DB.que(token));
        if (MyStrUtils.isEmpty(userName))
            return false;

        //获取用户权限list，注意这里使用了一个缓存，所以每当有人员、角色、权限变动时，都要调用clearCache清空缓存防止脏数据
        List<String> powers = DB.qryList(userPowerCache, "select p.* from users u ", //
                " left join userrole ur on u.userName=ur.userName ", //
                " left join roles r on ur.roleName=r.roleName ", //
                " left join rolepower rp on rp.roleName=r.roleName ", //
                " left join powers p on p.powerName=rp.powerName ", //
                " where u.userName=", DB.que(userName));

        if (powers.size() == 0) //如果什么权限都没有
            return false;

        //清除methodId关键字
        String methodId2 = MyStrUtils.replaceIgnoreCase(methodId, "backend", "");
        methodId2 = MyStrUtils.replaceIgnoreCase(methodId2, "frontend", "");
        methodId2 = MyStrUtils.replaceIgnoreCase(methodId2, "public", "");
        methodId2 = MyStrUtils.trimAllWhitespace(methodId2);
        if (MyStrUtils.isEmpty(methodId2))
            return false;

        for (String p : powers) { //methodId如果以当前用户拥有的任一个权限开头，就返回true
            if (MyStrUtils.startsWithIgnoreCase(methodId2, p))
                return true;
        }

        return false;
    }

}
