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
public class Oldpo implements ActiveEntity<Oldpo> {
	public static final String ID = "ID";

	public static final String LINENO = "lineno";

	public static final String POWITHNOTE = "POWithNote";

	public static final String PROJECT = "Project";

	public static final String PO = "PO";

	public static final String VENDER = "Vender";

	public static final String ITEM = "item";

	public static final String DESCRIPTION = "Description";

	public static final String ORDERQTY = "OrderQTY";

	public static final String UNIT = "Unit";

	public static final String MATERIAL = "Material";

	public static final String PRICE = "Price";

	public static final String DELIVERY = "Delivery";

	public static final String WEEKS = "Weeks";

	public static final String CHANGES = "Changes";

	public static final String POSTATUS = "POStatus";

	public static final String RECEIVED = "Received";

	public static final String CHECKPASS = "CheckPass";

	public static final String INSTORAGE = "InStorage";

	public static final String TAKEAWAY = "takeAway";

	public static final String PACKSLIP = "PackSlip";

	public static final String CHECKRECORD = "CheckRecord";

	public static final String NOTE = "Note";

	public static final String SN = "SN";

	public static final String DEADLINE = "DeadLine";

	public static final String CURRENCY = "Currency";

	public static final String PN = "PN";

	public static final String DESCS = "descs";

	public static final String APPLIED = "Applied";

	public static final String CHECKED = "Checked";

	public static final String RECDATE = "RecDate";

	public static final String MATERIALPO = "MaterialPO";

	@Id
	private Integer id;


	private Integer lineno;

	@Column(name="POWithNote", length=200)
	private String pOWithNote;

	@Column(name="Project", length=32)
	private String project;

	@Column(name="PO", length=40)
	private String po;

	@Column(name="Vender", length=60)
	@SingleFKey(name="oldpo_ibfk_1", refs={"vender","VenderID"})
	private String vender;

	@Column(name="item", length=24)
	private String item;

	@Column(name="Description", length=300)
	private String description;

	private Integer orderQTY;

	@Column(name="Unit", length=16)
	private String unit;

	@Column(name="Material", length=60)
	private String material;

	@Column(name="Price", length=24)
	private String price;

	@Column(name="Delivery", length=24)
	private String delivery;

	@Column(name="Weeks", length=200)
	private String weeks;

	@Column(name="Changes", length=100)
	private String changes;

	@Column(name="POStatus", length=32)
	private String pOStatus;

	private Integer received;

	private Integer checkPass;

	private Integer inStorage;

	@Column(name="takeAway", length=32)
	private String takeAway;

	@Column(name="PackSlip", length=120)
	private String packSlip;

	@Column(name="CheckRecord", length=40)
	private String checkRecord;

	@Column(name="Note", length=200)
	private String note;

	@Column(name="SN", length=40)
	private String sn;

	private Date deadLine;

	@Column(name="Currency", length=6)
	private String currency;

	@Column(name="PN", length=40)
	private String pn;

	@Column(name="descs", length=300)
	private String descs;

	@Column(name="Applied", length=1)
	private String applied;

	@Column(name="Checked", length=1)
	private String checked;

	@Column(name="RecDate", length=12)
	private String recDate;

	@Column(name="MaterialPO", length=40)
	private String materialPO;


	public Integer getId(){
		return id;
	}

	public Oldpo setId(Integer id){
		this.id=id;
		return this;
	}

	public Integer getLineno(){
		return lineno;
	}

	public Oldpo setLineno(Integer lineno){
		this.lineno=lineno;
		return this;
	}

	public String getPOWithNote(){
		return pOWithNote;
	}

	public Oldpo setPOWithNote(String pOWithNote){
		this.pOWithNote=pOWithNote;
		return this;
	}

	public String getProject(){
		return project;
	}

	public Oldpo setProject(String project){
		this.project=project;
		return this;
	}

	public String getPo(){
		return po;
	}

	public Oldpo setPo(String po){
		this.po=po;
		return this;
	}

	public String getVender(){
		return vender;
	}

	public Oldpo setVender(String vender){
		this.vender=vender;
		return this;
	}

	public String getItem(){
		return item;
	}

	public Oldpo setItem(String item){
		this.item=item;
		return this;
	}

	public String getDescription(){
		return description;
	}

	public Oldpo setDescription(String description){
		this.description=description;
		return this;
	}

	public Integer getOrderQTY(){
		return orderQTY;
	}

	public Oldpo setOrderQTY(Integer orderQTY){
		this.orderQTY=orderQTY;
		return this;
	}

	public String getUnit(){
		return unit;
	}

	public Oldpo setUnit(String unit){
		this.unit=unit;
		return this;
	}

	public String getMaterial(){
		return material;
	}

	public Oldpo setMaterial(String material){
		this.material=material;
		return this;
	}

	public String getPrice(){
		return price;
	}

	public Oldpo setPrice(String price){
		this.price=price;
		return this;
	}

	public String getDelivery(){
		return delivery;
	}

	public Oldpo setDelivery(String delivery){
		this.delivery=delivery;
		return this;
	}

	public String getWeeks(){
		return weeks;
	}

	public Oldpo setWeeks(String weeks){
		this.weeks=weeks;
		return this;
	}

	public String getChanges(){
		return changes;
	}

	public Oldpo setChanges(String changes){
		this.changes=changes;
		return this;
	}

	public String getPOStatus(){
		return pOStatus;
	}

	public Oldpo setPOStatus(String pOStatus){
		this.pOStatus=pOStatus;
		return this;
	}

	public Integer getReceived(){
		return received;
	}

	public Oldpo setReceived(Integer received){
		this.received=received;
		return this;
	}

	public Integer getCheckPass(){
		return checkPass;
	}

	public Oldpo setCheckPass(Integer checkPass){
		this.checkPass=checkPass;
		return this;
	}

	public Integer getInStorage(){
		return inStorage;
	}

	public Oldpo setInStorage(Integer inStorage){
		this.inStorage=inStorage;
		return this;
	}

	public String getTakeAway(){
		return takeAway;
	}

	public Oldpo setTakeAway(String takeAway){
		this.takeAway=takeAway;
		return this;
	}

	public String getPackSlip(){
		return packSlip;
	}

	public Oldpo setPackSlip(String packSlip){
		this.packSlip=packSlip;
		return this;
	}

	public String getCheckRecord(){
		return checkRecord;
	}

	public Oldpo setCheckRecord(String checkRecord){
		this.checkRecord=checkRecord;
		return this;
	}

	public String getNote(){
		return note;
	}

	public Oldpo setNote(String note){
		this.note=note;
		return this;
	}

	public String getSn(){
		return sn;
	}

	public Oldpo setSn(String sn){
		this.sn=sn;
		return this;
	}

	public Date getDeadLine(){
		return deadLine;
	}

	public Oldpo setDeadLine(Date deadLine){
		this.deadLine=deadLine;
		return this;
	}

	public String getCurrency(){
		return currency;
	}

	public Oldpo setCurrency(String currency){
		this.currency=currency;
		return this;
	}

	public String getPn(){
		return pn;
	}

	public Oldpo setPn(String pn){
		this.pn=pn;
		return this;
	}

	public String getDescs(){
		return descs;
	}

	public Oldpo setDescs(String descs){
		this.descs=descs;
		return this;
	}

	public String getApplied(){
		return applied;
	}

	public Oldpo setApplied(String applied){
		this.applied=applied;
		return this;
	}

	public String getChecked(){
		return checked;
	}

	public Oldpo setChecked(String checked){
		this.checked=checked;
		return this;
	}

	public String getRecDate(){
		return recDate;
	}

	public Oldpo setRecDate(String recDate){
		this.recDate=recDate;
		return this;
	}

	public String getMaterialPO(){
		return materialPO;
	}

	public Oldpo setMaterialPO(String materialPO){
		this.materialPO=materialPO;
		return this;
	}

}
