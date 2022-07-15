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
public class Partcount implements ActiveEntity<Partcount> {
	public static final String ID = "ID";

	public static final String PACKINGSLIPINVOICE = "PackingSlipInvoice";

	public static final String DATE = "`Date`";

	public static final String PARTID = "PartID";

	public static final String RECORDSTOCK = "RecordStock";

	public static final String COUNTEDQTY = "CountedQty";

	public static final String DIFFERENCE = "Difference";

	public static final String NOTES = "Notes";

	@Id
	private Integer id;


	@Column(name="PackingSlipInvoice", length=30)
	private String packingSlipInvoice;

	@Column(name="`Date`")
	private Date date;

	@Column(name="PartID", length=40)
	@SingleFKey(name="partcount_ibfk_1", refs={"part","PartID"})
	private String partID;

	private Integer recordStock;

	private Integer countedQty;

	private Integer difference;

	@Column(name="Notes", length=60)
	private String notes;


	public Integer getId(){
		return id;
	}

	public Partcount setId(Integer id){
		this.id=id;
		return this;
	}

	public String getPackingSlipInvoice(){
		return packingSlipInvoice;
	}

	public Partcount setPackingSlipInvoice(String packingSlipInvoice){
		this.packingSlipInvoice=packingSlipInvoice;
		return this;
	}

	public Date getDate(){
		return date;
	}

	public Partcount setDate(Date date){
		this.date=date;
		return this;
	}

	public String getPartID(){
		return partID;
	}

	public Partcount setPartID(String partID){
		this.partID=partID;
		return this;
	}

	public Integer getRecordStock(){
		return recordStock;
	}

	public Partcount setRecordStock(Integer recordStock){
		this.recordStock=recordStock;
		return this;
	}

	public Integer getCountedQty(){
		return countedQty;
	}

	public Partcount setCountedQty(Integer countedQty){
		this.countedQty=countedQty;
		return this;
	}

	public Integer getDifference(){
		return difference;
	}

	public Partcount setDifference(Integer difference){
		this.difference=difference;
		return this;
	}

	public String getNotes(){
		return notes;
	}

	public Partcount setNotes(String notes){
		this.notes=notes;
		return this;
	}

}
