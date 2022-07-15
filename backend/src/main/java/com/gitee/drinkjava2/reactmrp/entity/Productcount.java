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
public class Productcount implements ActiveEntity<Productcount> {
	public static final String ID = "ID";

	public static final String PACKINGSLIPINVOICE = "PackingSlipInvoice";

	public static final String DATE = "`Date`";

	public static final String PRODUCTID = "ProductID";

	public static final String RECORDSTOCK = "RecordStock";

	public static final String COUNTEDQTY = "CountedQty";

	public static final String RECORDRETURNSTOCK = "RecordReturnStock";

	public static final String COUNTEDRETURNQTY = "CountedReturnQty";

	public static final String DIFFERENCE = "Difference";

	public static final String NOTES = "Notes";

	@Id
	private Integer id;


	@Column(name="PackingSlipInvoice", length=30)
	private String packingSlipInvoice;

	@Column(name="`Date`")
	private Date date;

	@Column(name="ProductID", length=46)
	@SingleFKey(name="productcount_ibfk_1", refs={"product","ProductID"})
	private String productID;

	private Integer recordStock;

	private Integer countedQty;

	private Integer recordReturnStock;

	private Integer countedReturnQty;

	private Integer difference;

	@Column(name="Notes", length=60)
	private String notes;


	public Integer getId(){
		return id;
	}

	public Productcount setId(Integer id){
		this.id=id;
		return this;
	}

	public String getPackingSlipInvoice(){
		return packingSlipInvoice;
	}

	public Productcount setPackingSlipInvoice(String packingSlipInvoice){
		this.packingSlipInvoice=packingSlipInvoice;
		return this;
	}

	public Date getDate(){
		return date;
	}

	public Productcount setDate(Date date){
		this.date=date;
		return this;
	}

	public String getProductID(){
		return productID;
	}

	public Productcount setProductID(String productID){
		this.productID=productID;
		return this;
	}

	public Integer getRecordStock(){
		return recordStock;
	}

	public Productcount setRecordStock(Integer recordStock){
		this.recordStock=recordStock;
		return this;
	}

	public Integer getCountedQty(){
		return countedQty;
	}

	public Productcount setCountedQty(Integer countedQty){
		this.countedQty=countedQty;
		return this;
	}

	public Integer getRecordReturnStock(){
		return recordReturnStock;
	}

	public Productcount setRecordReturnStock(Integer recordReturnStock){
		this.recordReturnStock=recordReturnStock;
		return this;
	}

	public Integer getCountedReturnQty(){
		return countedReturnQty;
	}

	public Productcount setCountedReturnQty(Integer countedReturnQty){
		this.countedReturnQty=countedReturnQty;
		return this;
	}

	public Integer getDifference(){
		return difference;
	}

	public Productcount setDifference(Integer difference){
		this.difference=difference;
		return this;
	}

	public String getNotes(){
		return notes;
	}

	public Productcount setNotes(String notes){
		this.notes=notes;
		return this;
	}

}
