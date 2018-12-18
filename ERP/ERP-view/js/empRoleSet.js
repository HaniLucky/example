var id;//用户ID
var clickRow=function(rowIndex,rowData){
	
	id=rowData.uuid;
	$('#tree').tree({
		url:'/api/tree/user/'+id,
		animate:true,
		checkbox:true,
		method:'get'
	});
	
}
/**
 * 保存角色权限
 */
function save(){
	
	var nodes=$('#tree').tree('getChecked');
	var checkedStr="";
	for( var i=0;i<nodes.length;i++ ){
		if(i>0){
			checkedStr+=",";
		}		
		checkedStr+=nodes[i].id;		
	}
	
	$.ajax({
		url:'/api/role/',
		dataType:'json',
		data:{'id':id,'checkedStr':checkedStr},
		type:'post',
		success:function(value){			
			$.messager.alert("提示",value.message);			
		}
		
	});
	
}