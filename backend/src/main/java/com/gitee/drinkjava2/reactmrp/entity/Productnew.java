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
public class Productnew implements ActiveEntity<Productnew> {
	public static final String PRODUCTID = "ProductID";

	public static final String REC = "Rec";


	@Column(name="ProductID", length=46)
	private String productID;

	private Integer rec;


	public String getProductID(){
		return productID;
	}

	public Productnew setProductID(String productID){
		this.productID=productID;
		return this;
	}

	public Integer getRec(){
		return rec;
	}

	public Productnew setRec(Integer rec){
		this.rec=rec;
		return this;
	}

}
