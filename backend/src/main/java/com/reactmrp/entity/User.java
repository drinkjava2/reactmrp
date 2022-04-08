package com.reactmrp.entity;

import com.github.drinkjava2.jdialects.annotation.jpa.Id;
import com.github.drinkjava2.jdialects.annotation.jpa.Table;
import com.github.drinkjava2.jsqlbox.ActiveEntity;

@Table(name = "users")
public class User implements ActiveEntity<User> {
    @Id
    String username;

    String password;

    String token;

    String poPrefix; //订单前缀，下订单时才用到

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPoPrefix() {
        return poPrefix;
    }

    public void setPoPrefix(String poPrefix) {
        this.poPrefix = poPrefix;
    }

}
