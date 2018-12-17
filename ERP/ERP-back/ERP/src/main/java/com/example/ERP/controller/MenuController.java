package com.example.ERP.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.ERP.service.MenuService;
import com.example.ERP.utils.TreeUtils;
import com.example.ERP.vo.Menu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Covet on 2018/12/17.
 */
@RestController
@RequestMapping(value = "/menu")
@Api(value = "菜单", description = "菜单Controller")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value="查询树形菜单json", notes="查询树形菜单json")
    @RequestMapping(value = "/meunTree", method = RequestMethod.GET)
    public Menu meunTree() {

        Menu menu =  menuService.menuTree();
        return menu;
    }
}
