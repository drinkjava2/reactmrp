package com.gitee.drinkjava2.reactmrp.deploy;

import static com.github.drinkjava2.jsqlbox.DB.que;

import java.util.List;
import java.util.Map;

import com.gitee.drinkjava2.reactmrp.config.ProjectTokenSecurity;
import com.gitee.drinkjava2.reactmrp.entity.Role;
import com.github.drinkjava2.jsqlbox.DB;
import com.github.drinkjava2.myserverless.MyServerlessEnv;

public class PublicBackend { //本项目在ProjectSecurity里设定为 deploy目录下只要类名以public开头就允许执行，所以只这个类里只存放不需要登录就能公开访问的方法

    /**
     * 这个方法接收userId和password,登录后将token放在cookie里并返回true, 如登录失败则返回false
     * CookieLogin将myToken放在cookie里，前端不用做任何事，但是如果前后端跨域了，配置比较麻烦，主要解决方案:采用https,即Samesite=None;Secure, 或用nginx把前后端配成同域
     * 最好是开发期用TokenLogin模式登录，发布后再改成CookieLogin模式登录，兼顾开发方便和发布安全
     */
    public static class CookieLogin extends template.JavaTemplate {
        public Object executeBody() {
            String myToken = MyServerlessEnv.tokenSecurity.login($1.toString(), $2.toString());
            if (myToken == null)
                return false;
            //这个只暂时适用Firefox, 在Chrome浏览器上不工作，Chrome上必须是同域，或采用HTTPS并且用SameSite=None;Secure;HttpOnly
            response.addHeader("Set-Cookie", "myToken=\"" + myToken + "\"" + ";SameSite=None;HttpOnly");
            return true;
        }
    }

    /**
     * 这个方法接收userId和password,登录后将token返回true, 如登录失败则返回null
     * TokenLogin安全性不好，但是跨域方便。前端拿到myToken后要用localStorage.setItem("myToken", myToken); 保存在localStroage里 
     */
    public static class TokenLogin extends template.JavaTemplate {
        public Object executeBody() {
            String token = MyServerlessEnv.tokenSecurity.login($1.toString(), $2.toString());
            return token;
        }
    }

    /**
     * 这个方法清除当前登录用户的服务端token，并返回true
     */
    public static class Logout extends template.JavaTemplate {
        public Object executeBody() {
            ProjectTokenSecurity.logout(myToken);
            return true;
        }
    }

    /**
     * 如果用户已登录，这个方法返回true，否则返回false
     */
    public static class CheckLogin extends template.JavaTemplate {
        public Object executeBody() {
            return ProjectTokenSecurity.isValidToken(myToken);
        }
    }


    /**
     * 返回当前登录用户信息，因为原前端仅用户来控制菜单显示且一个用户仅限1个角色（role），所以这个方法中如果用户有多个角色，也只取一个除developer之外最高等级的角色以兼容原前端代码 
     */
    public static class GetCurrentLoginUser extends template.JavaTemplate {
        public Object executeBody() {
            Map<String, Object> user = DB.qryMap("select userId as id, name, description, myToken as token, avatar, '' as role from users where myToken=", que(myToken));
            if (!user.isEmpty()) {
                List<String> roles =  DB.qryList("select r.roleName from users u ", //
                        " left join userrole ur on u.userId=ur.userId ", //
                        " left join roles r on ur.roleName=r.roleName ", //
                        " where u.userId=", DB.que(user.get("id")), " and r.roleName<>'developer' order by roleLevel"); //排除developer角色之后的最高业务级别role
                if (!roles.isEmpty())
                    user.put("role", roles.get(0)); //取roleLevel最小的roleName,即最高等级的角色 
            }
            return user;
        }
    } 
}
