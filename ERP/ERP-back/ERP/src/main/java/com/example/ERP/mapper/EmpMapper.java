package com.example.ERP.mapper;

import com.example.ERP.common.PageBean;
import com.example.ERP.vo.Dep;
import com.example.ERP.vo.Emp;
import com.example.ERP.vo.EmpVo;
import com.example.ERP.vo.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Covet on 2018/12/9.
 */
@Mapper
public interface EmpMapper extends tk.mybatis.mapper.common.Mapper<Emp> {
    List<EmpVo> queryListPageBaenByWheres(EmpVo emp);

    PageBean<EmpVo> test();

    Emp login(Emp emp);

    Menu menuTree(String menuId);

    List<Menu>  menuSubordinate(String pid);
}
