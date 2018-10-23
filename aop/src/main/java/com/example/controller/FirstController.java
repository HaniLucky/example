package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
	
	@RequestMapping(value = "/first")
	public Object first(){
		return "First controller";
	}
	
	@RequestMapping(value = "/doError")
	public Object doError() throws Exception{
		return 2/0;
	}

}
