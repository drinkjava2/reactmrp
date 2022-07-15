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
public class Ponumberhistory implements ActiveEntity<Ponumberhistory> {
	public static final String PO = "PO";

	@Id
	@Column(name="PO", length=30)
	private String po;



	public String getPo(){
		return po;
	}

	public Ponumberhistory setPo(String po){
		this.po=po;
		return this;
	}

}
