package com.example.vo;

import org.springframework.stereotype.Repository;

public class User {

	private String id;
	private String name;
	private String sex;
	private String address;
	private String phone;
	private String email;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String id, String name, String sex, String address, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
