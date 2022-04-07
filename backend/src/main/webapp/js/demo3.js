
function postDemo3Form(){ 
		    var rst = $$java(`
import com.alibaba.fastjson.JSON;
import com.reactmrp.entity.User;
import com.github.drinkjava2.jdialects.StrUtils;
import com.github.drinkjava2.jsqlbox.DB;
import static com.github.drinkjava2.jsqlbox.DB.*;
import com.github.drinkjava2.myserverless.util.MD5Util; 
	        User u = JSON.parseObject($1, User.class);
	        StringBuilder error = new StringBuilder();
	        if (StrUtils.isEmpty(u.getUsername()))
	            error.append("User name can not be empty. <br/>");
	        if (DB.qryIntValue("select count(*) from t_user where username=",que(u.getUsername()))>0)
	            error.append("User name already exist. <br/>");	        
	        if (StrUtils.isEmpty(u.getIdentity()))
	            error.append("Identity can not be empty. <br/> ");
	        if (StrUtils.isEmpty(u.getPhoneNumber()))
	            error.append("Phone_number can not be empty. <br/>");
	        if (StrUtils.isEmpty(u.getPassword()))
	            error.append("Password can not be empty. <br/>");
	        String errors = error.toString();
	        if (errors.isEmpty()) {
	            u.setPassword(MD5Util.encryptMD5(u.getUsername()));
	            u.insert(par());
	            return new JsonResult(200, "User saved, now have " + DB.entityCount(User.class) + " records.");
	        } else
      	        return new JsonResult(0, errors);
	        `, formToJSON("form1"));
		    
		     $("#msg").html(rst.msg);
			 if(rst.code==200){
				 $("#msg").css("background", "#dfb");
				
				 users=$qryMapList(`select * from t_user order by id`);
		         if(users.length>0){
		             html="User List:<br/>";
			         for(var i=0;i<users.length;i++) 
				          html+="User Name:" +  users[i].USERNAME+", identity:"+ users[i].IDENTITY+", phoneNumber:"+ users[i].PHONENUMBER+"<br/>";
			         $("#Users").html(html);
		         } 	  
			   }
			  else     
				 $("#msg").css("background", "#ffbeb8");			 
} 