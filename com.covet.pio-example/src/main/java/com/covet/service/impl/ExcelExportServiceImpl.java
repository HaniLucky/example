package com.covet.service.impl;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.covet.pojo.User;
import com.covet.service.ExcelExportService;

public class ExcelExportServiceImpl implements ExcelExportService{

	@SuppressWarnings({ "resource" })
	@Override
	public void excleExport() {
	
		// 1.准备数据
		User user1 = new User("1", "张三", "男", "程序员");
		User user2 = new User("2", "李四", "男", "学生");
		User user3 = new User("3", "王二", "男", "程序员");
		User user4 = new User("4", "麻子", "男", "程序员");
		User user5 = new User("5", "王五", "男", "程序员");
		List<User> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		userList.add(user4);
		userList.add(user5);
		
		String [] heads = {"ID","姓名","性别","工作"};
		// 1.创建excel
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		// 2.创建一个sheet页
		HSSFSheet sheet = workbook.createSheet();
		
		// 设置列宽(精确设置列宽)
		sheet.setColumnWidth(5, (int) (11 + 0.72) * 256);
		sheet.setColumnWidth(5, (int) (11 + 0.72) * 256);
		sheet.setColumnWidth(6, (int) (11 + 0.72) * 256);
		sheet.setColumnWidth(7, (int) (11 + 0.72) * 256);
		
		// 设置表头信息（写入Excel左上角是从(0,0)开始的）
		// 设置表头
		// 3.表头填充数据
		Row head = sheet.createRow(0);
		for (int i = 0; i < heads.length; i++) {
			// 单元格
			Cell cell = head.createCell(i);
			// 写入数据
			cell.setCellValue(heads[i]);
		}
		
		// 内容
		// 4.填充内容数据
		int contextIndex = 1; // 内容行从第二行开始
		for (Iterator<User> iterator = userList.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			// 内容行
			Row row = sheet.createRow(contextIndex++);
			row.createCell(0).setCellValue(user.getId());
			row.createCell(1).setCellValue(user.getName()); 
			row.createCell(2).setCellValue(user.getSex()); 
			row.createCell(3).setCellValue(user.getJob()); 
			
		}
		
		String fileName = "用户列表";
		 // 数据返回到浏览器下载
		 //OutputStream out = null ;
		 try {
			 workbook.write(new FileOutputStream("D://" + fileName + ".xls"));  //保存到本地
//			 request.setCharacterEncoding("UTF-8");  
//	         response.setCharacterEncoding("UTF-8");  
//	         response.setContentType("application/x-download"); 
//	         fileName = URLEncoder.encode(fileName, "UTF-8");  
//	         response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls"); 
//	         out = response.getOutputStream();  
//			 workbook.write(out);  
//	         out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	public static void main(String[] args) {
		ExcelExportService exportService = new ExcelExportServiceImpl();
		exportService.excleExport();
	}

}
