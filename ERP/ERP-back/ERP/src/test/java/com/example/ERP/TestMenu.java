package com.example.ERP;

import com.alibaba.fastjson.JSONObject;
import com.example.ERP.vo.Menu;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Covet on 2018/12/13.
 */
public class TestMenu {

//             0	系统菜单	icon-sys	-	-1


    public static void main(String[] args) {
        List<Menu> menus = new ArrayList<Menu>();

        Menu menu1 = new Menu();
        menu1.setMenuid("0");
        menu1.setMenuname("系统菜单");
        menu1.setIcon("icon-sys");
        menu1.setUrl("-");
        menu1.setPid("1");

        //            100	基础数据	icon-sys	-	0

        Menu menu2 = new Menu();
        menu2.setMenuid("100");
        menu2.setMenuname("基础数据");
        menu2.setIcon("icon-sys");
        menu2.setUrl("-");
        menu2.setPid("0");
//            101	商品类型	icon-sys	goodstype.html	100

        Menu menu3 = new Menu();
        menu3.setMenuid("101");
        menu3.setMenuname("商品类型");
        menu3.setIcon("icon-sys");
        menu3.setUrl("goodstype.html");
        menu3.setPid("100");

        //            102	商品	icon-sys	goods.html	100


        Menu menu4 = new Menu();
        menu4.setMenuid("102");
        menu4.setMenuname("商品");
        menu4.setIcon("icon-sys");
        menu4.setUrl("goods.html");
        menu4.setPid("100");

//
//        //            103	供应商	icon-sys	supplier.html?type=1	100

        Menu menu5 = new Menu();
        menu5.setMenuid("103");
        menu5.setMenuname("供应商");
        menu5.setIcon("icon-sys");
        menu5.setUrl("supplier.html?type=1");
        menu5.setPid("100");

//
//        //            104	客户	icon-sys	supplier.html?type=1	100
//
        Menu menu6 = new Menu();
        menu6.setMenuid("104");
        menu6.setMenuname("客户");
        menu6.setIcon("icon-sys");
        menu6.setUrl("supplier.html?type=1");
        menu6.setPid("100");
//
//        //            105	仓库	icon-sys	store.html	100
//
        Menu menu7 = new Menu();
        menu7.setMenuid("105");
        menu7.setMenuname("仓库");
        menu7.setIcon("icon-sys");
        menu7.setUrl("store.html");
        menu7.setPid("100");
//
//        //            200	人事管理	icon-sys	-	0
//
        Menu menu8 = new Menu();
        menu8.setMenuid("200");
        menu8.setMenuname("人事管理");
        menu8.setIcon("icon-sys");
        menu8.setUrl("-");
        menu8.setPid("0");
//
//        //            201	部门	icon-sys	dep.html	200
        Menu menu9 = new Menu();
        menu9.setMenuid("201");
        menu9.setMenuname("部门");
        menu9.setIcon("icon-sys");
        menu9.setUrl("dep.html");
        menu9.setPid("200");
//
//        //            202	员工	icon-sys	emp.html	200
//
        Menu menu10 = new Menu();
        menu10.setMenuid("202");
        menu10.setMenuname("员工");
        menu10.setIcon("icon-sys");
        menu10.setUrl("emp.html");
        menu10.setPid("200");

        menus.add(menu1);
        menus.add(menu2);
        menus.add(menu3);
        menus.add(menu4);
        menus.add(menu5);
        menus.add(menu6);
        menus.add(menu7);
        menus.add(menu8);
        menus.add(menu9);
        menus.add(menu10);

        Menu childrens = treeRoot(menus, menu1);
        System.out.println(JSONObject.toJSON(childrens));
    }

    private static Menu treeRoot2(List<Menu> sourceList, Menu rootMenu) {
        if (sourceList == null){
            return  null;
        }
        List<Menu> childList = new ArrayList<>();
        for(Menu menu : sourceList){
            if(rootMenu.getMenuid().equals(menu.getPid())){
                Menu menuChild = treeRoot2(sourceList,menu);
                childList.add(menuChild);
            }
        }

        if(childList.size()==0){
            return rootMenu;
        }
        rootMenu.setMenus(childList);
        return rootMenu;
    }

    public static Menu treeRoot(List<Menu> sourceList, Menu rootMenu)
    {
        if (sourceList == null)
        {
            return null;
        }
        List<Menu> childList=new ArrayList<>();
        for (Menu menu : sourceList) {
            if(rootMenu.getMenuid().equals(menu.getPid())){
                Menu menuChild = treeRoot(sourceList, menu);
                childList.add(menuChild);
            }
        }
        if(childList.size()==0){
            return rootMenu;
        }
        rootMenu.setMenus(childList);
        return rootMenu;
    }
}
