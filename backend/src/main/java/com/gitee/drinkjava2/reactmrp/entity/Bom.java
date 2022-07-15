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
public class Bom implements ActiveEntity<Bom> {
	public static final String ID = "ID";

	public static final String PRODUCTID = "ProductID";

	public static final String PARTID = "PartID";

	public static final String CATEGORY = "Category";

	public static final String OPTIONAL = "Optional";

	public static final String BOMQTY = "BOMQty";

	public static final String NOTES = "notes";

	public static final String ITEMNO = "ItemNO";

	public static final String VERSION1 = "Version1";

	public static final String VERSION2 = "Version2";

	public static final String VERSION3 = "Version3";

	public static final String ACC = "Acc";

	public static final String FATHERPARTID = "FatherPartID";

	public static final String BOM = "BOM";

	public static final String REPLACEPARTS = "ReplaceParts";

	@Id
	private Integer id;


	@Column(name="ProductID", length=46)
	private String productID;

	@Column(name="PartID", length=100)
	@SingleFKey(name="bom_ibfk_2", refs={"part","PartID"})
	private String partID;

	@Column(name="Category", length=10)
	private String category;

	@Column(name="Optional", length=1)
	private String optional;

	private Integer bOMQty;

	@Column(name="notes", length=200)
	private String notes;

	private Integer itemNO;

	@Column(name="Version1", length=30)
	@SingleFKey(name="bom_ibfk_3", refs={"part","PartID"})
	private String version1;

	@Column(name="Version2", length=30)
	@SingleFKey(name="bom_ibfk_4", refs={"part","PartID"})
	private String version2;

	@Column(name="Version3", length=30)
	@SingleFKey(name="bom_ibfk_5", refs={"part","PartID"})
	private String version3;

	@Column(name="Acc", length=1)
	private String acc;

	@Column(name="FatherPartID", length=40)
	@SingleFKey(name="bom_ibfk_6", refs={"part","PartID"})
	private String fatherPartID;

	@Column(name="BOM", length=5)
	private String bom;

	@Column(name="ReplaceParts", length=100)
	private String replaceParts;


	public Integer getId(){
		return id;
	}

	public Bom setId(Integer id){
		this.id=id;
		return this;
	}

	public String getProductID(){
		return productID;
	}

	public Bom setProductID(String productID){
		this.productID=productID;
		return this;
	}

	public String getPartID(){
		return partID;
	}

	public Bom setPartID(String partID){
		this.partID=partID;
		return this;
	}

	public String getCategory(){
		return category;
	}

	public Bom setCategory(String category){
		this.category=category;
		return this;
	}

	public String getOptional(){
		return optional;
	}

	public Bom setOptional(String optional){
		this.optional=optional;
		return this;
	}

	public Integer getBOMQty(){
		return bOMQty;
	}

	public Bom setBOMQty(Integer bOMQty){
		this.bOMQty=bOMQty;
		return this;
	}

	public String getNotes(){
		return notes;
	}

	public Bom setNotes(String notes){
		this.notes=notes;
		return this;
	}

	public Integer getItemNO(){
		return itemNO;
	}

	public Bom setItemNO(Integer itemNO){
		this.itemNO=itemNO;
		return this;
	}

	public String getVersion1(){
		return version1;
	}

	public Bom setVersion1(String version1){
		this.version1=version1;
		return this;
	}

	public String getVersion2(){
		return version2;
	}

	public Bom setVersion2(String version2){
		this.version2=version2;
		return this;
	}

	public String getVersion3(){
		return version3;
	}

	public Bom setVersion3(String version3){
		this.version3=version3;
		return this;
	}

	public String getAcc(){
		return acc;
	}

	public Bom setAcc(String acc){
		this.acc=acc;
		return this;
	}

	public String getFatherPartID(){
		return fatherPartID;
	}

	public Bom setFatherPartID(String fatherPartID){
		this.fatherPartID=fatherPartID;
		return this;
	}

	public String getBom(){
		return bom;
	}

	public Bom setBom(String bom){
		this.bom=bom;
		return this;
	}

	public String getReplaceParts(){
		return replaceParts;
	}

	public Bom setReplaceParts(String replaceParts){
		this.replaceParts=replaceParts;
		return this;
	}

}
