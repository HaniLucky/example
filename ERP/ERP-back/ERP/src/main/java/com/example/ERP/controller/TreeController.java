package com.example.ERP.controller;

import com.example.ERP.service.MenuService;
import com.example.ERP.service.RoleService;
import com.example.ERP.vo.Menu;
import com.example.ERP.vo.Tree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 构建树形菜单
 */
@RequestMapping(value = "/tree")
@RestController
@Api(value = "构建菜单", description = "构建左侧树形菜单和人员角色菜单，角色权限菜单")
public class TreeController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;
    /**
     * 获取菜单树
     */
    @ApiOperation(value="查询树形菜单json", notes="查询树形菜单json")
    @RequestMapping(value = "/meunTree", method = RequestMethod.GET)
    public Menu meunTree() {
        return menuService.menuTree();
    }

    /**
     * 根据用户获取角色
     * @param id 用户角色
     */
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public List<Tree> readEmpRoles(@PathVariable String id){
        return roleService.readEmpRoles(id);
    }

    /**
     * 根据角色获取菜单
     * @param id 角色
     */
    @RequestMapping(value = "/role/{id}",method = RequestMethod.GET)
    public List<Tree> readRoleMenus(@PathVariable String id){
        return roleService.readRoleMenus(id);
    }
}
