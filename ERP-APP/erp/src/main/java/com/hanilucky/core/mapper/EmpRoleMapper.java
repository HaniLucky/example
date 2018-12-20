package com.hanilucky.core.mapper;

import com.hanilucky.core.vo.EmpRole;

public interface EmpRoleMapper {
    int deleteByPrimaryKey(Integer key);

    int insert(EmpRole record);

    int insertSelective(EmpRole record);
}