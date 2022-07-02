package com.gitee.drinkjava2.reactmrp.entity;

import java.util.Date;

import com.github.drinkjava2.jdialects.annotation.jpa.Column;
import com.github.drinkjava2.jdialects.annotation.jpa.Id;
import com.github.drinkjava2.jdialects.annotation.jpa.Temporal;
import com.github.drinkjava2.jdialects.annotation.jpa.TemporalType;
import com.github.drinkjava2.jsqlbox.ActiveEntity;

public class Sample implements ActiveEntity<Sample> {
    @Id
    private Integer id;

    private String title;

    private String author;

    private Integer readings;

    @Column(length = 10)
    private String star;

    @Column(length = 30)
    private String status;

    @Temporal(TemporalType.TIMESTAMP) //如不加这个，会映射成Date,将丢失time字段
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getReadings() {
        return readings;
    }

    public void setReadings(Integer readings) {
        this.readings = readings;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
