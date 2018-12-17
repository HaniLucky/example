package com.example.ERP.common;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
    private Mapper<T> mapper;

	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		// 获取父类的type
		Type type = this.getClass().getGenericSuperclass();

		// 强转为ParameterizedType，可以使用获取泛型类型的方法
		ParameterizedType pType = (ParameterizedType) type;

		// 获取泛型的class
		this.clazz = (Class<T>) pType.getActualTypeArguments()[0];
	}

	@Override
	public T queryById(Object id) {
		T t = this.mapper.selectByPrimaryKey(id);
		return t;
	}

	@Override
	public List<T> queryAll() {
		List<T> list = this.mapper.select(null);
		return list;
	}

	@Override
	public Integer queryCountByWhere(T t) {
		int count = this.mapper.selectCount(t);
		return count;
	}

	@Override
	public List<T> queryListByWhere(T t) {
		List<T> list = this.mapper.select(t);
		return list;
	}

	@Override
	public PageBean<T> queryByPage(Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		List<T> list = this.mapper.select(null);
		PageBean<T> pageBean = new PageBean<>();
		pageBean.setRows(list);
		pageBean.setTotal(new PageInfo<>(list).getTotal());
		return pageBean;
	}

	/**
	 * 根据条件分页
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public List<T> queryListPageByWhere(Integer page, Integer rows,T t) {
		PageHelper.startPage(page, rows);
		List<T> list = this.mapper.select(t);
		return list;
	}

	public PageBean<T> queryListPageBaenByWhere(Integer page, Integer rows, T t){
		PageHelper.startPage(page, rows);
		List<T> list = this.mapper.select(t);
        PageBean<T> pageBean = new PageBean<>();
        pageBean.setRows(list);
        pageBean.setTotal(new PageInfo<>(list).getTotal());
        return pageBean;
	}

	@Override
	public T queryOne(T t) {
		T result = this.mapper.selectOne(t);
		return result;
	}

	@Override
	public int save(T t) {
		return this.mapper.insert(t);
	}

	@Override
	public int saveSelective(T t) {
		return this.mapper.insertSelective(t);
	}

	@Override
	public int updateById(T t) {
		return this.mapper.updateByPrimaryKey(t);
	}

	@Override
	public int updateByIdSelective(T t) {
		return this.mapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int deleteById(Long id) {
		return this.mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByIds(List<Object> ids) {
		// 声明条件
		Example example = new Example(this.clazz);
		example.createCriteria().andIn("id", ids);
		 return  this.mapper.deleteByExample(example);
	}

}