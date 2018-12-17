package com.example.ERP.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.ERP.common.PageBean;
import com.example.ERP.common.Result;
import com.example.ERP.service.EmpService;
import com.example.ERP.service.MenuService;
import com.example.ERP.vo.Emp;
import com.example.ERP.vo.EmpVo;
import com.example.ERP.vo.Menu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Covet on 2018/12/9.
 */
@RestController
@RequestMapping(value = "/emp")
@Api(value = "员工", description = "员工Controller")
public class EmpController {
    @Autowired
    private EmpService empService;

    @Autowired
    private MenuService menuService;

    //@PostMapping(value = "/s")
    public PageBean<Emp> listByPage(Integer page, Integer rows) {
        return empService.queryByPage(page, rows);
    }

    @ApiOperation(value="根据条件查询", notes="根据条件查询")
    @RequestMapping(value = "/listByPage", method = RequestMethod.POST)
    public PageBean<EmpVo> allDep(EmpVo emp, Integer page, Integer rows) throws ParseException {
        return empService.queryListPageBaenByWheres(emp, page, rows);
    }

    @ApiOperation(value="新增数据", notes="新增数据")
    @PostMapping(value = "/")
    public Result add(@RequestBody Emp emp) {
        return new Result(true, "添加成功", empService.save(emp));
    }

    @ApiOperation(value="根据id删除", notes="根据id删除")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id) {
        Integer num = empService.deleteById(id);
        return new Result(true, "删除成功", num);
    }

    @ApiOperation(value="更新数据", notes="更新数据")
    @PutMapping(value = "/")
    public Result update(@RequestBody Emp emp) {
        int num = empService.updateByIdSelective(emp);
        return new Result(true, "更新成功", num);
    }

    @ApiOperation(value="根据id查询", notes="根据id查询")
    @GetMapping(value = "/{id}")
    public Emp get(@PathVariable Long id) {
        return empService.queryById(id);
    }

    /**
     * 登录
     *
     * @param emp
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value="登录", notes="登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
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
    @RequestMapping(value = "showName", method = RequestMethod.GET)
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
