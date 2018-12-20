package com.example.ERP.service.impl;

import com.example.ERP.mapper.MenuMapper;
import com.example.ERP.service.MenuService;
import com.example.ERP.utils.TreeUtils;
import com.example.ERP.vo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Covet on 2018/12/16.
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 获取根菜单数据
     *
     * @return
     */
    @Override
    public Menu menuTree() {
        // 查询所有菜单数据
        List<Menu> menus = this.queryAll();
        // 一级菜单
        Menu rootMenu = this.queryById("0");
        // 组装菜单
        return TreeUtils.treeRoot(menus, rootMenu);
    }

    /**
     * 根据用户id获取菜单树
     *
     * @param id
     * @return
     */
    @Override
    public Menu readMenuTreeByEmpId(String id) {

        // 获取所有菜单合并起来的菜单树
        Menu menu = this.menuTree();
        // 获取用户的菜单树  用户的菜单树没有层级关系
        List<Menu> mepMenus =menuMapper.selectEmpMenus(id);

        // 重新组装菜单树
        Menu empMenuTree = TreeUtils.empMenuTree(menu, mepMenus);
        return empMenuTree;
    }



    /**
     * 拼装用户的menu数据
     * @param empuuid
     * @return
     */
    @Override
    public Menu readMenuByEmpUuid(String empuuid){
        //1.获取所有的菜单
        Menu menu = menuTree();
        // 获取用户的菜单树  用户的菜单树没有层级关系
        List<Menu> userMenuList =menuMapper.selectEmpMenus(empuuid);
        List<String> userMenuUuids = new ArrayList<>();
        for (Menu menu1 : userMenuList) {
            userMenuUuids.add(menu1.getMenuid());
        }
        //移除一级菜单用
        List<Menu> remove1 = new ArrayList();

        //一级菜单
        for(Menu menu1:menu.getMenus()){ // 循环一级菜单
            //移除二级菜单用
            List<Menu> remove2 = new ArrayList();
            //二级菜单
            for(Menu menu2:menu1.getMenus()){  // 循环二级菜单
                // =====这里不应该用对象去做对比  应该用id=====
                /*if(!userMenuList.contains(menu2)){  //想知道当前的二级菜单是否用户没有
                    remove2.add(menu2);
                }*/
                if(!userMenuUuids.contains(menu2.getMenuid())){
                    remove2.add(menu2);
                }
            }
            menu1.getMenus().removeAll(remove2);  //从一级菜单中一并移除所有没有的二级菜单
            if(menu1.getMenus().size() == 0){  //判断一级菜单下是否有二级菜单
                remove1.add(menu1);
            }
        }
        menu.getMenus().removeAll(remove1);   //将没有二级菜单的一级菜单一并从menu中移除
        return menu;
    }
}
