package com.hanilucky.core.mapper;

import java.util.List;

import com.hanilucky.core.vo.Dep;

public interface DepMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(Dep record);

    int insertSelective(Dep record);

    Dep selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(Dep record);

    int updateByPrimaryKey(Dep record);

	List<Dep> selectList(Dep dep);

}