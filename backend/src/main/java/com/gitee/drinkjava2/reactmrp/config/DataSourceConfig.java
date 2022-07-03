package com.gitee.drinkjava2.reactmrp.config;

import com.github.drinkjava2.jbeanbox.BeanBox;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Project DataSource Configuration
 * 这里用到了jBeanBox来配置数据源，jBeanBox是一个小型的IOC/AOP工具，功能与Spring-Core或Guice类似
 * 
 * @author Yong Zhu
 * @since 1.0.0
 */
public class DataSourceConfig {

    public static class DataSourceBox extends MySqlDataSourceUtcTimeBox { //缺省用H2，如果本机装了MySql可以用MySQL。分别 对应 H2DataSourceBox或MySqlDataSourceUtcTimeBox
    }

    // H2Database memory database connection URL
    public static class H2DataSourceBox extends HikariCPBox {
        {
            injectValue("jdbcUrl", "jdbc:h2:mem:reactmrp;MODE=MYSQL;DB_CLOSE_DELAY=-1;TRACE_LEVEL_SYSTEM_OUT=0;DATABASE_TO_UPPER=false&characterEncoding=utf8");
            injectValue("driverClassName", "org.h2.Driver");
            injectValue("username", "sa");
            injectValue("password", "");
        }
    }

    // MySql connection URL with UTC
    public static class MySqlDataSourceUtcTimeBox extends MySqlDataSourceBox {
        {
            injectValue("jdbcUrl", "jdbc:mysql://127.0.0.1:3306/reactmrp?rewriteBatchedStatements=true&useSSL=false&serverTimezone=UTC&characterEncoding=utf8");
        }
    }

    // MySql connection URL
    public static class MySqlDataSourceBox extends HikariCPBox {
        {
            injectValue("jdbcUrl", "jdbc:mysql://127.0.0.1:3306/reactmrp?rewriteBatchedStatements=true&useSSL=false&characterEncoding=utf8");
            injectValue("driverClassName", "com.mysql.cj.jdbc.Driver"); //for MySQL connection6 and above
            injectValue("username", "root");// change to your user & password
            injectValue("password", "root888");
        }
    }

    // HikariCP is a DataSource pool much quicker than C3P0
    public static class HikariCPBox extends BeanBox {
        // In jBeanBox, bean default is singleton
        public HikariDataSource create() {
            HikariDataSource ds = new HikariDataSource();
            ds.addDataSourceProperty("cachePrepStmts", true); //如果不动态增改列，要设成true以提高性能
            ds.addDataSourceProperty("prepStmtCacheSize", 250);
            ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
            ds.addDataSourceProperty("useServerPrepStmts", true);
            ds.setMaximumPoolSize(3);
            ds.setConnectionTimeout(5000);
            this.setPreDestroy("close");// jBeanBox will close pool
            return ds;
        }
    }

}
