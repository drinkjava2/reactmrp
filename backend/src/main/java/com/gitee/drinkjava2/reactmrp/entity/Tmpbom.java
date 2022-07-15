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
public class Tmpbom implements ActiveEntity<Tmpbom> {
	public static final String PRODUCTID = "ProductID";

	public static final String ITEMNO = "ItemNO";

	public static final String PARTID = "PartID";

	public static final String MANUFACTURERPN = "ManufacturerPN";

	public static final String STOCKID = "StockID";

	public static final String LPRICE = "LPrice";

	public static final String HPRICE = "Hprice";

	public static final String BOMQTY = "BOMQTY";

	public static final String SUBSTITUTION = "Substitution";


	@Column(name="ProductID", length=30)
	private String productID;

	private Integer itemNO;

	@Column(name="PartID", length=30)
	private String partID;

	@Column(name="ManufacturerPN", length=30)
	private String manufacturerPN;

	@Column(name="StockID", length=30)
	private String stockID;

	private Double lPrice;

	private Double hprice;

	private Integer bomqty;

	@Column(name="Substitution", length=50)
	private String substitution;


	public String getProductID(){
		return productID;
	}

	public Tmpbom setProductID(String productID){
		this.productID=productID;
		return this;
	}

	public Integer getItemNO(){
		return itemNO;
	}

	public Tmpbom setItemNO(Integer itemNO){
		this.itemNO=itemNO;
		return this;
	}

	public String getPartID(){
		return partID;
	}

	public Tmpbom setPartID(String partID){
		this.partID=partID;
		return this;
	}

	public String getManufacturerPN(){
		return manufacturerPN;
	}

	public Tmpbom setManufacturerPN(String manufacturerPN){
		this.manufacturerPN=manufacturerPN;
		return this;
	}

	public String getStockID(){
		return stockID;
	}

	public Tmpbom setStockID(String stockID){
		this.stockID=stockID;
		return this;
	}

	public Double getLPrice(){
		return lPrice;
	}

	public Tmpbom setLPrice(Double lPrice){
		this.lPrice=lPrice;
		return this;
	}

	public Double getHprice(){
		return hprice;
	}

	public Tmpbom setHprice(Double hprice){
		this.hprice=hprice;
		return this;
	}

	public Integer getBomqty(){
		return bomqty;
	}

	public Tmpbom setBomqty(Integer bomqty){
		this.bomqty=bomqty;
		return this;
	}

	public String getSubstitution(){
		return substitution;
	}

	public Tmpbom setSubstitution(String substitution){
		this.substitution=substitution;
		return this;
	}

}
