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
public class Loginlog implements ActiveEntity<Loginlog> {
	public static final String ID = "ID";

	public static final String USERNAME = "username";

	public static final String MAC = "mac";

	public static final String INOUTDATE = "inoutdate";

	public static final String COMPUTERNAME = "computername";

	public static final String INOUTTYPE = "inouttype";

	@Id
	private Integer id;


	@Column(name="username", length=20)
	private String username;

	@Column(name="mac", length=50)
	private String mac;

	private Date inoutdate;

	@Column(name="computername", length=50)
	private String computername;

	@Column(name="inouttype", length=50)
	private String inouttype;


	public Integer getId(){
		return id;
	}

	public Loginlog setId(Integer id){
		this.id=id;
		return this;
	}

	public String getUsername(){
		return username;
	}

	public Loginlog setUsername(String username){
		this.username=username;
		return this;
	}

	public String getMac(){
		return mac;
	}

	public Loginlog setMac(String mac){
		this.mac=mac;
		return this;
	}

	public Date getInoutdate(){
		return inoutdate;
	}

	public Loginlog setInoutdate(Date inoutdate){
		this.inoutdate=inoutdate;
		return this;
	}

	public String getComputername(){
		return computername;
	}

	public Loginlog setComputername(String computername){
		this.computername=computername;
		return this;
	}

	public String getInouttype(){
		return inouttype;
	}

	public Loginlog setInouttype(String inouttype){
		this.inouttype=inouttype;
		return this;
	}

}
