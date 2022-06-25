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
import com.reactmrp.entity.Role;
import com.reactmrp.entity.User;

/**
 * MyServerless的TokenSecurity接口两个方法必须实现，以实现登录和token检查功能
 * 
 * @author Yong Zhu
 * @since 1.0.0
 */
public class ProjectSecurity implements TokenSecurity {
    //重要： 每当有人员、角色、权限变动时，都要调用userPowerCache.clearCache()清空缓存防止脏数据
    private static SimpleCacheHandler userPowerCache = new SimpleCacheHandler(300, 100 * 24 * 60 * 60);//缺省最多同时保存300个用户的权限表, 100天过期
    private static SimpleCacheHandler userRoleCache = new SimpleCacheHandler(300, 100 * 24 * 60 * 60);//缺省最多同时保存300个用户的权限表, 100天过期    
    
    public static String encodePassword(String password) {
        return MD5Util.encryptMD5("theSalt" + password);
    }

    @Override
    public String login(String userId, String password) { 
        if (MyStrUtils.isEmpty(userId) || MyStrUtils.isEmpty(password))
            return null;
        List<User> users = DB.entityFindBySample(new User().setUserId(userId).setPassword(encodePassword(password)));
        if(users.isEmpty())
            users = DB.entityFindBySample(new User().setName(userId).setPassword(encodePassword(password)));
        if (users.size()!=1)
            return null;
        String myToken = users.get(0).getUserId() + "_" + StrUtils.getRandomString(50); 
        users.get(0).setMyToken(myToken).update(DB.IGNORE_EMPTY);
        return myToken;
    }
 
    @Override
    public boolean allow(String myToken, String methodId) {
        return ifAllow(myToken, methodId);
    }

    public static boolean isValidToken(String myToken) { 
        myToken=MyStrUtils.trimAllWhitespace(myToken);
        if(MyStrUtils.isEmpty(myToken) || myToken.length()<10)
            return false;
        String userId = DB.qryString("select userId from users where myToken=", DB.que(myToken));
        return !(MyStrUtils.isEmpty(userId));
    }
    
    public static void logout(String myToken) {
        DB.exe("update users set myToken=null where myToken=", DB.que(myToken));
    }
    
    public static boolean ifAllow(String myToken, String methodId) {
        //只要方法id里包含public（不分大小写)都允许执行，通常是固定放在后端的方法，即BackendPublicxxx之类的。  在部署时要检查，所有的public方法都必须是允许不登录就允许执行的
        if (MyStrUtils.containsIgnoreCase(methodId, "public"))
            return true;

        //检查是否token存在
        String userId = DB.qryString("select userId from users where myToken=", DB.que(myToken));
        if (MyStrUtils.isEmpty(userId))
            return false;

        //获取用户权限list，注意这里使用了一个缓存，所以每当有人员、角色、权限变动时，都要调用clearCache清空缓存防止脏数据
        List<String> powers = DB.qryList(userPowerCache, "select p.* from users u ", //
                " left join userrole ur on u.userId=ur.userId ", //
                " left join roles r on ur.roleName=r.roleName ", //
                " left join rolepower rp on rp.roleName=r.roleName ", //
                " left join powers p on p.powerName=rp.powerName ", //
                " where u.userId=", DB.que(userId));

        if (powers.size() == 0) //如果什么权限都没有
            return false;

        //清除methodId关键字
        String cleanedMethodId = MyStrUtils.replaceIgnoreCase(methodId, "backend", "");
        cleanedMethodId = MyStrUtils.replaceIgnoreCase(cleanedMethodId, "frontend", "");
        cleanedMethodId = MyStrUtils.replaceIgnoreCase(cleanedMethodId, "public", "");
        cleanedMethodId = MyStrUtils.trimAllWhitespace(cleanedMethodId);
        if (MyStrUtils.isEmpty(cleanedMethodId))
            return false;
        for (String p : powers) { //methodId如果以任一个权限名开头，就返回true
            if (MyStrUtils.startsWithIgnoreCase(cleanedMethodId, p))
                return true;
        }
        
        return false;
    }

    public static Role getHighestRole(String myToken) {
        //检查是否token存在
        String userId = DB.qryString("select userId from users where myToken=", DB.que(myToken));
        if (MyStrUtils.isEmpty(userId))
            return null;

        //获取用户除developer之外的所有role
        List<String> ids =  DB.qryList(userRoleCache, "select r.roleName from users u ", //
                " left join userrole ur on u.userId=ur.userId ", //
                " left join roles r on ur.roleName=r.roleName ", //
                " where u.userId=", DB.que(userId), " and r.roleName<>'developer' order by roleLevel");
        if (ids.isEmpty()) //如果什么权限都没有
            return null;
        return new Role().loadById(ids.get(0)); //loadbyId, 只返回等级最高的role
    }

    
}
