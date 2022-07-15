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
public class ExcelBatterytester implements ActiveEntity<ExcelBatterytester> {
	public static final String ID = "ID";

	public static final String LINENO = "LineNO";

	public static final String SN = "SN";

	public static final String RECIN = "RecIN";

	public static final String SENTOUT = "SentOUT";

	public static final String CATEGORY = "Category";

	public static final String STATUS = "Status";

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

	@Column(name="Category", length=30)
	private String category;

	@Column(name="Status", length=10)
	private String status;

	@Column(name="AssemblyDate", length=30)
	private String assemblyDate;

	@Column(name="OutMonth", length=30)
	private String outMonth;

	@Column(name="OutDate", length=30)
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

	public ExcelBatterytester setId(Integer id){
		this.id=id;
		return this;
	}

	public Integer getLineNO(){
		return lineNO;
	}

	public ExcelBatterytester setLineNO(Integer lineNO){
		this.lineNO=lineNO;
		return this;
	}

	public String getSn(){
		return sn;
	}

	public ExcelBatterytester setSn(String sn){
		this.sn=sn;
		return this;
	}

	public String getRecIN(){
		return recIN;
	}

	public ExcelBatterytester setRecIN(String recIN){
		this.recIN=recIN;
		return this;
	}

	public String getSentOUT(){
		return sentOUT;
	}

	public ExcelBatterytester setSentOUT(String sentOUT){
		this.sentOUT=sentOUT;
		return this;
	}

	public String getCategory(){
		return category;
	}

	public ExcelBatterytester setCategory(String category){
		this.category=category;
		return this;
	}

	public String getStatus(){
		return status;
	}

	public ExcelBatterytester setStatus(String status){
		this.status=status;
		return this;
	}

	public String getAssemblyDate(){
		return assemblyDate;
	}

	public ExcelBatterytester setAssemblyDate(String assemblyDate){
		this.assemblyDate=assemblyDate;
		return this;
	}

	public String getOutMonth(){
		return outMonth;
	}

	public ExcelBatterytester setOutMonth(String outMonth){
		this.outMonth=outMonth;
		return this;
	}

	public String getOutDate(){
		return outDate;
	}

	public ExcelBatterytester setOutDate(String outDate){
		this.outDate=outDate;
		return this;
	}

	public String getCustomer(){
		return customer;
	}

	public ExcelBatterytester setCustomer(String customer){
		this.customer=customer;
		return this;
	}

	public String getPurRen(){
		return purRen;
	}

	public ExcelBatterytester setPurRen(String purRen){
		this.purRen=purRen;
		return this;
	}

	public String getNote(){
		return note;
	}

	public ExcelBatterytester setNote(String note){
		this.note=note;
		return this;
	}

}
