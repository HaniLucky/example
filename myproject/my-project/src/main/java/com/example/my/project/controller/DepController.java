package com.example.my.project.controller;

import com.example.my.project.common.Result;
import com.example.my.project.service.DepService;
import com.example.my.project.vo.Dep;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Covet on 2018/12/5.
 */
@RestController
@RequestMapping(value = "/dep")
public class DepController {
    @Autowired
    private DepService depService;

    @RequestMapping(value = "/all")
    public Result allDep() {
        List<Dep> depList = depService.queryDepList();
        return new Result("200", "请求成功", depList);
    }
}
