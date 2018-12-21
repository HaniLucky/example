package com.hanilucky.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hanilucky.common.Result;
import com.hanilucky.core.service.MenuService;
import com.hanilucky.core.vo.Emp;
import com.hanilucky.core.vo.Menu;

/**
 * @author Administrator
 *	首页调用方法
 */
@RestController
@RequestMapping(value = "/index")
public class IndexController {
	
	@Autowired
    private MenuService menuService;
	
	 /**
     * 获取菜单树
     */
    // @RequestMapping(value = "/meunTree", method = RequestMethod.GET)
    public Menu meunTree() {
        return menuService.menuTree();
    }
	
    /**
     * 根据用户获取菜单树
     */
    @RequestMapping(value = "/meunTree", method = RequestMethod.GET)
    public Menu menuTreeByUserId2(HttpServletRequest request) {
    	// 从session中获取用户id
    	Emp user = (Emp) request.getSession().getAttribute("user");
        return menuService.readMenuByEmpUuid(user.getUuid());
    }
    
	/**
     * 返回用户名
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/showName", method = RequestMethod.GET)
    public Result showName(HttpServletRequest request) {
        Emp user = (Emp) request.getSession().getAttribute("user");
        if (user != null) {
            return new Result(true, user.getName(), null);
        }
        return new Result(false, null, null);
    }

    /**
     * 退出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Result logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return new Result(true, "SUCCESS", null);
    }
    
    
    /**
     * 根据用户id获取菜单树  第二种方式
     *	(暂时未实现)
     * @param id
     * @return
     */
    @RequestMapping(value = "/menu2", method = RequestMethod.GET)
    public Menu menuTreeByUserId(HttpServletRequest request) {
    	Emp user = (Emp) request.getSession().getAttribute("user");
        return menuService.readMenuTreeByEmpId(user.getUuid());
    }


}
