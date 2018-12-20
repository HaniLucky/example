var id;//角色ID
var clickRow=function(rowIndex,rowData){
	
	id=rowData.uuid;
	$('#tree').tree({
		url:'role/menu/'+id,
		method:'GET',
		animate:true,
		checkbox:true
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
		url:'role/menu/',
		dataType:'json',
		data:{'id':id,'checkedStr':checkedStr},
		type:'POST',
		success:function(value){			
			$.messager.alert("提示",value.message);			
		}
		
	});
	
}