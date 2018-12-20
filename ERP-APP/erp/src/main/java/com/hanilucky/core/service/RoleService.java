package com.hanilucky.core.service;

import java.util.List;

import com.hanilucky.common.PageBean;
import com.hanilucky.core.vo.Role;
import com.hanilucky.core.vo.Tree;

public interface RoleService {

	Integer save(Role role);

	Integer delete(Integer id);

	Integer update(Role role);

	Role queryById(Integer id);

	List<Role> list();

	PageBean<Role> page(int pageNum, int pageSize);

	PageBean<Role> pageList(Role role,int pageNum, int pageSize);

	List<Tree> readEmpRoles(String id);

	List<Tree> readRoleMenus(String id);

}
