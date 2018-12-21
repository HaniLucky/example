package com.hanilucky.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanilucky.common.PageBean;
import com.hanilucky.common.Result;
import com.hanilucky.core.service.BaseService;

/**
 *
 * 抽取的通用Controller 增 删 该 查 根据id查 查全部 全部分页 根据条件分页
 * 
 * @param <T>
 */
public class BaseController<T> {

	@Autowired
	private BaseService<T> baseService;

	// 查询全部
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<T> list() {
		return baseService.list();
	}

	// 分页查询
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public PageBean<T> page(int page, int rows) {
		return baseService.page(page, rows);
	}

	// 按条件分页
	@RequestMapping(value = "/page/list", method = RequestMethod.POST)
	public PageBean<T> pageList(T t, int page, int rows) {
		return baseService.pageList(t, page, rows);
	}

	// 按id查询
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public T queryById(@PathVariable Integer id) {
		return baseService.queryById(id);
	}

	// 新增
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Result save(@RequestBody T t) {
		Integer num = baseService.save(t);
		return new Result(true, "新增成功", num);
	}

	// 删除
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable Integer id) {
		Integer num = baseService.delete(id);
		return new Result(true, "删除成功", num);
	}

	// 修改
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Result update(@RequestBody T t) {
		Integer num = baseService.update(t);
		return new Result(true, "修改成功", num);
	}

}
