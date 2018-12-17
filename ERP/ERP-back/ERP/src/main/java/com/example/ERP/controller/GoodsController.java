package com.example.ERP.controller;

import com.example.ERP.common.PageBean;
import com.example.ERP.common.Result;
import com.example.ERP.service.GoodsService;
import com.example.ERP.vo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Covet on 2018/12/11.
 */
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/s",method = RequestMethod.POST)
    public List<Goods> deps() {
        return goodsService.queryAll();
    }
    @RequestMapping(value = "/listByPage",method = RequestMethod.POST)
    public PageBean<Goods> allDep(Goods goods, Integer page, Integer rows) throws ParseException {
        //   return goodsService.queryListPageBaenByWheres(goods,page,rows);
        return goodsService.queryListPageBaenByWhere(page,rows,goods);
    }

    @PostMapping(value = "/")
    public Result add(@RequestBody Goods goods) {
        return new Result(true,"添加成功",goodsService.save(goods));
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id) {
        Integer num = goodsService.deleteById(id);
        return new Result(true,"删除成功",num);
    }

    @PutMapping(value = "/")
    public Result update(@RequestBody Goods goods){
        Integer num = goodsService.updateById(goods);
        return new Result(true,"更新成功",num);
    }

    @GetMapping(value = "/{id}")
    public Goods get(@PathVariable Long id){
        return goodsService.queryById(id);
    }


}
