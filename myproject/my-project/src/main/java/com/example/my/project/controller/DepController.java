package com.example.my.project.controller;

import com.example.my.project.service.DepService;
import com.example.my.project.vo.Dep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Covet on 2018/12/5.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/dep")
public class DepController {
    @Autowired
    private DepService depService;

    @RequestMapping(value = "/all")
    public /*Result*/ List<Dep> allDep(String name,String tele) {
        System.out.println(name);
        System.out.println(tele);
        /*List<Dep> depList = depService.queryDepList();
        return new Result("200", "请求成功", depList);*/
        return  depService.queryDepList(name,tele);
    }

    @RequestMapping(value = "/add")
    public void add() {
        System.out.println("ok");
    }
}
