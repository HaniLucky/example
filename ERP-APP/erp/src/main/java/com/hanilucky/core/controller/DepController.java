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
import com.hanilucky.core.service.DepService;
import com.hanilucky.core.vo.Dep;

@RestController
@RequestMapping(value = "/dep")
public class DepController {

	@Autowired
	private DepService depService;

	// 查询全部
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Dep> list() {
		return depService.list();
	}

	// 分页查询
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public PageBean<Dep> page(int page, int rows) {
		return depService.page(page, rows);
	}

	// 按条件分页
	@RequestMapping(value = "/page/list", method = RequestMethod.POST)
	public PageBean<Dep> pageList(Dep dep,int page, int rows) {
		return depService.pageList(dep, page, rows);
	}

	// 新增
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Result save(@RequestBody Dep dep) {
		Integer num = depService.save(dep);
		return new Result(true, "新增成功", num);
	}

	// 删除
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable Integer id) {
		Integer num = depService.delete(id);
		return new Result(true, "删除成功", num);
	}

	// 修改
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Result update(@RequestBody Dep dep) {
		Integer num = depService.update(dep);
		return new Result(true, "修改成功", num);
	}

	// 按id查询
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Dep queryById(@PathVariable Integer id) {
		return depService.queryById(id);
	}
}
