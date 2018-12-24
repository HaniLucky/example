package log;

import org.junit.Test;
import org.slf4j.MDC;

import com.hanilucky.config.TraceLogger;

public class LogTest {
	@Test
	public void Test(){
//	    MDC.clear();
//	    MDC.put("sessionId" , "f9e287fad9e84cff8b2c2f2ed92adbe6");
//	    MDC.put("cityId" , "1");
//	    MDC.put("siteName" , "北京");
//	    MDC.put("userName" , "userwyh");
//	    TraceLogger. info("测试MDC打印一");
//			 
//	    MDC.put("mobile" , "110");
//	    TraceLogger. info("测试MDC打印二");
//	         
//	    MDC.put("mchId" , "12");
//	    MDC.put("mchName", "商户名称");
//	    TraceLogger. info("测试MDC打印三");
		MDC.clear();
		MDC.put("user", "张三");
		TraceLogger.info("测试MDC打印三");
	}
}

