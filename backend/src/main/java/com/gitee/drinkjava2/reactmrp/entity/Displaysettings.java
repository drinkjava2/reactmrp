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
public class Displaysettings implements ActiveEntity<Displaysettings> {
	public static final String ID = "ID";

	public static final String USERNAME = "username";

	public static final String FORM = "form";

	public static final String CHECKBOXES = "checkboxes";

	@Id
	private Integer id;


	@Column(name="username", length=20)
	private String username;

	@Column(name="form", length=20)
	private String form;

	@Column(name="checkboxes", length=500)
	private String checkboxes;


	public Integer getId(){
		return id;
	}

	public Displaysettings setId(Integer id){
		this.id=id;
		return this;
	}

	public String getUsername(){
		return username;
	}

	public Displaysettings setUsername(String username){
		this.username=username;
		return this;
	}

	public String getForm(){
		return form;
	}

	public Displaysettings setForm(String form){
		this.form=form;
		return this;
	}

	public String getCheckboxes(){
		return checkboxes;
	}

	public Displaysettings setCheckboxes(String checkboxes){
		this.checkboxes=checkboxes;
		return this;
	}

}
