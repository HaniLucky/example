package com.example.my.project.user.service.impl;

import com.example.my.project.user.mapper.UserMapper;
import com.example.my.project.user.service.UserService;
import com.example.my.project.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Covet on 2018/12/4.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> queryUser() {
        return userMapper.queryUser();
    }
}
