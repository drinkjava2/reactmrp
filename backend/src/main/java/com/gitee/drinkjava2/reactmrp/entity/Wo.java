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
public class Wo implements ActiveEntity<Wo> {
	public static final String WO = "WO";

	public static final String PRODUCTID = "ProductID";

	public static final String PRODUCTDESCRIPTION = "Productdescription";

	public static final String WOQTY = "WOQty";

	public static final String WODATE = "WODate";

	public static final String EXPECTEDFINISHDATE = "ExpectedFinishDate";

	public static final String FINISHED = "Finished";

	public static final String PENDING = "Pending";

	public static final String BACKORDER = "BackOrder";

	public static final String FINISHDATE = "FinishDate";

	public static final String NOTES = "Notes";

	@Id
	@Column(name="WO", length=30)
	private String wo;


	@Column(name="ProductID", length=46)
	@SingleFKey(name="wo_ibfk_1", refs={"product","ProductID"})
	private String productID;

	@Column(name="Productdescription", length=60)
	private String productdescription;

	private Integer wOQty;

	private Date wODate;

	private Date expectedFinishDate;

	private Integer finished;

	@Column(name="Pending", length=8)
	private String pending;

	private Integer backOrder;

	private Date finishDate;

	@Column(name="Notes", length=60)
	private String notes;


	public String getWo(){
		return wo;
	}

	public Wo setWo(String wo){
		this.wo=wo;
		return this;
	}

	public String getProductID(){
		return productID;
	}

	public Wo setProductID(String productID){
		this.productID=productID;
		return this;
	}

	public String getProductdescription(){
		return productdescription;
	}

	public Wo setProductdescription(String productdescription){
		this.productdescription=productdescription;
		return this;
	}

	public Integer getWOQty(){
		return wOQty;
	}

	public Wo setWOQty(Integer wOQty){
		this.wOQty=wOQty;
		return this;
	}

	public Date getWODate(){
		return wODate;
	}

	public Wo setWODate(Date wODate){
		this.wODate=wODate;
		return this;
	}

	public Date getExpectedFinishDate(){
		return expectedFinishDate;
	}

	public Wo setExpectedFinishDate(Date expectedFinishDate){
		this.expectedFinishDate=expectedFinishDate;
		return this;
	}

	public Integer getFinished(){
		return finished;
	}

	public Wo setFinished(Integer finished){
		this.finished=finished;
		return this;
	}

	public String getPending(){
		return pending;
	}

	public Wo setPending(String pending){
		this.pending=pending;
		return this;
	}

	public Integer getBackOrder(){
		return backOrder;
	}

	public Wo setBackOrder(Integer backOrder){
		this.backOrder=backOrder;
		return this;
	}

	public Date getFinishDate(){
		return finishDate;
	}

	public Wo setFinishDate(Date finishDate){
		this.finishDate=finishDate;
		return this;
	}

	public String getNotes(){
		return notes;
	}

	public Wo setNotes(String notes){
		this.notes=notes;
		return this;
	}

}
