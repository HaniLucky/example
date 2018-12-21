package com.hanilucky.core.service;

import java.util.List;

import com.hanilucky.common.PageBean;
import com.hanilucky.core.vo.Menu;

public interface MenuService {

	Integer save(Menu menu);

	Integer delete(String id);

	Integer update(Menu menu);

	Menu queryById(String id);

	List<Menu> list();

	PageBean<Menu> page(int pageNum, int pageSize);

	PageBean<Menu> pageList(Menu menu, int pageNum, int pageSize);

	Menu menuTree();

	Menu readMenuByEmpUuid(Integer id);

	Menu readMenuTreeByEmpId(Integer id);


	

}
