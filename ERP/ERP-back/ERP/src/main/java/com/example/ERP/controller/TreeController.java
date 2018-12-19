package com.example.ERP.controller;

import com.example.ERP.service.MenuService;
import com.example.ERP.service.RoleService;
import com.example.ERP.vo.Menu;
import com.example.ERP.vo.Tree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "查询树形菜单json", notes = "查询树形菜单json")
    @RequestMapping(value = "/meunTree", method = RequestMethod.GET)
    public Menu meunTree() {
        return menuService.menuTree();
    }

    /**
     * 根据用户获取角色
     *
     * @param id 用户角色
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户角色",notes = "根据用户获取角色列表")
    public List<Tree> readEmpRoles(@PathVariable String id) {
        return roleService.readEmpRoles(id);
    }

    /**
     * 根据角色获取菜单
     *
     * @param id 角色
     */
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "获取角色菜单",notes = "根据角色菜单数据")
    public List<Tree> readRoleMenus(@PathVariable String id) {
        return roleService.readRoleMenus(id);
    }


    /**
     * 根据用户id获取菜单树  第一种方式
     * @param id
     * @return
     */
    @RequestMapping(value = "/menu/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据用户id获取菜单树",notes = "根据用户id获取菜单树")
    public Menu menuTreeByUserId2(@PathVariable(value = "id",required = true)@ApiParam(name = "id" ,value = "用户id",required=true) String id) {
        return menuService.readMenuByEmpuuid(id);
    }

    /**
     * 根据用户id获取菜单树  第二种方式
     * @param id
     * @return
     */
    @RequestMapping(value = "/menu2/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据用户id获取菜单树",notes = "根据用户id获取菜单树")
    public Menu menuTreeByUserId(@PathVariable(value = "id",required = true)@ApiParam(name = "id" ,value = "用户id",required=true) String id) {
        return menuService.readMenuTreeByEmpId(id);
    }




}
