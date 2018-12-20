package com.hanilucky.core.mapper;

import com.hanilucky.core.vo.RoleMenu;

public interface RoleMenuMapper {
	Integer deleteByPrimaryKey(Integer key);

	Integer insert(RoleMenu record);

	Integer insertSelective(RoleMenu record);
}