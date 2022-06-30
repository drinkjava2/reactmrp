package com.reactmrp.entity;

import com.github.drinkjava2.jdialects.annotation.jdia.COLUMN;
import com.github.drinkjava2.jdialects.annotation.jpa.Id;
import com.github.drinkjava2.jdialects.annotation.jpa.Table;
import com.github.drinkjava2.jsqlbox.ActiveEntity;

@Table(name = "users")
public class User implements ActiveEntity<User> {

    @Id
    @COLUMN(length = 32)
    private String userId; //本项目数据库列命名和实体属性完全一致,即数据库中也是userId这种列名

    @COLUMN(length = 32)
    private String name;

    @COLUMN(length = 32)
    private String password;

    @COLUMN(length = 200)
    private String myToken; //每登录一次就生成一个随机myToken

    @COLUMN(length = 100)
    private String description;

    @COLUMN(length = 20)
    private String mobilePhone;

    @COLUMN(length = 80)
    private String avatar;

    public String getUserId() {
        return userId;
    }

    public User setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getMyToken() {
        return myToken;
    }

    public User setMyToken(String myToken) {
        this.myToken = myToken;
        return this;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public User setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public User setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public User setDescription(String description) {
        this.description = description;
        return this;
    }

}
