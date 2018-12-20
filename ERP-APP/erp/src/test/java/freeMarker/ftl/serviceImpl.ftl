package com.hanilucky.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hanilucky.common.PageBean;
import com.hanilucky.core.mapper.${"${tempNm}"?cap_first}Mapper;
import com.hanilucky.core.service.${"${tempNm}"?cap_first}Service;
import com.hanilucky.core.vo.${"${tempNm}"?cap_first};

@Service
public class ${"${tempNm}"?cap_first}ServiceImpl implements ${"${tempNm}"?cap_first}Service {

	@Autowired
	private ${"${tempNm}"?cap_first}Mapper ${tempNm}Mapper;

	@Override
	public Integer save(${"${tempNm}"?cap_first} ${tempNm}) {
		return ${tempNm}Mapper.insertSelective(${tempNm});
	}

	@Override
	public Integer delete(Integer id) {
		return ${tempNm}Mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer update(${"${tempNm}"?cap_first} ${tempNm}) {
		return ${tempNm}Mapper.updateByPrimaryKeySelective(${tempNm});
	}

	@Override
	public ${"${tempNm}"?cap_first} queryById(Integer id) {
		return ${tempNm}Mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<${"${tempNm}"?cap_first}> list() {
		List<${"${tempNm}"?cap_first}> list = ${tempNm}Mapper.selectList(null);
		return list;
	}

	@Override
	public PageBean<${"${tempNm}"?cap_first}> page(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<${"${tempNm}"?cap_first}> list = ${tempNm}Mapper.selectList(null);
		PageInfo<${"${tempNm}"?cap_first}> pageInfo = new PageInfo<>(list);
		return new PageBean<${"${tempNm}"?cap_first}>(list, pageInfo.getTotal());
	}

	@Override
	public PageBean<${"${tempNm}"?cap_first}> pageList(${"${tempNm}"?cap_first} ${tempNm}, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<${"${tempNm}"?cap_first}> list = ${tempNm}Mapper.selectList(${tempNm});
		PageInfo<${"${tempNm}"?cap_first}> pageInfo = new PageInfo<>(list);
		return new PageBean<${"${tempNm}"?cap_first}>(list, pageInfo.getTotal());
	}

}
