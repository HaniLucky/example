package com.hanilucky.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hanilucky.common.PageBean;
import com.hanilucky.core.mapper.OrdersMapper;
import com.hanilucky.core.service.OrdersService;
import com.hanilucky.core.vo.Orders;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersMapper ordersMapper;

	@Override
	public Integer save(Orders orders) {
		return ordersMapper.insertSelective(orders);
	}

	@Override
	public Integer delete(Integer id) {
		return ordersMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer update(Orders orders) {
		return ordersMapper.updateByPrimaryKeySelective(orders);
	}

	@Override
	public Orders queryById(Integer id) {
		return ordersMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Orders> list() {
		List<Orders> list = ordersMapper.selectList(null);
		return list;
	}

	@Override
	public PageBean<Orders> page(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Orders> list = ordersMapper.selectList(null);
		PageInfo<Orders> pageInfo = new PageInfo<>(list);
		return new PageBean<Orders>(list, pageInfo.getTotal());
	}

	@Override
	public PageBean<Orders> pageList(Orders orders, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Orders> list = ordersMapper.selectList(orders);
		PageInfo<Orders> pageInfo = new PageInfo<>(list);
		return new PageBean<Orders>(list, pageInfo.getTotal());
	}

}
