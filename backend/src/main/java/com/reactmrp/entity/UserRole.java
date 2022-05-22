package com.reactmrp.entity;

import com.github.drinkjava2.jdialects.annotation.jdia.COLUMN;
import com.github.drinkjava2.jdialects.annotation.jdia.SingleFKey;
import com.github.drinkjava2.jdialects.annotation.jpa.Id;
import com.github.drinkjava2.jsqlbox.ActiveEntity;

public class UserRole implements ActiveEntity<UserRole> {
    @Id
    @SingleFKey(refs = {"users", "userId"})
    @COLUMN(length = 32)
    private String userId;

    @Id
    @SingleFKey(refs = {"roles", "roleName"})
    @COLUMN(length = 32)
    private String roleName;

    public String getUserId() {
        return userId;
    }

    public UserRole setUserId(String userId) {
        this.userId = userId;
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
