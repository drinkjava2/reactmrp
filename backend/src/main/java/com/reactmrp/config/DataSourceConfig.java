package com.reactmrp.config;

import com.github.drinkjava2.jbeanbox.BeanBox;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Global DataSource Configuration
 * @author Yong Zhu
 * @since 1.0.0
 */
public class DataSourceConfig {
    
    public static class DataSourceBox extends H2DataSourceBox { //usually test on H2 or MySql
    }
 
	// H2Database memory database connection URL
	public static class H2DataSourceBox extends HikariCPBox {
		{
			injectValue("jdbcUrl", "jdbc:h2:mem:reactmrp;MODE=MYSQL;DB_CLOSE_DELAY=-1;TRACE_LEVEL_SYSTEM_OUT=0;DATABASE_TO_UPPER=false");
			injectValue("driverClassName", "org.h2.Driver");
			injectValue("username", "sa");
			injectValue("password", "");
		}
	}

	// MySql connection URL with UTC
	public static class MySqlDataSourceUtcTimeBox extends MySqlDataSourceBox {
		{
			injectValue("jdbcUrl",
					"jdbc:mysql://127.0.0.1:3306/reactmrp?rewriteBatchedStatements=true&useSSL=false&serverTimezone=UTC");
		}
	}

	   // MySql connection URL
    public static class MySqlDataSourceBox extends HikariCPBox {
        {
            injectValue("jdbcUrl",
                    "jdbc:mysql://127.0.0.1:3306/reactmrp?rewriteBatchedStatements=true&useSSL=false");
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
