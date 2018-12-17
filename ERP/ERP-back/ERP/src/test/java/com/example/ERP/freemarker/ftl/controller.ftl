package com.example.ERP.controller;

import com.example.ERP.common.PageBean;
import com.example.ERP.common.Result;
import com.example.ERP.service.${"${tempNm}"?cap_first}Service;
import com.example.ERP.vo.${"${tempNm}"?cap_first};
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/dep")
@Api(value = "部门", description = "部门Controller")
public class ${"${tempNm}"?cap_first}Controller {

	private static final Logger logger = LoggerFactory.getLogger(${"${tempNm}"?cap_first}Controller.class);

	@Autowired
	private ${"${tempNm}"?cap_first}Service ${tempNm}Service;

	@ApiOperation(value="按条件分页查询", notes="按条件分页查询")
	@RequestMapping(value = "/listByPage",method = RequestMethod.POST)
	public PageBean<${"${tempNm}"?cap_first}> allDep(${"${tempNm}"?cap_first} ${tempNm}, Integer page, Integer rows) {
		return depService.queryListPageBaenByWhere(page,rows,${tempNm});
	}

    @ApiOperation(value="查询所有", notes="查询所有")
    @RequestMapping(value = "/s",method = RequestMethod.POST)
    public List<${"${tempNm}"?cap_first}> ${tempNm}s() {
        return ${tempNm}Service.queryAll();
        }

        @ApiOperation(value="新增", notes="新增")
        @PostMapping(value = "/")
        public Result add(@RequestBody ${"${tempNm}"?cap_first} ${tempNm}) {
        return new Result(true,"添加成功",depService.save(${tempNm}));
        }

        @ApiOperation(value="根据id删除", notes="根据id删除")
        @DeleteMapping(value = "/{id}")
        public Result delete(@PathVariable Long id) {
        Integer num = depService.deleteById(id);
        return new Result(true,"删除成功",num);
        }

        @ApiOperation(value="更新数据(根据id更新)", notes="更新数据(根据id更新)")
        @PutMapping(value = "/")
        public Result update(@RequestBody ${"${tempNm}"?cap_first} ${tempNm}){
        Integer num = ${tempNm}Service.updateById(${tempNm});
        return new Result(true,"更新成功",num);
        }

        @GetMapping(value = "/{id}")
        public Dep get(@PathVariable Long id){
        return ${tempNm}Service.queryById(id);
        }


}
