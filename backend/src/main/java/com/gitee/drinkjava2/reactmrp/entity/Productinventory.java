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
public class Productinventory implements ActiveEntity<Productinventory> {
	public static final String ID = "ID";

	public static final String WO = "WO";

	public static final String PACKINGSLIP = "PackingSlip";

	public static final String PRODUCTID = "ProductID";

	public static final String MODEL = "Model";

	public static final String SN = "SN";

	public static final String OLDSN = "oldSN";

	public static final String TRANSDUCER = "Transducer";

	public static final String MEMCHIP = "MEMChip";

	public static final String SROCHANNEL = "SROChannel";

	public static final String BATCHNO = "BatchNO";

	public static final String BOARDVER = "BoardVer";

	public static final String FIRMWARE = "Firmware";

	public static final String SMARTWATCHER_SN = "SmartWatcher_SN";

	public static final String PCBWORKING_TIME = "PCBWorking_time";

	public static final String HOUSING_VERSION = "Housing_Version";

	public static final String OD = "OD";

	public static final String PRESS = "Press";

	public static final String TEM = "Tem";

	public static final String ASSEMBLY_DATE = "Assembly_Date";

	public static final String CURRENT_CALIDATE = "Current_CaliDate";

	public static final String CURRENT = "`Current`";

	public static final String REC = "Rec";

	public static final String SENT = "Sent";

	public static final String NEW_OLD = "New_old";

	public static final String DISPLAY = "Display";

	public static final String WIRELESS_MODLE = "Wireless_Modle";

	public static final String WIRELESS_BOARD = "Wireless_Board";

	public static final String SD_BOARD = "SD_Board";

	public static final String BLUETOOTH_FIRMVER = "Bluetooth_FirmVer";

	public static final String BLUETOOTH_NO = "Bluetooth_No";

	public static final String KEYPADBOARD_VERSION = "KeypadBoard_version";

	public static final String MAINBOARD_ANDFIRMVER = "MainBoard_andFirmVer";

	public static final String BATTERY_PACK = "Battery_Pack";

	public static final String ENCLOSURE_VERSION = "Enclosure_Version";

	public static final String TROUBLE_SHOOTING = "Trouble_Shooting";

	public static final String RETURN_PRODUCTLINE = "Return_ProductLine";

	public static final String HOUSING_DAMAGE = "Housing_Damage";

	public static final String SENSOR_DAMAGE = "Sensor_Damage";

	public static final String BOARD_DAMAGE = "Board_Damage";

	public static final String RECALIBRATION_SHLEF = "Recalibration_Shlef";

	public static final String SENSOR_TOREPAIR = "Sensor_ToRepair";

	public static final String SHIPMENT_NEWLOGGER = "Shipment_Newlogger";

	public static final String OUT_MONTH = "Out_month";

	public static final String CUSTOMER = "Customer";

	public static final String CATEGORY = "Category";

	public static final String CA_VR = "CA_VR";

	public static final String NOTE = "NOTE";

	@Id
	private Integer id;


	@Column(name="WO", length=30)
	private String wo;

	@Column(name="PackingSlip", length=30)
	private String packingSlip;

	@Column(name="ProductID", length=46)
	@SingleFKey(name="productinventory_ibfk_1", refs={"product","ProductID"})
	private String productID;

	@Column(name="Model", length=30)
	private String model;

	@Column(name="SN", length=20)
	private String sn;

	@Column(name="oldSN", length=10)
	private String oldSN;

	@Column(name="Transducer", length=32)
	private String transducer;

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

	@Column(name="SmartWatcher_SN", length=20)
	private String smartwatcherSn;

	@Column(name="PCBWorking_time", length=10)
	private String pcbworkingTime;

	@Column(name="Housing_Version", length=94)
	private String housingVersion;

	@Column(name="OD", length=10)
	private String od;

	@Column(name="Press", length=12)
	private String press;

	@Column(name="Tem", length=8)
	private String tem;

	@Column(name="Assembly_Date", length=14)
	private String assemblyDate;

	@Column(name="Current_CaliDate", length=14)
	private String currentCalidate;

	@Column(name="`Current`", length=12)
	private String current;

	@Column(name="Rec", length=1)
	private String rec;

	@Column(name="Sent", length=1)
	private String sent;

	@Column(name="New_old", length=3)
	private String newOld;

	@Column(name="Display", length=24)
	private String display;

	@Column(name="Wireless_Modle", length=20)
	private String wirelessModle;

	@Column(name="Wireless_Board", length=18)
	private String wirelessBoard;

	@Column(name="SD_Board", length=4)
	private String sdBoard;

	@Column(name="Bluetooth_FirmVer", length=26)
	private String bluetoothFirmver;

	@Column(name="Bluetooth_No", length=12)
	private String bluetoothNo;

	@Column(name="KeypadBoard_version", length=14)
	private String keypadboardVersion;

	@Column(name="MainBoard_andFirmVer", length=24)
	private String mainboardAndfirmver;

	@Column(name="Battery_Pack", length=26)
	private String batteryPack;

	@Column(name="Enclosure_Version", length=12)
	private String enclosureVersion;

	@Column(name="Trouble_Shooting", length=1)
	private String troubleShooting;

	@Column(name="Return_ProductLine", length=1)
	private String returnProductline;

	@Column(name="Housing_Damage", length=1)
	private String housingDamage;

	@Column(name="Sensor_Damage", length=1)
	private String sensorDamage;

	@Column(name="Board_Damage", length=1)
	private String boardDamage;

	@Column(name="Recalibration_Shlef", length=1)
	private String recalibrationShlef;

	@Column(name="Sensor_ToRepair", length=1)
	private String sensorTorepair;

	@Column(name="Shipment_Newlogger", length=1)
	private String shipmentNewlogger;

	@Column(name="Out_month", length=12)
	private String outMonth;

	@Column(name="Customer", length=100)
	private String customer;

	@Column(name="Category", length=14)
	private String category;

	@Column(name="CA_VR", length=8)
	private String caVr;

	@Column(name="NOTE", length=300)
	private String note;


	public Integer getId(){
		return id;
	}

	public Productinventory setId(Integer id){
		this.id=id;
		return this;
	}

	public String getWo(){
		return wo;
	}

	public Productinventory setWo(String wo){
		this.wo=wo;
		return this;
	}

	public String getPackingSlip(){
		return packingSlip;
	}

	public Productinventory setPackingSlip(String packingSlip){
		this.packingSlip=packingSlip;
		return this;
	}

	public String getProductID(){
		return productID;
	}

	public Productinventory setProductID(String productID){
		this.productID=productID;
		return this;
	}

	public String getModel(){
		return model;
	}

	public Productinventory setModel(String model){
		this.model=model;
		return this;
	}

	public String getSn(){
		return sn;
	}

	public Productinventory setSn(String sn){
		this.sn=sn;
		return this;
	}

	public String getOldSN(){
		return oldSN;
	}

	public Productinventory setOldSN(String oldSN){
		this.oldSN=oldSN;
		return this;
	}

	public String getTransducer(){
		return transducer;
	}

	public Productinventory setTransducer(String transducer){
		this.transducer=transducer;
		return this;
	}

	public String getMEMChip(){
		return mEMChip;
	}

	public Productinventory setMEMChip(String mEMChip){
		this.mEMChip=mEMChip;
		return this;
	}

	public String getSROChannel(){
		return sROChannel;
	}

	public Productinventory setSROChannel(String sROChannel){
		this.sROChannel=sROChannel;
		return this;
	}

	public String getBatchNO(){
		return batchNO;
	}

	public Productinventory setBatchNO(String batchNO){
		this.batchNO=batchNO;
		return this;
	}

	public String getBoardVer(){
		return boardVer;
	}

	public Productinventory setBoardVer(String boardVer){
		this.boardVer=boardVer;
		return this;
	}

	public String getFirmware(){
		return firmware;
	}

	public Productinventory setFirmware(String firmware){
		this.firmware=firmware;
		return this;
	}

	public String getSmartwatcherSn(){
		return smartwatcherSn;
	}

	public Productinventory setSmartwatcherSn(String smartwatcherSn){
		this.smartwatcherSn=smartwatcherSn;
		return this;
	}

	public String getPcbworkingTime(){
		return pcbworkingTime;
	}

	public Productinventory setPcbworkingTime(String pcbworkingTime){
		this.pcbworkingTime=pcbworkingTime;
		return this;
	}

	public String getHousingVersion(){
		return housingVersion;
	}

	public Productinventory setHousingVersion(String housingVersion){
		this.housingVersion=housingVersion;
		return this;
	}

	public String getOd(){
		return od;
	}

	public Productinventory setOd(String od){
		this.od=od;
		return this;
	}

	public String getPress(){
		return press;
	}

	public Productinventory setPress(String press){
		this.press=press;
		return this;
	}

	public String getTem(){
		return tem;
	}

	public Productinventory setTem(String tem){
		this.tem=tem;
		return this;
	}

	public String getAssemblyDate(){
		return assemblyDate;
	}

	public Productinventory setAssemblyDate(String assemblyDate){
		this.assemblyDate=assemblyDate;
		return this;
	}

	public String getCurrentCalidate(){
		return currentCalidate;
	}

	public Productinventory setCurrentCalidate(String currentCalidate){
		this.currentCalidate=currentCalidate;
		return this;
	}

	public String getCurrent(){
		return current;
	}

	public Productinventory setCurrent(String current){
		this.current=current;
		return this;
	}

	public String getRec(){
		return rec;
	}

	public Productinventory setRec(String rec){
		this.rec=rec;
		return this;
	}

	public String getSent(){
		return sent;
	}

	public Productinventory setSent(String sent){
		this.sent=sent;
		return this;
	}

	public String getNewOld(){
		return newOld;
	}

	public Productinventory setNewOld(String newOld){
		this.newOld=newOld;
		return this;
	}

	public String getDisplay(){
		return display;
	}

	public Productinventory setDisplay(String display){
		this.display=display;
		return this;
	}

	public String getWirelessModle(){
		return wirelessModle;
	}

	public Productinventory setWirelessModle(String wirelessModle){
		this.wirelessModle=wirelessModle;
		return this;
	}

	public String getWirelessBoard(){
		return wirelessBoard;
	}

	public Productinventory setWirelessBoard(String wirelessBoard){
		this.wirelessBoard=wirelessBoard;
		return this;
	}

	public String getSdBoard(){
		return sdBoard;
	}

	public Productinventory setSdBoard(String sdBoard){
		this.sdBoard=sdBoard;
		return this;
	}

	public String getBluetoothFirmver(){
		return bluetoothFirmver;
	}

	public Productinventory setBluetoothFirmver(String bluetoothFirmver){
		this.bluetoothFirmver=bluetoothFirmver;
		return this;
	}

	public String getBluetoothNo(){
		return bluetoothNo;
	}

	public Productinventory setBluetoothNo(String bluetoothNo){
		this.bluetoothNo=bluetoothNo;
		return this;
	}

	public String getKeypadboardVersion(){
		return keypadboardVersion;
	}

	public Productinventory setKeypadboardVersion(String keypadboardVersion){
		this.keypadboardVersion=keypadboardVersion;
		return this;
	}

	public String getMainboardAndfirmver(){
		return mainboardAndfirmver;
	}

	public Productinventory setMainboardAndfirmver(String mainboardAndfirmver){
		this.mainboardAndfirmver=mainboardAndfirmver;
		return this;
	}

	public String getBatteryPack(){
		return batteryPack;
	}

	public Productinventory setBatteryPack(String batteryPack){
		this.batteryPack=batteryPack;
		return this;
	}

	public String getEnclosureVersion(){
		return enclosureVersion;
	}

	public Productinventory setEnclosureVersion(String enclosureVersion){
		this.enclosureVersion=enclosureVersion;
		return this;
	}

	public String getTroubleShooting(){
		return troubleShooting;
	}

	public Productinventory setTroubleShooting(String troubleShooting){
		this.troubleShooting=troubleShooting;
		return this;
	}

	public String getReturnProductline(){
		return returnProductline;
	}

	public Productinventory setReturnProductline(String returnProductline){
		this.returnProductline=returnProductline;
		return this;
	}

	public String getHousingDamage(){
		return housingDamage;
	}

	public Productinventory setHousingDamage(String housingDamage){
		this.housingDamage=housingDamage;
		return this;
	}

	public String getSensorDamage(){
		return sensorDamage;
	}

	public Productinventory setSensorDamage(String sensorDamage){
		this.sensorDamage=sensorDamage;
		return this;
	}

	public String getBoardDamage(){
		return boardDamage;
	}

	public Productinventory setBoardDamage(String boardDamage){
		this.boardDamage=boardDamage;
		return this;
	}

	public String getRecalibrationShlef(){
		return recalibrationShlef;
	}

	public Productinventory setRecalibrationShlef(String recalibrationShlef){
		this.recalibrationShlef=recalibrationShlef;
		return this;
	}

	public String getSensorTorepair(){
		return sensorTorepair;
	}

	public Productinventory setSensorTorepair(String sensorTorepair){
		this.sensorTorepair=sensorTorepair;
		return this;
	}

	public String getShipmentNewlogger(){
		return shipmentNewlogger;
	}

	public Productinventory setShipmentNewlogger(String shipmentNewlogger){
		this.shipmentNewlogger=shipmentNewlogger;
		return this;
	}

	public String getOutMonth(){
		return outMonth;
	}

	public Productinventory setOutMonth(String outMonth){
		this.outMonth=outMonth;
		return this;
	}

	public String getCustomer(){
		return customer;
	}

	public Productinventory setCustomer(String customer){
		this.customer=customer;
		return this;
	}

	public String getCategory(){
		return category;
	}

	public Productinventory setCategory(String category){
		this.category=category;
		return this;
	}

	public String getCaVr(){
		return caVr;
	}

	public Productinventory setCaVr(String caVr){
		this.caVr=caVr;
		return this;
	}

	public String getNote(){
		return note;
	}

	public Productinventory setNote(String note){
		this.note=note;
		return this;
	}

}
