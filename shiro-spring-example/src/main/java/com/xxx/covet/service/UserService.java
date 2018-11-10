package com.xxx.covet.service;

import java.util.List;

import com.xxx.covet.pojo.User;

public interface UserService {

	List<User> users();

	User queryUser(User user);

}
