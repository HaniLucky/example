package com.hanilucky.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hanilucky.common.PageBean;
import com.hanilucky.core.mapper.StoreMapper;
import com.hanilucky.core.service.StoreService;
import com.hanilucky.core.vo.Store;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreMapper storeMapper;

	@Override
	public Integer save(Store store) {
		return storeMapper.insertSelective(store);
	}

	@Override
	public Integer delete(Integer id) {
		return storeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer update(Store store) {
		return storeMapper.updateByPrimaryKeySelective(store);
	}

	@Override
	public Store queryById(Integer id) {
		return storeMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Store> list() {
		List<Store> list = storeMapper.selectList(null);
		return list;
	}

	@Override
	public PageBean<Store> page(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Store> list = storeMapper.selectList(null);
		PageInfo<Store> pageInfo = new PageInfo<>(list);
		return new PageBean<Store>(list, pageInfo.getTotal());
	}

	@Override
	public PageBean<Store> pageList(Store store, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Store> list = storeMapper.selectList(store);
		PageInfo<Store> pageInfo = new PageInfo<>(list);
		return new PageBean<Store>(list, pageInfo.getTotal());
	}

}
