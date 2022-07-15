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
public class ExcelInventory implements ActiveEntity<ExcelInventory> {
	public static final String ID = "ID";

	public static final String LINENO = "LineNO";

	public static final String MODEL = "Model";

	public static final String SN = "SN";

	public static final String OLDSN = "oldSN";

	public static final String TRANSDUCER = "Transducer";

	public static final String SENSORORINGBATCHNO = "SensorOringBatchNO";

	public static final String MEMCHIP = "MEMChip";

	public static final String SROCHANNEL = "SROChannel";

	public static final String BATCHNO = "BatchNO";

	public static final String BOARDVER = "BoardVer";

	public static final String FIRMWARE = "Firmware";

	public static final String SMARTWATCHERSN = "SmartWatcherSN";

	public static final String PCBWORKINGTIME = "PCBWorkingTime";

	public static final String HOUSINGVERSION = "HousingVersion";

	public static final String OD = "OD";

	public static final String PRESS = "Press";

	public static final String TEM = "Tem";

	public static final String ASSEMBLYDATE = "AssemblyDate";

	public static final String CURRENTCALIDATE = "CurrentCaliDate";

	public static final String CURRENT = "`Current`";

	public static final String RECIN = "RecIn";

	public static final String SENTOUT = "SentOut";

	public static final String NEWOLD = "NewOld";

	public static final String DISPLAY = "Display";

	public static final String WIRELESSMODLE = "WirelessModle";

	public static final String WIRELESSBOARD = "WirelessBoard";

	public static final String SDBOARD = "SDBoard";

	public static final String BLUETOOTHFIRMVER = "BluetoothFirmVer";

	public static final String BLUETOOTHNO = "BluetoothNo";

	public static final String KEYPADVER = "KeypadVer";

	public static final String MAINBOARDVER = "MainBoardVer";

	public static final String BATTERYPACK = "BatteryPack";

	public static final String ENCLOSUREVERSION = "EnclosureVersion";

	public static final String TROUBLESHOOTING = "TroubleShooting";

	public static final String RECALIBRATIONSHLEF = "RecalibrationShlef";

	public static final String SENSORTOREPAIR = "SensorToRepair";

	public static final String SHIPMENTNEWLOGGER = "ShipmentNewLogger";

	public static final String OUTMONTH = "OutMonth";

	public static final String CUSTOMER = "Customer";

	public static final String CATEGORY = "Category";

	public static final String CAVR = "CAVR";

	public static final String NOTE = "NOTE";

	@Id
	private Integer id;


	private Integer lineNO;

	@Column(name="Model", length=30)
	private String model;

	@Column(name="SN", length=20)
	private String sn;

	@Column(name="oldSN", length=10)
	private String oldSN;

	@Column(name="Transducer", length=32)
	private String transducer;

	@Column(name="SensorOringBatchNO", length=20)
	private String sensorOringBatchNO;

	@Column(name="MEMChip", length=2)
	private String mEMChip;

	@Column(name="SROChannel", length=10)
	private String sROChannel;

	@Column(name="BatchNO", length=18)
	private String batchNO;

	@Column(name="BoardVer", length=22)
	private String boardVer;

	@Column(name="Firmware", length=24)
	private String firmware;

	@Column(name="SmartWatcherSN", length=20)
	private String smartWatcherSN;

	@Column(name="PCBWorkingTime", length=10)
	private String pCBWorkingTime;

	@Column(name="HousingVersion", length=94)
	private String housingVersion;

	@Column(name="OD", length=10)
	private String od;

	@Column(name="Press", length=12)
	private String press;

	@Column(name="Tem", length=8)
	private String tem;

	@Column(name="AssemblyDate", length=14)
	private String assemblyDate;

	@Column(name="CurrentCaliDate", length=14)
	private String currentCaliDate;

	@Column(name="`Current`", length=12)
	private String current;

	@Column(name="RecIn", length=1)
	private String recIn;

	@Column(name="SentOut", length=1)
	private String sentOut;

	@Column(name="NewOld", length=3)
	private String newOld;

	@Column(name="Display", length=24)
	private String display;

	@Column(name="WirelessModle", length=20)
	private String wirelessModle;

	@Column(name="WirelessBoard", length=18)
	private String wirelessBoard;

	@Column(name="SDBoard", length=4)
	private String sDBoard;

	@Column(name="BluetoothFirmVer", length=26)
	private String bluetoothFirmVer;

	@Column(name="BluetoothNo", length=12)
	private String bluetoothNo;

	@Column(name="KeypadVer", length=14)
	private String keypadVer;

	@Column(name="MainBoardVer", length=24)
	private String mainBoardVer;

	@Column(name="BatteryPack", length=26)
	private String batteryPack;

	@Column(name="EnclosureVersion", length=12)
	private String enclosureVersion;

	@Column(name="TroubleShooting", length=1)
	private String troubleShooting;

	@Column(name="RecalibrationShlef", length=1)
	private String recalibrationShlef;

	@Column(name="SensorToRepair", length=1)
	private String sensorToRepair;

	@Column(name="ShipmentNewLogger", length=1)
	private String shipmentNewLogger;

	@Column(name="OutMonth", length=12)
	private String outMonth;

	@Column(name="Customer", length=100)
	private String customer;

	@Column(name="Category", length=14)
	private String category;

	@Column(name="CAVR", length=8)
	private String cavr;

	@Column(name="NOTE", length=500)
	private String note;


	public Integer getId(){
		return id;
	}

	public ExcelInventory setId(Integer id){
		this.id=id;
		return this;
	}

	public Integer getLineNO(){
		return lineNO;
	}

	public ExcelInventory setLineNO(Integer lineNO){
		this.lineNO=lineNO;
		return this;
	}

	public String getModel(){
		return model;
	}

	public ExcelInventory setModel(String model){
		this.model=model;
		return this;
	}

	public String getSn(){
		return sn;
	}

	public ExcelInventory setSn(String sn){
		this.sn=sn;
		return this;
	}

	public String getOldSN(){
		return oldSN;
	}

	public ExcelInventory setOldSN(String oldSN){
		this.oldSN=oldSN;
		return this;
	}

	public String getTransducer(){
		return transducer;
	}

	public ExcelInventory setTransducer(String transducer){
		this.transducer=transducer;
		return this;
	}

	public String getSensorOringBatchNO(){
		return sensorOringBatchNO;
	}

	public ExcelInventory setSensorOringBatchNO(String sensorOringBatchNO){
		this.sensorOringBatchNO=sensorOringBatchNO;
		return this;
	}

	public String getMEMChip(){
		return mEMChip;
	}

	public ExcelInventory setMEMChip(String mEMChip){
		this.mEMChip=mEMChip;
		return this;
	}

	public String getSROChannel(){
		return sROChannel;
	}

	public ExcelInventory setSROChannel(String sROChannel){
		this.sROChannel=sROChannel;
		return this;
	}

	public String getBatchNO(){
		return batchNO;
	}

	public ExcelInventory setBatchNO(String batchNO){
		this.batchNO=batchNO;
		return this;
	}

	public String getBoardVer(){
		return boardVer;
	}

	public ExcelInventory setBoardVer(String boardVer){
		this.boardVer=boardVer;
		return this;
	}

	public String getFirmware(){
		return firmware;
	}

	public ExcelInventory setFirmware(String firmware){
		this.firmware=firmware;
		return this;
	}

	public String getSmartWatcherSN(){
		return smartWatcherSN;
	}

	public ExcelInventory setSmartWatcherSN(String smartWatcherSN){
		this.smartWatcherSN=smartWatcherSN;
		return this;
	}

	public String getPCBWorkingTime(){
		return pCBWorkingTime;
	}

	public ExcelInventory setPCBWorkingTime(String pCBWorkingTime){
		this.pCBWorkingTime=pCBWorkingTime;
		return this;
	}

	public String getHousingVersion(){
		return housingVersion;
	}

	public ExcelInventory setHousingVersion(String housingVersion){
		this.housingVersion=housingVersion;
		return this;
	}

	public String getOd(){
		return od;
	}

	public ExcelInventory setOd(String od){
		this.od=od;
		return this;
	}

	public String getPress(){
		return press;
	}

	public ExcelInventory setPress(String press){
		this.press=press;
		return this;
	}

	public String getTem(){
		return tem;
	}

	public ExcelInventory setTem(String tem){
		this.tem=tem;
		return this;
	}

	public String getAssemblyDate(){
		return assemblyDate;
	}

	public ExcelInventory setAssemblyDate(String assemblyDate){
		this.assemblyDate=assemblyDate;
		return this;
	}

	public String getCurrentCaliDate(){
		return currentCaliDate;
	}

	public ExcelInventory setCurrentCaliDate(String currentCaliDate){
		this.currentCaliDate=currentCaliDate;
		return this;
	}

	public String getCurrent(){
		return current;
	}

	public ExcelInventory setCurrent(String current){
		this.current=current;
		return this;
	}

	public String getRecIn(){
		return recIn;
	}

	public ExcelInventory setRecIn(String recIn){
		this.recIn=recIn;
		return this;
	}

	public String getSentOut(){
		return sentOut;
	}

	public ExcelInventory setSentOut(String sentOut){
		this.sentOut=sentOut;
		return this;
	}

	public String getNewOld(){
		return newOld;
	}

	public ExcelInventory setNewOld(String newOld){
		this.newOld=newOld;
		return this;
	}

	public String getDisplay(){
		return display;
	}

	public ExcelInventory setDisplay(String display){
		this.display=display;
		return this;
	}

	public String getWirelessModle(){
		return wirelessModle;
	}

	public ExcelInventory setWirelessModle(String wirelessModle){
		this.wirelessModle=wirelessModle;
		return this;
	}

	public String getWirelessBoard(){
		return wirelessBoard;
	}

	public ExcelInventory setWirelessBoard(String wirelessBoard){
		this.wirelessBoard=wirelessBoard;
		return this;
	}

	public String getSDBoard(){
		return sDBoard;
	}

	public ExcelInventory setSDBoard(String sDBoard){
		this.sDBoard=sDBoard;
		return this;
	}

	public String getBluetoothFirmVer(){
		return bluetoothFirmVer;
	}

	public ExcelInventory setBluetoothFirmVer(String bluetoothFirmVer){
		this.bluetoothFirmVer=bluetoothFirmVer;
		return this;
	}

	public String getBluetoothNo(){
		return bluetoothNo;
	}

	public ExcelInventory setBluetoothNo(String bluetoothNo){
		this.bluetoothNo=bluetoothNo;
		return this;
	}

	public String getKeypadVer(){
		return keypadVer;
	}

	public ExcelInventory setKeypadVer(String keypadVer){
		this.keypadVer=keypadVer;
		return this;
	}

	public String getMainBoardVer(){
		return mainBoardVer;
	}

	public ExcelInventory setMainBoardVer(String mainBoardVer){
		this.mainBoardVer=mainBoardVer;
		return this;
	}

	public String getBatteryPack(){
		return batteryPack;
	}

	public ExcelInventory setBatteryPack(String batteryPack){
		this.batteryPack=batteryPack;
		return this;
	}

	public String getEnclosureVersion(){
		return enclosureVersion;
	}

	public ExcelInventory setEnclosureVersion(String enclosureVersion){
		this.enclosureVersion=enclosureVersion;
		return this;
	}

	public String getTroubleShooting(){
		return troubleShooting;
	}

	public ExcelInventory setTroubleShooting(String troubleShooting){
		this.troubleShooting=troubleShooting;
		return this;
	}

	public String getRecalibrationShlef(){
		return recalibrationShlef;
	}

	public ExcelInventory setRecalibrationShlef(String recalibrationShlef){
		this.recalibrationShlef=recalibrationShlef;
		return this;
	}

	public String getSensorToRepair(){
		return sensorToRepair;
	}

	public ExcelInventory setSensorToRepair(String sensorToRepair){
		this.sensorToRepair=sensorToRepair;
		return this;
	}

	public String getShipmentNewLogger(){
		return shipmentNewLogger;
	}

	public ExcelInventory setShipmentNewLogger(String shipmentNewLogger){
		this.shipmentNewLogger=shipmentNewLogger;
		return this;
	}

	public String getOutMonth(){
		return outMonth;
	}

	public ExcelInventory setOutMonth(String outMonth){
		this.outMonth=outMonth;
		return this;
	}

	public String getCustomer(){
		return customer;
	}

	public ExcelInventory setCustomer(String customer){
		this.customer=customer;
		return this;
	}

	public String getCategory(){
		return category;
	}

	public ExcelInventory setCategory(String category){
		this.category=category;
		return this;
	}

	public String getCavr(){
		return cavr;
	}

	public ExcelInventory setCavr(String cavr){
		this.cavr=cavr;
		return this;
	}

	public String getNote(){
		return note;
	}

	public ExcelInventory setNote(String note){
		this.note=note;
		return this;
	}

}
