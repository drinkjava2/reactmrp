package com.gitee.drinkjava2.reactmrp.entity;

import static com.github.drinkjava2.jsqlbox.JAVA8.*;
import static com.github.drinkjava2.jsqlbox.SQL.*;
import static com.github.drinkjava2.jsqlbox.DB.*;
import com.github.drinkjava2.jdbpro.SqlItem;
import com.github.drinkjava2.jdialects.annotation.jdia.*;
import com.github.drinkjava2.jdialects.annotation.jpa.*;
import com.github.drinkjava2.jsqlbox.*;
import java.util.*;

import java.util.*;
@SuppressWarnings("all")
public class Remark implements ActiveEntity<Remark> {
	public static final String ID = "ID";

	public static final String REMARK = "remark";

	public static final String I = "i";

	public static final String F = "f";

	public static final String D = "d";

	public static final String S = "s";

	@Id
	private Integer id;


	private String remark;

	private Integer i;

	private Float f;

	private Date d;

	@Column(name="s", length=300)
	private String s;


	public Integer getId(){
		return id;
	}

	public Remark setId(Integer id){
		this.id=id;
		return this;
	}

	public String getRemark(){
		return remark;
	}

	public Remark setRemark(String remark){
		this.remark=remark;
		return this;
	}

	public Integer getI(){
		return i;
	}

	public Remark setI(Integer i){
		this.i=i;
		return this;
	}

	public Float getF(){
		return f;
	}

	public Remark setF(Float f){
		this.f=f;
		return this;
	}

	public Date getD(){
		return d;
	}

	public Remark setD(Date d){
		this.d=d;
		return this;
	}

	public String getS(){
		return s;
	}

	public Remark setS(String s){
		this.s=s;
		return this;
	}

}
