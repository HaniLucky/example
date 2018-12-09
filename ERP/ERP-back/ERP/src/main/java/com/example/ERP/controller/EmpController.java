package com.example.ERP.controller;

import com.example.ERP.common.PageBean;
import com.example.ERP.common.Result;
import com.example.ERP.service.EmpService;
import com.example.ERP.vo.Dep;
import com.example.ERP.vo.Emp;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Covet on 2018/12/9.
 */
@RestController
@RequestMapping(value = "/emp")
public class EmpController {
    @Autowired
    private EmpService empService;

    @PostMapping(value = "/listByPage")
    public PageBean<Emp> listByPage(Integer page, Integer rows){
        return empService.queryByPage(page,rows);
    }
    @RequestMapping(value = "/all",method = RequestMethod.POST)
    public PageBean<Emp> allDep(Emp emp, Integer page, Integer rows) {
        // 查询条件重新赋值
        // dep.setName("".equals(dep.getName())?null:dep.getName());
        // dep.setTele("".equals(dep.getTele())?null:dep.getTele());
        return empService.queryListPageBaenByWhere(page,rows,emp);
    }

    @PostMapping(value = "/")
    public Result add(@RequestBody Emp emp) {
        return new Result(true,"添加成功",empService.save(emp));
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id) {
        Integer num = empService.deleteById(id);
        return new Result(true,"删除成功",num);
    }

    @PutMapping(value = "/")
    public Result update(@RequestBody Emp emp){
        Integer num = empService.updateById(emp);
        return new Result(true,"更新成功",num);
    }

    @GetMapping(value = "/{id}")
    public Emp get(@PathVariable Long id){
        return empService.queryById(id);
    }

}
