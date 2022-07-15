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
public class Podetail implements ActiveEntity<Podetail> {
	public static final String ID = "ID";

	public static final String PO = "PO";

	public static final String ITEM = "Item";

	public static final String PRNUMBER = "PRNumber";

	public static final String REQUIREDBY = "Requiredby";

	public static final String PARTID = "PartID";

	public static final String PARTDESCRIPTION = "PartDescription";

	public static final String MANUFACTURERPN = "ManufacturerPN";

	public static final String POQTY = "POQty";

	public static final String DEADLINE = "Deadline";

	public static final String DAYSBEFOREDEADLINE = "DaysbeforeDeadline";

	public static final String EXPECTEDDELIVERYDATE = "Expecteddeliverydate";

	public static final String FOLLOWUP = "Followup";

	public static final String RECEIVED = "Received";

	public static final String PENDING = "Pending";

	public static final String BACKORDER = "BackOrder";

	public static final String FINISHDATE = "FinishDate";

	public static final String REALLEADWEEKS = "RealLeadWeeks";

	public static final String PRICE = "Price";

	public static final String PRICEOFLASTPO = "PriceofLastPO";

	public static final String VENDERPN = "VenderPN";

	public static final String NOTES = "Notes";

	public static final String CURRENCY = "Currency";

	@Id
	private Integer id;


	@Column(name="PO", length=30)
	@SingleFKey(name="podetail_ibfk_1", refs={"po","PO"})
	private String po;

	private Integer item;

	@Column(name="PRNumber", length=30)
	private String pRNumber;

	@Column(name="Requiredby", length=30)
	private String requiredby;

	@Column(name="PartID", length=30)
	@SingleFKey(name="podetail_ibfk_2", refs={"part","PartID"})
	private String partID;

	@Column(name="PartDescription", length=200)
	private String partDescription;

	@Column(name="ManufacturerPN", length=30)
	private String manufacturerPN;

	private Integer pOQty;

	private Date deadline;

	private Integer daysbeforeDeadline;

	private Date expecteddeliverydate;

	@Column(name="Followup", length=1)
	private String followup;

	private Integer received;

	@Column(name="Pending", length=1)
	private String pending;

	private Integer backOrder;

	private Date finishDate;

	private Integer realLeadWeeks;

	private Double price;

	private Double priceofLastPO;

	@Column(name="VenderPN", length=30)
	private String venderPN;

	@Column(name="Notes", length=60)
	private String notes;

	@Column(name="Currency", length=6)
	private String currency;


	public Integer getId(){
		return id;
	}

	public Podetail setId(Integer id){
		this.id=id;
		return this;
	}

	public String getPo(){
		return po;
	}

	public Podetail setPo(String po){
		this.po=po;
		return this;
	}

	public Integer getItem(){
		return item;
	}

	public Podetail setItem(Integer item){
		this.item=item;
		return this;
	}

	public String getPRNumber(){
		return pRNumber;
	}

	public Podetail setPRNumber(String pRNumber){
		this.pRNumber=pRNumber;
		return this;
	}

	public String getRequiredby(){
		return requiredby;
	}

	public Podetail setRequiredby(String requiredby){
		this.requiredby=requiredby;
		return this;
	}

	public String getPartID(){
		return partID;
	}

	public Podetail setPartID(String partID){
		this.partID=partID;
		return this;
	}

	public String getPartDescription(){
		return partDescription;
	}

	public Podetail setPartDescription(String partDescription){
		this.partDescription=partDescription;
		return this;
	}

	public String getManufacturerPN(){
		return manufacturerPN;
	}

	public Podetail setManufacturerPN(String manufacturerPN){
		this.manufacturerPN=manufacturerPN;
		return this;
	}

	public Integer getPOQty(){
		return pOQty;
	}

	public Podetail setPOQty(Integer pOQty){
		this.pOQty=pOQty;
		return this;
	}

	public Date getDeadline(){
		return deadline;
	}

	public Podetail setDeadline(Date deadline){
		this.deadline=deadline;
		return this;
	}

	public Integer getDaysbeforeDeadline(){
		return daysbeforeDeadline;
	}

	public Podetail setDaysbeforeDeadline(Integer daysbeforeDeadline){
		this.daysbeforeDeadline=daysbeforeDeadline;
		return this;
	}

	public Date getExpecteddeliverydate(){
		return expecteddeliverydate;
	}

	public Podetail setExpecteddeliverydate(Date expecteddeliverydate){
		this.expecteddeliverydate=expecteddeliverydate;
		return this;
	}

	public String getFollowup(){
		return followup;
	}

	public Podetail setFollowup(String followup){
		this.followup=followup;
		return this;
	}

	public Integer getReceived(){
		return received;
	}

	public Podetail setReceived(Integer received){
		this.received=received;
		return this;
	}

	public String getPending(){
		return pending;
	}

	public Podetail setPending(String pending){
		this.pending=pending;
		return this;
	}

	public Integer getBackOrder(){
		return backOrder;
	}

	public Podetail setBackOrder(Integer backOrder){
		this.backOrder=backOrder;
		return this;
	}

	public Date getFinishDate(){
		return finishDate;
	}

	public Podetail setFinishDate(Date finishDate){
		this.finishDate=finishDate;
		return this;
	}

	public Integer getRealLeadWeeks(){
		return realLeadWeeks;
	}

	public Podetail setRealLeadWeeks(Integer realLeadWeeks){
		this.realLeadWeeks=realLeadWeeks;
		return this;
	}

	public Double getPrice(){
		return price;
	}

	public Podetail setPrice(Double price){
		this.price=price;
		return this;
	}

	public Double getPriceofLastPO(){
		return priceofLastPO;
	}

	public Podetail setPriceofLastPO(Double priceofLastPO){
		this.priceofLastPO=priceofLastPO;
		return this;
	}

	public String getVenderPN(){
		return venderPN;
	}

	public Podetail setVenderPN(String venderPN){
		this.venderPN=venderPN;
		return this;
	}

	public String getNotes(){
		return notes;
	}

	public Podetail setNotes(String notes){
		this.notes=notes;
		return this;
	}

	public String getCurrency(){
		return currency;
	}

	public Podetail setCurrency(String currency){
		this.currency=currency;
		return this;
	}

}
