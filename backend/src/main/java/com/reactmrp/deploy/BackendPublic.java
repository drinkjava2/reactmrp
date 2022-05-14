package com.reactmrp.deploy;

import com.github.drinkjava2.myserverless.MyServerlessEnv;
import com.reactmrp.config.ProjectSecurity;
 
public class BackendPublic { //本项目在ProjectSecurity里设定为 只有类名中有public字样就允许执行，所以只这个类里只能存放少量必须公开的方法

    public static class Login extends com.reactmrp.template.JavaTemplate {
        public Object executeBody() {
            String token = MyServerlessEnv.getTokenSecurity().login($1, $2);
            return token;
        } 
    }

    public static class CheckLogin extends com.reactmrp.template.JavaTemplate {
        @Override
        public Object executeBody() {
            boolean result = ProjectSecurity.ifLogin(token);
            return result;
        }
    }

}
