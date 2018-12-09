package com.example.ERP.service.impl;

import com.example.ERP.common.BaseServiceImpl;
import com.example.ERP.mapper.DepMapper;
import com.example.ERP.service.DepService;
import com.example.ERP.vo.Dep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Covet on 2018/12/9.
 */
@Service
public class DepServiceImpl extends BaseServiceImpl<Dep>implements DepService {
    @Autowired
    private DepMapper depMapper;

//    @Override
//    public Dep selectDepById(Long id) {
//        return depMapper.selectByPrimaryKey(id);
//    }
}
