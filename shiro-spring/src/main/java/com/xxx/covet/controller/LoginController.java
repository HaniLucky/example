package com.xxx.covet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xxx.covet.pojo.User;
import com.xxx.covet.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(User user, Model model) {
		// 调用登录方法
		User loginUser = userService.login(user);
		if (loginUser != null) {
			model.addAttribute("sucessMsg", "登录成功");
			model.addAttribute("name", user.getUsername());
			return "jsp/success.jsp";
		} else {
			model.addAttribute("sucessMsg", "登录失败，用户名或密码不存在");
			return "jsp/fail.jsp";
		}
	}
	
	
}
