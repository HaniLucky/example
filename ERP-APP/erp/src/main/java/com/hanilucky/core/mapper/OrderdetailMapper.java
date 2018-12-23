package com.hanilucky.core.mapper;

import java.util.List;

import com.hanilucky.core.vo.Orderdetail;

public interface OrderdetailMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(Orderdetail record);

    int insertSelective(Orderdetail record);

    Orderdetail selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(Orderdetail record);

    int updateByPrimaryKey(Orderdetail record);

	List<Orderdetail> selectList(Object object);

	Integer saveBatch(List<Orderdetail> orderdetails);
}