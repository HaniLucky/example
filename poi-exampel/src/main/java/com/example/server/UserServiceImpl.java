package com.example.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserMapper;
import com.example.vo.User;

@Service
public class UserServiceImpl {

	@Autowired
	private UserMapper userMapper; 
	
	public List<User> getUsers(){
		return userMapper.getUsers();
	}
}
