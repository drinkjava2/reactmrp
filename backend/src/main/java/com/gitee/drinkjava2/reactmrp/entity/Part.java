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
public class Part implements ActiveEntity<Part> {
	public static final String PARTID = "PartID";

	public static final String MANUFACTURERPN = "ManufacturerPN";

	public static final String TOTALCURRENTSTOCK = "TotalCurrentStock";

	public static final String UNIT = "Unit";

	public static final String PRICE = "Price";

	public static final String STOCKONHOLD = "StockonHold";

	public static final String PENDINGPOS = "PendingPOs";

	public static final String STOCKAVAILABLE = "StockAvailable";

	public static final String SAFETYSTOCKLEVEL = "SafetyStockLevel";

	public static final String MAXSTOCKLEVEL = "MaxStockLevel";

	public static final String SHORTAGE = "Shortage";

	public static final String MINIMALPOQTY = "MinimalPOQty";

	public static final String PONEEDED = "PONeeded";

	public static final String EXPECTEDLEADWEEKS = "ExpectedLeadWeeks";

	public static final String DESCRIPTION = "description";

	public static final String MATERIAL = "Material";

	public static final String USEDHOUSING = "UsedHousing";

	public static final String VENDER1ID = "Vender1ID";

	public static final String VENDER1PN = "Vender1PN";

	public static final String VENDER2ID = "Vender2ID";

	public static final String VENDER2PN = "Vender2PN";

	public static final String COUNTEDQTY = "CountedQTY";

	public static final String LOCATION_NO = "Location_NO";

	public static final String CURRENCY = "Currency";

	public static final String MODEL = "Model";

	public static final String NOTE = "note";

	public static final String BOMSTOCK = "BomStock";

	public static final String CATEGORY = "category";

	public static final String MANUFACTURER = "Manufacturer";

	public static final String BOM = "BOM";

	public static final String CLASS = "class";

	public static final String SLB_PN = "SLB_PN";

	public static final String HLB_PN = "HLB_PN";

	public static final String SELLPRICE = "SellPrice";

	public static final String DISCOUNT = "Discount";

	public static final String DRAWINGNO = "DrawingNO";

	public static final String CERTIFIED = "Certified";

	public static final String OBSOLETED = "Obsoleted";

	@Id
	@Column(name="PartID", length=40)
	private String partID;


	@Column(name="ManufacturerPN", length=30)
	private String manufacturerPN;

	private Integer totalCurrentStock;

	@Column(name="Unit", length=10)
	private String unit;

	private Double price;

	private Integer stockonHold;

	private Integer pendingPOs;

	private Integer stockAvailable;

	private Integer safetyStockLevel;

	private Integer maxStockLevel;

	private Integer shortage;

	private Integer minimalPOQty;

	private Integer pONeeded;

	private Integer expectedLeadWeeks;

	@Column(name="description", length=120)
	private String description;

	@Column(name="Material", length=20)
	private String material;

	private Integer usedHousing;

	@Column(name="Vender1ID", length=30)
	@SingleFKey(name="part_ibfk_1", refs={"vender","VenderID"})
	private String vender1ID;

	@Column(name="Vender1PN", length=60)
	private String vender1PN;

	@Column(name="Vender2ID", length=30)
	@SingleFKey(name="part_ibfk_2", refs={"vender","VenderID"})
	private String vender2ID;

	@Column(name="Vender2PN", length=30)
	private String vender2PN;

	private Integer countedQTY;

	@Column(name="Location_NO", length=16)
	private String locationNo;

	@Column(name="Currency", length=6)
	private String currency;

	@Column(name="Model", length=10)
	private String model;

	@Column(name="note", length=90)
	private String note;

	private Integer bomStock;

	@Column(name="category", length=20)
	private String category;

	@Column(name="Manufacturer", length=30)
	private String manufacturer;

	@Column(name="BOM", length=3)
	private String bom;

	@Column(name="class", length=1)
	private String clazz;

	@Column(name="SLB_PN", length=14)
	private String slbPn;

	@Column(name="HLB_PN", length=14)
	private String hlbPn;

	private Double sellPrice;

	private Double discount;

	@Column(name="DrawingNO", length=40)
	private String drawingNO;

	@Column(name="Certified", length=9)
	private String certified;

	@Column(name="Obsoleted", length=10)
	private String obsoleted;


	public String getPartID(){
		return partID;
	}

	public Part setPartID(String partID){
		this.partID=partID;
		return this;
	}

	public String getManufacturerPN(){
		return manufacturerPN;
	}

	public Part setManufacturerPN(String manufacturerPN){
		this.manufacturerPN=manufacturerPN;
		return this;
	}

	public Integer getTotalCurrentStock(){
		return totalCurrentStock;
	}

	public Part setTotalCurrentStock(Integer totalCurrentStock){
		this.totalCurrentStock=totalCurrentStock;
		return this;
	}

	public String getUnit(){
		return unit;
	}

	public Part setUnit(String unit){
		this.unit=unit;
		return this;
	}

	public Double getPrice(){
		return price;
	}

	public Part setPrice(Double price){
		this.price=price;
		return this;
	}

	public Integer getStockonHold(){
		return stockonHold;
	}

	public Part setStockonHold(Integer stockonHold){
		this.stockonHold=stockonHold;
		return this;
	}

	public Integer getPendingPOs(){
		return pendingPOs;
	}

	public Part setPendingPOs(Integer pendingPOs){
		this.pendingPOs=pendingPOs;
		return this;
	}

	public Integer getStockAvailable(){
		return stockAvailable;
	}

	public Part setStockAvailable(Integer stockAvailable){
		this.stockAvailable=stockAvailable;
		return this;
	}

	public Integer getSafetyStockLevel(){
		return safetyStockLevel;
	}

	public Part setSafetyStockLevel(Integer safetyStockLevel){
		this.safetyStockLevel=safetyStockLevel;
		return this;
	}

	public Integer getMaxStockLevel(){
		return maxStockLevel;
	}

	public Part setMaxStockLevel(Integer maxStockLevel){
		this.maxStockLevel=maxStockLevel;
		return this;
	}

	public Integer getShortage(){
		return shortage;
	}

	public Part setShortage(Integer shortage){
		this.shortage=shortage;
		return this;
	}

	public Integer getMinimalPOQty(){
		return minimalPOQty;
	}

	public Part setMinimalPOQty(Integer minimalPOQty){
		this.minimalPOQty=minimalPOQty;
		return this;
	}

	public Integer getPONeeded(){
		return pONeeded;
	}

	public Part setPONeeded(Integer pONeeded){
		this.pONeeded=pONeeded;
		return this;
	}

	public Integer getExpectedLeadWeeks(){
		return expectedLeadWeeks;
	}

	public Part setExpectedLeadWeeks(Integer expectedLeadWeeks){
		this.expectedLeadWeeks=expectedLeadWeeks;
		return this;
	}

	public String getDescription(){
		return description;
	}

	public Part setDescription(String description){
		this.description=description;
		return this;
	}

	public String getMaterial(){
		return material;
	}

	public Part setMaterial(String material){
		this.material=material;
		return this;
	}

	public Integer getUsedHousing(){
		return usedHousing;
	}

	public Part setUsedHousing(Integer usedHousing){
		this.usedHousing=usedHousing;
		return this;
	}

	public String getVender1ID(){
		return vender1ID;
	}

	public Part setVender1ID(String vender1ID){
		this.vender1ID=vender1ID;
		return this;
	}

	public String getVender1PN(){
		return vender1PN;
	}

	public Part setVender1PN(String vender1PN){
		this.vender1PN=vender1PN;
		return this;
	}

	public String getVender2ID(){
		return vender2ID;
	}

	public Part setVender2ID(String vender2ID){
		this.vender2ID=vender2ID;
		return this;
	}

	public String getVender2PN(){
		return vender2PN;
	}

	public Part setVender2PN(String vender2PN){
		this.vender2PN=vender2PN;
		return this;
	}

	public Integer getCountedQTY(){
		return countedQTY;
	}

	public Part setCountedQTY(Integer countedQTY){
		this.countedQTY=countedQTY;
		return this;
	}

	public String getLocationNo(){
		return locationNo;
	}

	public Part setLocationNo(String locationNo){
		this.locationNo=locationNo;
		return this;
	}

	public String getCurrency(){
		return currency;
	}

	public Part setCurrency(String currency){
		this.currency=currency;
		return this;
	}

	public String getModel(){
		return model;
	}

	public Part setModel(String model){
		this.model=model;
		return this;
	}

	public String getNote(){
		return note;
	}

	public Part setNote(String note){
		this.note=note;
		return this;
	}

	public Integer getBomStock(){
		return bomStock;
	}

	public Part setBomStock(Integer bomStock){
		this.bomStock=bomStock;
		return this;
	}

	public String getCategory(){
		return category;
	}

	public Part setCategory(String category){
		this.category=category;
		return this;
	}

	public String getManufacturer(){
		return manufacturer;
	}

	public Part setManufacturer(String manufacturer){
		this.manufacturer=manufacturer;
		return this;
	}

	public String getBom(){
		return bom;
	}

	public Part setBom(String bom){
		this.bom=bom;
		return this;
	}

	public String getClazz(){
		return clazz;
	}

	public Part setClazz(String clazz){
		this.clazz=clazz;
		return this;
	}

	public String getSlbPn(){
		return slbPn;
	}

	public Part setSlbPn(String slbPn){
		this.slbPn=slbPn;
		return this;
	}

	public String getHlbPn(){
		return hlbPn;
	}

	public Part setHlbPn(String hlbPn){
		this.hlbPn=hlbPn;
		return this;
	}

	public Double getSellPrice(){
		return sellPrice;
	}

	public Part setSellPrice(Double sellPrice){
		this.sellPrice=sellPrice;
		return this;
	}

	public Double getDiscount(){
		return discount;
	}

	public Part setDiscount(Double discount){
		this.discount=discount;
		return this;
	}

	public String getDrawingNO(){
		return drawingNO;
	}

	public Part setDrawingNO(String drawingNO){
		this.drawingNO=drawingNO;
		return this;
	}

	public String getCertified(){
		return certified;
	}

	public Part setCertified(String certified){
		this.certified=certified;
		return this;
	}

	public String getObsoleted(){
		return obsoleted;
	}

	public Part setObsoleted(String obsoleted){
		this.obsoleted=obsoleted;
		return this;
	}

}
