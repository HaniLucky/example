$(function(){
	
	$('#grid').datagrid({
		url:url,
		columns:columns	
	});
	
	//查询
	$('#btnSearch').bind('click',function(){		
		var formdata=$("#searchForm").serializeJSON();		
		//日期处理
		formdata['date2']=formdata['date2']+" 23:59:59";		
		$('#grid').datagrid('load',formdata);			
		showPie(chartUrl+"?date1="+formdata['date1']+"&date2="+formdata['date2'],chartTitle);				
	});
	
	
	
	showPie(chartUrl,chartTitle);
	
});

/**
 * 输出饼图
 * @param url
 */
function showPie(url,title){
	
	//输出饼图
	$.ajax({
		url:url,
		dataType:'json',
		success:function(value){
			
			//输出图表
	        $('#container').highcharts({
	            chart: {
	                plotBackgroundColor: null,
	                plotBorderWidth: null,
	                plotShadow: false,
	                type: 'pie'
	            },
	            title: {
	                text: title
	            },
	            tooltip: {
	                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	            },
	            plotOptions: {
	                pie: {
	                    allowPointSelect: true,
	                    cursor: 'pointer',
	                    dataLabels: {
	                        enabled: false
	                    },
	                    showInLegend: true
	                }
	            },
	            series: [{
	                name: "比例",
	                colorByPoint: true,
	                data:value
	            }]
	        });
			   
			
		}			
	});
	
}