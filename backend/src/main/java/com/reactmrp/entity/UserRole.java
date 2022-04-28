package com.reactmrp.entity;

import com.github.drinkjava2.jdialects.annotation.jdia.COLUMN;
import com.github.drinkjava2.jdialects.annotation.jdia.SingleFKey;
import com.github.drinkjava2.jdialects.annotation.jpa.Id;
import com.github.drinkjava2.jsqlbox.ActiveEntity;

public class UserRole implements ActiveEntity<UserRole> {
    @Id
    @SingleFKey(refs = {"users", "userName"})
    @COLUMN(length = 32)
    private String userName;

    @Id
    @SingleFKey(refs = {"roles", "roleName"})
    @COLUMN(length = 32)
    private String roleName;

    public String getUserName() {
        return userName;
    }

    public UserRole setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public UserRole setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

}
