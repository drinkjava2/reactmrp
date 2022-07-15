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
public class LogPart implements ActiveEntity<LogPart> {
	public static final String ID = "ID";

	public static final String PARTID = "PartID";

	public static final String OLDQTY = "OldQTY";

	public static final String NEWQTY = "NewQTY";

	public static final String CHANGEDQTY = "ChangedQTY";

	public static final String OPERATETYPE = "OperateType";

	public static final String OPERATESLIP = "OperateSlip";

	public static final String OPERATER = "Operater";

	public static final String OPERATETIME = "OperateTime";

	public static final String PO = "PO";

	public static final String BATCHNO = "BatchNo";

	public static final String WORKORDER = "Workorder";

	public static final String ORF = "ORF";

	public static final String NOTE = "note";

	public static final String PICKEDBY = "PickedBy";

	@Id
	private Integer id;


	@Column(name="PartID", length=40)
	private String partID;

	private Integer oldQTY;

	private Integer newQTY;

	private Integer changedQTY;

	@Column(name="OperateType", length=12)
	private String operateType;

	@Column(name="OperateSlip", length=30)
	private String operateSlip;

	@Column(name="Operater", length=30)
	private String operater;

	private Date operateTime;

	@Column(name="PO", length=30)
	private String po;

	@Column(name="BatchNo", length=30)
	private String batchNo;

	@Column(name="Workorder", length=20)
	private String workorder;

	@Column(name="ORF", length=12)
	private String orf;

	@Column(name="note", length=60)
	private String note;

	@Column(name="PickedBy", length=10)
	private String pickedBy;


	public Integer getId(){
		return id;
	}

	public LogPart setId(Integer id){
		this.id=id;
		return this;
	}

	public String getPartID(){
		return partID;
	}

	public LogPart setPartID(String partID){
		this.partID=partID;
		return this;
	}

	public Integer getOldQTY(){
		return oldQTY;
	}

	public LogPart setOldQTY(Integer oldQTY){
		this.oldQTY=oldQTY;
		return this;
	}

	public Integer getNewQTY(){
		return newQTY;
	}

	public LogPart setNewQTY(Integer newQTY){
		this.newQTY=newQTY;
		return this;
	}

	public Integer getChangedQTY(){
		return changedQTY;
	}

	public LogPart setChangedQTY(Integer changedQTY){
		this.changedQTY=changedQTY;
		return this;
	}

	public String getOperateType(){
		return operateType;
	}

	public LogPart setOperateType(String operateType){
		this.operateType=operateType;
		return this;
	}

	public String getOperateSlip(){
		return operateSlip;
	}

	public LogPart setOperateSlip(String operateSlip){
		this.operateSlip=operateSlip;
		return this;
	}

	public String getOperater(){
		return operater;
	}

	public LogPart setOperater(String operater){
		this.operater=operater;
		return this;
	}

	public Date getOperateTime(){
		return operateTime;
	}

	public LogPart setOperateTime(Date operateTime){
		this.operateTime=operateTime;
		return this;
	}

	public String getPo(){
		return po;
	}

	public LogPart setPo(String po){
		this.po=po;
		return this;
	}

	public String getBatchNo(){
		return batchNo;
	}

	public LogPart setBatchNo(String batchNo){
		this.batchNo=batchNo;
		return this;
	}

	public String getWorkorder(){
		return workorder;
	}

	public LogPart setWorkorder(String workorder){
		this.workorder=workorder;
		return this;
	}

	public String getOrf(){
		return orf;
	}

	public LogPart setOrf(String orf){
		this.orf=orf;
		return this;
	}

	public String getNote(){
		return note;
	}

	public LogPart setNote(String note){
		this.note=note;
		return this;
	}

	public String getPickedBy(){
		return pickedBy;
	}

	public LogPart setPickedBy(String pickedBy){
		this.pickedBy=pickedBy;
		return this;
	}

}
