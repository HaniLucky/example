package com.example.my.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/** TODO 这里要使用 tk.mybatis.spring.annotation.MapperScan 的注解
 * 	如果在mapper接口直接使用@Mapper注解的时候回报错  会报相同bean
 */
//@tk.mybatis.spring.annotation.MapperScan("com.example.my.project.mapper")
//@MapperScan("com.example.my.project.mapper")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
