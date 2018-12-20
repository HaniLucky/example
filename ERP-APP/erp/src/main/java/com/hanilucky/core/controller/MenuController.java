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
import com.hanilucky.core.service.MenuService;
import com.hanilucky.core.vo.Menu;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	// 查询全部
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Menu> list() {
		return menuService.list();
	}

	// 分页查询
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public PageBean<Menu> page(int page, int rows) {
		return menuService.page(page, rows);
	}

	// 按条件分页
	@RequestMapping(value = "/page/list", method = RequestMethod.POST)
	public PageBean<Menu> pageList(Menu menu,int page, int rows) {
		return menuService.pageList(menu, page, rows);
	}

	// 新增
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Result save(@RequestBody Menu menu) {
		Integer num = menuService.save(menu);
		return new Result(true, "新增成功", num);
	}

	// 删除
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable String id) {
		Integer num = menuService.delete(id);
		return new Result(true, "删除成功", num);
	}

	// 修改
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Result update(@RequestBody Menu menu) {
		Integer num = menuService.update(menu);
		return new Result(true, "修改成功", num);
	}

	// 按id查询
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Menu queryById(@PathVariable String id) {
		return menuService.queryById(id);
	}
}
