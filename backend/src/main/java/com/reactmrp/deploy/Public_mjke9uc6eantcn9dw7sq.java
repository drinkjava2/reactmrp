package com.reactmrp.deploy;



import static com.github.drinkjava2.jsqlbox.DB.*;
import com.reactmrp.entity.*;
import com.alibaba.fastjson.*;
import com.github.drinkjava2.jsqlbox.*;
import com.github.drinkjava2.myserverless.*;
import com.github.drinkjava2.myserverless.util.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.Connection; 


@SuppressWarnings("all")
public class Public_mjke9uc6eantcn9dw7sq extends com.reactmrp.template.JavaTemplate{

    @Override
	public Object executeBody() {
		/* MYSERVERLESS BODY BEGIN */String token = MyServerlessEnv.getTokenSecurity().login($1, $2); return token;/* MYSERVERLESS BODY END */
	}

}
