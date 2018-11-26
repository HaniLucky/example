package com.xxx.covet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String login(User user) {
		List<User>  userList = userService.login(user);
		if (!CollectionUtils.isEmpty(userList)) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
	public String page(@PathVariable String page){
		return page;
	}

}
