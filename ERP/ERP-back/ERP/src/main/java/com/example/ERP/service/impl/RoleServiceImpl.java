package com.example.ERP.service.impl;

import com.example.ERP.mapper.MenuMapper;
import com.example.ERP.mapper.RoleMapper;
import com.example.ERP.service.MenuService;
import com.example.ERP.service.RoleService;
import com.example.ERP.vo.Menu;
import com.example.ERP.vo.Role;
import com.example.ERP.vo.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Covet on 2018/12/17.
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuService menuService;


    /**
     * 读取角色菜单
     * @return
     */
    @Override
    public List<Tree> readRoleMenus() {
        List<Tree> trees = new ArrayList<>();
        //RoleMapper.readRoleMenus();
        Menu menu = menuService.menuTree();
        for (Menu menu1 : menu.getMenus()){
            // 一级菜单构建
            Tree tree1 = new Tree();
            tree1.setId(menu1.getMenuid());
            tree1.setText(menu1.getMenuname());
            for (Menu menu2:menu1.getMenus()){
                Tree tree2 = new Tree();
                tree2.setId(menu2.getMenuid());
                tree2.setText(menu2.getMenuname());
                tree1.getChildren().add(tree2); // 将二级菜单加到一级菜单
            }
            trees.add(tree1);
        }
        return trees;
    }

    @Override
    public List<Tree> readRoleMenus(String id) {
        List<Tree> trees = new ArrayList<>();
        //RoleMapper.readRoleMenus();

        List<String> menuids = new ArrayList<>();
        // 根据角色获取获取角色菜单集合
        List<Menu> menus =roleMapper.selectRoleMeunByRoleId(id);
        for (Menu menu : menus){
            menuids.add(menu.getMenuid());
        }
        // 获取根菜单数据
        Menu menu0 = menuService.menuTree();
        for (Menu menu1 : menu0.getMenus()){
            // 一级菜单构建
            Tree tree1 = new Tree();
            tree1.setId(menu1.getMenuid());// 节点id
            tree1.setText(menu1.getMenuname());// 节点名称
            // 二级菜单循环
            for (Menu menu2:menu1.getMenus()){
                Tree tree2 = new Tree();
                tree2.setId(menu2.getMenuid()); // 节点id
                tree2.setText(menu2.getMenuname()); // 节点名称
                // 条件:?? 如果当前循环的菜单 在角色的权限菜单集合,则勾选
                if (menuids.contains(menu2.getMenuid())){
                    tree2.setChecked(true); // 勾选
                }
                tree1.getChildren().add(tree2); // 将二级菜单加到一级菜单
            }
            trees.add(tree1);
        }
        return trees;
    }

    /**
     * 读取用户角色
     * @param id
     * @return
     */
    @Override
    public List<Tree> readEmpRoles(String id) {
        List<Tree> trees = new ArrayList<Tree>();
        // 获取该用户的角色列表
        List<Role> empRoleList = roleMapper.selectEmpRoleByEmpId(id);
        List<Long> roles = new ArrayList<>();
        // 获取角色所包含的角色列表id 用于对比是否包含
        for (Role role : empRoleList) {
            roles.add(role.getUuid());
        }
        // 获取全部角色
        List<Role> roleList = roleMapper.selectAll();
        // 组装Tree
        for (Role role : roleList) {
            Tree tree = new Tree();
            tree.setId(String.valueOf(role.getUuid()));   // 设置id
            tree.setText(role.getName());       // 设置描述
            if (roles.contains(role.getUuid())){   // 如果用户包含这个角色
                tree.setChecked(true);      // 勾选
            }
            trees.add(tree);   // 组装该数据
        }
        return trees;
    }

}
