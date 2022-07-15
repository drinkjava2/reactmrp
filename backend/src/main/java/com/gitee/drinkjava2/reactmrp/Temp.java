package com.gitee.drinkjava2.reactmrp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;

import com.gitee.drinkjava2.reactmrp.config.DataSourceConfig.HikariCPBox;
import com.github.drinkjava2.jbeanbox.JBEANBOX;
import com.github.drinkjava2.jdialects.Dialect;
import com.github.drinkjava2.jdialects.TableModelUtils; 
 
public class Temp {//临时类，用于调试任意小片段
 
    public static class PPS_DS extends HikariCPBox {
        {
            injectValue("jdbcUrl", "jdbc:mysql://127.0.0.1:3306/pps?rewriteBatchedStatements=true&useSSL=false&serverTimezone=UTC&characterEncoding=utf8");
            injectValue("driverClassName", "com.mysql.cj.jdbc.Driver"); //for MySQL connection6 and above
            injectValue("username", "root");// change to your user & password
            injectValue("password", "root888");
        }
    }
    
    @Test
    public void DbToJavaSrcFiles() { 
        Map<String, Object> setting = new HashMap<String, Object>();
        setting.put(TableModelUtils.OPT_EXCLUDE_TABLES, Arrays.asList("Dbsample")); // 排除表名
        setting.put(TableModelUtils.OPT_PACKAGE_NAME, "com.gitee.drinkjava2.reactmrp.entity");// 包名
        setting.put(TableModelUtils.OPT_IMPORTS, "import java.util.*;\n@SuppressWarnings(\"all\")"); // 追加新的imports
        setting.put(TableModelUtils.OPT_REMOVE_DEFAULT_IMPORTS, false); // 不去除自带的imports
        setting.put(TableModelUtils.OPT_CLASS_DEFINITION, "public class $Class implements ActiveEntity<$Class> {");// 类定义
        setting.put(TableModelUtils.OPT_FIELD_FLAGS, true); // 全局静态属性字段标记
        setting.put(TableModelUtils.OPT_FIELD_FLAGS_STATIC, true); // 全局静态属性字段标记
        setting.put(TableModelUtils.OPT_FIELD_FLAGS_STYLE, "upper"); // 大写
        setting.put(TableModelUtils.OPT_FIELDS, true); // 属性
        setting.put(TableModelUtils.OPT_GETTER_SETTERS, true); // getter setter
        setting.put(TableModelUtils.OPT_PUBLIC_FIELD, false); // 属性定义成public
        setting.put(TableModelUtils.OPT_LINK_STYLE, true); // 链式getter/setter风格
 
        DataSource ds = JBEANBOX.getBean(PPS_DS.class);
        TableModelUtils.db2JavaSrcFiles(ds, Dialect.MySQL57Dialect, "E:\\reactmrp\\backend\\src\\main\\java\\com\\gitee\\drinkjava2\\reactmrp\\entity", setting);
          
    }
     

}
