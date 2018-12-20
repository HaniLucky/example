package com.hanilucky.common;

import java.util.List;

public class PageBean<T> {
    private List<T> rows;  //要返回的某一页的记录列表
    private Long total; //总记录数

    
    
    public PageBean() {
		super();
	}
    
    

	public PageBean(List<T> rows, Long total) {
		super();
		this.rows = rows;
		this.total = total;
	}



	public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }


}