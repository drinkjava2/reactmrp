package com.reactmrp.deploy;



import static com.github.drinkjava2.jsqlbox.DB.par;
import static com.github.drinkjava2.jsqlbox.DB.qryMapList;
import static com.github.drinkjava2.jsqlbox.DB.qry;
import com.github.drinkjava2.jsqlbox.*;
import com.github.drinkjava2.myserverless.BaseTemplate;

@SuppressWarnings("unused")
public class PUBLIC_p3y0s5twbrlwrqoyzo7u extends com.reactmrp.template.QryMapListTemplate{
    
	@Override
	public Object executeBody() {
		/* MYSERVERLESS BODY BEGIN */
		String sql = "select * from users order by username";
		/* MYSERVERLESS BODY END */
		String[] paramArray = getParamArray();
		if (paramArray.length == 0)
			return qryMapList(sql);
		else
			return qryMapList(sql, par((Object[]) paramArray));
	}

}
