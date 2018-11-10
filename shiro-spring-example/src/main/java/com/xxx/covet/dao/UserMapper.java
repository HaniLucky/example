package com.xxx.covet.dao;

import java.util.List;

import com.xxx.covet.pojo.User;

public interface UserMapper {
   
	List<User> queryAllUsers();

	User queryUser(User user);

}