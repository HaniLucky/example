package com.hanilucky.core.service.impl;

import java.util.ArrayList;
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

	
	/**
	 * 拼装用户的menu数据
	 * 
	 * @param empuuid
	 * @return
	 */
	@Override
	public Menu readMenuByEmpUuid(Integer empuuid) {
		// 1.获取所有的菜单
		Menu menu = menuTree();
		// 获取用户的菜单树 用户的菜单树没有层级关系
		List<Menu> userMenuList = menuMapper.selectEmpMenus(empuuid);
		List<String> userMenuUuids = new ArrayList<>();
		for (Menu menu1 : userMenuList) {
			userMenuUuids.add(menu1.getMenuid());
		}
		// 移除一级菜单用
		List<Menu> remove1 = new ArrayList<Menu>();

		// 一级菜单
		for (Menu menu1 : menu.getMenus()) { // 循环一级菜单
			// 移除二级菜单用
			List<Menu> remove2 = new ArrayList<Menu>();
			// 二级菜单
			for (Menu menu2 : menu1.getMenus()) { // 循环二级菜单
				// =====这里不应该用对象去做对比 应该用id=====
				/*
				 * if(!userMenuList.contains(menu2)){ //想知道当前的二级菜单是否用户没有
				 * remove2.add(menu2); }
				 */
				if (!userMenuUuids.contains(menu2.getMenuid())) {
					remove2.add(menu2);
				}
			}
			menu1.getMenus().removeAll(remove2); // 从一级菜单中一并移除所有没有的二级菜单
			if (menu1.getMenus().size() == 0) { // 判断一级菜单下是否有二级菜单
				remove1.add(menu1);
			}
		}
		menu.getMenus().removeAll(remove1); // 将没有二级菜单的一级菜单一并从menu中移除
		return menu;
	}

	
	/**
	 * 根据用户id获取菜单树
	 *
	 * @param id
	 * @return
	 */
	@Override
	public Menu readMenuTreeByEmpId(Integer id) {

		// 获取所有菜单合并起来的菜单树
		Menu menu = this.menuTree();
		// 返回的最终菜单
		Menu menus = createMenu(menu);
		// 获取用户的菜单树 用户的菜单树没有层级关系
		List<Menu> mepMenus = menuMapper.selectEmpMenus(id);

		// 重新组装菜单树
		List<String> empMenuUuidList = new ArrayList<>();
		for (Menu empMenu : mepMenus) {
			empMenuUuidList.add(empMenu.getMenuid());
		}
		for (Menu menu1 : menu.getMenus()) { // 循环一级菜单
			Menu m1 = createMenu(menu1);// 克隆一级菜单
			for (Menu menu2 : m1.getMenus()) {
				if (empMenuUuidList.contains(menu2.getMenuid())) { // 如果当前菜单在用户的菜单集合中
					Menu m2 = createMenu(menu2); // 克隆二级菜单
					m1.getMenus().add(m2); // 将二级菜单挂到一级菜单下
				}
			}
			if (m1.getMenus().size() > 0) { // 判断当前一级菜单下有没有二级菜单
				menu.getMenus().add(m1); // 将一级菜单挂到根菜单下
			}

		}
		return menus;
	}

	/**
	 * 克隆菜单
	 * 
	 * @param sourceMenu
	 *            原来的菜单
	 * @return
	 */
	private static Menu createMenu(Menu sourceMenu) {
		Menu menu = new Menu();
		menu.setMenuid(sourceMenu.getMenuid());
		menu.setMenuname(sourceMenu.getMenuname());
		menu.setIcon(sourceMenu.getIcon());
		menu.setUrl(sourceMenu.getUrl());
		menu.setMenus(new ArrayList<>());
		return menu;
	}

}
