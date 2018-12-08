package com.example.my.project.controller;

import com.alibaba.fastjson.JSON;
import com.example.my.project.common.Result;
import com.example.my.project.service.DepService;
import com.example.my.project.vo.Dep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dep相关操作
 * Created by Covet on 2018/12/5.
 */
// @CrossOrigin
@RestController
@RequestMapping(value = "/dep")
public class DepController {
    private static final Logger logger = LoggerFactory.getLogger(DepController.class);

    @Autowired
    private DepService depService;

    @RequestMapping(value = "/all")
    public List<Dep> allDep(String name,String tele,Integer page,Integer rows) {
        return  depService.queryDepList(name,tele,page,rows);
    }

    @PostMapping(value = "/add")
    public Result add(@RequestBody Dep dep) {
        Integer num = depService.add(dep);
        return new Result(true,"添加成功",num);
    }

    @GetMapping(value = "/delete")
    public Result delete(String id) {
        Integer num = depService.delete(id);
        return new Result(true,"删除成功",num);
    }

    @GetMapping(value = "/get")
    public Dep get(String id){
        return depService.getDepById(id);
    }

    @PostMapping(value = "/update")
    public Result update(@RequestBody Dep dep){
        Integer num = depService.updateDepById(dep);
        return new Result(true,"更新成功",num);
    }

    public void exportExcel(){
        System.out.println("导出");
    }
    public void importExcel(){
        System.out.println("导入");
    }
}
