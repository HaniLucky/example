package com.hanilucky.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hanilucky.common.Result;
import com.hanilucky.core.service.EmpService;
import com.hanilucky.core.vo.Emp;

@RestController
@RequestMapping(value= "/sso")
public class LoginController {

	@Autowired
	private EmpService empService;

	/**
	 * 登录
	 * 不使用shiro完成登陆  无法对权限保护
	 * @param emp
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Result login(Emp emp, HttpServletRequest request, HttpServletResponse response) {
		// 登录时应该使用加密后的密码
		emp.setPwd(new Md5Hash(emp.getPwd(), emp.getUsername(), 2).toString());
		Emp user = empService.login(emp);
		if (user == null) {
			return new Result(false, "登录失败", null);
		}
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		return new Result(true, "登录成功", user);
	}
	
	/**
	 * 登录
	 * shiro登陆
	 * @param emp
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login/shiro", method = RequestMethod.POST)
	public Result loginShiro(Emp emp, HttpServletRequest request, HttpServletResponse response) {
		String username = emp.getUsername();
		String password = emp.getPwd();
		// 创建token
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// 获取subject(主题,当前用户的操作类,封装了一系列的操作,应用程序与shiro交互的入口部门)
		Subject subject = SecurityUtils.getSubject();
		try {
			// 调用自定义realm的认证方法(doGetAuthenticationInfo)
			subject.login(token);
			return new Result(true, "登录成功", null);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return new Result(false, "登录失败", null);
		}
	}
	
}
