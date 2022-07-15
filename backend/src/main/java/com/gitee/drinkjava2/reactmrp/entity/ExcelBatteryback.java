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
public class ExcelBatteryback implements ActiveEntity<ExcelBatteryback> {
	public static final String ID = "ID";

	public static final String LINENO = "LineNO";

	public static final String SN = "SN";

	public static final String NEWOLD = "NewOld";

	public static final String RECIN = "RecIN";

	public static final String SENTOUT = "SentOUT";

	public static final String DISCARD = "Discard";

	public static final String ASSEMBLYDATE = "AssemblyDate";

	public static final String OUTDATE = "OutDate";

	public static final String RETURNDATE = "ReturnDate";

	public static final String PURREN = "PurRen";

	public static final String NOTE = "NOTE";

	@Id
	private Integer id;


	private Integer lineNO;

	@Column(name="SN", length=30)
	private String sn;

	@Column(name="NewOld", length=10)
	private String newOld;

	@Column(name="RecIN", length=1)
	private String recIN;

	@Column(name="SentOUT", length=1)
	private String sentOUT;

	@Column(name="Discard", length=1)
	private String discard;

	@Column(name="AssemblyDate", length=30)
	private String assemblyDate;

	@Column(name="OutDate", length=30)
	private String outDate;

	@Column(name="ReturnDate", length=30)
	private String returnDate;

	@Column(name="PurRen", length=20)
	private String purRen;

	@Column(name="NOTE", length=500)
	private String note;


	public Integer getId(){
		return id;
	}

	public ExcelBatteryback setId(Integer id){
		this.id=id;
		return this;
	}

	public Integer getLineNO(){
		return lineNO;
	}

	public ExcelBatteryback setLineNO(Integer lineNO){
		this.lineNO=lineNO;
		return this;
	}

	public String getSn(){
		return sn;
	}

	public ExcelBatteryback setSn(String sn){
		this.sn=sn;
		return this;
	}

	public String getNewOld(){
		return newOld;
	}

	public ExcelBatteryback setNewOld(String newOld){
		this.newOld=newOld;
		return this;
	}

	public String getRecIN(){
		return recIN;
	}

	public ExcelBatteryback setRecIN(String recIN){
		this.recIN=recIN;
		return this;
	}

	public String getSentOUT(){
		return sentOUT;
	}

	public ExcelBatteryback setSentOUT(String sentOUT){
		this.sentOUT=sentOUT;
		return this;
	}

	public String getDiscard(){
		return discard;
	}

	public ExcelBatteryback setDiscard(String discard){
		this.discard=discard;
		return this;
	}

	public String getAssemblyDate(){
		return assemblyDate;
	}

	public ExcelBatteryback setAssemblyDate(String assemblyDate){
		this.assemblyDate=assemblyDate;
		return this;
	}

	public String getOutDate(){
		return outDate;
	}

	public ExcelBatteryback setOutDate(String outDate){
		this.outDate=outDate;
		return this;
	}

	public String getReturnDate(){
		return returnDate;
	}

	public ExcelBatteryback setReturnDate(String returnDate){
		this.returnDate=returnDate;
		return this;
	}

	public String getPurRen(){
		return purRen;
	}

	public ExcelBatteryback setPurRen(String purRen){
		this.purRen=purRen;
		return this;
	}

	public String getNote(){
		return note;
	}

	public ExcelBatteryback setNote(String note){
		this.note=note;
		return this;
	}

}
