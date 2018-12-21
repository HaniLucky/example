package com.hanilucky.core.service.impl;

import java.util.List;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hanilucky.common.PageBean;
import com.hanilucky.common.Result;
import com.hanilucky.core.mapper.EmpMapper;
import com.hanilucky.core.service.EmpService;
import com.hanilucky.core.vo.Emp;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmpMapper empMapper;

	@Override
	public Integer save(Emp emp) {
		return empMapper.insertSelective(emp);
	}

	@Override
	public Integer delete(Integer id) {
		return empMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer update(Emp emp) {
		return empMapper.updateByPrimaryKeySelective(emp);
	}

	@Override
	public Emp queryById(Integer id) {
		return empMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Emp> list() {
		List<Emp> list = empMapper.selectList(null);
		return list;
	}

	@Override
	public PageBean<Emp> page(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Emp> list = empMapper.selectList(null);
		PageInfo<Emp> pageInfo = new PageInfo<>(list);
		return new PageBean<Emp>(list, pageInfo.getTotal());
	}

	@Override
	public PageBean<Emp> pageList(Emp emp, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Emp> list = empMapper.selectList(emp);
		PageInfo<Emp> pageInfo = new PageInfo<>(list);
		return new PageBean<Emp>(list, pageInfo.getTotal());
	}

	@Override
	public Emp login(Emp emp) {
		return empMapper.login(emp);
	}

	@Override
	public Result updatePwd(Emp emp) {
		// 获取用户信息
		Emp userInfo = queryById(emp.getUuid());
		if(userInfo == null ){
			return new Result(false, "用户不存在", null);
		}
		// 新密码加密
		emp.setPwd(new Md5Hash(emp.getPwd(),userInfo.getUsername(),2).toString());
		Integer num = update(emp);
		// 更新数据
		return new Result(true, "密码修改成功", null);
	}

}
