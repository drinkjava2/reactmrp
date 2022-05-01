package com.reactmrp.entity;

import com.github.drinkjava2.jdialects.annotation.jdia.COLUMN;
import com.github.drinkjava2.jdialects.annotation.jpa.Id;
import com.github.drinkjava2.jdialects.annotation.jpa.Table;
import com.github.drinkjava2.jsqlbox.ActiveEntity;

@Table(name="users")
public class User implements ActiveEntity<User> {

    @Id
    @COLUMN(length = 32)
    private String userName; //本项目数据库列命名和实体属性完全一致,即数据库中也是userName这种列名

    @COLUMN(length = 32)
    private String password;

    @COLUMN(length = 200)
    private String token; //每登录一次就生成一个随机token

    @COLUMN(length = 20)
    private String mobilePhone;

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getToken() {
        return token;
    }

    public User setToken(String token) {
        this.token = token;
        return this;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public User setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

}
