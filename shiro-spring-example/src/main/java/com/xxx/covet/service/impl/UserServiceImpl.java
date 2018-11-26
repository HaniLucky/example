package com.xxx.covet.service.impl;

import java.util.List;
import java.util.Set;

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
	public List<User> login(User user) {
		return userMapper.login(user);
	}

	@Override
	public User queryUserByUsername(String username) {
		return userMapper.queryUserByUsername(username);
	}

	@Override
	public Set<String> findRoles(String username) {
		return userMapper.findRoles(username);
	}

	@Override
	public Set<String> findPermissions(String username) {
		return userMapper.findPermissions(username);
	}
	
}
