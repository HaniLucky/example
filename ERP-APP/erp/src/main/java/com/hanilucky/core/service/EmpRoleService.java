package com.hanilucky.core.service;

import java.util.List;

import com.hanilucky.core.vo.EmpRole;
import com.hanilucky.core.vo.Role;

public interface EmpRoleService {

	int deleteById(Integer id);

	Integer save(EmpRole empRole);

	List<Role> readEmpRoleByEmpId(Integer uuid);

}
