package com.reactmrp.entity;

import com.github.drinkjava2.jdialects.annotation.jdia.COLUMN;
import com.github.drinkjava2.jdialects.annotation.jpa.Id;
import com.github.drinkjava2.jdialects.annotation.jpa.Table;
import com.github.drinkjava2.jsqlbox.ActiveEntity;

@Table(name = "roles")
public class Role implements ActiveEntity<Role> {
    @Id
    @COLUMN(length = 32)
    private String roleName;

    private Integer roleLevel; //角色等级，数字越小，等级最高，某些情况下，可以利用这个字段来查找当前用户的最高等级角色。

    public String getRoleName() {
        return roleName;
    }

    public Role setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public Role setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
        return this;
    }

}
