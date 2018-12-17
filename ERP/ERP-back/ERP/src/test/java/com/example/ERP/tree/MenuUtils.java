package com.example.ERP.tree;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
/**
 * 构建树形菜单
 * MenuUtils
 * @author lishang
 * 2018年8月30日
 */
public class MenuUtils {
    /**
     * 针对二级菜单
     * treeMenuList:( ). <br/> 
     * @author lishang 
     * @param sourceList
     * @return
     */
    public static List<Menu> treeMenuList(List<Menu> sourceList){
        List<Menu> targetList=new ArrayList<>();
        if (sourceList==null) {
            return null;
        }
        List<Menu> pmenus=new ArrayList<>();
        for (Menu menu : sourceList) {
            if(menu.getParentId()==0l||menu==null){
                pmenus.add(menu);
            }
        }
        sourceList.removeAll(pmenus);
        for (int i = 0; i < pmenus.size(); i++) {
            Menu pmenu=pmenus.get(i);
            List<Menu> cmenus=new ArrayList<>();
            for (Menu menu : sourceList) {
                if(pmenu.getId().equals(menu.getParentId())){
                    cmenus.add(menu);
                }
            }
            pmenu.setChildrens(cmenus);
            sourceList.removeAll(cmenus);
        }
        return targetList;
    }
    
    public static List<Menu> treeRoot(List<Menu> sourceList){
        
        
        return sourceList;
    }
    
    
    /**
     * 递归获取菜单
     * treeRoot:( ). <br/> 
     * @author lishang 
     * @param sourceList
     * @param rootMenu
     * @return
     */
    public static Menu treeRoot(List<Menu> sourceList,Menu rootMenu)
    {
        if (sourceList == null)
        {
            return null;
        }
        List<Menu> childList=new ArrayList<>();
        for (Menu menu : sourceList) {
            if(rootMenu.getId().equals(menu.getParentId())){
                Menu menuChild = treeRoot(sourceList, menu);
                childList.add(menuChild);
            }
        }
        if(childList.size()==0){
            return rootMenu;
        }
        rootMenu.setChildrens(childList);
        return rootMenu;
    }
    
    
    public static void main(String[] args) {
        List<Menu> sourceList=new ArrayList<>();
        
        Menu menu=new Menu();
        menu.setParentId(0l);
        menu.setId(1l);
        menu.setName("菜单一级");
        sourceList.add(menu);
        
        Menu menu2=new Menu();
        menu2.setParentId(1l);
        menu2.setId(2l);
        menu2.setName("菜单二级1");
        sourceList.add(menu2);
        
        Menu menu3=new Menu();
        menu3.setParentId(2l);
        menu3.setId(3l);
        menu3.setName("菜单三级");
        sourceList.add(menu3);
        
        Menu menu4=new Menu();
        menu4.setParentId(3l);
        menu4.setId(4l);
        menu4.setName("菜单四级");
        sourceList.add(menu4);
        
        Menu menu5=new Menu();
        menu5.setParentId(1l);
        menu5.setId(6l);
        menu5.setName("菜单二级2");
        sourceList.add(menu5);
        
        Menu childrens = treeRoot(sourceList, menu);
        System.out.println(JSONObject.toJSON(childrens));
    }

}