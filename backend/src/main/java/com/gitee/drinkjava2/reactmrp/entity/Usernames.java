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
public class Usernames implements ActiveEntity<Usernames> {
	public static final String USERNAME = "username";

	public static final String PASSWORD = "password";

	public static final String POPREFIX = "POPrefix";

	@Id
	@Column(name="username", length=20)
	private String username;


	@Column(name="password", length=15)
	private String password;

	@Column(name="POPrefix", length=2)
	private String pOPrefix;


	public String getUsername(){
		return username;
	}

	public Usernames setUsername(String username){
		this.username=username;
		return this;
	}

	public String getPassword(){
		return password;
	}

	public Usernames setPassword(String password){
		this.password=password;
		return this;
	}

	public String getPOPrefix(){
		return pOPrefix;
	}

	public Usernames setPOPrefix(String pOPrefix){
		this.pOPrefix=pOPrefix;
		return this;
	}

}
