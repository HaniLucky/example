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
    public /*Result*/ List<Dep> allDep(String name,String tele) {
        logger.info("查询Dep列表,参数为:name:{},tele:{}",name,tele);
        /*List<Dep> depList = depService.queryDepList();
        return new Result("200", "请求成功", depList);*/
        return  depService.queryDepList(name,tele);
    }

    @PostMapping(value = "/add")
    public Map<String,Object> add(@RequestBody Dep dep) {
        Integer num = depService.add(dep);
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        map.put("message","添加成功");
        return map;
    }

    @GetMapping(value = "/delete")
    public Map<String,Object> delete(String id) {
        System.out.println(id);
        Integer num = depService.delete(id);
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        map.put("message","删除成功");
        return map;
    }

    @GetMapping(value = "/get")
    public /*Result<Dep>*/ Dep get(String id){

//        Dep dep =  depService.getDepById(id);
        return depService.getDepById(id);
   // return new Result(true,"success",dep);
    }

    @PostMapping(value = "/update")
    public Result update(@RequestBody Dep dep){
        Integer num = depService.updateDepById(dep);
        return new Result(true,"更新成功",num);
    }
}
