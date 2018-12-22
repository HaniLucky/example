package com.hanilucky.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hanilucky.common.PageBean;
import com.hanilucky.core.mapper.SupplierMapper;
import com.hanilucky.core.service.SupplierService;
import com.hanilucky.core.vo.Supplier;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierMapper supplierMapper;

	@Override
	public Integer save(Supplier supplier) {
		return supplierMapper.insertSelective(supplier);
	}

	@Override
	public Integer delete(Integer id) {
		return supplierMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer update(Supplier supplier) {
		return supplierMapper.updateByPrimaryKeySelective(supplier);
	}

	@Override
	public Supplier queryById(Integer id) {
		return supplierMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Supplier> list(Supplier supplier) {
		List<Supplier> list = supplierMapper.selectList(supplier);
		return list;
	}

	@Override
	public PageBean<Supplier> page(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Supplier> list = supplierMapper.selectList(null);
		PageInfo<Supplier> pageInfo = new PageInfo<>(list);
		return new PageBean<Supplier>(list, pageInfo.getTotal());
	}

	@Override
	public PageBean<Supplier> pageList(Supplier supplier, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Supplier> list = supplierMapper.selectList(supplier);
		PageInfo<Supplier> pageInfo = new PageInfo<>(list);
		return new PageBean<Supplier>(list, pageInfo.getTotal());
	}

}
