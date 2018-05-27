package com.xxx.consume.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xxx.service.DemoService;

@RestController
@RequestMapping
public class ConsumeController {

	@Reference
	private DemoService demoService;
	
	@RequestMapping("test")
	public String test(){
		return demoService.sayHello("Hello World");
	}
	
}
