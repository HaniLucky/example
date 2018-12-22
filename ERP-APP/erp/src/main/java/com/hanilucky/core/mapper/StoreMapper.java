package com.hanilucky.core.mapper;

import java.util.List;

import com.hanilucky.core.vo.Store;

public interface StoreMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);

	List<Store> selectList(Store store);
}