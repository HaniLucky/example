package com.hanilucky.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hanilucky.common.PageBean;
import com.hanilucky.common.Result;
import com.hanilucky.core.service.RoleService;
import com.hanilucky.core.vo.Role;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	// 查询全部
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Role> list() {
		return roleService.list();
	}

	// 分页查询
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public PageBean<Role> page(int page, int rows) {
		return roleService.page(page, rows);
	}

	// 按条件分页
	@RequestMapping(value = "/page/list", method = RequestMethod.POST)
	public PageBean<Role> pageList(Role role,int page, int rows) {
		return roleService.pageList(role, page, rows);
	}

	// 新增
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Result save(@RequestBody Role role) {
		Integer num = roleService.save(role);
		return new Result(true, "新增成功", num);
	}

	// 删除
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable Integer id) {
		Integer num = roleService.delete(id);
		return new Result(true, "删除成功", num);
	}

	// 修改
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Result update(@RequestBody Role role) {
		Integer num = roleService.update(role);
		return new Result(true, "修改成功", num);
	}

	// 按id查询
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Role queryById(@PathVariable Integer id) {
		return roleService.queryById(id);
	}
}
