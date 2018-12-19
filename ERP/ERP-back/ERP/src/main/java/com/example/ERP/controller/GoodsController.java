package com.example.ERP.controller;

import com.example.ERP.common.PageBean;
import com.example.ERP.common.Result;
import com.example.ERP.service.GoodsService;
import com.example.ERP.vo.Goods;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Covet on 2018/12/11.
 */
@RestController
@RequestMapping(value = "/goods")
@Api(value = "商品", description = "商品增删改查")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "根据id查询数据", notes = "根据id查询数据")
    @GetMapping(value = "/{id}")
    public Goods get(@PathVariable Long id) {
        return goodsService.queryById(id);
    }

    @ApiOperation(value = "查询全部", notes = "查询全部")
    @RequestMapping(value = "/s", method = RequestMethod.POST)
    public List<Goods> deps() {
        return goodsService.queryAll();
    }

    @ApiOperation(value = "根据条件分页查询", notes = "根据条件分页查询")
    @RequestMapping(value = "/listByPage", method = RequestMethod.POST)
    public PageBean<Goods> allDep(Goods goods, Integer page, Integer rows) throws ParseException {
        //   return goodsService.queryListPageBaenByWheres(goods,page,rows);
        return goodsService.queryListPageBaenByWhere(page, rows, goods);
    }

    @ApiOperation(value = "新增数据", notes = "新增数据")
    @PostMapping(value = "/")
    public Result add(@RequestBody Goods goods) {
        return new Result(true, "添加成功", goodsService.save(goods));
    }

    @ApiOperation(value = "根据id删除", notes = "根据id删除")
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable Long id) {
        Integer num = goodsService.deleteById(id);
        return new Result(true, "删除成功", num);
    }

    @ApiOperation(value = "根据id更新数据", notes = "根据id更新数据")
    @PutMapping(value = "/")
    public Result updateById(@RequestBody Goods goods) {
        Integer num = goodsService.updateById(goods);
        return new Result(true, "更新成功", num);
    }


}
