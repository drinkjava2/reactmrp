//json$fromServ return example: {"code":200, "msg":"sucess", data:"some data", debugInfo:"Error:xxx"}
//data$fromServ return example: "some data"

function json$fromServ(text) {
	  if(text==null || text=="" || text.indexOf("FULL ")==0) 
		  return "";
	  return getRemoteJson("", text, arguments);
}

function json$java(text) {
	return getRemoteJson("java", text, arguments);
}

function json$javaTx(text) {
	return getRemoteJson("javaTx", text, arguments);
}

function json$qryObject(text) {
	return getRemoteJson("qryObject", text, arguments);
}
  
function json$qryArray(text) {
	return getRemoteJson("qryArray", text, arguments);
}

function json$qryArrayList(text) {
	return getRemoteJson("qryArrayList", text, arguments);
}

function json$qryTitleArrayList(text) {
	return getRemoteJson("qryTitleArrayList", text, arguments);
}
   
function json$qryMap(text) {
	return getRemoteJson("qryMap", text, arguments);
}

function json$qryList(text) {
	return getRemoteJson("qryList", text, arguments);
}

function json$qryMapList(text) {
	return getRemoteJson("qryMapList", text, arguments);
}

function json$qryEntity(text) {
	return getRemoteJson("qryEntity", text, arguments);
}

function json$qryEntityList(text) {
	return getRemoteJson("qryEntityList", text, arguments);
}

//Below methods return JSON's data 
function data$fromServ(text) {
	  if(text==null || text=="" || text.indexOf("FULL ")==0) 
		  return "";
	  return getRemoteJsonData("", text, arguments);
}

function data$java(text) {
	return getRemoteJsonData("java", text, arguments);
}

function data$javaTx(text) {
	return getRemoteJsonData("javaTx", text, arguments);
}

function data$qryObject(text) {
	return getRemoteJsonData("qryObject", text, arguments);
}

function data$qryArray(text) {
	return getRemoteJsonData("qryArray", text, arguments);
}

function data$qryArrayList(text) {
	return getRemoteJsonData("qryArrayList", text, arguments);
}

function data$qryTitleArrayList(text) {
	return getRemoteJsonData("qryTitleArrayList", text, arguments);
}
 
function data$qryMap(text) {
	return getRemoteJsonData("qryMap", text, arguments);
}

function data$qryList(text) {
	return getRemoteJsonData("qryList", text, arguments);
}

function data$qryMapList(text) {
	return getRemoteJsonData("qryMapList", text, arguments);
}

function data$qryEntity(text) {
	return getRemoteJsonData("qryEntity", text, arguments);
}

function threadSleep(delay){
	 var t=(new Date()).getTime();
	 while ((new Date()).getTime() - t < delay)
		 continue;  
}
	
function data$qryEntityList(text) {
	return getRemoteJsonData("qryEntityList", text, arguments);
}
 
async function getRemoteResponse(methodName, text, args){
  let bodyJson= {"remoteMethod":methodName,"$0": text};
  for (var i = 1; i < args.length; i++) 
	   bodyJson["$"+i]=args[i]; 
  if (window.localStorage)  
	   bodyJson["token"]=localStorage.getItem("token");  
  try{ 
	  let response= await fetch("/myserverless.do", {
		    method : 'POST',
		    headers: { 'Content-Type': 'application/json;charset=utf-8' },
			body : JSON.stringify(bodyJson)
	      });
	  return await response.json();
  }catch(e){
	  console.log('Request failed ', e);
  }
}

function getRemoteJson(remoteMethod, text, args){
	 var jsonOrHtml= null; 
	 getRemoteResponse(remoteMethod, text, args).then((result)=>{
		 jsonOrHtml=result;
		 
	 
	 });
	 var jsonObj=JSON.parse(jsonOrHtml);
	  if(jsonObj.debugInfo!=null)
	      console.log(jsonObj.debugInfo); 
	  return jsonObj;
}

function getRemoteJsonData(remoteMethod, text, args){
	 var jsonOrHtml= null;
	 getRemoteResponse(remoteMethod, text, args).then((result)=>{jsonOrHtml=result;}); 
	 var jsonObj;  
	 try {
	   jsonObj=JSON.parse(jsonOrHtml);
	 } catch(e){
	 	return jsonOrHtml;
	 } 
	 if(jsonObj.debugInfo!=null)
		 console.log(jsonObj.debugInfo);
	 return jsonObj.data;
}


//serialize Object
$.fn.serializeObject = function(){
   var o = {};  
   var a = this.serializeArray();  
   $.each(a, function() {  
       if (o[this.name]) {  
           if (!o[this.name].push) {  
               o[this.name] = [o[this.name]];  
           }  
           o[this.name].push(this.value || '');  
       } else {  
           o[this.name] = this.value || '';  
       }  
   });  
   return o;  
};

//change a from to JSON string
function formToJSON(formName){ 
    var jsonuserinfo = $("#"+formName).serializeObject();
	return JSON.stringify(jsonuserinfo);
}  