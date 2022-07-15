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
public class ExcelUsb implements ActiveEntity<ExcelUsb> {
	public static final String ID = "ID";

	public static final String LINENO = "LineNO";

	public static final String SN = "SN";

	public static final String RECIN = "RecIN";

	public static final String SENTOUT = "SentOUT";

	public static final String NEWOLD = "NewOld";

	public static final String CATEGORY = "Category";

	public static final String ASSEMBLYDATE = "AssemblyDate";

	public static final String OUTMONTH = "OutMonth";

	public static final String OUTDATE = "OutDate";

	public static final String CUSTOMER = "Customer";

	public static final String PURREN = "PurRen";

	public static final String NOTE = "NOTE";

	@Id
	private Integer id;


	private Integer lineNO;

	@Column(name="SN", length=30)
	private String sn;

	@Column(name="RecIN", length=1)
	private String recIN;

	@Column(name="SentOUT", length=1)
	private String sentOUT;

	@Column(name="NewOld", length=10)
	private String newOld;

	@Column(name="Category", length=30)
	private String category;

	@Column(name="AssemblyDate", length=20)
	private String assemblyDate;

	@Column(name="OutMonth", length=20)
	private String outMonth;

	@Column(name="OutDate", length=20)
	private String outDate;

	@Column(name="Customer", length=60)
	private String customer;

	@Column(name="PurRen", length=20)
	private String purRen;

	@Column(name="NOTE", length=500)
	private String note;


	public Integer getId(){
		return id;
	}

	public ExcelUsb setId(Integer id){
		this.id=id;
		return this;
	}

	public Integer getLineNO(){
		return lineNO;
	}

	public ExcelUsb setLineNO(Integer lineNO){
		this.lineNO=lineNO;
		return this;
	}

	public String getSn(){
		return sn;
	}

	public ExcelUsb setSn(String sn){
		this.sn=sn;
		return this;
	}

	public String getRecIN(){
		return recIN;
	}

	public ExcelUsb setRecIN(String recIN){
		this.recIN=recIN;
		return this;
	}

	public String getSentOUT(){
		return sentOUT;
	}

	public ExcelUsb setSentOUT(String sentOUT){
		this.sentOUT=sentOUT;
		return this;
	}

	public String getNewOld(){
		return newOld;
	}

	public ExcelUsb setNewOld(String newOld){
		this.newOld=newOld;
		return this;
	}

	public String getCategory(){
		return category;
	}

	public ExcelUsb setCategory(String category){
		this.category=category;
		return this;
	}

	public String getAssemblyDate(){
		return assemblyDate;
	}

	public ExcelUsb setAssemblyDate(String assemblyDate){
		this.assemblyDate=assemblyDate;
		return this;
	}

	public String getOutMonth(){
		return outMonth;
	}

	public ExcelUsb setOutMonth(String outMonth){
		this.outMonth=outMonth;
		return this;
	}

	public String getOutDate(){
		return outDate;
	}

	public ExcelUsb setOutDate(String outDate){
		this.outDate=outDate;
		return this;
	}

	public String getCustomer(){
		return customer;
	}

	public ExcelUsb setCustomer(String customer){
		this.customer=customer;
		return this;
	}

	public String getPurRen(){
		return purRen;
	}

	public ExcelUsb setPurRen(String purRen){
		this.purRen=purRen;
		return this;
	}

	public String getNote(){
		return note;
	}

	public ExcelUsb setNote(String note){
		this.note=note;
		return this;
	}

}
