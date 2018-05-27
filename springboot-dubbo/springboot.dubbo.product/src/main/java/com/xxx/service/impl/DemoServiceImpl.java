package com.xxx.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.xxx.service.DemoService;

@Service(interfaceClass = DemoService.class)
@Component
public class DemoServiceImpl implements DemoService {

	public String sayHello(String name) {
		System.err.println("请求进来了");
		return name;
	}

}
