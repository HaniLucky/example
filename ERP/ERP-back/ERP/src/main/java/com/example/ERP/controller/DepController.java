package com.example.ERP.controller;

import com.example.ERP.common.PageBean;
import com.example.ERP.common.Result;
import com.example.ERP.service.DepService;
import com.example.ERP.vo.Dep;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Covet on 2018/12/9.
 */
@RestController
@RequestMapping(value = "/dep")
@Api(value = "部门", description = "部门Controller")
public class DepController {
    private static final Logger logger = LoggerFactory.getLogger(DepController.class);
    @Autowired
    private DepService depService;

    @ApiOperation(value="按条件分页查询", notes="按条件分页查询")
    @RequestMapping(value = "/listByPage",method = RequestMethod.POST)
    public PageBean<Dep> allDep(Dep dep, Integer page, Integer rows) {
        // 查询条件重新赋值
        dep.setName("".equals(dep.getName())?null:dep.getName());
        dep.setTele("".equals(dep.getTele())?null:dep.getTele());
        return depService.queryListPageBaenByWhere(page,rows,dep);
    }

    @ApiOperation(value="查询所有", notes="查询所有")
    @RequestMapping(value = "/s",method = RequestMethod.POST)
    public List<Dep> deps() {
        return depService.queryAll();
    }

    @ApiOperation(value="新增部门", notes="新增部门")
    @PostMapping(value = "/")
    public Result add(@RequestBody Dep dep) {
        return new Result(true,"添加成功",depService.save(dep));
    }

    @ApiOperation(value="根据id删除部门", notes="根据id删除部门")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id) {
        Integer num = depService.deleteById(id);
        return new Result(true,"删除成功",num);
    }

    @ApiOperation(value="更新数据(根据id更新)", notes="更新数据(根据id更新)")
    @PutMapping(value = "/")
    public Result update(@RequestBody Dep dep){
        Integer num = depService.updateById(dep);
        return new Result(true,"更新成功",num);
    }

    @GetMapping(value = "/{id}")
    public Dep get(@PathVariable Long id){
        return depService.queryById(id);
    }



    public void exportExcel(){
        System.out.println("导出");
    }
    public void importExcel(){
        System.out.println("导入");
    }
}
