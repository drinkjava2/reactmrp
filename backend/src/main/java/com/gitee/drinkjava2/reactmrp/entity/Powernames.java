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
public class Powernames implements ActiveEntity<Powernames> {
	public static final String POWERNAME = "powername";

	public static final String ADMIN = "Admin";

	public static final String MANAGER = "Manager";

	public static final String INVENTORY = "Inventory";

	public static final String PURCHASE = "Purchase";

	public static final String NORMALUSER = "NormalUser";

	public static final String BOM_MANAGEMENT = "BOM_Management";

	public static final String EXPORT = "Export";


	@Column(name="powername", length=60)
	private String powername;

	private Integer admin;

	private Integer manager;

	private Integer inventory;

	private Integer purchase;

	private Integer normalUser;

	@Column(name="BOM_Management")
	private Integer bomManagement;

	private Integer export;


	public String getPowername(){
		return powername;
	}

	public Powernames setPowername(String powername){
		this.powername=powername;
		return this;
	}

	public Integer getAdmin(){
		return admin;
	}

	public Powernames setAdmin(Integer admin){
		this.admin=admin;
		return this;
	}

	public Integer getManager(){
		return manager;
	}

	public Powernames setManager(Integer manager){
		this.manager=manager;
		return this;
	}

	public Integer getInventory(){
		return inventory;
	}

	public Powernames setInventory(Integer inventory){
		this.inventory=inventory;
		return this;
	}

	public Integer getPurchase(){
		return purchase;
	}

	public Powernames setPurchase(Integer purchase){
		this.purchase=purchase;
		return this;
	}

	public Integer getNormalUser(){
		return normalUser;
	}

	public Powernames setNormalUser(Integer normalUser){
		this.normalUser=normalUser;
		return this;
	}

	public Integer getBomManagement(){
		return bomManagement;
	}

	public Powernames setBomManagement(Integer bomManagement){
		this.bomManagement=bomManagement;
		return this;
	}

	public Integer getExport(){
		return export;
	}

	public Powernames setExport(Integer export){
		this.export=export;
		return this;
	}

}
