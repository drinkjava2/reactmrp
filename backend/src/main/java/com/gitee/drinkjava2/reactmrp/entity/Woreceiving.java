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
public class Woreceiving implements ActiveEntity<Woreceiving> {
	public static final String ID = "ID";

	public static final String WO = "WO";

	public static final String PRODUCTID = "ProductID";

	public static final String RECEIVE = "Receive";

	public static final String DATE = "`Date`";

	public static final String PACKINGSLIPINVOICE = "PackingSlipInvoice";

	@Id
	private Integer id;


	@Column(name="WO", length=30)
	@SingleFKey(name="woreceiving_ibfk_1", refs={"wo","WO"})
	private String wo;

	@Column(name="ProductID", length=46)
	@SingleFKey(name="woreceiving_ibfk_2", refs={"product","ProductID"})
	private String productID;

	private Integer receive;

	@Column(name="`Date`")
	private Date date;

	@Column(name="PackingSlipInvoice", length=30)
	private String packingSlipInvoice;


	public Integer getId(){
		return id;
	}

	public Woreceiving setId(Integer id){
		this.id=id;
		return this;
	}

	public String getWo(){
		return wo;
	}

	public Woreceiving setWo(String wo){
		this.wo=wo;
		return this;
	}

	public String getProductID(){
		return productID;
	}

	public Woreceiving setProductID(String productID){
		this.productID=productID;
		return this;
	}

	public Integer getReceive(){
		return receive;
	}

	public Woreceiving setReceive(Integer receive){
		this.receive=receive;
		return this;
	}

	public Date getDate(){
		return date;
	}

	public Woreceiving setDate(Date date){
		this.date=date;
		return this;
	}

	public String getPackingSlipInvoice(){
		return packingSlipInvoice;
	}

	public Woreceiving setPackingSlipInvoice(String packingSlipInvoice){
		this.packingSlipInvoice=packingSlipInvoice;
		return this;
	}

}
