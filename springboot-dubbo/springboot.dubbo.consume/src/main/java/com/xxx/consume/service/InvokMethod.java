package com.xxx.consume.service;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xxx.service.DemoService;

@Service
public class InvokMethod {

	
	
	@Reference
	private DemoService demoService;
	
	
	public void t(){
		String sayHello = demoService.sayHello("Hello World");
		System.err.println(sayHello);
	}
}
