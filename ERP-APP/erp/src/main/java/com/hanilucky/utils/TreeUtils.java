package com.hanilucky.utils;

import java.util.ArrayList;
import java.util.List;

import com.hanilucky.core.vo.Menu;

/**
 * Created by Covet on 2018/12/17.
 */
public class TreeUtils {

    /**
     * 构建树形菜单
     * @param sourceList  菜单列表
     * @param rootMenu  一级菜单
     * @return
     */
    public static Menu treeRoot(List<Menu> sourceList, Menu rootMenu) {
        // 菜单列表为空  返回空
        if (sourceList == null) {
            return null;
        }

        // 循环下级菜单填充到一级菜单
        List<Menu> childList = new ArrayList<>();
        for (Menu menu : sourceList) {
            if (rootMenu.getMenuid().equals(menu.getPid())) {

                // 递归调用
                Menu menuChild = treeRoot(sourceList, menu);
                childList.add(menuChild);
            }
        }

        // 一级菜单下的子菜单长度为空则只返回一个一级菜单
        if (childList.size() == 0) {
            return rootMenu;
        }

        // 将下级菜单挂到上级菜单中
        rootMenu.setMenus(childList);
        return rootMenu;
    }

    /**
     * 构建用户菜单树
     * @param menu  系统所有菜单拼接成的菜单树(根菜单)
     * @param mepMenus      用户所拥有的菜单
     * @return
     */
    public static Menu empMenuTree(Menu menu, List<Menu> mepMenus) {
        List<String> empMenuUuidList = new ArrayList<>();
        for (Menu empMenu : mepMenus) {
            empMenuUuidList.add(empMenu.getMenuid());
        }
        Menu menus = createMenu(menu);
        for(Menu menu1 : menu.getMenus()){ //循环一级菜单
            Menu m1 = createMenu(menu1);// 克隆一级菜单
            for (Menu menu2:m1.getMenus()){
                if(empMenuUuidList.contains(menu2.getMenuid())){ // 如果当前菜单在用户的菜单集合中
                    Menu m2 = createMenu(menu2); // 克隆二级菜单
                    m1.getMenus().add(m2); //将二级菜单挂到一级菜单下
                }
            }
            if (m1.getMenus().size()>0){ // 判断当前一级菜单下有没有二级菜单
                menu.getMenus().add(m1); // 将一级菜单挂到根菜单下
            }

        }
        return menus;
    }

    /**
     * 克隆菜单
     * @param sourceMenu 原来的菜单
     * @return
     */
    private static Menu createMenu(Menu sourceMenu){
        Menu menu = new Menu();
        menu.setMenuid(sourceMenu.getMenuid());
        menu.setMenuname(sourceMenu.getMenuname());
        menu.setIcon(sourceMenu.getIcon());
        menu.setUrl(sourceMenu.getUrl());
        menu.setMenus(new ArrayList<>());
        return menu;
    }

}
