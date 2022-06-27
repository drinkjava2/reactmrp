package template;

import static com.github.drinkjava2.jsqlbox.DB.par;
import static com.github.drinkjava2.jsqlbox.DB.qry;
import com.github.drinkjava2.myserverless.BaseTemplate;
import com.github.drinkjava2.jdbpro.handler.TitleArrayListHandler;
import com.github.drinkjava2.jsqlbox.DB;
import com.github.drinkjava2.myserverless.BaseTemplate;


@SuppressWarnings("unused")
public class QryTitleArrayListTemplate extends BaseTemplate {
    
	@Override
	public Object executeBody() {
		/* MYSERVERLESS BODY BEGIN */
		String sql = null;
		/* MYSERVERLESS BODY END */
		Object[] paramArray = getParamArray();
		if (paramArray.length == 0)
			return qry(new TitleArrayListHandler(), sql);
		else
			return qry(new TitleArrayListHandler(), sql, par((Object[]) paramArray));
	}

}
