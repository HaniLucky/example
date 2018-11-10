package com.example.originapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class OriginApplication {

	public static void main(String[] args) {
		SpringApplication.run(OriginApplication.class, args);
	}
	
	// @CrossOrigin  注解解决跨域问题
	@RequestMapping(value = "app", method= RequestMethod.GET)
	public String test(){
		return "Hello World!";
	}
	
}
