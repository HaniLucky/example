package com.example.ERP.controller;

import com.example.ERP.common.Result;
import com.example.ERP.service.RoleMenuService;
import com.example.ERP.service.RoleService;
import com.example.ERP.vo.Role;
import com.example.ERP.vo.RoleMenu;
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

    @Autowired
    private RoleMenuService roleMenuService;

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


    @RequestMapping(value = "/menu",method = RequestMethod.POST)
    public Result updateRoleMenu(String id,String checkedStr){
        try {
            // 获取权限数据
            String[] split = checkedStr.split(",");
            int num =roleMenuService.deleteById(id);
            // 清空中间表数据
            for (String menuuuid : split) {
                RoleMenu roleMenu = new RoleMenu(id,menuuuid);
                roleMenuService.save(roleMenu);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(true,"更新角色权限失败",null);
        }
        // 更新数据
        return new Result(true,"更新角色权限成功",null);
    }


    /**
     * 读取用户角色
     * @param id  用户id
     * @return
     */
    @RequestMapping(value = "/role/{id}",method = RequestMethod.GET)
    public List<Tree> readEmpRoles(@PathVariable String id){
        List<Tree> list=roleService.readEmpRoles(id);
        return list;
    }
}
