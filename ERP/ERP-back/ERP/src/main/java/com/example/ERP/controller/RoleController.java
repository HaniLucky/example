package com.example.ERP.controller;

import com.example.ERP.common.PageBean;
import com.example.ERP.common.Result;
import com.example.ERP.service.EmpRoleService;
import com.example.ERP.service.RoleMenuService;
import com.example.ERP.service.RoleService;
import com.example.ERP.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private EmpRoleService empRoleService;

    /**
     * 角色权限设置
     * @param id
     * @param checkedStr
     * @return
     */
    @RequestMapping(value = "/role",method = RequestMethod.POST)
    public Result updateEmpRole (String id,String checkedStr){
        try {
            // 获取权限数据
            String[] split = checkedStr.split(",");
            int num =empRoleService.deleteById(id);
            // 清空中间表数据
            for (String roleuuid : split) {
                EmpRole empRole = new EmpRole(Integer.parseInt(id),Integer.parseInt(roleuuid));
                empRoleService.save(empRole);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(true,"更新角色权限失败",null);
        }
        // 更新数据
        return new Result(true,"更新角色权限成功",null);
    }

    /**
     * 用户角色设置
     * @param id
     * @param checkedStr
     * @return
     */
    @RequestMapping(value = "/menu",method = RequestMethod.POST)
    public Result updateRoleMenu(String id,String checkedStr){
        try {
            // 获取权限数据
            String[] split = checkedStr.split(",");
            // 清空中间表数据 根据用户id
            int num =roleMenuService.deleteById(id);

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

    @ApiOperation(value="按条件分页查询", notes="按条件分页查询")
    @RequestMapping(value = "/listByPage",method = RequestMethod.POST)
    public PageBean<Role> allDep(Role role, Integer page, Integer rows) {
        // 查询条件重新赋值
        return roleService.queryListPageBaenByWhere(page,rows,role);
    }

    @ApiOperation(value="查询所有", notes="查询所有")
    @RequestMapping(value = "/s",method = RequestMethod.POST)
    public List<Role> menus() {
        return roleService.queryAll();
    }

    @ApiOperation(value="新增部门", notes="新增部门")
    @PostMapping(value = "/")
    public Result add(@RequestBody Role role) {
        return new Result(true,"添加成功",roleService.save(role));
    }

    @ApiOperation(value="根据id删除部门", notes="根据id删除部门")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id) {
        Integer num = roleService.deleteById(id);
        return new Result(true,"删除成功",num);
    }

    @ApiOperation(value="更新数据(根据id更新)", notes="更新数据(根据id更新)")
    @PutMapping(value = "/")
    public Result update(@RequestBody Role role){
        Integer num = roleService.updateById(role);
        return new Result(true,"更新成功",num);
    }

    @GetMapping(value = "/{id}")
    public Role get(@PathVariable Long id){
        return roleService.queryById(id);
    }
}
