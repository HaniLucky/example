package com.hanilucky.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.hanilucky.core.vo.Emp;

/**
 * 获取用户相关信息
 * @author Administrator
 *
 */
public class UserUtils {

	/**
	 * 获取用户信息
	 * @return
	 */
	public static Emp getUser() {
		Subject subject = SecurityUtils.getSubject();
		return (Emp) subject.getPrincipal();
	}

	/**
	 *	获取用户名
	 * @return
	 */
	public static String getUserName() {
		return getUser().getUsername();
	}
	/**
	 *	获取用户id
	 * @return
	 */
	public static Integer getUserId() {
		return getUser().getUuid();
	}

	/**
	 * 获取用户名字
	 * @return
	 */
	public static String getName() {
		return getUser().getName();
	}

}
