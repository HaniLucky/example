package com.example.ERP.controller;

import com.example.ERP.common.PageBean;
import com.example.ERP.common.Result;
import com.example.ERP.service.EmpService;
import com.example.ERP.vo.Emp;
import com.example.ERP.vo.EmpVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/emp")
@Api(value = "员工", description = "员工Controller")
public class EmpController {
    @Autowired
    private EmpService empService;


    @ApiOperation(value="查询全部", notes="查询全部")
    @GetMapping(value = "/")
    public List<Emp> all() {
        return empService.queryAll();
    }

    @ApiOperation(value="根据id查询", notes="根据id查询")
    @GetMapping(value = "/{id}")
    public Emp get(@PathVariable Long id) {
        return empService.queryById(id);
    }

    @ApiOperation(value="根据条件查询", notes="根据条件查询")
    @RequestMapping(value = "/listByPage", method = RequestMethod.POST)
    public PageBean<EmpVo> allDep(EmpVo emp, Integer page, Integer rows) throws ParseException {
        return empService.queryListPageBaenByWheres(emp, page, rows);
    }

    @ApiOperation(value="新增数据", notes="新增数据")
    @PostMapping(value = "/")
    public Result add(@RequestBody Emp emp) {
        // 初始化密码为用户名
        emp.setPwd(new Md5Hash(emp.getUsername(),emp.getUsername(),2).toString());
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






}
