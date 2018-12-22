package com.hanilucky.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hanilucky.core.service.EmpService;
import com.hanilucky.core.service.MenuService;
import com.hanilucky.core.vo.Emp;
import com.hanilucky.core.vo.Menu;

/**
 * 自定义realm
 * 实现认证,授权
 * @author Administrator
 *
 */
//@Component  (项目中没有扫描这个注解)
public class MyRealm extends AuthorizingRealm {

	@Autowired
	private EmpService empService;

	@Autowired
	private MenuService menuService;

	private static final Logger log = LoggerFactory.getLogger(MyRealm.class);

	/**
	 * 这些过滤器分为两组，一组是认证过滤器，一组是授权过滤器。
	 * 第一组:认证过滤器:anon，authcBasic，auchc，user
	 * 第二组:授权过滤器过滤器:perms，roles，ssl，rest，port是第二组
	 * 登录等认证操作走第一组,调用doGetAuthenticationInfo(不是一定会掉这个方法)
	 * 鉴权等操作走第二组,调用doGetAuthorizationInfo(不是一定调用这个方法)
	 * 第一组授权过滤器实在第一组的认证过滤器的基础上调用的
	 */

	/**
	 * /dep/**=perms["testPerms"]
	 * /dep/**=anon,perms["testPerms"]
	 * 或者role["roleName"]时会调用这个方法
	 * 授权  判断是否有权限访问资源
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.info("=====================调用了授权方法=====================");
		Emp user = (Emp) principals.getPrimaryPrincipal(); // 该对象的值在调用doGetAuthenticationInfo的返回值设置
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 根据用户id获取菜单列表权限
		List<Menu> menus = menuService.readEmpMenuByEmpId(user.getUuid());
		// 查角色
		// XXXX
		// 添加角色
		// info.addRole(role);
		// 将菜单菜单添加到授权对象中
		for (Menu menu : menus) {
			info.addStringPermission(menu.getMenuname());
		}
		return info;
	}

	/**
	 * 认证   登录
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		log.info("=====================调用了认证方法=====================");
		// 获取令牌(要进行认证的主体信息)
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		// 获取用户名与密码
		String username = token.getUsername();
		String password = new String(token.getPassword());
		
		Emp emp = new Emp();
		emp.setUsername(username);
		emp.setPwd(new Md5Hash(password, username, 2).toString());

		// 登陆
		Emp user = empService.login(emp);
		if (user == null) { // 登录失败
			log.info("============登录失败,用户名或密码错误==========");
			return null;
		}
		// 参数1 principal 主角对象
		// 参数2 credentials 密码
		// 参数3 realmName realm名字
		log.info("============登录成功,用户名或密码错误==========");
		return new SimpleAuthenticationInfo(user, password, user.getName());
		// 如果校验失败返回null 如果校验成功返回SimpleAuthenticationInfo实例
	}

}
