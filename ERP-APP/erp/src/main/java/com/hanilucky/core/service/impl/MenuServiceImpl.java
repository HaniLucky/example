package com.hanilucky.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hanilucky.common.PageBean;
import com.hanilucky.core.mapper.MenuMapper;
import com.hanilucky.core.service.MenuService;
import com.hanilucky.core.vo.Menu;
import com.hanilucky.utils.TreeUtils;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public Integer save(Menu menu) {
		return menuMapper.insertSelective(menu);
	}

	@Override
	public Integer delete(String id) {
		return menuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer update(Menu menu) {
		return menuMapper.updateByPrimaryKeySelective(menu);
	}

	@Override
	public Menu queryById(String id) {
		return menuMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Menu> list() {
		List<Menu> list = menuMapper.selectList(null);
		return list;
	}

	@Override
	public PageBean<Menu> page(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Menu> list = menuMapper.selectList(null);
		PageInfo<Menu> pageInfo = new PageInfo<>(list);
		return new PageBean<Menu>(list, pageInfo.getTotal());
	}

	@Override
	public PageBean<Menu> pageList(Menu menu, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Menu> list = menuMapper.selectList(menu);
		PageInfo<Menu> pageInfo = new PageInfo<>(list);
		return new PageBean<Menu>(list, pageInfo.getTotal());
	}

	@Override
	public Menu menuTree() {
		// 查询所有菜单数据
        List<Menu> menus = this.list();
        // 一级菜单
        Menu rootMenu = this.queryById("0");
        // 组装菜单
        return TreeUtils.treeRoot(menus, rootMenu);
	}

}
