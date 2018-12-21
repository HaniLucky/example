package com.hanilucky.core.controller;

import java.util.List;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hanilucky.common.PageBean;
import com.hanilucky.common.Result;
import com.hanilucky.core.service.EmpService;
import com.hanilucky.core.vo.Emp;

@RestController
@RequestMapping(value = "/emp")
public class EmpController {

	@Autowired
	private EmpService empService;

	// 查询全部
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Emp> list() {
		return empService.list();
	}

	// 分页查询
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public PageBean<Emp> page(int page, int rows) {
		return empService.page(page, rows);
	}

	// 按条件分页
	@RequestMapping(value = "/page/list", method = RequestMethod.POST)
	public PageBean<Emp> pageList(Emp emp,int page, int rows) {
		return empService.pageList(emp, page, rows);
	}

	// 新增
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Result save(@RequestBody Emp emp) {
		// 设置密码默认为用户名
		emp.setPwd(new Md5Hash(emp.getUsername(),emp.getUsername(),2).toString());
		Integer num = empService.save(emp);
		return new Result(true, "新增成功", num);
	}

	// 删除
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable Integer id) {
		Integer num = empService.delete(id);
		return new Result(true, "删除成功", num);
	}

	// 修改
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Result update(@RequestBody Emp emp) {
		Integer num = empService.update(emp);
		return new Result(true, "修改成功", num);
	}

	// 按id查询
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Emp queryById(@PathVariable Integer id) {
		return empService.queryById(id);
	}
	@RequestMapping(value = "/pwd", method = RequestMethod.PUT)
	public Result updatePwd(@RequestBody Emp emp) {
		return empService.updatePwd(emp);
	}
}
