/* Copyright 2018-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package com.reactmrp.config;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import com.github.drinkjava2.jbeanbox.ClassScanner;
import com.github.drinkjava2.jbeanbox.JBEANBOX;
import com.github.drinkjava2.jdialects.Dialect;
import com.github.drinkjava2.jsqlbox.DB;
import com.github.drinkjava2.jsqlbox.DbContext;
import com.github.drinkjava2.jtransactions.tinytx.TinyTxConnectionManager;
import com.github.drinkjava2.myserverless.MyServerlessEnv;
import com.reactmrp.config.DataSourceConfig.DataSourceBox;
import com.reactmrp.entity.Power;
import com.reactmrp.entity.Role;
import com.reactmrp.entity.RoleAction;
import com.reactmrp.entity.User;
import com.reactmrp.entity.UserRole;
import com.reactmrp.template.JavaTemplate;
import com.reactmrp.template.JavaTxTemplate;
import com.reactmrp.template.QryArrayListTemplate;
import com.reactmrp.template.QryArrayTemplate;
import com.reactmrp.template.QryEntityListTemplate;
import com.reactmrp.template.QryEntityTemplate;
import com.reactmrp.template.QryListTemplate;
import com.reactmrp.template.QryMapListTemplate;
import com.reactmrp.template.QryMapTemplate;
import com.reactmrp.template.QryObjectTemplate;
import com.reactmrp.template.QryTitleArrayListTemplate;

/**
 * InitConfig is a servlet, but the static method can also be called directly
 * InitConfig可以放在web.xml中启动，也可以直接调用它的静态方法来初始化数据库和MyServerless模板
 * 
 * @author Yong Zhu
 * @since 1.0.0
 */
public class InitConfig extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        initMyServerlessTemplates();
        initDataBase();
    }

    public static void initMyServerlessTemplates() { //登记自定义的MyServerless模板
        MyServerlessEnv.registerMethodTemplate("java", JavaTemplate.class);
        MyServerlessEnv.registerMethodTemplate("javaTx", JavaTxTemplate.class);
        MyServerlessEnv.registerMethodTemplate("qryObject", QryObjectTemplate.class);
        MyServerlessEnv.registerMethodTemplate("qryArray", QryArrayTemplate.class);
        MyServerlessEnv.registerMethodTemplate("qryArrayList", QryArrayListTemplate.class);
        MyServerlessEnv.registerMethodTemplate("qryTitleArrayList", QryTitleArrayListTemplate.class);
        MyServerlessEnv.registerMethodTemplate("qryMap", QryMapTemplate.class);
        MyServerlessEnv.registerMethodTemplate("qryMapList", QryMapListTemplate.class);
        MyServerlessEnv.registerMethodTemplate("qryList", QryListTemplate.class);
        MyServerlessEnv.registerMethodTemplate("qryEntity", QryEntityTemplate.class);
        MyServerlessEnv.registerMethodTemplate("qryEntityList", QryEntityListTemplate.class);
    }

    @SuppressWarnings("all")
    public static void initDataBase() {
        //初始化数据库， 本示例使用H2或MySql数据库，如使用其它数据库只要更改下面的DataSourceBox设置即可
        DataSource ds = JBEANBOX.getBean(DataSourceBox.class);

        //本项目使用jSqlBox作为DAO工具，以下是jSqlBox的配置
        DbContext.setGlobalNextAllowShowSql(true); //允许输出SQL日志到控制台
        Dialect.setGlobalNamingConversion(new MyEntityNamingRule()); //全局表和字段名映射，表名列名为一对一关系，不作变换
        DbContext ctx = new DbContext(ds); //ctx是全局单例
        ctx.setConnectionManager(TinyTxConnectionManager.instance());// 事务配置
        DbContext.setGlobalDbContext(ctx);// 设定全局缺省上下文

        //创建数据库表
        List<Class> classes = ClassScanner.scanPackages("com.reactmrp.entity"); //扫描所有实体以创建数据库表
        classes.stream().distinct().forEach(e -> {
            for (String ddl : ctx.toDropAndCreateDDL(e)) {
                DB.gctx().quiteExecute(ddl);
            }
        });

        //新建用户 
        new User().setUserName("developer").setPassword(MyTokenSecurity.encodePassword("123")).insert();
        new User().setUserName("admin").setPassword(MyTokenSecurity.encodePassword("123")).insert();
        new User().setUserName("manager").setPassword(MyTokenSecurity.encodePassword("123")).insert();
        new User().setUserName("user").setPassword(MyTokenSecurity.encodePassword("123")).insert();

        //新建角色
        new Role().setRoleName("developerRole").insert();
        new Role().setRoleName("adminRole").insert();
        new Role().setRoleName("managerRole").insert();
        new Role().setRoleName("userRole").insert();

        //新建权限名
        new Power().setPowerName("DeveloperPower").insert();
        new Power().setPowerName("UserCreate").insert();
        new Power().setPowerName("UserUpdate").insert();
        new Power().setPowerName("UserRead").insert();
        new Power().setPowerName("OrderCreate").insert();
        new Power().setPowerName("OrderUpdate").insert();
        new Power().setPowerName("OrderRead").insert();

        //给用户添加角色
        UserRole ur = new UserRole().setUserName("developer").setRoleName("developerRole").insert() //
                .setRoleName("adminRole").insert(); //developer同时也具备admin角色
        ur.setUserName("admin").setRoleName("adminRole").insert();
        ur.setUserName("manager").setRoleName("managerRole").insert();
        ur.setUserName("user").setRoleName("userRole").insert();

        //给角色添加行为权限
        RoleAction ra = new RoleAction().setRoleName("developerRole") //
                .setPowerName("DeveloperPower").insert();

        ra.setRoleName("adminRole");
        ra.setPowerName("UserCreate").insert();
        ra.setPowerName("UserUpdate").insert();
        ra.setPowerName("UserRead").insert();
        ra.setPowerName("OrderCreate").insert();
        ra.setPowerName("OrderUpdate").insert();
        ra.setPowerName("OrderRead").insert();

        ra.setRoleName("managerRole");
        ra.setPowerName("OrderCreate").insert();
        ra.setPowerName("OrderUpdate").insert();
        ra.setPowerName("OrderRead").insert();

        ra.setRoleName("userRole");
        ra.setPowerName("OrderRead").insert();

    }

    public static void main(String[] args) throws SQLException {
        initDataBase();
        System.out.println(DB.qryMapList("select * from users"));
    }

}
