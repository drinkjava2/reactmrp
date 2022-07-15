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
public class Customer implements ActiveEntity<Customer> {
	public static final String CUSTOMERID = "CustomerID";

	public static final String CUSTOMERNAME = "CustomerName";

	public static final String ORDERMETHOD = "OrderMethod";

	public static final String PAYMENTMETHOD = "Paymentmethod";

	public static final String OURACCOUNTNUMBER = "OurAccountNumber";

	public static final String PASSWORD = "Password";

	public static final String CONTACT1 = "Contact1";

	public static final String PHONE1 = "Phone1";

	public static final String EMAIL1 = "Email1";

	public static final String CONTACT2 = "Contact2";

	public static final String PHONE2 = "Phone2";

	public static final String EMAIL2 = "Email2";

	public static final String ADDRESS = "Address";

	public static final String WEBSITE = "Website";

	public static final String FAX = "Fax";

	public static final String DESCRIPTION = "Description";

	public static final String NOTE = "Note";

	@Id
	@Column(name="CustomerID", length=30)
	private String customerID;


	@Column(name="CustomerName", length=50)
	private String customerName;

	@Column(name="OrderMethod", length=30)
	private String orderMethod;

	@Column(name="Paymentmethod", length=30)
	private String paymentmethod;

	@Column(name="OurAccountNumber", length=30)
	private String ourAccountNumber;

	@Column(name="Password", length=30)
	private String password;

	@Column(name="Contact1", length=30)
	private String contact1;

	@Column(name="Phone1", length=30)
	private String phone1;

	@Column(name="Email1", length=30)
	private String email1;

	@Column(name="Contact2", length=30)
	private String contact2;

	@Column(name="Phone2", length=30)
	private String phone2;

	@Column(name="Email2", length=30)
	private String email2;

	@Column(name="Address", length=80)
	private String address;

	@Column(name="Website", length=30)
	private String website;

	@Column(name="Fax", length=30)
	private String fax;

	@Column(name="Description", length=120)
	private String description;

	@Column(name="Note", length=50)
	private String note;


	public String getCustomerID(){
		return customerID;
	}

	public Customer setCustomerID(String customerID){
		this.customerID=customerID;
		return this;
	}

	public String getCustomerName(){
		return customerName;
	}

	public Customer setCustomerName(String customerName){
		this.customerName=customerName;
		return this;
	}

	public String getOrderMethod(){
		return orderMethod;
	}

	public Customer setOrderMethod(String orderMethod){
		this.orderMethod=orderMethod;
		return this;
	}

	public String getPaymentmethod(){
		return paymentmethod;
	}

	public Customer setPaymentmethod(String paymentmethod){
		this.paymentmethod=paymentmethod;
		return this;
	}

	public String getOurAccountNumber(){
		return ourAccountNumber;
	}

	public Customer setOurAccountNumber(String ourAccountNumber){
		this.ourAccountNumber=ourAccountNumber;
		return this;
	}

	public String getPassword(){
		return password;
	}

	public Customer setPassword(String password){
		this.password=password;
		return this;
	}

	public String getContact1(){
		return contact1;
	}

	public Customer setContact1(String contact1){
		this.contact1=contact1;
		return this;
	}

	public String getPhone1(){
		return phone1;
	}

	public Customer setPhone1(String phone1){
		this.phone1=phone1;
		return this;
	}

	public String getEmail1(){
		return email1;
	}

	public Customer setEmail1(String email1){
		this.email1=email1;
		return this;
	}

	public String getContact2(){
		return contact2;
	}

	public Customer setContact2(String contact2){
		this.contact2=contact2;
		return this;
	}

	public String getPhone2(){
		return phone2;
	}

	public Customer setPhone2(String phone2){
		this.phone2=phone2;
		return this;
	}

	public String getEmail2(){
		return email2;
	}

	public Customer setEmail2(String email2){
		this.email2=email2;
		return this;
	}

	public String getAddress(){
		return address;
	}

	public Customer setAddress(String address){
		this.address=address;
		return this;
	}

	public String getWebsite(){
		return website;
	}

	public Customer setWebsite(String website){
		this.website=website;
		return this;
	}

	public String getFax(){
		return fax;
	}

	public Customer setFax(String fax){
		this.fax=fax;
		return this;
	}

	public String getDescription(){
		return description;
	}

	public Customer setDescription(String description){
		this.description=description;
		return this;
	}

	public String getNote(){
		return note;
	}

	public Customer setNote(String note){
		this.note=note;
		return this;
	}

}
