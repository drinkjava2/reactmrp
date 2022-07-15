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
public class Projectdetail implements ActiveEntity<Projectdetail> {
	public static final String ID = "ID";

	public static final String PROJECT = "Project";

	public static final String ITEM = "Item";

	public static final String PRODUCTID = "ProductID";

	public static final String QTY = "QTY";

	@Id
	private Integer id;


	@Column(name="Project", length=30)
	@SingleFKey(name="projectdetail_ibfk_1", refs={"project","Project"})
	private String project;

	private Integer item;

	@Column(name="ProductID", length=46)
	@SingleFKey(name="projectdetail_ibfk_2", refs={"product","ProductID"})
	private String productID;

	private Integer qty;


	public Integer getId(){
		return id;
	}

	public Projectdetail setId(Integer id){
		this.id=id;
		return this;
	}

	public String getProject(){
		return project;
	}

	public Projectdetail setProject(String project){
		this.project=project;
		return this;
	}

	public Integer getItem(){
		return item;
	}

	public Projectdetail setItem(Integer item){
		this.item=item;
		return this;
	}

	public String getProductID(){
		return productID;
	}

	public Projectdetail setProductID(String productID){
		this.productID=productID;
		return this;
	}

	public Integer getQty(){
		return qty;
	}

	public Projectdetail setQty(Integer qty){
		this.qty=qty;
		return this;
	}

}
