package com.example.my.project.user.controller;

import com.example.my.project.common.Result;
import com.example.my.project.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Covet on 2018/12/4.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping(value = "/all")
    public Result all(){
        return new Result("200","请求成功",userService.queryUser());
    }
}
