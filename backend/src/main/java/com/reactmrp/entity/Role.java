package com.reactmrp.entity;

import com.github.drinkjava2.jdialects.annotation.jdia.COLUMN;
import com.github.drinkjava2.jdialects.annotation.jpa.Id;
import com.github.drinkjava2.jdialects.annotation.jpa.Table;
import com.github.drinkjava2.jsqlbox.ActiveEntity;

/**
 * 简单的系统，只要给每个用户设一个或多个role就可以了，复杂的系统，每个role还可以设定它的细节权限（Power)，具体是通过定制TokenSecurity的实现类来检查methodId是否包含Power字符串来控制权限。
 * 本演示因为每个Role只分配了单个与Role重名的Power，所以看起来好象就控制到Role一级似的，实际上它可以精确控制到每个前端方法
 * 
 * @author Yong
 */
@Table(name = "roles")
public class Role implements ActiveEntity<Role> {
    @Id
    @COLUMN(length = 32)
    private String roleName;

    private Integer roleLevel; //可选参数。角色等级，数字越小，等级最高，某些情况下，当一个用户有多个role时，可以利用这个字段来快速查找当前用户的最高等级角色

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
