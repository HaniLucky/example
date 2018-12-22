package com.hanilucky.core.service;

import java.util.List;

import com.hanilucky.common.PageBean;
import com.hanilucky.core.vo.Orders;

public interface OrdersService {

	Integer save(Orders orders);

	Integer delete(Integer id);

	Integer update(Orders orders);

	Orders queryById(Integer id);

	List<Orders> list();

	PageBean<Orders> page(int pageNum, int pageSize);

	PageBean<Orders> pageList(Orders orders,int pageNum, int pageSize);

}
