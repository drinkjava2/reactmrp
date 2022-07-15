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
public class Poreceiving implements ActiveEntity<Poreceiving> {
	public static final String ID = "ID";

	public static final String PO = "PO";

	public static final String PODETAILID = "PODetailID";

	public static final String PARTID = "PartID";

	public static final String RECEIVE = "Receive";

	public static final String DATE = "`Date`";

	public static final String RECEIVETIME = "ReceiveTime";

	public static final String PACKINGSLIPINVOICE = "PackingSlipInvoice";

	public static final String VENDORPACKINGSLIP = "VendorPackingSlip";

	@Id
	private Integer id;


	@Column(name="PO", length=30)
	private String po;

	@SingleFKey(name="poreceiving_ibfk_1", refs={"podetail","ID"})
	private Integer pODetailID;

	@Column(name="PartID", length=30)
	@SingleFKey(name="poreceiving_ibfk_2", refs={"part","PartID"})
	private String partID;

	private Integer receive;

	@Column(name="`Date`")
	private Date date;

	private Date receiveTime;

	@Column(name="PackingSlipInvoice", length=30)
	private String packingSlipInvoice;

	@Column(name="VendorPackingSlip", length=30)
	private String vendorPackingSlip;


	public Integer getId(){
		return id;
	}

	public Poreceiving setId(Integer id){
		this.id=id;
		return this;
	}

	public String getPo(){
		return po;
	}

	public Poreceiving setPo(String po){
		this.po=po;
		return this;
	}

	public Integer getPODetailID(){
		return pODetailID;
	}

	public Poreceiving setPODetailID(Integer pODetailID){
		this.pODetailID=pODetailID;
		return this;
	}

	public String getPartID(){
		return partID;
	}

	public Poreceiving setPartID(String partID){
		this.partID=partID;
		return this;
	}

	public Integer getReceive(){
		return receive;
	}

	public Poreceiving setReceive(Integer receive){
		this.receive=receive;
		return this;
	}

	public Date getDate(){
		return date;
	}

	public Poreceiving setDate(Date date){
		this.date=date;
		return this;
	}

	public Date getReceiveTime(){
		return receiveTime;
	}

	public Poreceiving setReceiveTime(Date receiveTime){
		this.receiveTime=receiveTime;
		return this;
	}

	public String getPackingSlipInvoice(){
		return packingSlipInvoice;
	}

	public Poreceiving setPackingSlipInvoice(String packingSlipInvoice){
		this.packingSlipInvoice=packingSlipInvoice;
		return this;
	}

	public String getVendorPackingSlip(){
		return vendorPackingSlip;
	}

	public Poreceiving setVendorPackingSlip(String vendorPackingSlip){
		this.vendorPackingSlip=vendorPackingSlip;
		return this;
	}

}
