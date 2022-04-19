package com.reactmrp.deploy;



import com.github.drinkjava2.myserverless.JsonResult;
import com.github.drinkjava2.myserverless.MyServerlessEnv;
import com.github.drinkjava2.myserverless.util.MyServerlessStrUtils; 


@SuppressWarnings("all")
public class Public_2vasa1awftbnnyu2jbne extends com.reactmrp.template.JavaTemplate{

    @Override
	public Object executeBody() {
		/* MYSERVERLESS BODY BEGIN */
        String token = MyServerlessEnv.getTokenSecurity().login($1, $2);
		        if (!MyServerlessStrUtils.isEmpty(token))
	            return new JsonResult(200, "login success", token);
	        else
	            return new JsonResult(403, "login fail", "").setStatus(403);/* MYSERVERLESS BODY END */
	}

}
