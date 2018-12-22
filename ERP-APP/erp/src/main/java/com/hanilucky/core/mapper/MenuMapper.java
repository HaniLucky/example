package com.hanilucky.core.mapper;

import java.util.List;

import com.hanilucky.core.vo.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(String menuid);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String menuid);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

	List<Menu> selectList(Menu menu);

	List<Menu> selectRoleMeunByRoleId(String id);

	List<Menu> selectEmpMenus(Integer empuuid);

}