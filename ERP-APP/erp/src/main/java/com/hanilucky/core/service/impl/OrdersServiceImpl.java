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
import com.hanilucky.constant.StateEnum;
import com.hanilucky.core.mapper.OrderdetailMapper;
import com.hanilucky.core.mapper.OrdersMapper;
import com.hanilucky.core.service.OrderdetailService;
import com.hanilucky.core.service.OrdersService;
import com.hanilucky.core.vo.Orderdetail;
import com.hanilucky.core.vo.Orders;
import com.hanilucky.utils.UserUtils;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersMapper ordersMapper;

	@Autowired
	private OrderdetailService orderdetailService;

	@Autowired
	private OrderdetailMapper orderdetailMapper;

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
		// List<Orders> list = ordersMapper.selectOrderList(null);
		// 在for 循环之外声明一个对象 没次都是不同地址值得引用
		Orderdetail orderdetail = null;
		List<Orderdetail> orderdetails = null;
		for (Orders orders : list) {
			// 将订单详情添加到订单中
			orderdetail = new Orderdetail();
			orderdetail.setOrdersuuid(orders.getUuid());
			orderdetails = orderdetailMapper.selectList(orderdetail);
			orders.setOrderdetails(orderdetails);
		}
		return list;
	}

	@Override
	public PageBean<Orders> page(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Orders> list = ordersMapper.selectList(null);
		
		// 根据订单表分页 如果是根据关联查询分页数据count的数不正确
		// List<Orders> list = ordersMapper.selectOrderList(null);

		// 在for 循环之外声明一个对象 没次都是同一个地址值重新赋值  而不是每次都创建一个对象
		Orderdetail orderdetail = null;
		List<Orderdetail> orderdetails = null;
		for (Orders orders : list) {
			// 将订单详情添加到订单中
			orderdetail = new Orderdetail();
			orderdetail.setOrdersuuid(orders.getUuid());
			orderdetails = orderdetailMapper.selectList(orderdetail);
			orders.setOrderdetails(orderdetails);
		}
		PageInfo<Orders> pageInfo = new PageInfo<>(list);
		return new PageBean<Orders>(list, pageInfo.getTotal());
	}

	@Override
	public PageBean<Orders> pageList(Orders orders, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Orders> list = ordersMapper.selectList(orders);
		
		// List<Orders> list = ordersMapper.selectOrderList(orders);
		//  在for 循环之外声明一个对象 没次都是同一个地址值重新赋值  而不是每次都创建一个对象
		Orderdetail orderdetail = null;
		List<Orderdetail> orderdetails = null;
		for (Orders order : list) {
			// 将订单详情组装到到订单数据中
			orderdetail = new Orderdetail();
			orderdetail.setOrdersuuid(order.getUuid());
			orderdetails = orderdetailMapper.selectList(orderdetail);
			order.setOrderdetails(orderdetails);
		}
		PageInfo<Orders> pageInfo = new PageInfo<>(list);
		return new PageBean<Orders>(list, pageInfo.getTotal());
	}

	/**
	 * 保存订单数据
	 * 	  保存订单主表数据并保存订单明细表数据
	 * 	 1.保存主表order数据时将数据库自增的主键返回给orderdetail的关联子段插入
	 * 		（逐渐返回xml中添加设置）
	 * 	 2.保存订单明细表使用批量添加，不应该私用单一的新增
	 */
	@Override
	public Integer saveOrder(List<Orderdetail> orderdetails, String type) {
		// 添加订单
		Orders orders = new Orders();
		// 从shiro对象中当前操作人的对象id
		orders.setCreater(UserUtils.getUserId());
		orders.setCreatetime(new Date());
		orders.setState(StateEnum.UNCHECKED.value()); // 订单状态 0 未审核 1 已审核 2已确认 3 已入库
		orders.setType(type); // 1采购订单 2 销售订单
		
		// 计算合计总价
		BigDecimal totalMoney = new BigDecimal(0);
		for (Orderdetail orderdetail : orderdetails) {
			totalMoney = totalMoney.add(orderdetail.getMoney());
		}
		orders.setTotalmoney(totalMoney);
		// 添加订单 改写新增方法将生成的主键返回
		this.save(orders);
		
		// 添加订单详情数据
		for (Orderdetail orderdetail : orderdetails) {
			orderdetail.setState(StateEnum.UNCHECKED.value());
			orderdetail.setOrdersuuid(orders.getUuid());
		}
		
		/**
		 * 批量添加
		 */
		Integer num = orderdetailService.saveBatch(orderdetails);
		// 添加订单详情多条数据
		return num;
	}

}
