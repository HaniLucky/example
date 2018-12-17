package com.example.ERP.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.ERP.common.BaseServiceImpl;
import com.example.ERP.service.MenuService;
import com.example.ERP.utils.TreeUtils;
import com.example.ERP.vo.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Covet on 2018/12/16.
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService  {

    /**
     * 获取根菜单数据
     * @return
     */
    @Override
    public Menu menuTree() {
        // 查询所有菜单数据
        List<Menu> menus = this.queryAll();
        // 查询父层级
        Menu rootMenu = this.queryById("0");
        Menu childrens = TreeUtils.treeRoot(menus, rootMenu);
        System.out.println(JSONObject.toJSON(childrens));
        return childrens;
    }
}
