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
public class Partpicking implements ActiveEntity<Partpicking> {
	public static final String ID = "ID";

	public static final String PICKINGSLIP = "PickingSlip";

	public static final String PARTRELEASEBASEDONWO = "PartReleasebasedonWO";

	public static final String WO = "WO";

	public static final String PARTID = "PartID";

	public static final String PICKQTY = "PickQTY";

	public static final String DATE = "`Date`";

	public static final String PICKEDBY = "PickedBy";

	public static final String NOTES = "Notes";

	public static final String PICKINGTYPE = "PickingType";

	@Id
	private Integer id;


	@Column(name="PickingSlip", length=30)
	private String pickingSlip;

	@Column(name="PartReleasebasedonWO", length=1)
	private String partReleasebasedonWO;

	@Column(name="WO", length=30)
	private String wo;

	@Column(name="PartID", length=30)
	@SingleFKey(name="partpicking_ibfk_1", refs={"part","PartID"})
	private String partID;

	private Integer pickQTY;

	@Column(name="`Date`")
	private Date date;

	@Column(name="PickedBy", length=30)
	private String pickedBy;

	@Column(name="Notes", length=60)
	private String notes;

	@Column(name="PickingType", length=8)
	private String pickingType;


	public Integer getId(){
		return id;
	}

	public Partpicking setId(Integer id){
		this.id=id;
		return this;
	}

	public String getPickingSlip(){
		return pickingSlip;
	}

	public Partpicking setPickingSlip(String pickingSlip){
		this.pickingSlip=pickingSlip;
		return this;
	}

	public String getPartReleasebasedonWO(){
		return partReleasebasedonWO;
	}

	public Partpicking setPartReleasebasedonWO(String partReleasebasedonWO){
		this.partReleasebasedonWO=partReleasebasedonWO;
		return this;
	}

	public String getWo(){
		return wo;
	}

	public Partpicking setWo(String wo){
		this.wo=wo;
		return this;
	}

	public String getPartID(){
		return partID;
	}

	public Partpicking setPartID(String partID){
		this.partID=partID;
		return this;
	}

	public Integer getPickQTY(){
		return pickQTY;
	}

	public Partpicking setPickQTY(Integer pickQTY){
		this.pickQTY=pickQTY;
		return this;
	}

	public Date getDate(){
		return date;
	}

	public Partpicking setDate(Date date){
		this.date=date;
		return this;
	}

	public String getPickedBy(){
		return pickedBy;
	}

	public Partpicking setPickedBy(String pickedBy){
		this.pickedBy=pickedBy;
		return this;
	}

	public String getNotes(){
		return notes;
	}

	public Partpicking setNotes(String notes){
		this.notes=notes;
		return this;
	}

	public String getPickingType(){
		return pickingType;
	}

	public Partpicking setPickingType(String pickingType){
		this.pickingType=pickingType;
		return this;
	}

}
