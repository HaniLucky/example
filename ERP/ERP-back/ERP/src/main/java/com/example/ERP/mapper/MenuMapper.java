package com.example.ERP.mapper;

import com.example.ERP.vo.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Covet on 2018/12/16.
 */
@Mapper
public interface MenuMapper extends tk.mybatis.mapper.common.Mapper<Menu> {
    List<Menu> selectEmpMenus(String id);
}
