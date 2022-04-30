package com.reactmrp.deploy;

import com.github.drinkjava2.myserverless.MyServerlessEnv;
import com.reactmrp.config.ProjectSecurity;

@SuppressWarnings("all")
public class BackendPublic {

    public static class Login extends com.reactmrp.template.JavaTemplate {
        public Object executeBody() {
            String token = MyServerlessEnv.getTokenSecurity().login($1, $2);
            return token;
        } 
    }

    public static class CheckLogin extends com.reactmrp.template.JavaTemplate {
        @Override
        public Object executeBody() {
            boolean result = ProjectSecurity.ifLogin(json.getString("token"));
            return result;
        }
    }

}
