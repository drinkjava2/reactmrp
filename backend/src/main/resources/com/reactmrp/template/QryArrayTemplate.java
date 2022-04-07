package com.reactmrp.template;

import static com.github.drinkjava2.jsqlbox.DB.par;
import static com.github.drinkjava2.jsqlbox.DB.qry;
import com.github.drinkjava2.jsqlbox.*;
import com.github.drinkjava2.myserverless.BaseTemplate;

import org.apache.commons.dbutils.handlers.ArrayHandler;

@SuppressWarnings("unused")
public class QryArrayTemplate extends BaseTemplate {
    
	@Override
	public Object executeBody() {
		/* MYSERVERLESS BODY BEGIN */
		String sql = null;
		/* MYSERVERLESS BODY END */
		String[] paramArray = getParamArray();
		if (paramArray.length == 0)
			return qry(new ArrayHandler(), sql);
		else
			return qry(new ArrayHandler(), sql, par((Object[]) paramArray));
	}

}
