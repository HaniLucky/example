package com.hanilucky.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hanilucky.core.service.EmpService;
import com.hanilucky.core.vo.Emp;
/**
 * 自定义realm
 * 实现认证,授权
 * @author Administrator
 *
 */
@Component
public class MyRealm extends AuthorizingRealm{
	

	@Autowired
	private EmpService empService;

	private static final Logger log = LoggerFactory.getLogger(MyRealm.class);
	/**
	 * 授权  判断是否有权限访问资源
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 认证   登录
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		log.info("调用了认证方法");
		// 获取令牌(要进行认证的主体信息)
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		// 获取用户名与密码
		String username = token.getUsername();
		String password = new String( token.getPassword());
		Emp emp = new Emp();
		emp.setUsername(username);
		emp.setPwd(password);
		Emp user = empService.login(emp);
		if (user == null) { // 登录失败
			log.info("============登录失败,用户名或密码错误==========");
			return null;
		}
		// 参数1 principal 主角对象
		// 参数2 credentials 密码
		// 参数3 realmName realm名字
		return new SimpleAuthenticationInfo(user, password, user.getName());
		// 如果校验失败返回null 如果校验成功返回SimpleAuthenticationInfo实例
	}

}
