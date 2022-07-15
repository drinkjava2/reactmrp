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
public class Shipment implements ActiveEntity<Shipment> {
	public static final String ID = "ID";

	public static final String PRODUCTID = "ProductID";

	public static final String SN = "SN";

	public static final String SHIPQTY = "ShipQty";

	public static final String DATE = "`Date`";

	public static final String PACKINGSLIPINVOICE = "PackingSlipInvoice";

	public static final String NOTES = "Notes";

	public static final String NEW_OLD = "NEW_OLD";

	@Id
	private Integer id;


	@Column(name="ProductID", length=46)
	@SingleFKey(name="shipment_ibfk_1", refs={"product","ProductID"})
	private String productID;

	@Column(name="SN", length=20)
	private String sn;

	private Integer shipQty;

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

	public Shipment setId(Integer id){
		this.id=id;
		return this;
	}

	public String getProductID(){
		return productID;
	}

	public Shipment setProductID(String productID){
		this.productID=productID;
		return this;
	}

	public String getSn(){
		return sn;
	}

	public Shipment setSn(String sn){
		this.sn=sn;
		return this;
	}

	public Integer getShipQty(){
		return shipQty;
	}

	public Shipment setShipQty(Integer shipQty){
		this.shipQty=shipQty;
		return this;
	}

	public Date getDate(){
		return date;
	}

	public Shipment setDate(Date date){
		this.date=date;
		return this;
	}

	public String getPackingSlipInvoice(){
		return packingSlipInvoice;
	}

	public Shipment setPackingSlipInvoice(String packingSlipInvoice){
		this.packingSlipInvoice=packingSlipInvoice;
		return this;
	}

	public String getNotes(){
		return notes;
	}

	public Shipment setNotes(String notes){
		this.notes=notes;
		return this;
	}

	public String getNewOld(){
		return newOld;
	}

	public Shipment setNewOld(String newOld){
		this.newOld=newOld;
		return this;
	}

}
