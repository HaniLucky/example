package com.xxx.covet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xxx.covet.pojo.User;
import com.xxx.covet.service.UserService;

@Controller
@RequestMapping(value = "/user")

public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(User user) {
		User u = userService.queryUser(user);
		if (u != null)
			return "sucess";
		else
			return "fail";
	}
}
