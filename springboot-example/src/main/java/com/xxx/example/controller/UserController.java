package com.xxx.example.controller;

import com.xxx.example.po.User;
import com.xxx.example.service.UserService;
import com.xxx.example.utils.ConstantUtils;
import com.xxx.example.utils.ExampleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by issuser on 2018/4/27.
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户Controller", description = "对用户的相关参数")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/get")
    @ApiOperation(value = "get", notes = "get")
    public void get(@ApiParam(value = "用户名") String username, @ApiParam(value = "密码") String password) {
        System.out.println(username);
    }

    @ApiOperation(value = "查询所有用户", notes = "查所有用户")
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ExampleResult findAllUser() {
        System.out.println("进来了");
        try {
            List<User> userList = userService.findAllUser();
            return exampleResult(ConstantUtils.C_RESULT_SUCCESS_CODE, ConstantUtils.C_RESULT_SUCCESS_MSG, userList);
        } catch (Exception e) {
            e.printStackTrace();
            return exampleResult(ConstantUtils.C_RESULT_FAIL_CODE, ConstantUtils.C_RESULT_FAIL_MSG, null);
        }
    }

    private ExampleResult exampleResult(String code, String msg, Object data) {
        return new ExampleResult(code, msg, data);
    }
}
