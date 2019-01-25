package app;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hanilucky.core.service.EmpService;
import com.hanilucky.core.vo.Emp;
/**
 * 单元测试
 */
public class LoginTest extends BaseTest{
	@Autowired
	private EmpService empService;
	@Test
	public void test(){
		List<Emp> list = empService.list();
		System.err.println(JSON.toJSON(list));
	}
}
