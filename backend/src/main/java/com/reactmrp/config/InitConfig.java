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

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import org.junit.Test;

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
import com.reactmrp.entity.RolePower;
import com.reactmrp.entity.User;
import com.reactmrp.entity.UserRole;

import template.ExecuteSqlTemplate;
import template.JavaTemplate;
import template.JavaTxTemplate;
import template.QryArrayListTemplate;
import template.QryArrayTemplate;
import template.QryEntityListTemplate;
import template.QryEntityTemplate;
import template.QryListTemplate;
import template.QryMapListTemplate;
import template.QryMapTemplate;
import template.QryObjectTemplate;
import template.QryTitleArrayListTemplate;

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
        initMyServerlessTemplates(); //登记自定义的MyServerless模板
        initDataBase(); //删除并重建数据库
        insertUserAndPowers(); //插入初始用户、角色、权限
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
        MyServerlessEnv.registerMethodTemplate("executeSql", ExecuteSqlTemplate.class);
    }

    @SuppressWarnings("all")
    public static void initDataBase() {
        //初始化数据库， 本示例使用H2或MySql数据库，如使用其它数据库只要更改下面的DataSourceBox设置即可
        DataSource ds = JBEANBOX.getBean(DataSourceBox.class);

        //本项目使用jSqlBox作为DAO工具，以下是jSqlBox的配置
        DbContext.setGlobalNextAllowShowSql(true); //允许输出SQL日志到控制台
        Dialect.setGlobalNamingConversion(new ProjectNamingRule()); //全局表和字段名映射，表名列名为一对一关系，不作变换
        DbContext ctx = new DbContext(ds); //ctx是全局单例
        ctx.setConnectionManager(TinyTxConnectionManager.instance());// 事务配置
        DbContext.setGlobalDbContext(ctx);// 设定全局缺省上下文

        //创建数据库表
        List<Class> classes = ClassScanner.scanPackages("com.reactmrp.entity"); //扫描所有实体以创建数据库表

        for (int i = 0; i < 10; i++) //我够狠，先静默删库10遍，保证所有表格包括有关联约束关系的表格都全部删除
            classes.stream().distinct().forEach(e -> {
                for (String ddl : ctx.toDropDDL(e))
                    DB.gctx().quiteExecute(ddl); //静默执行
            });
        classes.stream().distinct().forEach(e -> { //然后新建所有表格
            for (String ddl : ctx.toCreateDDL(e))
                DB.gctx().quiteExecute(ddl);
        });

    }

    public static void insertUserAndPowers() {//插入用户、角色、权限
        //新建用户 
<<<<<<< HEAD
        User u = new User();
        u.setUserId("developer").setAvatar("https://s1.ax1x.com/2020/04/28/J5hUaT.jpg").setPassword(ProjectSecurity.encodePassword("123")).insert();
        u.setUserId("admin").setAvatar("https://s1.ax1x.com/2020/04/28/J5hUaT.jpg").setPassword(ProjectSecurity.encodePassword("123")).insert();
        u.setUserId("editor").setAvatar("https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png").setPassword(ProjectSecurity.encodePassword("123")).insert();
        u.setUserId("guest").setAvatar("https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png").setPassword(ProjectSecurity.encodePassword("123")).insert();

        //新建角色
        new Role().setRoleName("developer").setRoleLevel(4).insert();
        new Role().setRoleName("admin").setRoleLevel(3).insert();
        new Role().setRoleName("editor").setRoleLevel(2).insert();
        new Role().setRoleName("guest").setRoleLevel(1).insert();

        //给用户添加角色
        UserRole ur = new UserRole();
        ur.setUserId("developer").setRoleName("developer").insert();
        ur.setUserId("developer").setRoleName("admin").insert(); //developer用户通常同时具有developer和admin两个角色
=======
        new User().setUserId("developer").setPassword(ProjectSecurity.encodePassword("123")).insert();
        new User().setUserId("admin").setPassword(ProjectSecurity.encodePassword("123")).insert();
        new User().setUserId("editor").setPassword(ProjectSecurity.encodePassword("123")).insert();
        new User().setUserId("guest").setPassword(ProjectSecurity.encodePassword("123")).insert();

        //新建角色
        new Role().setRoleName("developer").insert();
        new Role().setRoleName("admin").insert();
        new Role().setRoleName("editor").insert();
        new Role().setRoleName("user").insert();

        //新建权限名
        new Power().setPowerName("DevelopDynamicCompile").insert();
        new Power().setPowerName("UserCreate").insert();
        new Power().setPowerName("UserUpdate").insert();
        new Power().setPowerName("UserRead").insert();
        new Power().setPowerName("OrderCreate").insert();
        new Power().setPowerName("OrderUpdate").insert();
        new Power().setPowerName("OrderRead").insert();

        //给用户添加角色
        UserRole ur = new UserRole().setUserId("developer").setRoleName("developer").insert() //
                .setRoleName("admin").insert(); //developer用户通常同时具有developer和admin两个角色
>>>>>>> 24eca7fbcee53e79aed9aaef3fb3a09d9b7c9299
        ur.setUserId("admin").setRoleName("admin").insert();
        ur.setUserId("editor").setRoleName("editor").insert();
        ur.setUserId("guest").setRoleName("guest").insert();

<<<<<<< HEAD
        //新建权限名
        new Power().setPowerName("developer").insert();
        new Power().setPowerName("admin").insert();
        new Power().setPowerName("editor").insert();
        new Power().setPowerName("guest").insert();

        //给角色添加权限
        RolePower ra = new RolePower();
        ra.setRoleName("developer").setPowerName("developer").insert();//developer权限允许动态编译前端代码
        ra.setRoleName("admin").setPowerName("admin").insert();
        ra.setRoleName("editor").setPowerName("editor").insert();
        ra.setRoleName("guest").setPowerName("guest").insert();
=======
        //给角色添加行为权限
        RolePower ra = new RolePower().setRoleName("developer").setPowerName("DevelopDynamicCompile").insert();//developer本身只需要一个权限，就是允许动态编译前端代码

        ra.setRoleName("admin"); //admin通常具有所有业务相关权限
        ra.setPowerName("UserCreate").insert();
        ra.setPowerName("UserUpdate").insert();
        ra.setPowerName("UserRead").insert();
        ra.setPowerName("OrderCreate").insert();
        ra.setPowerName("OrderUpdate").insert();
        ra.setPowerName("OrderRead").insert();

        ra.setRoleName("editor"); //editor
        ra.setPowerName("UserRead").insert();
        ra.setPowerName("OrderCreate").insert();
        ra.setPowerName("OrderUpdate").insert();
        ra.setPowerName("OrderRead").insert();

        ra.setRoleName("guest"); //普通用户
        ra.setPowerName("OrderRead").insert();
>>>>>>> 24eca7fbcee53e79aed9aaef3fb3a09d9b7c9299
    }

    @Test
    public void testInitDataBase() { //测试数据库
        Dialect.setGlobalAllowReservedWords(false);
        initDataBase();
        //DbContext.gctx().setAllowShowSQL(true);
        insertUserAndPowers();
        List<String> powers = DB.qryList("select p.* from users u ", //
                " left join userrole ur on u.userId=ur.userId ", //
                " left join roles r on ur.roleName=r.roleName ", //
                " left join rolepower rp on rp.roleName=r.roleName ", //
                " left join powers p on p.powerName=rp.powerName ", //
                " where u.userId=", DB.que("developer"));
        for (String p : powers) {
            System.out.println(p);
        }

        List<Object> roles = DB.qryList("select r.roleName from users u ", //
                " left join userrole ur on u.userId=ur.userId ", //
                " left join roles r on ur.roleName=r.roleName ", //
                " where u.userId=", DB.que("developer"), " and r.roleName<>'developer' order by roleLevel");
        System.out.println(roles);
    }

}
