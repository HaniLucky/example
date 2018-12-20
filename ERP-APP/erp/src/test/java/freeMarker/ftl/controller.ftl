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
import com.hanilucky.core.service.${"${tempNm}"?cap_first}Service;
import com.hanilucky.core.vo.${"${tempNm}"?cap_first};

@RestController
@RequestMapping(value = "/${tempNm}")
public class ${"${tempNm}"?cap_first}Controller {

	@Autowired
	private ${"${tempNm}"?cap_first}Service ${tempNm}Service;

	// 查询全部
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<${"${tempNm}"?cap_first}> list() {
		return ${tempNm}Service.list();
	}

	// 分页查询
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public PageBean<${"${tempNm}"?cap_first}> page(int page, int rows) {
		return ${tempNm}Service.page(page, rows);
	}

	// 按条件分页
	@RequestMapping(value = "/page/list", method = RequestMethod.POST)
	public PageBean<${"${tempNm}"?cap_first}> pageList(${"${tempNm}"?cap_first} ${tempNm},int page, int rows) {
		return ${tempNm}Service.pageList(${tempNm}, page, rows);
	}

	// 新增
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Result save(@RequestBody ${"${tempNm}"?cap_first} ${tempNm}) {
		Integer num = ${tempNm}Service.save(${tempNm});
		return new Result(true, "新增成功", num);
	}

	// 删除
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable Integer id) {
		Integer num = ${tempNm}Service.delete(id);
		return new Result(true, "删除成功", num);
	}

	// 修改
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Result update(@RequestBody ${"${tempNm}"?cap_first} ${tempNm}) {
		Integer num = ${tempNm}Service.update(${tempNm});
		return new Result(true, "修改成功", num);
	}

	// 按id查询
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ${"${tempNm}"?cap_first} queryById(@PathVariable Integer id) {
		return ${tempNm}Service.queryById(id);
	}
}
