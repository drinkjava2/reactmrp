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
public class Project implements ActiveEntity<Project> {
	public static final String PROJECT = "Project";

	public static final String NOTES = "Notes";

	@Id
	@Column(name="Project", length=30)
	private String project;


	@Column(name="Notes", length=60)
	private String notes;


	public String getProject(){
		return project;
	}

	public Project setProject(String project){
		this.project=project;
		return this;
	}

	public String getNotes(){
		return notes;
	}

	public Project setNotes(String notes){
		this.notes=notes;
		return this;
	}

}
