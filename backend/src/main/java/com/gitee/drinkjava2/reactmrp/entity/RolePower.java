package com.gitee.drinkjava2.reactmrp.entity;

import com.github.drinkjava2.jdialects.annotation.jdia.COLUMN;
import com.github.drinkjava2.jdialects.annotation.jdia.SingleFKey;
import com.github.drinkjava2.jdialects.annotation.jpa.Id;
import com.github.drinkjava2.jsqlbox.ActiveEntity;

public class RolePower implements ActiveEntity<RolePower> {
    @Id
    @SingleFKey(refs = {"roles", "roleName"})
    private String roleName;

    @Id
    @SingleFKey(refs = {"powers", "powerName"})
    @COLUMN(length = 32)
    private String powerName;

    public String getRoleName() {
        return roleName;
    }

    public RolePower setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public String getPowerName() {
        return powerName;
    }

    public RolePower setPowerName(String powerName) {
        this.powerName = powerName;
        return this;
    }

}
