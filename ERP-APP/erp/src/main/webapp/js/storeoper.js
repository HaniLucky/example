$(function(){
	
	$('#grid').datagrid({
		url:'storeoper_listByPage.action',
		columns:[[
		  		    {field:'uuid',title:'编号',width:100},
		  		    {field:'empuuid',title:'员工',width:100,formatter:function(value,row,index){
		  		    	return ajax("emp_get.action?id=",value,"emp_"+index,"t.name");		  		    	
		  		    }},
		  		    {field:'opertime',title:'操作日期',width:120,formatter:function(value){
		  		    	return new Date(value).Format('yyyy-MM-dd hh:mm');
		  		    }},
		  		    {field:'storeuuid',title:'仓库',width:100,formatter:function(value,row,index){
		  		    	return ajax("store_get.action?id=",value,"store_"+index,"t.name");		  		    	
		  		    }},
		  		    {field:'goodsuuid',title:'商品',width:100,formatter:function(value,row,index){
		  		    	return ajax("goods_get.action?id=",value,"goods_"+index,"t.name");		  		    	
		  		    }},
		  		    {field:'num',title:'数量',width:100},
		  		    {field:'type',title:'类型',width:100,formatter:function(value){
		  		    	return storetype[value];
		  		    }}	    
		 ]],
		 singleSelect:true,
		 pagination:true
		 
		
	});
	
	//查询
	$('#btnSearch').bind('click',function(){
		var formdata=$('#searchForm').serializeJSON();
		$('#grid').datagrid('load',formdata);
	});
	
	
});