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
public class Powergroups implements ActiveEntity<Powergroups> {
	public static final String POWERGROUP = "PowerGroup";

	public static final String USERNAMES = "usernames";

	@Id
	@Column(name="PowerGroup", length=40)
	private String powerGroup;


	@Column(name="usernames", length=300)
	private String usernames;


	public String getPowerGroup(){
		return powerGroup;
	}

	public Powergroups setPowerGroup(String powerGroup){
		this.powerGroup=powerGroup;
		return this;
	}

	public String getUsernames(){
		return usernames;
	}

	public Powergroups setUsernames(String usernames){
		this.usernames=usernames;
		return this;
	}

}
