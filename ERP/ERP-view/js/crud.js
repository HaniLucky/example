
var method="";//保存提交的方法名称 
var pre = "/api/" // 匹配接口的前缀
$(function(){
	
	//如果listParam没有定义则设置初始值为"";
	if(typeof(listParam)=='undefined'){
		listParam="";
	}
	//如果saveParam没有定义则设置初始值为"";
	if(typeof(saveParam)=='undefined'){
		saveParam="";
	}
	
	//表格数据初始化
	$('#grid').datagrid({
		url:pre+name+'/'+'listByPage'+listParam,
		columns:columns,
		singleSelect:true,
		pagination:true,
		toolbar: [{
			iconCls: 'icon-add',
			text:'增加',
			handler: function(){				
				$('#editWindow').window('open');
				$('#editForm').form('clear');
				method="add";
			}
		},
		{
			iconCls: 'icon-save',
			text:'导出',
			handler: function(){	
				var formdata=getFormData('searchForm');
				$.download(name+'_export.action'+listParam,formdata );
			}
		},
		{
			iconCls: 'icon-tip',
			text:'导入',
			handler: function(){	
				$('#uploadWindow').window('open');//打开窗口				
			}
		}
		]

	});
	
	//条件查询
	$('#btnSearch').bind('click',function(){
		//表单searchForm的数据转换为json对象
		var formdata=$('#searchForm').serializeJSON();	
		//JSON.stringify 用于将json对象转换为json字符串
		//alert(JSON.stringify(formdata));	
		
		//将表单的对象提交到后端做条件查询 
		$('#grid').datagrid('load',formdata);	
	});
	
	
	
	//保存和新增
	$('#btnSave').bind('click',function(){
		//前端验证
		var isValidate= $('#editForm').form('validate');
		if(isValidate==false){//如果有控件未通过验证
			return ;//返回，不向下执行。。
		}
		
		//表单searchForm的数据转换为json对象
		//var formdata=$('#editForm').serializeJSON();		
		// alert('ok');
		// 将表单数据转换为js对象 JSON.stringify 将数据转换为json字符串
		var formdata=JSON.stringify($('#editForm').serializeJSON());	
		// console.info(formdata);
		// 判断如果id为空就是新增加 不为空就是更新
		/*var uuid = $("#uuid").val();
		if (uuid == "") {
			method = 'POST'
		}else{
			method = "PUT"
		}*/
		method = $("#uuid").val()== ""? 'POST' : 'PUT';
		$.ajax({
			// url:"http://127.0.0.1:8080"+name+'/'+method+saveParam,
			// url:pre+name+'/'+method+saveParam,
			url:pre+name+'/'+saveParam,
			data:formdata,
			dataType:'json',
			type:method,
			// 传递json contentType需要声明为json
			contentType: "application/json",
			success:function(value){
				if(value.success){
					$('#editWindow').window('close');
					$('#grid').datagrid('reload');
				}
				$.messager.alert('提示',value.message);				
			}
			
		});
		
	});
	
	
	
	
	//数据导入
	$('#btnUpload').bind('click',function(){
		$.ajax({
			 url:name+'_upload.action',
			 type:'post',
			 data:new FormData($('#uploadForm')[0]),
			 dataType:'json',
			 processData:false,
			 contentType:false,
			 success:function(value){			 
				$.messager.alert("温馨提示",value.message);
				if(value.success){
					$('#uploadWindow').window('close');
					$('#grid').datagrid('reload');
				}
			 }
		 });		
	});
	
	
});

/**
 * 删除 
 * RestApi DELETE请求
 */
function dele(id){
	
	$.messager.confirm('提示','确定要删除此记录吗？',function(r){
		if(r)
		{
			$.ajax({
				url:pre+name+'/'+id,
				type:'DELETE',
				dataType:'json',
				success:function(value){
					if(value.success){
						$('#grid').datagrid('reload');
					}
					$.messager.alert('提示',value.message);
				}
			});		
		}	
	});	
}

/**
 * 编辑
 */
function edit(id){
	// 打开编辑框
	$('#editWindow').window('open');
	// 清除表单
	$('#editForm').form('clear');
	$('#editForm').form('load',pre+name+'/'+id);	
	method="update";
}