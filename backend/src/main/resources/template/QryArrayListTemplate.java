package template;

import static com.github.drinkjava2.jsqlbox.DB.par;
import static com.github.drinkjava2.jsqlbox.DB.qry;
import com.github.drinkjava2.jsqlbox.*;
import com.github.drinkjava2.myserverless.BaseTemplate;
import org.apache.commons.dbutils.handlers.ArrayListHandler;


@SuppressWarnings("unused")
public class QryArrayListTemplate extends BaseTemplate {

    @Override
    public Object executeBody() {
        /* MYSERVERLESS BODY BEGIN */
        String sql = null;
        /* MYSERVERLESS BODY END */
        Object[] paramArray = getParamArray();
        if (paramArray.length == 0)
            return qry(new ArrayListHandler(), sql);
        else
            return qry(new ArrayListHandler(), sql, par((Object[]) paramArray));
    }

}
