package com.hanilucky.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hanilucky.common.Result;
import com.hanilucky.core.service.MenuService;
import com.hanilucky.core.vo.Emp;
import com.hanilucky.core.vo.Menu;

import redis.clients.jedis.Jedis;

/**
 * @author Administrator 首页调用方法
 */
@RestController
@RequestMapping(value = "/index")
public class IndexController {
	
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private MenuService menuService;

	@Autowired
	private Jedis jedis;
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
	 * 根据用户id获取菜单树 第二种方式 (暂时未实现)
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/menu2", method = RequestMethod.GET)
	public Menu menuTreeByUserId(HttpServletRequest request) {
		Emp user = (Emp) request.getSession().getAttribute("user");
		return menuService.readMenuTreeByEmpId(user.getUuid());
	}

	/**
	 * 根据用户获取菜单树
	 */
	@RequestMapping(value = "/menuTree/shiro", method = RequestMethod.GET)
	public Menu menuTreeByUserId2Shiro(HttpServletRequest request) {
		Menu menu = null;
		// 从shiro中获取主体信息
		Subject subject = SecurityUtils.getSubject();
		Emp user = (Emp) subject.getPrincipal();
		// 从Redis中获取菜单树
		// 从session中获取用户id
		String meunTree =  "";
		try {
			meunTree = jedis.get("menuTree" + user.getUuid());
		} catch (Exception e) {
			log.error("============redis异常=============");
			e.printStackTrace();
		}
		
		if (meunTree == null || "".equals(meunTree)) {
			log.info("============ 从数据库中查询菜单树 ============");
			menu = menuService.readMenuByEmpUuid(user.getUuid());
			// 将查询到的菜单树存到redis中  下次刷新首页不需要再次查库
			try {
				jedis.set("menuTree" + user.getUuid(), JSON.toJSONString(menu));
			}catch (Exception e) {
				log.error("============redis异常=============");
				e.printStackTrace();
			}
		} else {
			log.info("============ 从redis中获取菜单树 ============");
			// 必须使用该方法反序列化  指定泛型
			menu = JSON.parseObject(meunTree,Menu.class);
		}
		return menu;
	}

	@RequestMapping(value = "/showName/shiro", method = RequestMethod.GET)
	public Result showNameShiro(HttpServletRequest request) {
		// Emp user = (Emp) request.getSession().getAttribute("user");
		Subject subject = SecurityUtils.getSubject();
		Emp user = (Emp) subject.getPrincipal();
		if (user != null) {
			return new Result(true, user.getName(), null);
		}
		return new Result(false, null, null);
	}

	/**
	 * 退出
	 */
	@RequestMapping(value = "/logout/shiro", method = RequestMethod.GET)
	public Result logoutShiro(HttpServletRequest request) {
		// request.getSession().removeAttribute("user");
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return new Result(true, "SUCCESS", null);
	}
}
