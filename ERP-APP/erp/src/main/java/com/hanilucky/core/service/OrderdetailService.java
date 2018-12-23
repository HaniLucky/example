package com.hanilucky.core.service;

import java.util.List;

import com.hanilucky.common.PageBean;
import com.hanilucky.core.vo.Orderdetail;

public interface OrderdetailService {

	Integer save(Orderdetail orderdetail);

	Integer delete(Integer id);

	Integer update(Orderdetail orderdetail);

	Orderdetail queryById(Integer id);

	List<Orderdetail> list();

	PageBean<Orderdetail> page(int pageNum, int pageSize);

	PageBean<Orderdetail> pageList(Orderdetail orderdetail,int pageNum, int pageSize);

	Integer saveBatch(List<Orderdetail> orderdetails);

}
