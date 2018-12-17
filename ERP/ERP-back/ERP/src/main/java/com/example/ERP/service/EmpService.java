package com.example.ERP.service;

import com.example.ERP.common.BaseService;
import com.example.ERP.common.PageBean;
import com.example.ERP.vo.Emp;
import com.example.ERP.vo.EmpVo;
import com.example.ERP.vo.Menu;

import java.util.List;

/**
 * Created by Covet on 2018/12/9.
 */
public interface EmpService extends BaseService<Emp>{

    PageBean<EmpVo> queryListPageBaenByWheres(EmpVo emp, Integer page, Integer rows);

    PageBean<EmpVo> test();

    Emp login(Emp emp);

    Menu menuTree(String s);

    List<Menu> menuSubordinate(String pid);
}
