package com.demo;

import static com.github.drinkjava2.jsqlbox.DB.par;
import static com.github.drinkjava2.jsqlbox.DB.qryEntityList;

import com.github.drinkjava2.jdialects.ClassCacheUtils;
import com.github.drinkjava2.jsqlbox.DbException;
import com.github.drinkjava2.myserverless.BaseTemplate;
import com.github.drinkjava2.myserverless.util.MyServerlessStrUtils;

@SuppressWarnings("unused")
public class QryEntityListTemplate extends BaseTemplate {
    
	@Override
	public Object executeBody() {
		/* MYSERVERLESS BODY BEGIN */
		String sql = null;
		/* MYSERVERLESS BODY END */
		String entityClassName = MyServerlessStrUtils.substringBefore(sql, ",");
		Class<?> entityClass = ClassCacheUtils.checkClassExist(entityClassName);
		DbException.assureNotNull(entityClass, "Entity class parameter can not be null");
		sql = MyServerlessStrUtils.substringAfter(sql, ",");
		String[] paramArray = getParamArray();
		Object result;
		if (paramArray.length == 0)
			result = qryEntityList(entityClass, sql);
		else
			result = qryEntityList(entityClass, sql, par((Object[])paramArray));
		return result;
	}

}
