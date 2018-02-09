package com.xxx.covet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xxx.covet.pojo.User;
import com.xxx.covet.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/getUser")
	public String selectUser(Model model){
		List<User> user = userService.selectAllUser();
		System.err.println(user.get(0).toString());
		model.addAttribute("item", user);
		return "list";
		
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public String saveUser(){
		int i = userService.saveUser();
		return "";
		
	}

}
