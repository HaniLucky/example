package com.example.originapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class OriginApplication {

	public static void main(String[] args) {
		SpringApplication.run(OriginApplication.class, args);
	}
	
	@RequestMapping(value = "app", method= RequestMethod.GET)
	public String test(){
		return "Hello World!";
	}
	
}
