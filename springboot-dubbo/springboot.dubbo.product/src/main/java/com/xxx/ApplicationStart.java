package com.xxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

@SpringBootApplication
@EnableDubboConfiguration
public class ApplicationStart {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationStart.class, args);
	}

}
