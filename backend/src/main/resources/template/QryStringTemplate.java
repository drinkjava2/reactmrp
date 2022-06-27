package template;

import static com.github.drinkjava2.jsqlbox.DB.par;
import static com.github.drinkjava2.jsqlbox.DB.qryString;
import static com.github.drinkjava2.jsqlbox.DB.qry;
import com.github.drinkjava2.jsqlbox.*;
import com.github.drinkjava2.myserverless.BaseTemplate;

@SuppressWarnings("unused")
public class QryStringTemplate extends BaseTemplate {

	@Override
	public Object executeBody() {
		/* MYSERVERLESS BODY BEGIN */
		String sql = null;
		/* MYSERVERLESS BODY END */
		Object[] paramArray = getParamArray();
		if (paramArray.length == 0)
			return qryString(sql);
		else
			return qryString(sql, par((Object[])paramArray));
	}

}
