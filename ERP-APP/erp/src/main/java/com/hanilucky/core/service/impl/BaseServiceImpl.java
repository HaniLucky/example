package com.hanilucky.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hanilucky.common.PageBean;
import com.hanilucky.core.mapper.BaseMapper;
import com.hanilucky.core.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T>{
	
	@Autowired 
	private BaseMapper<T> baseMapper;
	
	@Override
	public Integer save(T t) {
		return baseMapper.insertSelective(t);
	}

	@Override
	public Integer delete(Integer id) {
		return baseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer update(T t) {
		return baseMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public T queryById(Integer id) {
		return baseMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<T> list() {
		return baseMapper.selectList(null);
	}

	@Override
	public PageBean<T> page(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<T> list = baseMapper.selectList(null);
		PageInfo<T> pageInfo = new PageInfo<>(list);
		return new PageBean<T>(list, pageInfo.getTotal());
	}

	@Override
	public PageBean<T> pageList(T t, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<T> list = baseMapper.selectList(t);
		PageInfo<T> pageInfo = new PageInfo<>(list);
		return new PageBean<T>(list, pageInfo.getTotal());
	}
	
}
