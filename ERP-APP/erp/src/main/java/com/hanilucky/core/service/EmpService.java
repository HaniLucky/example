package com.hanilucky.core.service;

import java.util.List;

import com.hanilucky.common.PageBean;
import com.hanilucky.core.vo.Emp;

public interface EmpService {

	Integer save(Emp emp);

	Integer delete(Integer id);

	Integer update(Emp emp);

	Emp queryById(Integer id);

	List<Emp> list();

	PageBean<Emp> page(int pageNum, int pageSize);

	PageBean<Emp> pageList(Emp emp,int pageNum, int pageSize);

}
