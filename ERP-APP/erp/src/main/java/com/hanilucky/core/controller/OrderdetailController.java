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
import com.hanilucky.core.service.OrderdetailService;
import com.hanilucky.core.vo.Orderdetail;

@RestController
@RequestMapping(value = "/orderdetail")
public class OrderdetailController {

	@Autowired
	private OrderdetailService orderdetailService;

	// 查询全部
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Orderdetail> list() {
		return orderdetailService.list();
	}

	// 分页查询
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public PageBean<Orderdetail> page(int page, int rows) {
		return orderdetailService.page(page, rows);
	}

	// 按条件分页
	@RequestMapping(value = "/page/list", method = RequestMethod.POST)
	public PageBean<Orderdetail> pageList(Orderdetail orderdetail,int page, int rows) {
		return orderdetailService.pageList(orderdetail, page, rows);
	}

	// 新增
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Result save(@RequestBody Orderdetail orderdetail) {
		Integer num = orderdetailService.save(orderdetail);
		return new Result(true, "新增成功", num);
	}

	// 删除
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable Integer id) {
		Integer num = orderdetailService.delete(id);
		return new Result(true, "删除成功", num);
	}

	// 修改
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Result update(@RequestBody Orderdetail orderdetail) {
		Integer num = orderdetailService.update(orderdetail);
		return new Result(true, "修改成功", num);
	}

	// 按id查询
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Orderdetail queryById(@PathVariable Integer id) {
		return orderdetailService.queryById(id);
	}
}
