var id;//角色ID
var clickRow=function(rowIndex,rowData){
	
	id=rowData.uuid;
	$('#tree').tree({
		url:'/api/role/'+id,
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
		url:'/api/role/menu',
		dataType:'json',
		data:{'id':id,'checkedStr':checkedStr},
		type:'post',
		success:function(value){			
			$.messager.alert("提示",value.message);			
		}
		
	});
	
}

// 加载树形菜单
/*$(function(){
	$('#tree').tree({
		url:'/api/role/',// 指定地址
		checkbox:true, // 添加复选框
		animate:true // 动画效果
	})
})*/