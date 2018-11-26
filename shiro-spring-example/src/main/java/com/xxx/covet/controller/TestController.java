package com.xxx.covet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xxx.covet.pojo.User;
import com.xxx.covet.service.UserService;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private UserService userService;

	// http://127.0.0.1:8081/com.covet.ssm-example/test/index.action
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

}
