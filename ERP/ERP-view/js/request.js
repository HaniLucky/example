var url=location.search;
var Request = new Object();
if(url.indexOf("?")!=-1)
{
    var str = url.substr(1)  
    strs = str.split("&");
    for(var i=0;i<strs.length;i++)
    {
        Request[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
    }
}

if (Request['type']=='1') {
	document.title="供应商管理";
}
if(Request['type']=='2'){
	document.title="客户管理";
}