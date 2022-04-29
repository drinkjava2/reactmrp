package com.reactmrp.deploy;



import static com.github.drinkjava2.jsqlbox.DB.*;

import com.reactmrp.config.ProjectSecurity;
import com.reactmrp.entity.*;
import com.alibaba.fastjson.*;
import com.github.drinkjava2.jsqlbox.*;
import com.github.drinkjava2.myserverless.*;
import com.github.drinkjava2.myserverless.util.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.Connection; 


@SuppressWarnings("all")
public class BackendCheckLogin_do extends com.reactmrp.template.JavaTemplate{

    @Override
	public Object executeBody() {
		/* MYSERVERLESS BODY BEGIN */
        return ProjectSecurity.ifLogin(json.getString("token"));
        /* MYSERVERLESS BODY END */
	}

}
