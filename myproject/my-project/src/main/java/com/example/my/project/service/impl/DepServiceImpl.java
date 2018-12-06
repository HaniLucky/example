package com.example.my.project.service.impl;

import com.example.my.project.mapper.DepMapper;
import com.example.my.project.service.DepService;
import com.example.my.project.vo.Dep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Covet on 2018/12/5.
 */
@Service
public class DepServiceImpl implements DepService {
    @Autowired
    private DepMapper depMapper;

    @Override
    public List<Dep> queryDepList(String name, String tele) {
        return depMapper.queryDepList(name, tele);
    }
}
