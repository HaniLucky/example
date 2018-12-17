package com.example.ERP.controller;

import com.example.ERP.common.Result;
import com.example.ERP.service.RoleService;
import com.example.ERP.vo.Role;
import com.example.ERP.vo.Tree;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.Request;

import java.util.List;

/**
 * Created by Covet on 2018/12/17.
 */
@RestController
@RequestMapping(value = "/role")
@Api(value = "角色", description = "角色Controller")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 读取角色菜单
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public List<Tree> readRoleMenus(@PathVariable String id){
        List<Tree> list=roleService.readRoleMenus(id);
        return list;
    }

    /**
     * 获取角色集合
     * @return
     */
    @RequestMapping(value = "/s",method = RequestMethod.POST)
    public List<Role> list(){
        return roleService.queryAll();
    }
}
