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
public class Po implements ActiveEntity<Po> {
	public static final String PO = "PO";

	public static final String CREATOR = "Creator";

	public static final String VENDERID = "VenderID";

	public static final String PODATE = "PODate";

	public static final String NOTES = "Notes";

	public static final String PROJECT = "project";

	@Id
	@Column(name="PO", length=30)
	private String po;


	@Column(name="Creator", length=30)
	private String creator;

	@Column(name="VenderID", length=30)
	@SingleFKey(name="po_ibfk_1", refs={"vender","VenderID"})
	private String venderID;

	private Date pODate;

	@Column(name="Notes", length=60)
	private String notes;

	@Column(name="project", length=60)
	private String project;


	public String getPo(){
		return po;
	}

	public Po setPo(String po){
		this.po=po;
		return this;
	}

	public String getCreator(){
		return creator;
	}

	public Po setCreator(String creator){
		this.creator=creator;
		return this;
	}

	public String getVenderID(){
		return venderID;
	}

	public Po setVenderID(String venderID){
		this.venderID=venderID;
		return this;
	}

	public Date getPODate(){
		return pODate;
	}

	public Po setPODate(Date pODate){
		this.pODate=pODate;
		return this;
	}

	public String getNotes(){
		return notes;
	}

	public Po setNotes(String notes){
		this.notes=notes;
		return this;
	}

	public String getProject(){
		return project;
	}

	public Po setProject(String project){
		this.project=project;
		return this;
	}

}
