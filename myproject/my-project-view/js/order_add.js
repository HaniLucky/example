var isEditingRowIndex;//当前编辑行索引
$(function(){
	
	$('#ordersgrid').datagrid({		
		columns:[[		  		
		  		    {field:'goodsuuid',title:'商品编号',width:100,editor:{type:'numberbox',options:{
		  		    	disabled:true		  		    	
		  		    }}},
		  		    {field:'goodsname',title:'商品名称',width:100,editor:{type:'combobox',options:{
		  		    	url:'goods_list.action',
		  		    	valueField:'name',
		  		    	textField:'name',
		  		    	onSelect:function(record){
		  		    		//获取价格编辑框
		  		    		var priceEdt= $('#ordersgrid').datagrid('getEditor',{index: isEditingRowIndex ,field:'price'});
		  		    		if(Request['type']=='1'){//如果是采购申请
		  		    		    //为价格编辑框赋值（采购价格）
			  		    		$(priceEdt.target).val(record.inprice);
		  		    		}
		  		    		if(Request['type']=='2'){//如果是销售订单录入
		  		    		    //为价格编辑框赋值（销售价格）
			  		    		$(priceEdt.target).val(record.outprice);
			  		    	}
		  		    		
		  		    		//获取商品编号编辑框 
		  		    		var goodsuuidEdt= $('#ordersgrid').datagrid('getEditor',{index: isEditingRowIndex ,field:'goodsuuid'});
		  		    		//为商品编号编辑框赋值（商品编号）
		  		    		$(goodsuuidEdt.target).val(record.uuid);
		  		    		
		  		    		cal();//重新计算金额
		  		    		sum();//重新计算合计
		  		    		
		  		    	}
		  		    }}},
		  		    {field:'price',title:'价格',width:100,editor:{type:'numberbox',options:{
		  		    	precision:2		  		    	
		  		    }}},
		  		    {field:'num',title:'数量',width:100,editor:'numberbox'},
		  		    {field:'money',title:'金额',width:100,editor:{type:'numberbox',options:{
		  		    	precision:2,
		  		    	disabled:true
		  		    }}},
		  		    {field:'-',title:'操作',width:100,formatter:function(value,row,index){
		  		    	return "<a href='#' onClick='deleteRow("+index+")'>删除</a>";
		  		    }}
	     ]],
	     singleSelect:true,
	     toolbar: [{
				iconCls: 'icon-add',
				text:'增加',
				handler: function(){
					//增加一个行
					$('#ordersgrid').datagrid('appendRow',{'num':0,'money':0});
					//alert("关闭行："+isEditingRowIndex);
					//关闭编辑行
					$('#ordersgrid').datagrid('endEdit',isEditingRowIndex);
					//得到最后一行的索引
					isEditingRowIndex= $('#ordersgrid').datagrid('getRows').length-1;
					//alert("开启行："+isEditingRowIndex);
					//开启编辑行
					$('#ordersgrid').datagrid('beginEdit',isEditingRowIndex);
					
					
					bindGridEvent();//绑定编辑框键盘输入
				}
		  }],
		  onClickRow:function(rowIndex,rowData){
			    
			    //关闭编辑行
				$('#ordersgrid').datagrid('endEdit',isEditingRowIndex);
				//得到点击行的索引
				isEditingRowIndex= rowIndex;
				//开启编辑行
				$('#ordersgrid').datagrid('beginEdit',isEditingRowIndex);

				bindGridEvent();//绑定编辑框键盘输入
		  }
		
	});
	

	
	
	//供应商下拉列表
	
	$('#supplieruuid').combogrid({
		url:'supplier_list.action?t1.type='+Request['type'],
		columns: [[
		  		    {field:'uuid',title:'编号',width:100},
		  		    {field:'name',title:'名称',width:100},
		  		    {field:'address',title:'地址',width:100},
		  		    {field:'contact',title:'联系人',width:100},
		  		    {field:'tele',title:'电话',width:100},
		  		    {field:'email',title:'EMAIL',width:100}		  		    
			 ]] ,
		idField:'uuid',
		textField:'name',
		width:400,
		panelWidth:600,
		mode:'remote'
	});
	
	//提交订单
	$('#btnSave').bind('click',function(){
		//结束编辑行
		$('#ordersgrid').datagrid('endEdit',isEditingRowIndex);		
		var formdata=$('orderForm').serializeJSON();		
		//将表格数据转换为json字符串			
		formdata['json']=JSON.stringify($('#ordersgrid').datagrid('getRows'));		
		$.ajax({
			url:'orders_add.action?t.type='+Request['type'],
			dataType:'json',
			type:'post',
			data:formdata,
			success:function(value){
				if(value.success){
					//清空合计数
					$('#sum').html('0.00');
					//清空表格
					$('#ordersgrid').datagrid('loadData',{total:0,rows:[]});
					//关闭窗口
					$('#addOrdersWindow').window('close');
					//刷新主表格
					$('#grid').datagrid('reload');					
				}
				$.messager.alert('提示',value.message);				
			}			
		});
		
		
	});
	
	
	
})

/**
 * 计算
 */
function cal(){
	//获取价格编辑框
	var priceEdt= $('#ordersgrid').datagrid('getEditor',{index: isEditingRowIndex ,field:'price'});	
	//获取价格编辑框的值
	var price= $(priceEdt.target).val();
	//获取数量编辑框
	var numEdt= $('#ordersgrid').datagrid('getEditor',{index: isEditingRowIndex ,field:'num'});	
	//获取数量编辑框的值
	var num= $(numEdt.target).val();
	//计算金额 
	var money=(price*num).toFixed(2);
	//获取金额编辑框
	var moneyEdt=$('#ordersgrid').datagrid('getEditor',{index: isEditingRowIndex ,field:'money'});	
	//为金额编辑框赋值
	$(moneyEdt.target).val(money );
	//为金额单元格赋值
	$('#ordersgrid').datagrid('getRows')[isEditingRowIndex].money=money;
	
}

/**
 * 绑定表格内编辑框键盘输入事件
 */
function bindGridEvent(){
	
	//获取价格编辑框
	var priceEdt= $('#ordersgrid').datagrid('getEditor',{index: isEditingRowIndex ,field:'price'});
	//绑定价格编辑框键盘输入事件
	$(priceEdt.target).bind('keyup',function(){
		cal();//计算金额
		sum();//计算合计数
	});
	
	//获取数量编辑框
	var numEdt= $('#ordersgrid').datagrid('getEditor',{index: isEditingRowIndex ,field:'num'});
	//绑定数量编辑框键盘输入事件
	$(numEdt.target).bind('keyup',function(){
		cal();//计算金额
		sum();//计算合计数
	});
	
}

/**
 * 删除行
 * @param index
 */
function deleteRow(index){
	
	//结束编辑行
	$('#ordersgrid').datagrid('endEdit',isEditingRowIndex);
	
	$('#ordersgrid').datagrid('deleteRow',index);//删除行
	//读取表格数据
	var data= $('#ordersgrid').datagrid('getData');		
	//加载表格数据
	$('#ordersgrid').datagrid('loadData',data);
	
	sum();//计算合计数
}
/**
 * 求合计
 */
function sum(){
	
	//获取所有的行对象
	var rows= $('#ordersgrid').datagrid('getRows');
	var money=0;//金额
	
	for(var i=0;i<rows.length;i++){
		//parseFloat是将字符串转换为小数
		money+= parseFloat( rows[i].money) ;		
	}
	
	$('#sum').html(money.toFixed(2));//显示合计数
	
	
}
