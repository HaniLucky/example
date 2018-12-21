package com.hanilucky.core.service;

import java.util.List;

import com.hanilucky.common.PageBean;

public interface BaseService<T> {

	Integer save(T t);

	Integer delete(Integer id);

	Integer update(T t);

	T queryById(Integer id);

	List<T> list();

	PageBean<T> page(int pageNum, int pageSize);

	PageBean<T> pageList(T t, int pageNum, int pageSize);
}
