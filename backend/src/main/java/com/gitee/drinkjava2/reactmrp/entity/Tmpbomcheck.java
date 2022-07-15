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
public class Tmpbomcheck implements ActiveEntity<Tmpbomcheck> {
	public static final String PARTID = "PartID";

	public static final String FATHERPARTID = "FatherPartID";


	@Column(name="PartID", length=100)
	@SingleFKey(name="tmpbomcheck_ibfk_1", refs={"part","PartID"})
	private String partID;

	@Column(name="FatherPartID", length=40)
	@SingleFKey(name="tmpbomcheck_ibfk_2", refs={"part","PartID"})
	private String fatherPartID;


	public String getPartID(){
		return partID;
	}

	public Tmpbomcheck setPartID(String partID){
		this.partID=partID;
		return this;
	}

	public String getFatherPartID(){
		return fatherPartID;
	}

	public Tmpbomcheck setFatherPartID(String fatherPartID){
		this.fatherPartID=fatherPartID;
		return this;
	}

}
