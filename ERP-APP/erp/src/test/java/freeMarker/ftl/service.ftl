package com.hanilucky.core.service;

import java.util.List;

import com.hanilucky.common.PageBean;
import com.hanilucky.core.vo.${"${tempNm}"?cap_first};

public interface ${"${tempNm}"?cap_first}Service {

	Integer save(${"${tempNm}"?cap_first} ${tempNm});

	Integer delete(Integer id);

	Integer update(${"${tempNm}"?cap_first} ${tempNm});

	${"${tempNm}"?cap_first} queryById(Integer id);

	List<${"${tempNm}"?cap_first}> list();

	PageBean<${"${tempNm}"?cap_first}> page(int pageNum, int pageSize);

	PageBean<${"${tempNm}"?cap_first}> pageList(${"${tempNm}"?cap_first} ${tempNm},int pageNum, int pageSize);

}
