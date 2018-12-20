package com.hanilucky.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hanilucky.common.PageBean;
import com.hanilucky.core.mapper.DepMapper;
import com.hanilucky.core.service.DepService;
import com.hanilucky.core.vo.Dep;

@Service
public class DepServiceImpl implements DepService {

	@Autowired
	private DepMapper depMapper;

	@Override
	public Integer save(Dep dep) {
		return depMapper.insertSelective(dep);
	}

	@Override
	public Integer delete(Integer id) {
		return depMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer update(Dep dep) {
		return depMapper.updateByPrimaryKeySelective(dep);
	}

	@Override
	public Dep queryById(Integer id) {
		return depMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Dep> list() {
		List<Dep> list = depMapper.selectList(null);
		return list;
	}

	@Override
	public PageBean<Dep> page(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Dep> list = depMapper.selectList(null);
		PageInfo<Dep> pageInfo = new PageInfo<>(list);
		return new PageBean<Dep>(list, pageInfo.getTotal());
	}

	@Override
	public PageBean<Dep> pageList(Dep dep, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Dep> list = depMapper.selectList(dep);
		PageInfo<Dep> pageInfo = new PageInfo<>(list);
		return new PageBean<Dep>(list, pageInfo.getTotal());
	}

}
