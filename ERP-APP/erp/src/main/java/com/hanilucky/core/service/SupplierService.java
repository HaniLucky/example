package com.hanilucky.core.service;

import java.util.List;

import com.hanilucky.common.PageBean;
import com.hanilucky.core.vo.Supplier;

public interface SupplierService {

	Integer save(Supplier supplier);

	Integer delete(Integer id);

	Integer update(Supplier supplier);

	Supplier queryById(Integer id);

	List<Supplier> list(Supplier supplier);

	PageBean<Supplier> page(int pageNum, int pageSize);

	PageBean<Supplier> pageList(Supplier supplier,int pageNum, int pageSize);

}
