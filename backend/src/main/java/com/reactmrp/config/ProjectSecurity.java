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
    public String login(String userId, String password) {
        if (MyStrUtils.isEmpty(userId) || MyStrUtils.isEmpty(password))
            return null;
        List<User> users = DB.entityFindBySample(new User().setUserId(userId).setPassword(encodePassword(password)));
        if (users.isEmpty())
            users = DB.entityFindBySample(new User().setName(userId).setPassword(encodePassword(password)));
        if (users.size() != 1)
            return null;
        String myToken = users.get(0).getUserId() + "_" + StrUtils.getRandomString(50);
        users.get(0).setMyToken(myToken).update(DB.IGNORE_EMPTY);
        return myToken;
    }

    @Override
    public boolean allow(String myToken, String methodId, boolean hotCompile) {
        return ifAllow(myToken, methodId, hotCompile); //转为调用静态方法，静态方法的优点是可以在任意点发起调用，方便测试
    }

    public static boolean isValidToken(String myToken) {
        myToken = MyStrUtils.trimAllWhitespace(myToken);
        if (MyStrUtils.isEmpty(myToken) || myToken.length() < 10)
            return false;
        String userId = DB.qryString("select userId from users where myToken=", DB.que(myToken));
        return !(MyStrUtils.isEmpty(userId));
    }

    public static void logout(String myToken) {
        DB.exe("update users set myToken=null where myToken=", DB.que(myToken));
    }

    //为了提高性能也可以采用缓存，但每当有人员、权限变动时，都要调用userPowerCache.clearCache()清空缓存
    //private static SimpleCacheHandler userPowerCache = new SimpleCacheHandler(300, 100 * 24 * 60 * 60);//缺省最多同时保存300个用户的权限表, 100天过期

    public static boolean ifAllow(String myToken, String methodId, boolean hotCompile) {
        // 如果没登录只有一种情况可以执行，就是类在后端deploy目录下已存在，且方法名含有public
        boolean isPublic = MyStrUtils.containsIgnoreCase(methodId, "public");
        if (!hotCompile && isPublic)
            return true;

        //其余的只要未登录都拒绝执行
        if (MyStrUtils.isEmpty(myToken))
            return false;
        String userId = DB.qryString("select userId from users where myToken=", DB.que(myToken));
        if (MyStrUtils.isEmpty(userId))
            return false;

        //获取登录用户权限列表，这是一个典型的用户-角色-权限多对多关系
        List<String> powers = DB.qryList("select p.* from users u ", /* userPowerCache可选，见上, */
                " left join userrole ur on u.userId=ur.userId ", //
                " left join roles r on ur.roleName=r.roleName ", //
                " left join rolepower rp on rp.roleName=r.roleName ", //
                " left join powers p on p.powerName=rp.powerName ", //
                " where u.userId=", DB.que(userId));

        if (powers.isEmpty()) //如果什么权限都没有，拒绝执行
            return false;

        if (hotCompile && !powers.contains("hotCompile")) //如果要求hotCompile,但用户不具有hotCompile权限，拒绝执行 
            return false;

        powers.add("public");//所有已登录用户都自动具有public权限

        for (String p : powers) { //methodId如果以任一个权限开头，就允许执行，注意如果方法ID没有，缺省是default开头
            if (MyStrUtils.startsWithIgnoreCase(methodId, p))
                return true;
        }

        return false;
    }

}
