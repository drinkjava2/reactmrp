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
public class Returnproduct implements ActiveEntity<Returnproduct> {
	public static final String ID = "ID";

	public static final String PRODUCTID = "ProductID";

	public static final String SN = "SN";

	public static final String RETURNQTY = "ReturnQTY";

	public static final String DATE = "`Date`";

	public static final String PACKINGSLIPINVOICE = "PackingSlipInvoice";

	public static final String NOTES = "Notes";

	public static final String NEW_OLD = "NEW_OLD";

	@Id
	private Integer id;


	@Column(name="ProductID", length=46)
	@SingleFKey(name="returnproduct_ibfk_1", refs={"product","ProductID"})
	private String productID;

	@Column(name="SN", length=20)
	private String sn;

	private Integer returnQTY;

	@Column(name="`Date`")
	private Date date;

	@Column(name="PackingSlipInvoice", length=30)
	private String packingSlipInvoice;

	@Column(name="Notes", length=60)
	private String notes;

	@Column(name="NEW_OLD", length=3)
	private String newOld;


	public Integer getId(){
		return id;
	}

	public Returnproduct setId(Integer id){
		this.id=id;
		return this;
	}

	public String getProductID(){
		return productID;
	}

	public Returnproduct setProductID(String productID){
		this.productID=productID;
		return this;
	}

	public String getSn(){
		return sn;
	}

	public Returnproduct setSn(String sn){
		this.sn=sn;
		return this;
	}

	public Integer getReturnQTY(){
		return returnQTY;
	}

	public Returnproduct setReturnQTY(Integer returnQTY){
		this.returnQTY=returnQTY;
		return this;
	}

	public Date getDate(){
		return date;
	}

	public Returnproduct setDate(Date date){
		this.date=date;
		return this;
	}

	public String getPackingSlipInvoice(){
		return packingSlipInvoice;
	}

	public Returnproduct setPackingSlipInvoice(String packingSlipInvoice){
		this.packingSlipInvoice=packingSlipInvoice;
		return this;
	}

	public String getNotes(){
		return notes;
	}

	public Returnproduct setNotes(String notes){
		this.notes=notes;
		return this;
	}

	public String getNewOld(){
		return newOld;
	}

	public Returnproduct setNewOld(String newOld){
		this.newOld=newOld;
		return this;
	}

}
