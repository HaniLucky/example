package com.hanilucky.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {
	@RequestMapping(value = "/text")
	public String text (){
		return "Hello World";
	}

}
