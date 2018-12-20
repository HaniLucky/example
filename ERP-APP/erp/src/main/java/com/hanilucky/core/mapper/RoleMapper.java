package com.hanilucky.core.mapper;

import java.util.List;

import com.hanilucky.core.vo.Menu;
import com.hanilucky.core.vo.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	List<Role> selectList(Object object);

	List<Role> selectEmpRoleByEmpId(String id);


}