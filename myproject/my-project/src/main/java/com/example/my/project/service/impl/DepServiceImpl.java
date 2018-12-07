package com.example.my.project.service.impl;

import com.example.my.project.common.UUIDUtil;
import com.example.my.project.mapper.DepMapper;
import com.example.my.project.service.DepService;
import com.example.my.project.vo.Dep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Covet on 2018/12/5.
 */
@Service
public class DepServiceImpl implements DepService {
    @Autowired
    private DepMapper depMapper;

    @Override
    public List<Dep> queryDepList(String name, String tele) {
        Map<String,Object> condition = new HashMap<>();
        condition.put("name",name);
        condition.put("tele",tele);
        return depMapper.queryDepList(condition);
    }

    @Override
    public Integer add(Dep dep) {
        // dep.setUuid(UUIDUtil.Creeate32UUID());
        Integer num = depMapper.add(dep);
        System.out.println(dep.getUuid()); // 返回主键
        return num;
    }

    @Override
    public Integer delete(String id) {
        return depMapper.delete(id);
    }

    @Override
    public Dep getDepById(String id) {
        return depMapper.getDepById(id);
    }

    @Override
    public Integer updateDepById(Dep dep) {
        return depMapper.updateDepById(dep);
    }
}
