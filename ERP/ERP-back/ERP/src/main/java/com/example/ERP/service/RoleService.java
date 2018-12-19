package com.example.ERP.service;

import com.example.ERP.common.BaseService;
import com.example.ERP.vo.Role;
import com.example.ERP.vo.Tree;

import java.util.List;

/**
 * Created by Covet on 2018/12/17.
 */
public interface RoleService extends BaseService<Role>{
    List<Tree> readRoleMenus();

    List<Tree> readRoleMenus(String id);

    List<Tree> readEmpRoles(String id);
}
