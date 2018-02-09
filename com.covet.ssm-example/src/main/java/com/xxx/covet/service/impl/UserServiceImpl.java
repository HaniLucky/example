package com.xxx.covet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.covet.dao.UserMapper;
import com.xxx.covet.pojo.User;
import com.xxx.covet.pojo.UserExample;
import com.xxx.covet.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> selectAllUser() {
		UserExample example = new UserExample();
		List<User> list = userMapper.selectByExample(example);
		return list;
	}

	@Override
	public int saveUser() {
		User user = new User();
		user.setcName("test");
		int i = userMapper.insert(user);
		return i;
	}

}
