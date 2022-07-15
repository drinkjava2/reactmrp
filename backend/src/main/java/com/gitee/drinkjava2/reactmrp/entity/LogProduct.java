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
public class LogProduct implements ActiveEntity<LogProduct> {
	public static final String ID = "ID";

	public static final String PRODUCTID = "ProductID";

	public static final String OLDQTY = "OldQTY";

	public static final String NEWQTY = "NewQTY";

	public static final String CHANGEDQTY = "ChangedQTY";

	public static final String OPERATETYPE = "OperateType";

	public static final String OPERATESLIP = "OperateSlip";

	public static final String OPERATER = "Operater";

	public static final String OPERATETIME = "OperateTime";

	public static final String OLDRETURNEDQTY = "OldReturnedQTY";

	public static final String NEWRETURNEDQTY = "NewReturnedQTY";

	public static final String CHANGEDRETURNEDQTY = "ChangedReturnedQTY";

	@Id
	private Integer id;


	@Column(name="ProductID", length=46)
	private String productID;

	private Integer oldQTY;

	private Integer newQTY;

	private Integer changedQTY;

	@Column(name="OperateType", length=10)
	private String operateType;

	@Column(name="OperateSlip", length=30)
	private String operateSlip;

	@Column(name="Operater", length=30)
	private String operater;

	private Date operateTime;

	private Integer oldReturnedQTY;

	private Integer newReturnedQTY;

	private Integer changedReturnedQTY;


	public Integer getId(){
		return id;
	}

	public LogProduct setId(Integer id){
		this.id=id;
		return this;
	}

	public String getProductID(){
		return productID;
	}

	public LogProduct setProductID(String productID){
		this.productID=productID;
		return this;
	}

	public Integer getOldQTY(){
		return oldQTY;
	}

	public LogProduct setOldQTY(Integer oldQTY){
		this.oldQTY=oldQTY;
		return this;
	}

	public Integer getNewQTY(){
		return newQTY;
	}

	public LogProduct setNewQTY(Integer newQTY){
		this.newQTY=newQTY;
		return this;
	}

	public Integer getChangedQTY(){
		return changedQTY;
	}

	public LogProduct setChangedQTY(Integer changedQTY){
		this.changedQTY=changedQTY;
		return this;
	}

	public String getOperateType(){
		return operateType;
	}

	public LogProduct setOperateType(String operateType){
		this.operateType=operateType;
		return this;
	}

	public String getOperateSlip(){
		return operateSlip;
	}

	public LogProduct setOperateSlip(String operateSlip){
		this.operateSlip=operateSlip;
		return this;
	}

	public String getOperater(){
		return operater;
	}

	public LogProduct setOperater(String operater){
		this.operater=operater;
		return this;
	}

	public Date getOperateTime(){
		return operateTime;
	}

	public LogProduct setOperateTime(Date operateTime){
		this.operateTime=operateTime;
		return this;
	}

	public Integer getOldReturnedQTY(){
		return oldReturnedQTY;
	}

	public LogProduct setOldReturnedQTY(Integer oldReturnedQTY){
		this.oldReturnedQTY=oldReturnedQTY;
		return this;
	}

	public Integer getNewReturnedQTY(){
		return newReturnedQTY;
	}

	public LogProduct setNewReturnedQTY(Integer newReturnedQTY){
		this.newReturnedQTY=newReturnedQTY;
		return this;
	}

	public Integer getChangedReturnedQTY(){
		return changedReturnedQTY;
	}

	public LogProduct setChangedReturnedQTY(Integer changedReturnedQTY){
		this.changedReturnedQTY=changedReturnedQTY;
		return this;
	}

}
