package com.example.ERP.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.ERP.common.PageBean;
import com.example.ERP.common.Result;
import com.example.ERP.service.MenuService;
import com.example.ERP.utils.TreeUtils;
import com.example.ERP.vo.Dep;
import com.example.ERP.vo.Menu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @ApiOperation(value="按条件分页查询", notes="按条件分页查询")
    @RequestMapping(value = "/listByPage",method = RequestMethod.POST)
    public PageBean<Menu> allDep(Menu menu, Integer page, Integer rows) {
        // 查询条件重新赋值
        return menuService.queryListPageBaenByWhere(page,rows,menu);
    }

    @ApiOperation(value="查询所有", notes="查询所有")
    @RequestMapping(value = "/s",method = RequestMethod.POST)
    public List<Menu> menus() {
        return menuService.queryAll();
    }

    @ApiOperation(value="新增部门", notes="新增部门")
    @PostMapping(value = "/")
    public Result add(@RequestBody Menu menu) {
        return new Result(true,"添加成功",menuService.save(menu));
    }

    @ApiOperation(value="根据id删除部门", notes="根据id删除部门")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id) {
        Integer num = menuService.deleteById(id);
        return new Result(true,"删除成功",num);
    }

    @ApiOperation(value="更新数据(根据id更新)", notes="更新数据(根据id更新)")
    @PutMapping(value = "/")
    public Result update(@RequestBody Menu menu){
        Integer num = menuService.updateById(menu);
        return new Result(true,"更新成功",num);
    }

    @GetMapping(value = "/{id}")
    public Menu get(@PathVariable Long id){
        return menuService.queryById(id);
    }



    public void exportExcel(){
        System.out.println("导出");
    }
    public void importExcel(){
        System.out.println("导入");
    }


}
