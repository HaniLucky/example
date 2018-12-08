package com.example.my.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.my.project.common.UUIDUtil;
import com.example.my.project.mapper.DepMapper;
import com.example.my.project.service.DepService;
import com.example.my.project.vo.Dep;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

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
    public List<Dep> queryDepList(String name, String tele,Integer page,Integer rows) {
        PageHelper.startPage(page,rows);
        Example example = new Example(Dep.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andEqualTo("name", name);
        }
        if (!StringUtils.isEmpty(tele)) {
            criteria.andEqualTo("tele", tele);
        }
        List<Dep> list = depMapper.selectByExample(example);
        // return depMapper.queryDepList(condition);
        PageInfo<Dep> list2 = new PageInfo<>(list);
        System.err.println(JSON.toJSON(list2));
        return list;
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
        // return depMapper.delete(id);
        return 0;
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
