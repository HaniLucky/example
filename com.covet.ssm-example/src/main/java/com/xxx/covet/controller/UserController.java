package com.xxx.covet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.xxx.covet.pojo.User;
import com.xxx.covet.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/getUser")
	@ResponseBody
	public List<User> selectUser(){
		List<User> user = userService.selectAllUser();
		System.err.println(user.get(0).toString());
		return user;
		
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public String saveUser(){
		int i = userService.saveUser();
		return "";
		
	}

}
