package template;

import static com.github.drinkjava2.jsqlbox.DB.*;
import com.github.drinkjava2.jsqlbox.*;
import com.reactmrp.entity.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.Connection; 
import com.github.drinkjava2.myserverless.BaseTemplate;
import com.github.drinkjava2.myserverless.JsonResult;
import com.github.drinkjava2.jtransactions.tinytx.TinyTxConnectionManager;

@SuppressWarnings("all")
public class JavaTxTemplate extends BaseTemplate {

    public Object executeBody() {
        if(1>0) {
        /* MYSERVERLESS BODY BEGIN */
        return null;
        /* MYSERVERLESS BODY END */
        }
        return null;
    }

    @Override
    public JsonResult execute() {
        Object data = null;
        // open transaction
        Exception ex = null;
        TinyTxConnectionManager tx = (TinyTxConnectionManager) gctx().getConnectionManager();
        try {
            tx.startTransaction(Connection.TRANSACTION_READ_COMMITTED);
            data = executeBody();
            tx.commitTransaction();
        } catch (Exception e) {
            System.err.println("Transaction roll back...");
            try {
                tx.rollbackTransaction();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (e instanceof RuntimeException)
                throw (RuntimeException) e;
            else
                throw new RuntimeException(e);
        }
        if (data != null && data instanceof JsonResult)
            return (JsonResult) data;
        return new JsonResult(code, message, data);
    }

}
