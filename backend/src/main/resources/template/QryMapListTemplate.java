package template;

import static com.github.drinkjava2.jsqlbox.DB.par;
import static com.github.drinkjava2.jsqlbox.DB.qryMapList;
import static com.github.drinkjava2.jsqlbox.DB.qry;
import com.github.drinkjava2.jsqlbox.*;
import com.github.drinkjava2.myserverless.BaseTemplate;

@SuppressWarnings("unused")
public class QryMapListTemplate extends BaseTemplate {
    
	@Override
	public Object executeBody() {
		/* MYSERVERLESS BODY BEGIN */
		String sql = null;
		/* MYSERVERLESS BODY END */
		Object[] paramArray = getParamArray();
		if (paramArray.length == 0)
			return qryMapList(sql);
		else
			return qryMapList(sql, par((Object[]) paramArray));
	}

}
