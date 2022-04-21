// fetchMyServlessJson fetch a json from MyServerless server,
// Return example: {"code":200, "msg":"sucess", data:"foo", debugInfo:"bar"}
async function fetchMyServlessJson(methodName, text, args){
	  let bodyJson= {"remoteMethod":methodName,"$0": text};
	  for (let i = 1; i < args.length; i++) 
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
		  return {"code":403, "msg":"Request failed", "data":null};
	  }
	}
  
async function getMyServJson(text){
	return await fetchMyServlessJson("", text, arguments);
}

async function $java(text) {
	return await fetchMyServlessJson("java", text, arguments);
}
 
async function $javaTx(text) {
	return await fetchMyServlessJson("javaTx", text, arguments);
} 

async function $qryObject(text) {
	return await fetchMyServlessJson("qryObject", text, arguments);
}
  
async function $qryArray(text) {
	return await fetchMyServlessJson("qryArray", text, arguments);
}

async function $qryArrayList(text) {
	return await fetchMyServlessJson("qryArrayList", text, arguments);
}

async function $qryTitleArrayList(text) {
	return await fetchMyServlessJson("qryTitleArrayList", text, arguments);
}
   
async function $qryMap(text) {
	return await fetchMyServlessJson("qryMap", text, arguments);
}

async function $qryList(text) {
	return await fetchMyServlessJson("qryList", text, arguments);
}

async function $qryMapList(text) {
	return await fetchMyServlessJson("qryMapList", text, arguments);
} 

async function $qryEntity(text) {
	return await fetchMyServlessJson("qryEntity", text, arguments);
}

async function $qryEntityList(text) {
	return await fetchMyServlessJson("qryEntityList", text, arguments);
}
  

//===============
async function data$java(text) {
	let json= await fetchMyServlessJson("java", text, arguments);
	return json.data;
}  

async function data$qryObject(text) {
	return await fetchMyServlessJson("qryObject", text, arguments).data;
}
  
async function data$qryArray(text) {
	return await fetchMyServlessJson("qryArray", text, arguments).data;
}

async function data$qryArrayList(text) {
	return await fetchMyServlessJson("qryArrayList", text, arguments).data;
}

async function data$qryTitleArrayList(text) {
	return await fetchMyServlessJson("qryTitleArrayList", text, arguments).data;
}
   
async function data$qryMap(text) {
	return await fetchMyServlessJson("qryMap", text, arguments).data;
}

async function data$qryList(text) {
	return await fetchMyServlessJson("qryList", text, arguments).data;
}

async function data$javaTx(text) {
	return await fetchMyServlessJson("javaTx", text, arguments).data;
}  

async function data$qryMapList(text) {
	return await fetchMyServlessJson("qryMapList", text, arguments).data;
}

async function data$qryEntity(text) {
	return await fetchMyServlessJson("qryEntity", text, arguments).data;
}

async function data$qryEntityList(text) {
	return await fetchMyServlessJson("qryEntityList", text, arguments).data;
}
 
//== below misc methods not important, will delete at next push =====

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
function transFormToJSON(formName){ 
    var jsonuserinfo = $("#"+formName).serializeObject();
	return JSON.stringify(jsonuserinfo);
}  

//sleep main thread
function threadSleep(delay){
	 var t=(new Date()).getTime();
	 while ((new Date()).getTime() - t < delay)
		 continue;  
}