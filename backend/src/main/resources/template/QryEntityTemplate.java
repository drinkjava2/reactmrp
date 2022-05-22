package template;

import static com.github.drinkjava2.jsqlbox.DB.par;
import static com.github.drinkjava2.jsqlbox.DB.qryEntityList;

import java.util.List;

import com.github.drinkjava2.jdialects.ClassCacheUtils;
import com.github.drinkjava2.jsqlbox.DbException;
import com.github.drinkjava2.myserverless.BaseTemplate;
import com.github.drinkjava2.myserverless.util.MyStrUtils;

@SuppressWarnings("unused")
public class QryEntityTemplate extends BaseTemplate {
    
	@Override
	public Object executeBody() {
		/* MYSERVERLESS BODY BEGIN */
		String sql = null;
		/* MYSERVERLESS BODY END */
		String entityClassName = MyStrUtils.substringBefore(sql, ",");
		Class<?> entityClass = ClassCacheUtils.checkClassExist(entityClassName);
		DbException.assureNotNull(entityClass, "Entity class parameter can not be null");
		sql = MyStrUtils.substringAfter(sql, ",");
		String[] paramArray = getParamArray();
		List<Object> result;
		if (paramArray.length == 0)
			result = qryEntityList(entityClass, sql);
		else
			result = qryEntityList(entityClass, sql, par((Object[])paramArray));
		if (result == null || result.size() == 0)
			return null;
		else
			return result.get(0);
	}

}
