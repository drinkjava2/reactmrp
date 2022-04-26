// fetchJSon fetch a json from MyServerless server,
// Return example: {"code":200, "msg":"sucess", data:"foo", debugInfo:"bar"}
async function fetchJSon(methodName, text, args){
	  let bodyJson= {"remoteMethod":methodName,"$0": text};
	  for (let i = 1; i < args.length; i++) 
		   bodyJson["$"+i]=args[i]; 
	  if (window.localStorage)  
		   bodyJson["token"]=localStorage.getItem("token");  
	  let bodyJsonStr=JSON.stringify(bodyJson);
	  try{ 
		  let response= await fetch("/myserverless.do", {
			    method : "POST",
			    mode: "cors",
			    headers: {"Accept":"application/json", "Content-Type": "application/json;charset=utf-8"},
				body : bodyJsonStr
		      });
		  return await response.json();
	  }catch(e){
		  console.log("Request failed ", e);
		  return {"code":403, "msg":"Request failed", "data":null};
	  }
	}

function syncFetchJSon(methodName, text, args){
	let bodyJson= {"remoteMethod":methodName,"$0": text};
	for (let i = 1; i < args.length; i++) 
		   bodyJson["$"+i]=args[i]; 
	if (window.localStorage)  
		   bodyJson["token"]=localStorage.getItem("token");  
	let bodyJsonStr=JSON.stringify(bodyJson);
	let xhr = new XMLHttpRequest(); 
	xhr.open('POST', '/myserverless.do', false); 
	xhr.setRequestHeader("Content-Type","application/json;charset=utf-8");
	xhr.setRequestHeader("Accept","application/json");
	try {
	  xhr.send(bodyJsonStr);
	  if (xhr.status != 200) {
		  return {"code":403, "msg":"Request failed", "data":null};
	  } else {
		  return JSON.parse(xhr.responseText);
	  }
	} catch(err) { // 代替 onerror
		return {"code":403, "msg":"Request failed", "data":null};
	}
}

//同步方法只有几个
function sync$getMyServJson(text){		return  syncFetchJSon("", text, arguments); }
function syncData$getMyServJson(text){	return  syncFetchJSon("", text, arguments).data; }

function sync$java(text) { 				return syncFetchJSon("java", text, arguments); }
function sync$javaTx(text) {			return syncFetchJSon("javaTx", text, arguments); }

function syncData$java(text) { 			return syncFetchJSon("java", text, arguments).data; } 
function syncData$javaTx(text) {		return syncFetchJSon("javaTx", text, arguments).data; } 


//下面都是异步方法
async function $getMyServJson(text){return await fetchJSon("", text, arguments); }
async function data$getMyServJson(text){let json= await fetchJSon("", text, arguments); return json.data; }

async function $java(text) { 				return await fetchJSon("java", text, arguments); } 
async function $javaTx(text) {				return await fetchJSon("javaTx", text, arguments);} 
async function $qryObject(text) {			return await fetchJSon("qryObject", text, arguments);}  
async function $qryArray(text) {			return await fetchJSon("qryArray", text, arguments);}
async function $qryArrayList(text) {		return await fetchJSon("qryArrayList", text, arguments);}
async function $qryTitleArrayList(text) {	return await fetchJSon("qryTitleArrayList", text, arguments);}
async function $qryMap(text) {				return await fetchJSon("qryMap", text, arguments);}
async function $qryList(text) {				return await fetchJSon("qryList", text, arguments);}
async function $qryMapList(text) {			return await fetchJSon("qryMapList", text, arguments);} 
async function $qryEntity(text) {			return await fetchJSon("qryEntity", text, arguments);}
async function $qryEntityList(text) {		return await fetchJSon("qryEntityList", text, arguments);}

async function data$java(text) {			let json= await fetchJSon("java", text, arguments);	return json.data;}  
async function data$qryObject(text) {		let json= await fetchJSon("qryObject", text, arguments); return json.data;}  
async function data$qryArray(text) {		let json= await fetchJSon("qryArray", text, arguments); return json.data;}
async function data$qryArrayList(text) {	let json= await fetchJSon("qryArrayList", text, arguments); return json.data;}
async function data$qryTitleArrayList(text){let json= await fetchJSon("qryTitleArrayList", text, arguments); return json.data;}
async function data$qryMap(text) {			let json= await fetchJSon("qryMap", text, arguments); return json.data;}
async function data$qryList(text) {			let json= await fetchJSon("qryList", text, arguments); return json.data;}
async function data$javaTx(text) {			let json= await fetchJSon("javaTx", text, arguments); return json.data;}
async function data$qryMapList(text) {		let json= await fetchJSon("qryMapList", text, arguments); return json.data;}
async function data$qryEntity(text) {		let json= await fetchJSon("qryEntity", text, arguments); return json.data;}
async function data$qryEntityList(text) {	let json= await fetchJSon("qryEntityList", text, arguments); return json.data;}

function domByid(id){return document.getElementById(id);}
function domValById(id){return document.getElementById(id).value;}