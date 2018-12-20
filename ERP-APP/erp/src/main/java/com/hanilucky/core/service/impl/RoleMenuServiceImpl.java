package com.hanilucky.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanilucky.core.mapper.RoleMenuMapper;
import com.hanilucky.core.service.RoleMenuService;
import com.hanilucky.core.vo.RoleMenu;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {

	@Autowired
	private RoleMenuMapper roleMenuMapper;

	@Override
	public Integer deleteById(Integer id) {
		return roleMenuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer save(RoleMenu roleMenu) {
		return roleMenuMapper.insertSelective(roleMenu);
	}

}
