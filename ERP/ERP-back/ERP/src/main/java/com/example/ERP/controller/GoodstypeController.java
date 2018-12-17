package com.example.ERP.controller;

import com.example.ERP.common.PageBean;
import com.example.ERP.common.Result;
import com.example.ERP.service.EmpService;
import com.example.ERP.service.GoodstypeService;
import com.example.ERP.vo.Dep;
import com.example.ERP.vo.Emp;
import com.example.ERP.vo.EmpVo;
import com.example.ERP.vo.Goodstype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Covet on 2018/12/11.
 */
@RestController
@RequestMapping(value = "/goodstype")
public class GoodstypeController {
    @Autowired
    private GoodstypeService goodstypeService;

    @RequestMapping(value = "/s",method = RequestMethod.POST)
    public List<Goodstype> deps() {
        return goodstypeService.queryAll();
    }
    @RequestMapping(value = "/listByPage",method = RequestMethod.POST)
    public PageBean<Goodstype> allDep(Goodstype goodstype, Integer page, Integer rows) throws ParseException {
      //   return goodstypeService.queryListPageBaenByWheres(goodstype,page,rows);
        return goodstypeService.queryListPageBaenByWhere(page,rows,goodstype);
    }

    @PostMapping(value = "/")
    public Result add(@RequestBody Goodstype goodstype) {
        return new Result(true,"添加成功",goodstypeService.save(goodstype));
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id) {
        Integer num = goodstypeService.deleteById(id);
        return new Result(true,"删除成功",num);
    }

    @PutMapping(value = "/")
    public Result update(@RequestBody Goodstype goodstype){
        Integer num = goodstypeService.updateById(goodstype);
        return new Result(true,"更新成功",num);
    }

    @GetMapping(value = "/{id}")
    public Goodstype get(@PathVariable Long id){
        return goodstypeService.queryById(id);
    }


}
