package com.example.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.example.server.UserServiceImpl;
import com.example.util.ExcelResponseHelper;
import com.example.util.ImportExcelUtil;
import com.example.vo.User;

@RestController
public class UserController {

	private final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserServiceImpl userService;

	/*
	 * 导入
	 */
	@RequestMapping(value = "import")
	public List<String> importExcel(MultipartFile file, HttpServletResponse request) {
		List<User> users = new ArrayList<>();
		List<String> errorList = new ArrayList<String>();
		try {
			ImportExcelUtil util = new ImportExcelUtil();
			InputStream input = null;
			List<List<Object>> lists = null;
			if (file.isEmpty()) {
				log.error("文件不存在！");
			} else {
				if (errorList.size() == 0) {
					String fileName = file.getOriginalFilename();
					input = file.getInputStream();
					lists = util.getBankListByExcel(input, fileName);
					input.close();
					// 循环将excel中的数据存入库
					for (int i = 1; i < lists.size(); i++) {
						List<Object> list = lists.get(i);
						User user = new User(); // 实体类，改为自己的
						user.setId(util.getFormat(String.valueOf(list.get(0))));
						user.setName(util.getFormat(String.valueOf(list.get(1))));
						user.setSex(util.getFormat(String.valueOf(list.get(2))));
						user.setAddress(util.getFormat(String.valueOf(list.get(3))));
						user.setPhone(util.getFormat(String.valueOf(list.get(4))));
						user.setEmail(util.getFormat(String.valueOf(list.get(5))));
						users.add(user);
					}
				}
			}
			System.err.println(JSON.toJSON(users.size()));
			System.err.println(JSON.toJSON(users));
		} catch (Exception e) {
			errorList.add("导入单号数据错误");
			e.printStackTrace();
			log.error("系统错误", e.fillInStackTrace());
		}
		return errorList;
	}

	/*
	 * 导出
	 */
	@RequestMapping(value = "export")
	public void export(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = "用户信息";
		String[] headerArray = { "ID", "姓名", "性别", "地址", "电话", "邮箱" };
		/**
		 * 注意这只是07版本以前的做法对应的excel文件的后缀名为.xls 07版本和07版本以后的做法excel文件的后缀名为.xlsx
		 */
		// 创建新工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 新建工作表
		HSSFSheet sheet = workbook.createSheet();
		// 创建行,行号作为参数传递给createRow()方法,第一行从0开始计算
		// HSSFRow row = sheet.createRow(0);
		// 创建单元格,row已经确定了行号,列号作为参数传递给createCell(),第一列从0开始计算
		// HSSFCell cell = row.createCell(2);
		// 设置单元格的值,即C1的值(第一行,第三列)
		// cell.setCellValue("hello sheet");

		// 创建表头 并给表头赋值
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headerArray.length; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headerArray[i]);
			cell.setCellValue(text);
		}
		List<User> users = userService.getUsers();
		// 从第二行开始给内容赋值
		/*
		List<User> users = userService.getUsers();
		for (int i = 0; i < users.size(); i++) {
			HSSFRow row = sheet.createRow(i+1);
			User user = users.get(i);
			HSSFCell cell0 = row.createCell(0);
			HSSFCell cell1 = row.createCell(1);
			HSSFCell cell2 = row.createCell(2);
			HSSFCell cell3 = row.createCell(3);
			HSSFCell cell4 = row.createCell(4);
			HSSFCell cell5 = row.createCell(5);
			cell0.setCellValue(user.getId());
			cell1.setCellValue(user.getName());
			cell2.setCellValue(user.getSex());
			cell3.setCellValue(user.getAddress());
			cell4.setCellValue(user.getPhone());
			cell5.setCellValue(user.getEmail());
		}  
		*/
		
		
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			// 从第二行开始填充数据
			row = sheet.createRow(i + 1);
			List<String> datas = new ArrayList<>();
			datas.add(user.getId());
			datas.add(user.getName());
			datas.add(user.getSex());
			datas.add(user.getAddress());
			datas.add(user.getPhone());
			datas.add(user.getEmail());
			for (int j = 0; j < datas.size(); j++) {
				String string = datas.get(j);
				HSSFCell cell = row.createCell(j);
				HSSFRichTextString richString = new HSSFRichTextString(string);
				HSSFFont font3 = workbook.createFont();
				// 定义Excel数据颜色，这里设置为蓝色
				font3.setColor(HSSFColor.BLUE.index);
				richString.applyFont(font3);
				cell.setCellValue(richString);
			}
		}
		// cell5输出到磁盘中
		downloadLocal(workbook);
		response.setContentType("application/octet-stream");
		// 将文件名转码  防止中文名乱码
		response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
		// Excel文件名
		try {
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 将workbook中的内容写入输出流中
		workbook.write(response.getOutputStream());
	}

	/*
	 * 下载到本地
	 */
	private void downloadLocal(HSSFWorkbook workbook) throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\用户信息.xls"));
		workbook.write(fos);
		workbook.close();
		fos.close();
	}
	
	
	/*
	 * 导出
	 */
	@RequestMapping(value = "exportCallBase")
	public void exportCallBase(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileName = "用户信息";
		List<User> users = userService.getUsers();
		HSSFWorkbook workbook = generateSheet(users, null);
		response.setContentType("application/octet-stream");
		// 将文件名转码  防止中文名乱码
		response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
		// Excel文件名
		try {
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 将workbook中的内容写入输出流中
		workbook.write(response.getOutputStream());
	}
	
	/**
	 * 通用导出
	 * @param data
	 * @param saveFilePath
	 * @return
	 * @throws Exception
	 */
	public static <T> HSSFWorkbook generateSheet(List<T> data, String saveFilePath){
		// 创建HSSFWorkbooke对象想
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建sheel页
		HSSFSheet sheet = wb.createSheet("sheel");
		// 创建一行数据
		HSSFRow row = sheet.createRow(0);
		// 创建你一个输出流
		// FileOutputStream out = new FileOutputStream(saveFilePath);
		// 获取集合迭代器
		Iterator<T> it = data.iterator();
		int index = 0;
		boolean flag = true;
		try {
			while (it.hasNext()) {
				row = sheet.createRow(index++);
				// 获取实例
				T t = (T) it.next();
				// 通过反射获取类的属性对象
				Field[] fields = t.getClass().getDeclaredFields();
				for (short i = 0; i < fields.length; i++) {// 循环属性添加到单元格
					Field field = fields[i];
					// 排除静态属性
					if (field.toString().contains("static")) {
						continue;
					}
					// 创建单元格
					HSSFCell cell = row.createCell((short) i);
					// 获取属性名
					String fieldName = field.getName();
					// 获取get方法名
					String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					// 获取实例class对象
					Class tCls = t.getClass();
					// 获取get方法
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					// 执行get方法获取值
					Object value = getMethod.invoke(t, new Object[] {});
					String textValue = null;
					if (value instanceof Date) {  // 判断属性值是或否是Date类型
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
						textValue = sdf.format(date);
					} else {
						if (value == null) {
							value = "";
						}
						textValue = value.toString();
					}
					if (textValue != null) {
						// 处理数字
						Pattern p = Pattern.compile("^//d+(//.//d+)?{1}quot;");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) { // 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							cell.setCellValue(textValue);
						}
					}
				}
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally {
//			out.flush();
//			wb.write(out);
//			out.close();
		}
		System.out.println("导出完毕");
		return wb;
	}

                            			
	/*
	 * 下载模板
	 */
	@RequestMapping(value = "downloadExcelTemplate")
	public void downloadExcelTemplate(HttpServletResponse response) {
		try {
			ExcelResponseHelper.download(response, "用户导入模板.xls", "user.xls");
		} catch (Exception e) {
			log.error("error downloadExcelTemplate nationIndex.xls:", e);
		}
	}

	public static void main(String[] args) {
		Field[] fields = User.class.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			System.err.println(fields[i].getName());
		}
	}
}
