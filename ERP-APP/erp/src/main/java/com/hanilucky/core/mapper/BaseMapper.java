package com.hanilucky.core.mapper;

import java.util.List;

public interface BaseMapper<T> {
	int deleteByPrimaryKey(Integer uuid);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

	List<T> selectList(T t);
}
