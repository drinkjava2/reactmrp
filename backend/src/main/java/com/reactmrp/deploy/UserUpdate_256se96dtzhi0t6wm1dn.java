package com.reactmrp.deploy;



import static com.github.drinkjava2.jsqlbox.DB.par;
import static com.github.drinkjava2.jsqlbox.DB.qryMapList;
import static com.github.drinkjava2.jsqlbox.DB.qry;
import com.github.drinkjava2.jsqlbox.*;
import com.github.drinkjava2.myserverless.BaseTemplate;

@SuppressWarnings("unused")
public class UserUpdate_256se96dtzhi0t6wm1dn extends template.QryMapListTemplate{
    
	@Override
	public Object executeBody() {
		/* MYSERVERLESS BODY BEGIN */
		String sql = "select userId from users order by userId";
		/* MYSERVERLESS BODY END */
		String[] paramArray = getParamArray();
		if (paramArray.length == 0)
			return qryMapList(sql);
		else
			return qryMapList(sql, par((Object[]) paramArray));
	}

}
