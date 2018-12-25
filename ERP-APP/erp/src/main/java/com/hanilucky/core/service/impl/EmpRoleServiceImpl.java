package com.hanilucky.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanilucky.core.mapper.EmpRoleMapper;
import com.hanilucky.core.service.EmpRoleService;
import com.hanilucky.core.vo.EmpRole;
import com.hanilucky.core.vo.Role;

@Service
public class EmpRoleServiceImpl implements EmpRoleService {

	@Autowired
	private EmpRoleMapper empRoleMapper;
	@Override
	public int deleteById(Integer id) {
		return empRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer save(EmpRole empRole) {
		return empRoleMapper.insertSelective(empRole);
	}

	@Override
	public List<Role> readEmpRoleByEmpId(Integer uuid) {
		return empRoleMapper.readEmpRoleByEmpId(uuid);
	}

	
}
