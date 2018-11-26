package com.xxx.covet.service;

import java.util.List;
import java.util.Set;

import com.xxx.covet.pojo.User;

public interface UserService {

	List<User>  login(User user);

	User queryUserByUsername(String username);

	Set<String> findRoles(String username);

	Set<String> findPermissions(String username);


}
