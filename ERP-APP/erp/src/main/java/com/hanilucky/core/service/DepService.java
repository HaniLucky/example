package com.hanilucky.core.service;

import java.util.List;

import com.hanilucky.common.PageBean;
import com.hanilucky.core.vo.Dep;

public interface DepService {

	Integer save(Dep dep);

	Integer delete(Integer id);

	Integer update(Dep dep);

	Dep queryById(Integer id);

	List<Dep> list();

	PageBean<Dep> page(int pageNum, int pageSize);

	PageBean<Dep> pageList(Dep dep,int pageNum, int pageSize);

}
