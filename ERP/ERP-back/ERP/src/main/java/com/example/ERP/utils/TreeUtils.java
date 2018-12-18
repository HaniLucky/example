package com.example.ERP.utils;

import com.example.ERP.vo.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Covet on 2018/12/17.
 */
public class TreeUtils {

    /**
     * 构建树形菜单
     * @param sourceList  菜单列表
     * @param rootMenu  跟菜单
     * @return
     */
    public static Menu treeRoot(List<Menu> sourceList, Menu rootMenu) {
        if (sourceList == null) {
            return null;
        }
        List<Menu> childList = new ArrayList<>();
        for (Menu menu : sourceList) {
            if (rootMenu.getMenuid().equals(menu.getPid())) {
                Menu menuChild = treeRoot(sourceList, menu);
                childList.add(menuChild);
            }
        }
        if (childList.size() == 0) {
            return rootMenu;
        }
        rootMenu.setMenus(childList);
        return rootMenu;
    }

}
