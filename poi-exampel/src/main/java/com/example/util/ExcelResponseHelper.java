package com.example.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;

public class ExcelResponseHelper {
	public static void download(HttpServletResponse response, String filename, String templateName) throws IOException {
		response.addHeader("Content-Type", "application/vnd.ms-excel");
		// response.addHeader("Content-Type", "application/octet-stream");

		// response.addHeader("Content-Disposition", "attachment; filename=" +
		// filename);
		response.addHeader("Content-Disposition",
				"attachment; filename=" + new String(filename.getBytes("gb2312"), "ISO8859-1"));
		OutputStream out = response.getOutputStream();
		download(templateName, out);
		// out.flush();
	}

	public static void download(String templateName, OutputStream out) throws IOException {
		//String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		// path = path.substring(0, path.indexOf("/WEB-INF/"));
		String path = "D:/workspace/bi_workspace/poi-exampel/src/main/resources/excel";
		path = URLDecoder.decode(path + "/" + templateName, "utf-8");
		InputStream in = new FileInputStream(path);
		// StreamUtils.copy(in, out);
		byte buffer[] = new byte[4096];
		for (int bytesRead = -1; (bytesRead = in.read(buffer)) != -1;) {
			out.write(buffer, 0, bytesRead);
		}
		out.flush();
	}
}
