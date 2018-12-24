package com.hanilucky.core.mapper;

import java.util.List;

import com.hanilucky.core.vo.Orders;

public interface OrdersMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

	List<Orders> selectList(Orders orders);
	
	/**
	 * 一对多关联查询
	 * @param orders
	 * @return
	 */
	List<Orders> selectOrderList(Orders orders);
}