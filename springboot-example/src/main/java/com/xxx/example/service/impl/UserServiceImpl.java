package com.xxx.example.service.impl;

import com.xxx.example.mapper.UserMapper;
import com.xxx.example.po.User;
import com.xxx.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by issuser on 2018/4/27.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> findAllUser() {
        List<User> users = userMapper.findAll();
        return users;
    }
}
