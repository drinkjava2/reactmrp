package com.reactmrp.deploy;

import com.github.drinkjava2.myserverless.MyServerlessEnv;
import com.reactmrp.config.ProjectSecurity;
 
public class BackendPublic { //本项目在ProjectSecurity里设定为 只有类名中有public字样就允许执行，所以只这个类里只能存放不需要登录就能公开访问的方法

    /**
     *CookieLogin将myToken放在cookie里，前端不用做任何事，但是如果前后端跨域了，配置比较麻烦，主要解决方案:采用Samesite=None配合Secure(即https),或用nginx把前后端配成同域，或是开发期修改浏览器设定
     *最好是开发期用TokenLogin模式登录，发布后再改成CookieLogin模式登录，兼顾开发方便和发布安全
     */
    public static class CookieLogin extends com.reactmrp.template.JavaTemplate {
        public Object executeBody() {
            String myToken = MyServerlessEnv.tokenSecurity.login($1, $2); 
            if(myToken==null)
                return false;
            response.addHeader("Set-Cookie", "myToken=\""+myToken+"\""+";SameSite=None;HttpOnly");
            return true;
        } 
    }
    
    /**
     *TokenLogin安全性不好，但是跨域方便。前端拿到myToken后要用localStorage.setItem("myToken", myToken); 保存在localStroage里 
     */
    public static class TokenLogin extends com.reactmrp.template.JavaTemplate {
        public Object executeBody() {
            String token = MyServerlessEnv.tokenSecurity.login($1, $2);
            return token;
        } 
    }
    
    public static class Logout extends com.reactmrp.template.JavaTemplate {
        public Object executeBody() {
            ProjectSecurity.logout(myToken);
            return true;
        } 
    }

    public static class CheckLogin extends com.reactmrp.template.JavaTemplate { 
        public Object executeBody() {
            return ProjectSecurity.isValidToken(myToken);
        }
    }

}
