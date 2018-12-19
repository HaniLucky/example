package com.example.ERP.controller;

import com.example.ERP.common.PageBean;
import com.example.ERP.common.Result;
import com.example.ERP.service.GoodstypeService;
import com.example.ERP.vo.Goodstype;
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
@RequestMapping(value = "/goodstype")
@Api(value = "商品类型", description = "商品类型增删改查")
public class GoodstypeController {
    @Autowired
    private GoodstypeService goodstypeService;

    @ApiOperation(value = "根据id查询数据", notes = "根据id查询数据")
    @GetMapping(value = "/{id}")
    public Goodstype get(@PathVariable Long id) {
        return goodstypeService.queryById(id);
    }


    @ApiOperation(value = "查询全部", notes = "查询全部")
    @RequestMapping(value = "/s", method = RequestMethod.POST)
    public List<Goodstype> deps() {
        return goodstypeService.queryAll();
    }

    @ApiOperation(value = "根据条件分页查询", notes = "根据条件分页查询")
    @RequestMapping(value = "/listByPage", method = RequestMethod.POST)
    public PageBean<Goodstype> allDep(Goodstype goodstype, Integer page, Integer rows) throws ParseException {
        //   return goodstypeService.queryListPageBaenByWheres(goodstype,page,rows);
        return goodstypeService.queryListPageBaenByWhere(page, rows, goodstype);
    }

    @ApiOperation(value = "新增数据", notes = "新增数据")
    @PostMapping(value = "/")
    public Result add(@RequestBody Goodstype goodstype) {
        return new Result(true, "添加成功", goodstypeService.save(goodstype));
    }

    @ApiOperation(value = "根据id删除", notes = "根据id删除")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id) {
        Integer num = goodstypeService.deleteById(id);
        return new Result(true, "删除成功", num);
    }

    @ApiOperation(value = "根据id更新数据", notes = "根据id更新数据")
    @PutMapping(value = "/")
    public Result update(@RequestBody Goodstype goodstype) {
        Integer num = goodstypeService.updateById(goodstype);
        return new Result(true, "更新成功", num);
    }


}
