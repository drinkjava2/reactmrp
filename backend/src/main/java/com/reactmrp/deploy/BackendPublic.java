package com.reactmrp.deploy;

import javax.servlet.http.Cookie;

import com.github.drinkjava2.myserverless.MyServerlessEnv;
import com.reactmrp.config.ProjectSecurity;
 
public class BackendPublic { //本项目在ProjectSecurity里设定为 只有类名中有public字样就允许执行，所以只这个类里只能存放不需要登录就能公开访问的方法

    public static class Login extends com.reactmrp.template.JavaTemplate {
        public Object executeBody() {
            String myToken = MyServerlessEnv.getTokenSecurity().login($1, $2); 
            if(myToken==null)
                return "login fail";
            Cookie cookie = new Cookie("myToken", myToken);
            cookie.setHttpOnly(true); //httponly标记会让浏览器禁止javascript访问这个cookie
            response.addCookie(cookie);
            return "success";
        } 
    }
    
    public static class LoginReturnMyToken extends com.reactmrp.template.JavaTemplate {
        public Object executeBody() {
            String token = MyServerlessEnv.getTokenSecurity().login($1, $2);
            return token;
        } 
    }
    
    public static class Logout extends com.reactmrp.template.JavaTemplate {
        public Object executeBody() {
            ProjectSecurity.logout(myToken);
            return "success";
        } 
    }

    public static class CheckLogin extends com.reactmrp.template.JavaTemplate { 
        public Object executeBody() {
            return ProjectSecurity.isValidToken(myToken);
        }
    }

}
