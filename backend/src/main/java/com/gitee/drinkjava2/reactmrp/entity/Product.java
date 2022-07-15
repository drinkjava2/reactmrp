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
public class Product implements ActiveEntity<Product> {
	public static final String PRODUCTID = "ProductID";

	public static final String PRODUCTDESCRIPTION = "ProductDescription";

	public static final String MAXMONTHLYPRODUCTION = "MaxMonthlyProduction";

	public static final String TOTALCURRENTSTOCK = "TotalCurrentStock";

	public static final String UNIT = "Unit";

	public static final String PENDINGWOS = "PendingWOs";

	public static final String STOCKAVAILABLE = "StockAvailable";

	public static final String SAFETYSTOCK = "SafetyStock";

	public static final String SHORTAGE = "Shortage";

	public static final String MINIMALWOQTY = "MinimalWOQty";

	public static final String WOSUGGESTED = "WOSuggested";

	public static final String COUNTEDQTY = "CountedQTY";

	public static final String TOTALRETURNEDQTY = "TotalReturnedQTY";

	public static final String COUNTEDRETURNQTY = "CountedReturnQTY";

	public static final String CHECKEDNEW = "CheckedNew";

	public static final String CHECKEDOLD = "CheckedOld";

	public static final String LOCATIONNO = "LocationNO";

	public static final String DRAWINGNO = "DrawingNO";

	public static final String LONGDESC = "LongDesc";

	public static final String MODEL = "Model";

	public static final String PPSGROUP = "PPSGroup";

	@Id
	@Column(name="ProductID", length=46)
	private String productID;


	@Column(name="ProductDescription", length=60)
	private String productDescription;

	private Integer maxMonthlyProduction;

	private Integer totalCurrentStock;

	@Column(name="Unit", length=30)
	private String unit;

	private Integer pendingWOs;

	private Integer stockAvailable;

	private Integer safetyStock;

	private Integer shortage;

	private Integer minimalWOQty;

	private Integer wOSuggested;

	private Integer countedQTY;

	private Integer totalReturnedQTY;

	private Integer countedReturnQTY;

	private Integer checkedNew;

	private Integer checkedOld;

	@Column(name="LocationNO", length=16)
	private String locationNO;

	@Column(name="DrawingNO", length=20)
	private String drawingNO;

	@Column(name="LongDesc", length=70)
	private String longDesc;

	@Column(name="Model", length=30)
	private String model;

	@Column(name="PPSGroup", length=20)
	private String pPSGroup;


	public String getProductID(){
		return productID;
	}

	public Product setProductID(String productID){
		this.productID=productID;
		return this;
	}

	public String getProductDescription(){
		return productDescription;
	}

	public Product setProductDescription(String productDescription){
		this.productDescription=productDescription;
		return this;
	}

	public Integer getMaxMonthlyProduction(){
		return maxMonthlyProduction;
	}

	public Product setMaxMonthlyProduction(Integer maxMonthlyProduction){
		this.maxMonthlyProduction=maxMonthlyProduction;
		return this;
	}

	public Integer getTotalCurrentStock(){
		return totalCurrentStock;
	}

	public Product setTotalCurrentStock(Integer totalCurrentStock){
		this.totalCurrentStock=totalCurrentStock;
		return this;
	}

	public String getUnit(){
		return unit;
	}

	public Product setUnit(String unit){
		this.unit=unit;
		return this;
	}

	public Integer getPendingWOs(){
		return pendingWOs;
	}

	public Product setPendingWOs(Integer pendingWOs){
		this.pendingWOs=pendingWOs;
		return this;
	}

	public Integer getStockAvailable(){
		return stockAvailable;
	}

	public Product setStockAvailable(Integer stockAvailable){
		this.stockAvailable=stockAvailable;
		return this;
	}

	public Integer getSafetyStock(){
		return safetyStock;
	}

	public Product setSafetyStock(Integer safetyStock){
		this.safetyStock=safetyStock;
		return this;
	}

	public Integer getShortage(){
		return shortage;
	}

	public Product setShortage(Integer shortage){
		this.shortage=shortage;
		return this;
	}

	public Integer getMinimalWOQty(){
		return minimalWOQty;
	}

	public Product setMinimalWOQty(Integer minimalWOQty){
		this.minimalWOQty=minimalWOQty;
		return this;
	}

	public Integer getWOSuggested(){
		return wOSuggested;
	}

	public Product setWOSuggested(Integer wOSuggested){
		this.wOSuggested=wOSuggested;
		return this;
	}

	public Integer getCountedQTY(){
		return countedQTY;
	}

	public Product setCountedQTY(Integer countedQTY){
		this.countedQTY=countedQTY;
		return this;
	}

	public Integer getTotalReturnedQTY(){
		return totalReturnedQTY;
	}

	public Product setTotalReturnedQTY(Integer totalReturnedQTY){
		this.totalReturnedQTY=totalReturnedQTY;
		return this;
	}

	public Integer getCountedReturnQTY(){
		return countedReturnQTY;
	}

	public Product setCountedReturnQTY(Integer countedReturnQTY){
		this.countedReturnQTY=countedReturnQTY;
		return this;
	}

	public Integer getCheckedNew(){
		return checkedNew;
	}

	public Product setCheckedNew(Integer checkedNew){
		this.checkedNew=checkedNew;
		return this;
	}

	public Integer getCheckedOld(){
		return checkedOld;
	}

	public Product setCheckedOld(Integer checkedOld){
		this.checkedOld=checkedOld;
		return this;
	}

	public String getLocationNO(){
		return locationNO;
	}

	public Product setLocationNO(String locationNO){
		this.locationNO=locationNO;
		return this;
	}

	public String getDrawingNO(){
		return drawingNO;
	}

	public Product setDrawingNO(String drawingNO){
		this.drawingNO=drawingNO;
		return this;
	}

	public String getLongDesc(){
		return longDesc;
	}

	public Product setLongDesc(String longDesc){
		this.longDesc=longDesc;
		return this;
	}

	public String getModel(){
		return model;
	}

	public Product setModel(String model){
		this.model=model;
		return this;
	}

	public String getPPSGroup(){
		return pPSGroup;
	}

	public Product setPPSGroup(String pPSGroup){
		this.pPSGroup=pPSGroup;
		return this;
	}

}
