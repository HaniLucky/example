package com.hanilucky.core.mapper;

import java.util.List;

import com.hanilucky.core.vo.Emp;

public interface EmpMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(Emp record);

    int insertSelective(Emp record);

    Emp selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(Emp record);

    int updateByPrimaryKey(Emp record);

	List<Emp> selectList(Emp emp);

	Emp login(Emp emp);
}