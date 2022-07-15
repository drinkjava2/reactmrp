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
public class Partreceive implements ActiveEntity<Partreceive> {
	public static final String ID = "ID";

	public static final String VENDERID = "VenderID";

	public static final String PICKINGSLIP = "PickingSlip";

	public static final String PARTID = "PartID";

	public static final String RECEIVEQTY = "ReceiveQTY";

	public static final String DATE = "`Date`";

	public static final String NOTES = "Notes";

	@Id
	private Integer id;


	@Column(name="VenderID", length=30)
	@SingleFKey(name="partreceive_ibfk_1", refs={"vender","VenderID"})
	private String venderID;

	@Column(name="PickingSlip", length=30)
	private String pickingSlip;

	@Column(name="PartID", length=30)
	@SingleFKey(name="partreceive_ibfk_2", refs={"part","PartID"})
	private String partID;

	private Integer receiveQTY;

	@Column(name="`Date`")
	private Date date;

	@Column(name="Notes", length=60)
	private String notes;


	public Integer getId(){
		return id;
	}

	public Partreceive setId(Integer id){
		this.id=id;
		return this;
	}

	public String getVenderID(){
		return venderID;
	}

	public Partreceive setVenderID(String venderID){
		this.venderID=venderID;
		return this;
	}

	public String getPickingSlip(){
		return pickingSlip;
	}

	public Partreceive setPickingSlip(String pickingSlip){
		this.pickingSlip=pickingSlip;
		return this;
	}

	public String getPartID(){
		return partID;
	}

	public Partreceive setPartID(String partID){
		this.partID=partID;
		return this;
	}

	public Integer getReceiveQTY(){
		return receiveQTY;
	}

	public Partreceive setReceiveQTY(Integer receiveQTY){
		this.receiveQTY=receiveQTY;
		return this;
	}

	public Date getDate(){
		return date;
	}

	public Partreceive setDate(Date date){
		this.date=date;
		return this;
	}

	public String getNotes(){
		return notes;
	}

	public Partreceive setNotes(String notes){
		this.notes=notes;
		return this;
	}

}
