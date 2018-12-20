package com.hanilucky.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hanilucky.common.PageBean;
import com.hanilucky.core.mapper.EmpMapper;
import com.hanilucky.core.service.EmpService;
import com.hanilucky.core.vo.Emp;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmpMapper empMapper;

	@Override
	public Integer save(Emp emp) {
		return empMapper.insertSelective(emp);
	}

	@Override
	public Integer delete(Integer id) {
		return empMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer update(Emp emp) {
		return empMapper.updateByPrimaryKeySelective(emp);
	}

	@Override
	public Emp queryById(Integer id) {
		return empMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Emp> list() {
		List<Emp> list = empMapper.selectList(null);
		return list;
	}

	@Override
	public PageBean<Emp> page(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Emp> list = empMapper.selectList(null);
		PageInfo<Emp> pageInfo = new PageInfo<>(list);
		return new PageBean<Emp>(list, pageInfo.getTotal());
	}

	@Override
	public PageBean<Emp> pageList(Emp emp, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Emp> list = empMapper.selectList(emp);
		PageInfo<Emp> pageInfo = new PageInfo<>(list);
		return new PageBean<Emp>(list, pageInfo.getTotal());
	}

}
