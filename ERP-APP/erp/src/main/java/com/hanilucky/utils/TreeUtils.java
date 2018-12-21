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


}
