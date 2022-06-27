package template;

import static com.github.drinkjava2.jsqlbox.DB.par;
import static com.github.drinkjava2.jsqlbox.DB.qry;
import com.github.drinkjava2.jsqlbox.*;
import com.github.drinkjava2.myserverless.BaseTemplate;
import org.apache.commons.dbutils.handlers.ArrayListHandler;


@SuppressWarnings("unused")
public class ExecuteSqlTemplate extends BaseTemplate {

    @Override
    public Object executeBody() {
        /* MYSERVERLESS BODY BEGIN */
        String sql = null;
        /* MYSERVERLESS BODY END */
        Object[] paramArray = getParamArray();
        if (paramArray.length == 0)
            return DB.exe(sql);
        else
            return DB.exe(sql, par((Object[]) paramArray));
    }

}
