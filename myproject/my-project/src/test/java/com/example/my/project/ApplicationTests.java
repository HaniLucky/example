package com.example.my.project;

import com.alibaba.fastjson.JSON;
import com.example.my.project.service.DepService;
import com.example.my.project.vo.Dep;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Autowired
    private DepService depService;

    @Test
    public void contextLoads() {
        System.out.println(JSON.toJSON(depService.queryDepList("", "")));
    }

}
