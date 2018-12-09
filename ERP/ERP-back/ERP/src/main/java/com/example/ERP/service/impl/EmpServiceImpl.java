package com.example.ERP.service.impl;

import com.example.ERP.common.BaseServiceImpl;
import com.example.ERP.mapper.EmpMapper;
import com.example.ERP.service.EmpService;
import com.example.ERP.vo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;

/**
 * Created by Covet on 2018/12/9.
 */
@Service
public class EmpServiceImpl extends BaseServiceImpl<Emp> implements EmpService {
    @Autowired
    private EmpMapper empMapper;

//    @Override
//    public Emp selectDepById(Long id) {
//        return empMapper.selectByPrimaryKey(id);
//    }
}
