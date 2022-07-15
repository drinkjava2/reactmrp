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
public class Partonhold implements ActiveEntity<Partonhold> {
	public static final String ID = "ID";

	public static final String WO = "WO";

	public static final String PARTID = "PartID";

	public static final String ORIGINALONHOLD = "OriginalOnHold";

	public static final String RELEASED = "Released";

	public static final String CURRENTONHOLD = "CurrentOnHold";

	@Id
	private Integer id;


	@Column(name="WO", length=30)
	@SingleFKey(name="partonhold_ibfk_1", refs={"wo","WO"})
	private String wo;

	@Column(name="PartID", length=30)
	@SingleFKey(name="partonhold_ibfk_2", refs={"part","PartID"})
	private String partID;

	private Integer originalOnHold;

	private Integer released;

	private Integer currentOnHold;


	public Integer getId(){
		return id;
	}

	public Partonhold setId(Integer id){
		this.id=id;
		return this;
	}

	public String getWo(){
		return wo;
	}

	public Partonhold setWo(String wo){
		this.wo=wo;
		return this;
	}

	public String getPartID(){
		return partID;
	}

	public Partonhold setPartID(String partID){
		this.partID=partID;
		return this;
	}

	public Integer getOriginalOnHold(){
		return originalOnHold;
	}

	public Partonhold setOriginalOnHold(Integer originalOnHold){
		this.originalOnHold=originalOnHold;
		return this;
	}

	public Integer getReleased(){
		return released;
	}

	public Partonhold setReleased(Integer released){
		this.released=released;
		return this;
	}

	public Integer getCurrentOnHold(){
		return currentOnHold;
	}

	public Partonhold setCurrentOnHold(Integer currentOnHold){
		this.currentOnHold=currentOnHold;
		return this;
	}

}
