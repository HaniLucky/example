package com.xxx.covet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
	
	// http://127.0.0.1:8081/com.covet.ssm-example/test/index.action
	@RequestMapping(value = "/index" , method = RequestMethod.GET)
	public String index(){
		return "index";
	}

}
