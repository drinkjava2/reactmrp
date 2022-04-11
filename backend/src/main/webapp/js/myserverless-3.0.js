//json example: {"code":200, "msg":"sucess", data:"some data", debugInfo:"Error:xxx"}
//$$: return json object     
//$: return json.data

function $$callDeployed(text) {
	  if(text==null || text=="" || text.indexOf("FULL ")==0) 
		  return "";
	  return getRemoteJson("", text, arguments);
}

function $$java(text) {
	return getRemoteJson("java", text, arguments);
}

function $$javaTx(text) {
	return getRemoteJson("javaTx", text, arguments);
}

function $$qryObject(text) {
	return getRemoteJson("qryObject", text, arguments);
}
  
function $$qryArray(text) {
	return getRemoteJson("qryArray", text, arguments);
}

function $$qryArrayList(text) {
	return getRemoteJson("qryArrayList", text, arguments);
}

function $$qryTitleArrayList(text) {
	return getRemoteJson("qryTitleArrayList", text, arguments);
}
   
function $$qryMap(text) {
	return getRemoteJson("qryMap", text, arguments);
}

function $$qryList(text) {
	return getRemoteJson("qryList", text, arguments);
}

function $$qryMapList(text) {
	return getRemoteJson("qryMapList", text, arguments);
}

function $$qryEntity(text) {
	return getRemoteJson("qryEntity", text, arguments);
}

function $$qryEntityList(text) {
	return getRemoteJson("qryEntityList", text, arguments);
}

//Below methods return JSON's data 
function $callDeployed(text) {
	  if(text==null || text=="" || text.indexOf("FULL ")==0) 
		  return "";
	  return getRemoteJsonData("", text, arguments);
}

function $java(text) {
	return getRemoteJsonData("java", text, arguments);
}

function $javaTx(text) {
	return getRemoteJsonData("javaTx", text, arguments);
}

function $qryObject(text) {
	return getRemoteJsonData("qryObject", text, arguments);
}

function $qryArray(text) {
	return getRemoteJsonData("qryArray", text, arguments);
}

function $qryArrayList(text) {
	return getRemoteJsonData("qryArrayList", text, arguments);
}

function $qryTitleArrayList(text) {
	return getRemoteJsonData("qryTitleArrayList", text, arguments);
}
 
function $qryMap(text) {
	return getRemoteJsonData("qryMap", text, arguments);
}

function $qryList(text) {
	return getRemoteJsonData("qryList", text, arguments);
}

function $qryMapList(text) {
	return getRemoteJsonData("qryMapList", text, arguments);
}

function $qryEntity(text) {
	return getRemoteJsonData("qryEntity", text, arguments);
}

function $qryEntityList(text) {
	return getRemoteJsonData("qryEntityList", text, arguments);
}


function getRemoteResponse(methodName, text, args){
 var postJson;
 if (window.localStorage) {
   var develop_token=localStorage.getItem("develop_token");
   var token=localStorage.getItem("token");
   postJson= {"remoteMethod":methodName, "develop_token":develop_token, "token":token, "$0": text};
 } else 
   postJson= {"remoteMethod":methodName,"$0": text};
   for (var i = 1; i < args.length; i++) 
		  postJson["$"+i]=args[i]; 
  return $.ajax({
		type : 'POST',
		url : "/myserverless.do",
		cache : false,
		dataType : "json",
		data : postJson,
		async : false
	}).responseText;
}

function myserverlessLogin(username, password){
	  var jsonStr= $.ajax({
			type : 'POST',
			url : "/myserverless.do?login=true&username="+username+"&password="+password,
			cache : false,
			dataType : "json",
			data : {},
			async : false
		}).responseText;
	  return JSON.parse(jsonStr); 
}

function getRemoteJson(remoteMethod, text, args){
	 var jsonOrHtml= getRemoteResponse(remoteMethod, text, args); 
	 var jsonObj=JSON.parse(jsonOrHtml);
	  if(jsonObj.debugInfo!=null)
	      console.log(jsonObj.debugInfo); 
	  return jsonObj;
}

function getRemoteJsonData(remoteMethod, text, args){
	 var jsonOrHtml= getRemoteResponse(remoteMethod, text, args); 
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