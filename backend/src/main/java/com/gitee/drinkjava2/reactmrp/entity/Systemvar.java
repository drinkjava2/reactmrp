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
public class Systemvar implements ActiveEntity<Systemvar> {
	public static final String VERSION = "Version";

	public static final String TIP = "tip";

	public static final String CALLCOMMAND = "CallCommand";

	public static final String UPDATEFORUSER = "UpdateForUser";

	public static final String ALLOWEDOLDVERSIONS = "AllowedOldVersions";


	@Column(name="Version", length=10)
	private String version;

	@Column(name="tip", length=600)
	private String tip;

	@Column(name="CallCommand", length=300)
	private String callCommand;

	@Column(name="UpdateForUser", length=240)
	private String updateForUser;

	@Column(name="AllowedOldVersions", length=20)
	private String allowedOldVersions;


	public String getVersion(){
		return version;
	}

	public Systemvar setVersion(String version){
		this.version=version;
		return this;
	}

	public String getTip(){
		return tip;
	}

	public Systemvar setTip(String tip){
		this.tip=tip;
		return this;
	}

	public String getCallCommand(){
		return callCommand;
	}

	public Systemvar setCallCommand(String callCommand){
		this.callCommand=callCommand;
		return this;
	}

	public String getUpdateForUser(){
		return updateForUser;
	}

	public Systemvar setUpdateForUser(String updateForUser){
		this.updateForUser=updateForUser;
		return this;
	}

	public String getAllowedOldVersions(){
		return allowedOldVersions;
	}

	public Systemvar setAllowedOldVersions(String allowedOldVersions){
		this.allowedOldVersions=allowedOldVersions;
		return this;
	}

}
