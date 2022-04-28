package com.reactmrp.entity;

import com.github.drinkjava2.jdialects.annotation.jdia.COLUMN;
import com.github.drinkjava2.jdialects.annotation.jpa.Id;
import com.github.drinkjava2.jdialects.annotation.jpa.Table;
import com.github.drinkjava2.jsqlbox.ActiveEntity;

@Table(name = "powers")
public class Power implements ActiveEntity<Power> {
    @Id
    @COLUMN(length = 32)
    private String powerName;

    public String getPowerName() {
        return powerName;
    }

    public Power setPowerName(String powerName) {
        this.powerName = powerName;
        return this;
    }

}
