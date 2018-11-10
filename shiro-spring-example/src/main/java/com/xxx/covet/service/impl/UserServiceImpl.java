package com.xxx.covet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xxx.covet.dao.UserMapper;
import com.xxx.covet.pojo.User;
import com.xxx.covet.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> users() {
		
		return userMapper.queryAllUsers();
	}

	@Override
	public User queryUser(User user) {
	
		return userMapper.queryUser(user);
	}

	
}
