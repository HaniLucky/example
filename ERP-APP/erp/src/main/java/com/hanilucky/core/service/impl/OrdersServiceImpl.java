package com.hanilucky.core.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hanilucky.common.PageBean;
import com.hanilucky.core.mapper.OrdersMapper;
import com.hanilucky.core.service.OrderdetailService;
import com.hanilucky.core.service.OrdersService;
import com.hanilucky.core.vo.Orderdetail;
import com.hanilucky.core.vo.Orders;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersMapper ordersMapper;
	
	@Autowired
	private OrderdetailService orderdetailService;

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

	@Override
	public Integer saveOrder(List<Orderdetail> orderdetails,String type) {
		// 添加订单
		Orders orders = new Orders();
		orders.setCreater(1);
		orders.setCreatetime(new Date());
		orders.setState("0"); // 订单状态 0 未审核 1 已审核 2已确认 3 已入库
		orders.setType(type); // 1采购订单  2 销售订单
		BigDecimal totalMoney = new BigDecimal(0);
		for (Orderdetail orderdetail : orderdetails) {
			totalMoney = totalMoney.add(orderdetail.getMoney());
		}
		orders.setTotalmoney(totalMoney);
		// 添加订单    改写新增方法将生成的主键返回
		this.save(orders);
		// 添加订单详情数据
		for (Orderdetail orderdetail : orderdetails) {
			orderdetail.setState("0");
			orderdetail.setOrdersuuid(orders.getUuid());
		}
		Object json = JSON.toJSON(orderdetails.get(0));
		System.out.println(json);
		orderdetailService.saveBatch(orderdetails);
		// 添加订单详情多条数据
		return null;
	}

}
