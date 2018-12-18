package com.example.ERP.controller;

import com.example.ERP.common.Result;
import com.example.ERP.service.EmpService;
import com.example.ERP.service.MenuService;
import com.example.ERP.vo.Emp;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Covet on 2018/12/18.
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private EmpService empService;

    /**
     * 登录
     *
     * @param emp
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value="登录", notes="登录")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Result login(Emp emp, HttpServletRequest request, HttpServletResponse response) {
        // 登录时应该使用加密后的密码
        emp.setPwd(new Md5Hash(emp.getPwd(),emp.getUsername(),2).toString());
        Emp user = empService.login(emp);
        if (user == null) {
            return new Result(false, "登录失败", null);
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", emp);
        return new Result(true, "登录成功", user);
    }

    /**
     * 返回用户名
     *
     * @param request
     * @return
     */
    @ApiOperation(value="返回用户名", notes="从session中获取用户的用户名")
    @RequestMapping(value = "/showName", method = RequestMethod.GET)
    public Result showName(HttpServletRequest request) {
        Emp user = (Emp) request.getSession().getAttribute("user");
        if (user != null) {
            return new Result(true, user.getUsername(), null);
        }
        return new Result(false, null, null);
    }

    /**
     * 退出
     */
    @ApiOperation(value="退出方法", notes="退出方法,清除session的用户信息")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Result logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return new Result(true, "SUCCESS", null);
    }


    @ApiOperation(value="修改密码", notes="修改密码")
    @RequestMapping(value = "/pwd",method = RequestMethod.PUT)
    public Result updatePwd(){
        if (true){
            return  new Result(false,"旧密码不正确",null);
        }
        if(true){
            return new Result(true,"更新成功",null);
        }
        return  new Result(false,"更新密码失败",null);
    }
}
