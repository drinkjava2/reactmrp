//myserverless-pure.js是给不使用web工程化的场合使用的，如果使用了webpack，应当使用myserverless.js，这两个文件的区别是后者用在方法前加了export

const host="";
// Return example: {"code":200, "msg":"sucess", data:"foo", debugInfo:"bar"}
async function fetchJSon(methodName, text, args){//异步ajax
    let bodyJsonStr=getBodyJsonStr(methodName, text, args);
      try{ 
          //m参数只是给开发者用来区分API用的，后端不需要，布署时可以去掉这个参数
          let response= await fetch(host+"/my.do?m="+methodInfo(methodName, text), {
                method : "POST",
                mode: "cors",
                headers: {"Accept":"application/json", "Content-Type": "application/json;charset=utf-8"},
                //credentials: 'include',
                body : bodyJsonStr
              });
          let obj= await response.json();
          if(obj.debugInfo)console.error(obj.debugInfo);
          return obj;
      }catch(e){
          console.log("Request failed ", e);
          return {"code":403, "msg":"Request failed", "data":null};
      }
    }

function syncXhrJSon(methodName, text, args){//同步ajax
    let bodyJsonStr=getBodyJsonStr(methodName, text, args);
    let xhr = new XMLHttpRequest(); 
    xhr.open("POST", host+"/my.do?m="+methodInfo(methodName, text), false); 
    xhr.setRequestHeader("Content-Type","application/json;charset=utf-8");
    xhr.setRequestHeader("Accept","application/json");
    //xhr.withCredentials = true;
    try {
      xhr.send(bodyJsonStr);
      if (xhr.status !== 200) {
          return {"code":403, "msg":"Request failed", "data":null};
      } else {
          let obj= JSON.parse(xhr.responseText);
          if(obj.debugInfo)console.error(obj.debugInfo);
          return obj;
      }
    } catch(err) {
        return {"code":403, "msg":"Request failed", "data":null};
    }
}

function getBodyJsonStr(method, text, args){ 
    if(!method)
        method="";
    let bodyJson= {"remoteMethod":method,"$0": text};
    for (let i = 1; i < args.length; i++) 
           bodyJson["$"+i]=args[i]; 
    bodyJson["myToken"]="";
    if (window.localStorage && localStorage.getItem("myToken"))  
           bodyJson["myToken"]=localStorage.getItem("myToken"); 
    return JSON.stringify(bodyJson);
}

function methodInfo(methodName, text){ //methodInfo参数加在url中，这个只是用来快速定位API用的，不参与后端逻辑
      let rs = "";   
      for (var i = 0; i < text.length; i++) {
          let c = text.substr(i, 1);     
          if( ("a"<=c && c<="z") || ("A"<=c && c<="Z") || ("0"<=c && c<="9") || (c==="_" || c==="$")){
              rs += c;
          } else if (c===" "){
              rs += "+";
          } 
          if(rs.length>100){
             rs+="...";
             break;
          }
       }      
      if(methodName!=="")
          rs= methodName+"+"+rs;
      return rs;
};
     
function getMyToken(){
    return window.localStorage?localStorage.getItem("myToken"):null;  
}       

//异步方法
async function $myServerless(text){return await fetchJSon("", text, arguments); }
async function data$myServerless(text){let json= await fetchJSon("", text, arguments); return json.data; }

async function $java(text) {                 return await fetchJSon("java", text, arguments); } 
async function $javaTx(text) {               return await fetchJSon("javaTx", text, arguments);} 
async function $qryString(text) {            return await fetchJSon("qryString", text, arguments);}
async function $qryObject(text) {            return await fetchJSon("qryObject", text, arguments);}  
async function $qryArray(text) {             return await fetchJSon("qryArray", text, arguments);}
async function $qryArrayList(text) {         return await fetchJSon("qryArrayList", text, arguments);}
async function $qryTitleArrayList(text) {    return await fetchJSon("qryTitleArrayList", text, arguments);}
async function $qryMap(text) {               return await fetchJSon("qryMap", text, arguments);}
async function $qryList(text) {              return await fetchJSon("qryList", text, arguments);}
async function $qryMapList(text) {           return await fetchJSon("qryMapList", text, arguments);} 
async function $qryEntity(text) {            return await fetchJSon("qryEntity", text, arguments);}
async function $qryEntityList(text) {        return await fetchJSon("qryEntityList", text, arguments);}
async function $executeSql(text) {           return await fetchJSon("executeSql", text, arguments);}

async function data$java(text) {             let json= await fetchJSon("java", text, arguments); return json.data;}  
async function data$javaTx(text) {           let json= await fetchJSon("javaTx", text, arguments);   return json.data;}
async function data$qryString(text) {        let json= await fetchJSon("qryString", text, arguments); return json.data;}
async function data$qryObject(text) {        let json= await fetchJSon("qryObject", text, arguments); return json.data;}  
async function data$qryArray(text) {         let json= await fetchJSon("qryArray", text, arguments); return json.data;}
async function data$qryArrayList(text) {     let json= await fetchJSon("qryArrayList", text, arguments); return json.data;}
async function data$qryTitleArrayList(text){let json= await fetchJSon("qryTitleArrayList", text, arguments); return json.data;}
async function data$qryMap(text) {           let json= await fetchJSon("qryMap", text, arguments); return json.data;}
async function data$qryList(text) {          let json= await fetchJSon("qryList", text, arguments); return json.data;}
async function data$qryMapList(text) {       let json= await fetchJSon("qryMapList", text, arguments); return json.data;}
async function data$qryEntity(text) {        let json= await fetchJSon("qryEntity", text, arguments); return json.data;}
async function data$qryEntityList(text) {    let json= await fetchJSon("qryEntityList", text, arguments); return json.data;}
async function data$executeSql(text) {       let json= await fetchJSon("executeSql", text, arguments); return json.data;}

//同步方法
function sync$myServerless(text){            return  syncXhrJSon("", text, arguments); }
function syncData$myServerless(text){        return  syncXhrJSon("", text, arguments).data; }

function sync$java(text) {               return syncXhrJSon("java", text, arguments); }
function sync$javaTx(text) {             return syncXhrJSon("javaTx", text, arguments); }
function sync$qryString(text) {          return syncXhrJSon("qryString", text, arguments);}
function sync$qryObject(text) {          return syncXhrJSon("qryObject", text, arguments);}  
function sync$qryArray(text) {           return syncXhrJSon("qryArray", text, arguments);}
function sync$qryArrayList(text) {       return syncXhrJSon("qryArrayList", text, arguments);}
function sync$qryTitleArrayList(text) {  return syncXhrJSon("qryTitleArrayList", text, arguments);}
function sync$qryMap(text) {             return syncXhrJSon("qryMap", text, arguments);}
function sync$qryList(text) {            return syncXhrJSon("qryList", text, arguments);}
function sync$qryMapList(text) {         return syncXhrJSon("qryMapList", text, arguments);} 
function sync$qryEntity(text) {          return syncXhrJSon("qryEntity", text, arguments);}
function sync$qryEntityList(text) {      return syncXhrJSon("qryEntityList", text, arguments);}
function sync$executeSql(text) {         return syncXhrJSon("executeSql", text, arguments);}

function syncData$java(text) {               let json= syncXhrJSon("java", text, arguments); return json.data; } 
function syncData$javaTx(text){              let json= syncXhrJSon("javaTx", text, arguments); return json.data;} 
function syncData$qryString(text) {          let json= syncXhrJSon("qryString", text, arguments); return json.data;}
function syncData$qryObject(text) {          let json= syncXhrJSon("qryObject", text, arguments); return json.data;}  
function syncData$qryArray(text) {           let json= syncXhrJSon("qryArray", text, arguments); return json.data;}
function syncData$qryArrayList(text) {       let json= syncXhrJSon("qryArrayList", text, arguments); return json.data;}
function syncData$qryTitleArrayList(text){   let json= syncXhrJSon("qryTitleArrayList", text, arguments); return json.data;}
function syncData$qryMap(text) {             let json= syncXhrJSon("qryMap", text, arguments); return json.data;}
function syncData$qryList(text) {            let json= syncXhrJSon("qryList", text, arguments); return json.data;}
function syncData$qryMapList(text) {         let json= syncXhrJSon("qryMapList", text, arguments); return json.data;}
function syncData$qryEntity(text) {          let json= syncXhrJSon("qryEntity", text, arguments); return json.data;}
function syncData$qryEntityList(text) {      let json= syncXhrJSon("qryEntityList", text, arguments); return json.data;}
function syncData$executeSql(text) {         let json= syncXhrJSon("executeSql", text, arguments); return json.data;}
