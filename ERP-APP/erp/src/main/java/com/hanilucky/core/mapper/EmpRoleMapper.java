package com.hanilucky.core.mapper;

import java.util.List;

import com.hanilucky.core.vo.EmpRole;
import com.hanilucky.core.vo.Role;

public interface EmpRoleMapper {
    int deleteByPrimaryKey(Integer key);

    int insert(EmpRole record);

    int insertSelective(EmpRole record);
    
    List<Role> readEmpRoleByEmpId(Integer uuid);
}