package com.hanilucky.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hanilucky.common.Result;
import com.hanilucky.core.service.EmpRoleService;
import com.hanilucky.core.service.RoleService;
import com.hanilucky.core.vo.EmpRole;
import com.hanilucky.core.vo.Tree;

@RestController
@RequestMapping(value = "/emp/role")
public class EmpRoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private EmpRoleService empRoleService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public List<Tree> readEmpRoles(@PathVariable String id) {
		return roleService.readEmpRoles(id);
	}

	/**
	 * 用户角色设置
	 * 
	 * @param id
	 * @param checkedStr
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Result updateEmpRole(String id, String checkedStr) {
		try {
			// 获取权限数据
			String[] split = checkedStr.split(",");
			// 清空用户角色中间表数据
			int num = empRoleService.deleteById(Integer.parseInt(id));
			if (!StringUtils.isEmpty(checkedStr)) {
				// 新增用户角色中间表数据
				for (String roleuuid : split) {
					EmpRole empRole = new EmpRole(Integer.parseInt(id), Integer.parseInt(roleuuid));
					empRoleService.save(empRole);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(true, "更新角色权限失败", null);
		}
		// 更新数据
		return new Result(true, "更新角色权限成功", null);
	}

}
