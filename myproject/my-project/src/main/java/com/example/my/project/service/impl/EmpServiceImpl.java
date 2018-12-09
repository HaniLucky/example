package com.example.my.project.service.impl;

import com.example.my.project.common.BaseServiceImpl;
import com.example.my.project.service.EmpService;
import com.example.my.project.vo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Covet on 2018/12/9.
 */
@Service
public class EmpServiceImpl extends BaseServiceImpl<Emp> implements EmpService {
    @Autowired
    public EmpService empService;
}
