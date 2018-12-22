package com.hanilucky.core.service;

import java.util.List;

import com.hanilucky.common.PageBean;
import com.hanilucky.core.vo.Store;

public interface StoreService {

	Integer save(Store store);

	Integer delete(Integer id);

	Integer update(Store store);

	Store queryById(Integer id);

	List<Store> list();

	PageBean<Store> page(int pageNum, int pageSize);

	PageBean<Store> pageList(Store store,int pageNum, int pageSize);

}
