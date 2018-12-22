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
import com.hanilucky.core.service.SupplierService;
import com.hanilucky.core.vo.Supplier;

@RestController
//@RequestMapping(value = "/supplier")
@RequestMapping(value = {"/supplier","/{type}/supplier"})
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	// 按id查询
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Supplier queryById(@PathVariable Integer id) {
		return supplierService.queryById(id);
	}

	// 查询全部 http://127.0.0.1:8080/1/supplier  特殊
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Supplier> list(@PathVariable String type) {
		Supplier supplier = new Supplier();
		supplier.setType(type);
		return supplierService.list(supplier);
	}

	// 分页查询
	@RequestMapping(value = "/page/{type}", method = RequestMethod.POST)
	public PageBean<Supplier> page(int page, int rows, @PathVariable String type) {
		Supplier supplier = new Supplier();
		supplier.setType(type);
		return supplierService.pageList(supplier, page, rows);
	}

	// 按条件分页
	@RequestMapping(value = "/page/list/{type}", method = RequestMethod.POST)
	public PageBean<Supplier> pageList(Supplier supplier, int page, int rows, @PathVariable String type) {
		supplier.setType(type);
		return supplierService.pageList(supplier, page, rows);
	}

	// 新增
	@RequestMapping(value = "/{type}", method = RequestMethod.POST)
	public Result save(@RequestBody Supplier supplier, @PathVariable String type) {
		supplier.setType(type);
		Integer num = supplierService.save(supplier);
		return new Result(true, "新增成功", num);
	}

	// 修改
	@RequestMapping(value = "/{type}", method = RequestMethod.PUT)
	public Result update(@RequestBody Supplier supplier,@PathVariable String type) {
		supplier.setTele(type);
		Integer num = supplierService.update(supplier);
		return new Result(true, "修改成功", num);
	}

	// 删除
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable Integer id) {
		Integer num = supplierService.delete(id);
		return new Result(true, "删除成功", num);
	}

}
