#### ssm项目整合shiro

##### 添加相关依赖  pom.xml

```xml
<!-- shiro安全框架 -->
		<!-- apache shiro dependencies -->
		<dependency>
			<groupId>org.apache.shiro</groupId>
			<artifactId>shiro-core</artifactId>
			<version>1.2.3</version>
		</dependency>

		<dependency>
			<groupId>org.apache.shiro</groupId>
			<artifactId>shiro-web</artifactId>
			<version>1.2.3</version>
		</dependency>

		<dependency>
			<groupId>org.apache.shiro</groupId>
			<artifactId>shiro-spring</artifactId>
			<version>1.2.3</version>
		</dependency>

		<dependency>
			<groupId>org.apache.shiro</groupId>
			<artifactId>shiro-aspectj</artifactId>
			<version>1.2.3</version>
		</dependency>
```

##### 配置拦截器 web.xml

```xml
<filter>
		<filter-name>shiroFilter</filter-name>
		<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>shiroFilter</filter-name>
		<!-- <url-pattern>*.action</url-pattern> -->
		<!-- <url-pattern>*.html</url-pattern>
		<url-pattern>*</url-pattern> -->
		<url-pattern>/*</url-pattern>
	</filter-mapping>
```



##### 实现自定义Realm

```java
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
		log.info("============登录成功==========");
		return new SimpleAuthenticationInfo(user, password, user.getName());
		// 如果校验失败返回null 如果校验成功返回SimpleAuthenticationInfo实例
	}

}

```

##### 编写拦截规则

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
						http://www.springframework.org/schema/beans/spring-beans.xsd"> 
						
	<!-- Shiro的过滤器工程Bean：间接的加载9个内置过滤器 -->
	<bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">		
		<!-- 安全管理器：Shiro的核心组件 -->
		<property name="securityManager" ref="securityManager" />
		<!-- 认证相关：指定登录页面，当用户未登录时访问资源，则自动跳转到此页面 -->		
		<property name="loginUrl" value="/login.html" /> 
		<!-- 授权相关：指定错误页面，当用户登陆后访问没有权限资源，自动跳转此页面 -->
		<property name="unauthorizedUrl" value="/error.html" /> 
		<!-- 登录成功后跳转的页面 -->
		<!-- 前端已经进行了页面跳转  不需要shiro来做跳转了 -->
		<!-- <property name="successUrl" value="/index.html"></property> -->
		<!-- 用自定义过滤器替换默认perms的过滤规则 -->
		<!-- <property name="filters">
			<map>
				<entry key="perms" value-ref="myFilter" />
			</map>
		</property> -->

		<!-- 过滤器链定义：指定页面的访问规则 -->
		<property name="filterChainDefinitions">
			<value>
				<!-- 前端资源权限end -->
				/error.html = anon
				/adminjs/** = anon
				/css/** = anon
				/images/** =anon
				/js/** =anon
				/ui/** = anon
				<!-- 前端资源权限end -->
				<!-- 登录接口放开 -->
				/sso/login/* = anon
				<!-- 第二组过滤器需要经过第一组的过滤器之后才会起作用 -->
				<!-- 测试perms 系统中没有这个权限 -->
				<!-- /dep/**=perms["testPerms"] -->
				<!-- 余下所有页面都需要登录访问 -->
				/*.html = authc
				<!-- 余下的所有接口都要认证只有才能访问 -->
				/** = authc
			</value>
		</property>
	</bean>
	
	<!-- Shiro的安全管理器 是shiro的大脑-->
	<bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
		<property name="realm" ref="myRealm"></property>
	</bean>
	<!-- 是否可以通过注解注入// 待测试 -->
	<bean id="myRealm" class="com.hanilucky.shiro.MyRealm"/>
	
</beans>
```

