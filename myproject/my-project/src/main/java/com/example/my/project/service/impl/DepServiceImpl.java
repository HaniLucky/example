package com.example.my.project.service.impl;

import com.example.my.project.common.BaseServiceImpl;
import com.example.my.project.mapper.DepMapper;
import com.example.my.project.service.DepService;
import com.example.my.project.vo.Dep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Covet on 2018/12/5.
 */
@Service
public class DepServiceImpl extends BaseServiceImpl<Dep> implements DepService {
    @Autowired
    private DepMapper depMapper;



}
