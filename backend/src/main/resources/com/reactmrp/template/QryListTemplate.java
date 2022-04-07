package com.reactmrp.template;

import static com.github.drinkjava2.jsqlbox.DB.par;
import static com.github.drinkjava2.jsqlbox.DB.qryList;
import static com.github.drinkjava2.jsqlbox.DB.qry;
import com.github.drinkjava2.jsqlbox.*;
import com.github.drinkjava2.myserverless.BaseTemplate;

@SuppressWarnings("unused")
public class QryListTemplate extends BaseTemplate {
    
	@Override
	public Object executeBody() {
		/* MYSERVERLESS BODY BEGIN */
		String sql = null;
		/* MYSERVERLESS BODY END */
		String[] paramArray = getParamArray();
		if (paramArray.length == 0)
			return qryList(sql);
		else
			return qryList(sql, par((Object[]) paramArray));
	}

}
