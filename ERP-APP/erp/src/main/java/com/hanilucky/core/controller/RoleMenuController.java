package com.hanilucky.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hanilucky.common.Result;
import com.hanilucky.core.service.RoleMenuService;
import com.hanilucky.core.service.RoleService;
import com.hanilucky.core.vo.RoleMenu;
import com.hanilucky.core.vo.Tree;

@RestController
@RequestMapping(value = "/role/menu")
public class RoleMenuController {
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleMenuService roleMenuService;
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public List<Tree> readRoleMenus(@PathVariable String id) {
        return roleService.readRoleMenus(id);
    }

	
	 @RequestMapping(value = "/",method = RequestMethod.POST)
	 public Result updateRoleMenu(String id,String checkedStr){
	        try {
	            // 获取权限数据
	            // 清空角色菜单中间表数据 根据角色id
	            int num =roleMenuService.deleteById(Integer.valueOf(id));
	            // 新增角色菜单中间表数据
	            if(!StringUtils.isEmpty(checkedStr)){  // 传递的菜单项不是""时才插入  要不会分割一个串  插入一个roleId有值 menuid没有值得记录
	                String[] split = checkedStr.split(",");
	                for (String menuuuid : split) {
	                    RoleMenu roleMenu = new RoleMenu(Integer.valueOf(id),menuuuid);
	                    roleMenuService.save(roleMenu);
	                }
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	            return new Result(true,"更新角色权限失败",null);
	        }
	        // 更新数据
	        return new Result(true,"更新角色权限成功",null);
	    }
}
