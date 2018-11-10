package com.xxx.covet.pojo;

import java.util.Date;

public class User {
	private String username;
	private String password;
	private String age;
	private String name;
	private String role;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String password, String age, String name, String role) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
		this.name = name;
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", age=" + age + ", name=" + name + ", role="
				+ role + "]";
	}

	

}