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
import com.hanilucky.core.service.OrdersService;
import com.hanilucky.core.vo.Orders;

@RestController
@RequestMapping(value = "/orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;

	// 查询全部
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Orders> list() {
		return ordersService.list();
	}

	// 分页查询
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public PageBean<Orders> page(int page, int rows) {
		return ordersService.page(page, rows);
	}

	// 按条件分页
	/**按条件查询
	 * @param orders
	 * @param page
	 * @param rows
	 * @param state  0 
	 * @param type	 1 
	 * @return
	 */
	@RequestMapping(value = "/page/list/{state}/{type}", method = RequestMethod.POST)
	public PageBean<Orders> pageList(Orders orders,int page, int rows,@PathVariable String state , @PathVariable String type){
		orders.setState(state);
		orders.setType(type);
		return ordersService.pageList(orders, page, rows);
	}

	// 新增
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Result save(@RequestBody Orders orders) {
		Integer num = ordersService.save(orders);
		return new Result(true, "新增成功", num);
	}

	// 删除
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable Integer id) {
		Integer num = ordersService.delete(id);
		return new Result(true, "删除成功", num);
	}

	// 修改
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Result update(@RequestBody Orders orders) {
		Integer num = ordersService.update(orders);
		return new Result(true, "修改成功", num);
	}

	// 按id查询
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Orders queryById(@PathVariable Integer id) {
		return ordersService.queryById(id);
	}
}
