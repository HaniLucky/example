package com.example.ERP.service.impl;

import com.example.ERP.common.BaseServiceImpl;
import com.example.ERP.common.PageBean;
import com.example.ERP.mapper.EmpMapper;
import com.example.ERP.service.EmpService;
import com.example.ERP.vo.Emp;
import com.example.ERP.vo.EmpVo;
import com.example.ERP.vo.Menu;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;

/**
 * Created by Covet on 2018/12/9.
 */
@Service
public class EmpServiceImpl extends BaseServiceImpl<Emp> implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public  PageBean<EmpVo> queryListPageBaenByWheres(EmpVo emp, Integer page, Integer rows) {
        PageBean<EmpVo> pageBean = new PageBean<>();
        PageHelper.startPage(page,rows);
        List<EmpVo> list = empMapper.queryListPageBaenByWheres(emp);
        PageInfo pageInfo = new PageInfo<EmpVo>(list);
        pageBean.setTotal(pageInfo.getTotal());
        pageBean.setRows(list);
        return pageBean;
    }

    @Override
    public PageBean<EmpVo> test() {
        return empMapper.test();
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.login(emp);
    }

    @Override
    public Menu menuTree(String menuId) {
        return empMapper.menuTree(menuId);
    }

    @Override
    public List<Menu>  menuSubordinate(String pid) {
        return empMapper.menuSubordinate(pid);
    }

}
