var url='orders/page/list';
var itemRowIndex;//当前操作的明细行
$(function(){
	
	//如果是审核 
	if(Request['operation']=='check'&& Request['type']==1){
		url+='/0/1';//状态为0 未审核
		document.title="采购订单审核";	
		$('#btnCheck').show();
	}	
	
	//如果是确认 
	if(Request['operation']=='start'&& Request['type']==1){
		url+='/1/1';//状态为1 已审核
		document.title="采购订单确认";	
		$('#btnStart').show();
	}	
	
	//如果是入库 
	if(Request['operation']=='instore'&& Request['type']==1){
		url+='/2/1';//状态为2 已确认
		document.title="采购订单入库";
	}	
	

	//如果是采购订单查询
	if(Request['operation']=='order'&&Request['type']=='1'){
		url+='/1';//状态为0 
		document.title="采购订单查询";	
	}	
	
	//如果是我的采购订单 
	if(Request['operation']=='myorders' && Request['type']==1){
		url='orders/page/list/0/1';//状态为0 
		document.title="我的采购订单";	
		$('#btnAdd').show();//显示增加订单按钮
		$('#btnAdd').linkbutton({'text':'采购申请'});//更改按钮文字
		$('#name').html("供应商");
	}
	
	//如果是销售订单查询
	if(Request['operation']=='order'&&Request['type']=='2'){
		url+='/2';//状态为0 
		document.title="销售订单查询";	
	}	
	
	//如果是我的销售订单 
	if(Request['operation']=='myorders' && Request['type']==2){
		url='orders/page/list/0/2';//状态为0 
		document.title="我的销售订单";
		$('#btnAdd').show();//显示增加订单按钮
		$('#btnAdd').linkbutton({'text':'销售订单录入'});//更改按钮文字
		$('#name').html("客户");
	}
	
	$('#grid').datagrid({
		
		url:url,
		columns:[[
		  		    {field:'uuid',title:'编号',width:50},
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
		  		    {field:'creater',title:'下单员',width:100,formatter:function(value,row,index){
		  		    	
		  		    	return ajax('emp/',value,'creater_'+index,'name');
		  		    }},
		  		    {field:'checker',title:'审查员',width:100,formatter:function(value,row,index){
		  		    	
		  		    	return ajax('emp/',value,'checker_'+index,'name');
		  		    }},
		  		    {field:'starter',title:'采购员',width:100,formatter:function(value,row,index){
		  		    	
		  		    	return ajax('emp/',value,'starter_'+index,'name');
		  		    }},
		  		    {field:'ender',title:'库管员',width:100,formatter:function(value,row,index){
		  		    	
		  		    	return ajax('emp/',value,'ender_'+index,'name');
		  		    }},
		  		    {field:'supplieruuid',title:'供应商',width:200,formatter:function(value,row,index){
		  		    	
		  		    	return ajax('supplier/',value,'supplier_'+index,'name');
		  		    }},
		  		    {field:'totalmoney',title:'总金额',width:100},
		  		    {field:'state',title:'订单状态',width:100,formatter:function(value){
		  		    	return getState(value);	    	
		  		    }}
			]],
			singleSelect:true,
			pagination:true,
			fitColumns:true,
			
			onDblClickRow:function(rowIndex,rowData){
				$('#ordersWindow').window('open');
				
				$('#uuid').html( rowData.uuid );//流水号				
				//创建日期
				if(rowData.createtime!=null){
					$('#createtime').html( new Date(rowData.createtime).Format('yyyy-MM-dd'));
				}
				//审核日期
				if(rowData.checktime!=null){
					$('#checktime').html( new Date(rowData.checktime).Format('yyyy-MM-dd'));
				}
				//确认日期
				if(rowData.starttime!=null){
					$('#starttime').html( new Date(rowData.starttime).Format('yyyy-MM-dd'));
				}
				//入库日期
				if(rowData.endtime!=null){
					$('#endtime').html( new Date(rowData.endtime).Format('yyyy-MM-dd'));
				}
				
				//供应商
				$('#supplier').html( $('#supplier_'+rowIndex).html() );
				//下单人
				$('#creater').html( $('#creater_'+rowIndex).html() );
				//审核人
				$('#checker').html( $('#checker_'+rowIndex).html() );
				//确认人
				$('#starter').html( $('#starter_'+rowIndex).html() );
				//入库人
				$('#ender').html( $('#ender_'+rowIndex).html() );
				
				//状态
				$('#state').html(getState(rowData.state));
				//加载数据
				$('#itemgrid').datagrid('loadData',rowData.orderdetails);
				
			}
		
	});
	
	
	$('#itemgrid').datagrid({	
		title:'订单明细',
		height:300,
		columns:[[		   
		    {field:'goodsuuid',title:'商品编号',width:100},
		    {field:'goodsname',title:'商品名称',width:200},
		    {field:'price',title:'价格',width:100},
		    {field:'num',title:'数量',width:100},
		    {field:'money',title:'金额',width:100},
		    {field:'state',title:'状态',width:80,formatter:function(value){
  		    	return getDetailState(value);	    	
  		    }}	    
		 ]],
		 fitColumns:true,
		 rownumbers:true,
		 singleSelect:true,
		 onDblClickRow:function(rowIndex,rowData){
			 $('#itemWindow').window('open');//打开窗口
			 $('#goodsuuid').html(rowData.goodsuuid);//商品ID
			 $('#goodsname').html(rowData.goodsname);//商品名称
			 $('#num').html(rowData.num);//数量
			 $('#itemuuid').val(rowData.uuid);//ID
			 
			 if(rowData.state==0){
				 $('#btnEnd').show();		 
			 }else
			 {
				 $('#btnEnd').hide(); 
			 }	
			 itemRowIndex=rowIndex;
		 }
		 
	});
	
	
	//增加订单
	$('#btnAdd').bind('click',function(){		
		$('#addOrdersWindow').window('open');
	});
	
	//审核
	$('#btnCheck').bind('click',function(){		
		$.messager.confirm('提示','确定要审核吗？',function(r){
			if(r){			
				$.ajax({
					url:'orders_doCheck.action?id='+$('#uuid').html(),
					dataType:'json',
					success:function(value){
						if(value.success){
							$('#ordersWindow').window('close');//关闭窗口
							$('#grid').datagrid('reload');//刷新表格				
						}
						$.messager.alert('提示',value.message);
					}				
				});			
			}		
		});			
	});
	
	//确认
	$('#btnStart').bind('click',function(){		
		$.messager.confirm('提示','确定要确认吗？',function(r){
			if(r){
				$.ajax({
					url:'orders_doStart.action?id='+$('#uuid').html(),
					dataType:'json',
					success:function(value){
						if(value.success){
							$('#ordersWindow').window('close');//关闭窗口
							$('#grid').datagrid('reload');//刷新表格				
						}
						$.messager.alert('提示',value.message);
					}				
				});			
			}		
		});			
	});
	
	
	

	//入库
	$('#btnEnd').bind('click',function(){		
		$.messager.confirm('提示','确定要入库吗？',function(r){
			if(r){
				//提取表单数据
				var formdata=$('#itemForm').serializeJSON();
				
				$.ajax({
					url:'orderdetail_doInStore.action',
					dataType:'json',
					data:formdata,
					type:'post',
					success:function(value){
						if(value.success){
							//关闭窗口
							$('#itemWindow').window('close');
							//更改本地数据的状态
							$('#itemgrid').datagrid('getRows')[itemRowIndex].state='1';	
							//刷新数据
							$('#itemgrid').datagrid('loadData',$('#itemgrid').datagrid('getData'));							
							//刷新主表
							$('#grid').datagrid('reload');							
						}
						$.messager.alert('提示',value.message);
					}				
				});			
			}		
		});			
	});
	
});

/**
 * 获取状态
 * @returns {String}
 */
function getState(value){
	if(value==0){
  		return '未审核';
  	}
  	if(value==1){
  		return '已审核';
  	}
  	if(value==2){
  		return '已确认';
  	}
  	if(value==3){
  		return '已入库';
  	}
}

/**
 * 获取订单明细状态
 * @returns {String}
 */
function getDetailState(value){
	if(value==0){
  		return '未入库';
  	}
  	if(value==1){
  		return '已入库';
  	}
}

function getColumns(type){
	
	
}
