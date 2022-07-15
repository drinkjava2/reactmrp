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
public class Partbatch implements ActiveEntity<Partbatch> {
	public static final String ID = "ID";

	public static final String PARTID = "PartID";

	public static final String PO = "PO";

	public static final String BATCHNO = "BatchNo";

	public static final String QTY = "QTY";

	public static final String NOTE = "Note";

	@Id
	private Integer id;


	@Column(name="PartID", length=40)
	@SingleFKey(name="partbatch_ibfk_1", refs={"part","PartID"})
	private String partID;

	@Column(name="PO", length=30)
	private String po;

	@Column(name="BatchNo", length=30)
	private String batchNo;

	private Integer qty;

	@Column(name="Note", length=30)
	private String note;


	public Integer getId(){
		return id;
	}

	public Partbatch setId(Integer id){
		this.id=id;
		return this;
	}

	public String getPartID(){
		return partID;
	}

	public Partbatch setPartID(String partID){
		this.partID=partID;
		return this;
	}

	public String getPo(){
		return po;
	}

	public Partbatch setPo(String po){
		this.po=po;
		return this;
	}

	public String getBatchNo(){
		return batchNo;
	}

	public Partbatch setBatchNo(String batchNo){
		this.batchNo=batchNo;
		return this;
	}

	public Integer getQty(){
		return qty;
	}

	public Partbatch setQty(Integer qty){
		this.qty=qty;
		return this;
	}

	public String getNote(){
		return note;
	}

	public Partbatch setNote(String note){
		this.note=note;
		return this;
	}

}
