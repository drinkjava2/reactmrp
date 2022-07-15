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
public class ExcelProduct implements ActiveEntity<ExcelProduct> {
	public static final String ID = "ID";

	public static final String LINENO = "LineNO";

	public static final String MODEL = "Model";

	public static final String PARTNUMBER = "PartNumber";

	public static final String OD = "OD";

	public static final String MINQT = "MinQT";

	public static final String GOODQTYNEW = "GoodQtyNew";

	public static final String NEWGAUGELOCATION = "NewGaugeLocation";

	public static final String GOODQTYUSED = "GoodQtyUsed";

	public static final String USEDGAUGELOCATION = "UsedGaugeLocation";

	public static final String MINQTY = "MinQty";

	public static final String NOTE = "Note";

	@Id
	private Integer id;


	private Integer lineNO;

	@Column(name="Model", length=30)
	private String model;

	@Column(name="PartNumber", length=30)
	private String partNumber;

	@Column(name="OD", length=50)
	private String od;

	@Column(name="MinQT", length=30)
	private String minQT;

	@Column(name="GoodQtyNew", length=30)
	private String goodQtyNew;

	@Column(name="NewGaugeLocation", length=30)
	private String newGaugeLocation;

	@Column(name="GoodQtyUsed", length=30)
	private String goodQtyUsed;

	@Column(name="UsedGaugeLocation", length=30)
	private String usedGaugeLocation;

	@Column(name="MinQty", length=30)
	private String minQty;

	@Column(name="Note", length=500)
	private String note;


	public Integer getId(){
		return id;
	}

	public ExcelProduct setId(Integer id){
		this.id=id;
		return this;
	}

	public Integer getLineNO(){
		return lineNO;
	}

	public ExcelProduct setLineNO(Integer lineNO){
		this.lineNO=lineNO;
		return this;
	}

	public String getModel(){
		return model;
	}

	public ExcelProduct setModel(String model){
		this.model=model;
		return this;
	}

	public String getPartNumber(){
		return partNumber;
	}

	public ExcelProduct setPartNumber(String partNumber){
		this.partNumber=partNumber;
		return this;
	}

	public String getOd(){
		return od;
	}

	public ExcelProduct setOd(String od){
		this.od=od;
		return this;
	}

	public String getMinQT(){
		return minQT;
	}

	public ExcelProduct setMinQT(String minQT){
		this.minQT=minQT;
		return this;
	}

	public String getGoodQtyNew(){
		return goodQtyNew;
	}

	public ExcelProduct setGoodQtyNew(String goodQtyNew){
		this.goodQtyNew=goodQtyNew;
		return this;
	}

	public String getNewGaugeLocation(){
		return newGaugeLocation;
	}

	public ExcelProduct setNewGaugeLocation(String newGaugeLocation){
		this.newGaugeLocation=newGaugeLocation;
		return this;
	}

	public String getGoodQtyUsed(){
		return goodQtyUsed;
	}

	public ExcelProduct setGoodQtyUsed(String goodQtyUsed){
		this.goodQtyUsed=goodQtyUsed;
		return this;
	}

	public String getUsedGaugeLocation(){
		return usedGaugeLocation;
	}

	public ExcelProduct setUsedGaugeLocation(String usedGaugeLocation){
		this.usedGaugeLocation=usedGaugeLocation;
		return this;
	}

	public String getMinQty(){
		return minQty;
	}

	public ExcelProduct setMinQty(String minQty){
		this.minQty=minQty;
		return this;
	}

	public String getNote(){
		return note;
	}

	public ExcelProduct setNote(String note){
		this.note=note;
		return this;
	}

}
