package com.hanilucky.core.mapper;

import java.util.List;

import com.hanilucky.core.vo.Supplier;

public interface SupplierMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    Supplier selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);

	List<Supplier> selectList(Object object);
}