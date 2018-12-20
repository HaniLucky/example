var g_index;//主表格的展开行的索引
var g_index2;//子表格的双击行的索引

$(function(){
	
	$('#grid').datagrid({
		url:url,
		columns:[[
		  		    {field:'uuid',title:'编号',width:100},
		  		    {field:'createtime',title:'生成日期',width:100,formatter:function(value){
		  		    	return new Date(value).Format('yyyy-MM-dd');
		  		    }},
		  		    {field:'checktime',title:'检查日期',width:100,formatter:function(value){
		  		    	return new Date(value).Format('yyyy-MM-dd');
		  		    }},
		  		    {field:'starttime',title:'开始日期',width:100,formatter:function(value){
		  		    	return new Date(value).Format('yyyy-MM-dd');
		  		    }},
		  		    {field:'endtime',title:'结束日期',width:100,formatter:function(value){
		  		    	return new Date(value).Format('yyyy-MM-dd');
		  		    }},
		  		    {field:'type',title:'订单类型',width:100,formatter:function(value){
		  		    	return type[value];
		  		    }},
		  		    {field:'creater',title:'下单员',width:100,formatter:function(value,row,index){
		  		    	
		  		    	return ajax('emp_get.action?id=',value,'creater_'+index,'t.name');
		  		    }},
		  		    {field:'checker',title:'审查员',width:100,formatter:function(value,row,index){
		  		    	
		  		    	return ajax('emp_get.action?id=',value,'checker_'+index,'t.name');
		  		    }},
		  		    {field:'starter',title:'采购员',width:100,formatter:function(value,row,index){
		  		    	
		  		    	return ajax('emp_get.action?id=',value,'starter_'+index,'t.name');
		  		    }},
		  		    {field:'ender',title:'库管员',width:100,formatter:function(value,row,index){
		  		    	
		  		    	return ajax('emp_get.action?id=',value,'ender_'+index,'t.name');
		  		    }},
		  		    {field:'supplieruuid',title:'供应商ID',width:100,formatter:function(value,row,index){
		  		    	
		  		    	return ajax('supplier_get.action?id=',value,'supplier_'+index,'t.name');
		  		    }},
		  		    {field:'totalmoney',title:'总金额',width:100},
		  		    {field:'state',title:'订单状态',width:100,formatter:function(value){
		  		    	return state[value];
		  		    }},
		  		    operation
		  ]],
		 singleSelect:true,
		 pagination:true,
		 view:detailview,//定义扩展
		 detailFormatter:function(index,row){//展开的内容
			 return "<table id='ddv_"+index+"'></table>";
		 },
		 onExpandRow:function(index,row){//展开时触发的事件
			 //读取明细数据
			 $('#ddv_'+index).datagrid({
				 data:row.orderdetails,
				 columns:[[
				  		    {field:'uuid',title:'编号',width:100},
				  		    {field:'goodsuuid',title:'商品编号',width:100},
				  		    {field:'goodsname',title:'商品名称',width:100},
				  		    {field:'price',title:'价格',width:100},
				  		    {field:'num',title:'数量',width:100},
				  		    {field:'money',title:'金额',width:100},
				  		    {field:'endtime',title:'结束日期',width:100},
				  		    {field:'ender',title:'库管员',width:100},
				  		    {field:'storeuuid',title:'仓库编号',width:100},
				  		    {field:'state',title:'状态',width:100},
				  		    {field:'ordersuuid',title:'订单编号',width:100}	    
					]],
				singleSelect:true,
				onDblClickRow:function(rowIndex,rowData){
					$('#orderWindow').window('open');//打开订单明细窗口
					$('#goodsuuid').html(rowData.goodsuuid );//商品编号
					$('#goodsname').html(rowData.goodsname);//商品名称
					$('#num').html(rowData.num);//数量
					$('#id').val(rowData.uuid);//订单明细ID
					
					g_index=index;//展开行的索引
					g_index2=rowIndex;//双击行的索引
					
					if(Request['type']=='1'){//如果是入库
						$('#outDiv').hide();//隐藏出库按钮
					}
					if(Request['type']=='2'){//如果是入库
						$('#inDiv').hide();//隐藏入库按钮
					}
				},
				loadFilter:function(data){//data表示原始数据 ,相当于getRows()方法返回的结果
					var value={total:0,rows:[]};//返回的值必须是标准数据格式					
					for(var i=0;i<data.length;i++)
					{						
						if( data[i].state=='0' || !isFilter ){
							value.rows.push(data[i]);//push方法是向数组中添加元素  
						}												
					}					
					return value;
				}				 
			 });
			 //修复明细行高度
			 $('#grid').datagrid('fixDetailRowHeight',index);
			 
		 }
	});
	
	
	//查询
	$('#btnSearch').bind('click',function(){
		var formdata=getFormData("searchForm");
		$('#grid').datagrid('load',formdata);
		
	});
	
})

/**
 * 采购审核
 * @param id
 */
function doCheck(id){
	doLogic('确定要审核此订单吗？','orders_doCheck.action?id='+id);	
}


/**
 * 采购确认
 * @param id
 */
function doStart(id){
	doLogic('确定要确认此订单吗？','orders_doStart.action?id='+id);	
}

function doLogic(message,url){	
	$.messager.confirm('提示',message,function(r){
		if(r){			
			$.ajax({
				url:url,
				dataType:'json',
				success:function(value){
					if(value.success){
						$('#grid').datagrid('reload');//刷新表格				
					}
					$.messager.alert('提示',value.message);
				}				
			});			
		}		
	});		
}

/**
 * 入库
 */
function doInStore(){	
	doUrlStore('orderdetail_doInStore.action');
}


/**
 * 出库
 */
/*
function doOutStore(){		
	var formdata=$('#orderForm').serializeJSON();	
	$.ajax({
		url:'orderdetail_doOutStore.action',
		dataType:'json',
		type:'post',
		data:formdata,
		success:function(value){
			if(value.success){
				$('#orderWindow').window('close');//关闭窗口	
				//修改订单明细本地的状态值为1
				$('#ddv_'+g_index).datagrid('getRows')[g_index2].state='1';
				//移除当前操作的订单明细				
				$('#ddv_'+g_index).datagrid('deleteRow',g_index2);//移除明细行
				if($('#ddv_'+g_index).datagrid('getRows').length==0){//判断明细是否均已出库
					$('#grid').datagrid('reload');//重新加载主表格
				}	
			}
			$.messager.alert('提示',value.message);
		}		
	});	
}
*/
/**
 * 出库
 */
function doOutStore(){
	doUrlStore('orderdetail_doOutStore.action');
}

function doUrlStore(url){
	var formdata=$('#orderForm').serializeJSON();
	$.ajax({
		url:url,
		dataType:'json',
		type:'post',
		data:formdata,
		success:function(value){
			if(value.success){
				$('#orderWindow').window('close');//关闭窗口
				//修改订单明细本地的状态值为1
				$('#ddv_'+g_index).datagrid('getRows')[g_index2].state='1';
				//移除当前操作的订单明细				
				$('#ddv_'+g_index).datagrid('deleteRow',g_index2);//移除明细行
				if($('#ddv_'+g_index).datagrid('getRows').length==0){//判断明细是否均已入库
					$('#grid').datagrid('reload');//重新加载主表格
				}				
			}
			$.messager.alert('提示',value.message);
		}		
	});
}

/**
 * 导出订单
 * @param id
 */
function exportById(id){
	var formdata={'id':id};
	$.download('orders_exportById.action',formdata);	
}
