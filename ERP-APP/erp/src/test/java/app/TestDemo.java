package app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.hanilucky.core.service.EmpService;
import com.hanilucky.core.vo.Emp;

/**
 * 单元测试demo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-*.xml"})
public class TestDemo {
	
	@Autowired
	private EmpService empService;
	

	/*
	 * 根据id获取用户
	 */
	@Test
	public void getEmpByIdTest(){
		Emp user = empService.queryById(1);
		System.err.println(JSON.toJSON(user));
	}
}
