package com.hanilucky.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hanilucky.common.PageBean;
import com.hanilucky.core.mapper.OrderdetailMapper;
import com.hanilucky.core.service.OrderdetailService;
import com.hanilucky.core.vo.Orderdetail;

@Service
public class OrderdetailServiceImpl implements OrderdetailService {

	@Autowired
	private OrderdetailMapper orderdetailMapper;

	@Override
	public Integer save(Orderdetail orderdetail) {
		return orderdetailMapper.insertSelective(orderdetail);
	}

	@Override
	public Integer delete(Integer id) {
		return orderdetailMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer update(Orderdetail orderdetail) {
		return orderdetailMapper.updateByPrimaryKeySelective(orderdetail);
	}

	@Override
	public Orderdetail queryById(Integer id) {
		return orderdetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Orderdetail> list() {
		List<Orderdetail> list = orderdetailMapper.selectList(null);
		return list;
	}

	@Override
	public PageBean<Orderdetail> page(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Orderdetail> list = orderdetailMapper.selectList(null);
		PageInfo<Orderdetail> pageInfo = new PageInfo<>(list);
		return new PageBean<Orderdetail>(list, pageInfo.getTotal());
	}

	@Override
	public PageBean<Orderdetail> pageList(Orderdetail orderdetail, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Orderdetail> list = orderdetailMapper.selectList(orderdetail);
		PageInfo<Orderdetail> pageInfo = new PageInfo<>(list);
		return new PageBean<Orderdetail>(list, pageInfo.getTotal());
	}

	@Override
	public Integer saveBatch(List<Orderdetail> orderdetails) {
		return orderdetailMapper.saveBatch(orderdetails);
	}

	/**
	 * 查询条件查询全部
	 */
	@Override
	public List<Orderdetail> list(Orderdetail orderdetail) {
		return orderdetailMapper.selectList(orderdetail);
	}

}
