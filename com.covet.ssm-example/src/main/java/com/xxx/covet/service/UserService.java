package com.xxx.covet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xxx.covet.pojo.User;


public interface UserService {

	List<User> selectAllUser();

	int saveUser();

}
