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
public class ExcelReceiver implements ActiveEntity<ExcelReceiver> {
	public static final String ID = "ID";

	public static final String LINENO = "LineNO";

	public static final String SN = "SN";

	public static final String RECIN = "RecIN";

	public static final String SENTOUT = "SentOUT";

	public static final String ASSEMBLYDATE = "AssemblyDate";

	public static final String CUSTOMER = "Customer";

	public static final String OUTMONTH = "OutMonth";

	public static final String OUTDATE = "OutDate";

	public static final String CH = "CH";

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

	@Column(name="AssemblyDate", length=20)
	private String assemblyDate;

	@Column(name="Customer", length=60)
	private String customer;

	@Column(name="OutMonth", length=20)
	private String outMonth;

	@Column(name="OutDate", length=20)
	private String outDate;

	@Column(name="CH", length=10)
	private String ch;

	@Column(name="PurRen", length=20)
	private String purRen;

	@Column(name="NOTE", length=500)
	private String note;


	public Integer getId(){
		return id;
	}

	public ExcelReceiver setId(Integer id){
		this.id=id;
		return this;
	}

	public Integer getLineNO(){
		return lineNO;
	}

	public ExcelReceiver setLineNO(Integer lineNO){
		this.lineNO=lineNO;
		return this;
	}

	public String getSn(){
		return sn;
	}

	public ExcelReceiver setSn(String sn){
		this.sn=sn;
		return this;
	}

	public String getRecIN(){
		return recIN;
	}

	public ExcelReceiver setRecIN(String recIN){
		this.recIN=recIN;
		return this;
	}

	public String getSentOUT(){
		return sentOUT;
	}

	public ExcelReceiver setSentOUT(String sentOUT){
		this.sentOUT=sentOUT;
		return this;
	}

	public String getAssemblyDate(){
		return assemblyDate;
	}

	public ExcelReceiver setAssemblyDate(String assemblyDate){
		this.assemblyDate=assemblyDate;
		return this;
	}

	public String getCustomer(){
		return customer;
	}

	public ExcelReceiver setCustomer(String customer){
		this.customer=customer;
		return this;
	}

	public String getOutMonth(){
		return outMonth;
	}

	public ExcelReceiver setOutMonth(String outMonth){
		this.outMonth=outMonth;
		return this;
	}

	public String getOutDate(){
		return outDate;
	}

	public ExcelReceiver setOutDate(String outDate){
		this.outDate=outDate;
		return this;
	}

	public String getCh(){
		return ch;
	}

	public ExcelReceiver setCh(String ch){
		this.ch=ch;
		return this;
	}

	public String getPurRen(){
		return purRen;
	}

	public ExcelReceiver setPurRen(String purRen){
		this.purRen=purRen;
		return this;
	}

	public String getNote(){
		return note;
	}

	public ExcelReceiver setNote(String note){
		this.note=note;
		return this;
	}

}
