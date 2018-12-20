package com.hanilucky.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.Md5Hash;
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
	 *
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
	
}
