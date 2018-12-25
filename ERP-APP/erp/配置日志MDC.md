#### 将用户信息添加到日志记录中

##### 配置filtet

```xml
<filter>
	<filter-name>userFilter</filter-name>
	<filter-class>com.hanilucky.config.UserFilter</filter-class>
</filter>
<filter-mapping>
	<filter-name>userFilter</filter-name>
	<url-pattern>/*</url-pattern>
</filter-mapping>
```
##### 编写filter

```java
package com.hanilucky.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户信息相关的filter
 * 
 * @author 晓风轻 https://xwjie.github.io/PLMCodeTemplate/
 *
 */
public class UserFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// 得到用户个人相关的信息（登陆的用户，用户的语言）
		fillUserInfo((HttpServletRequest) request);

		try {
			chain.doFilter(request, response);
		} finally {
			// 由于tomcat线程重用，记得清空
			clearAllUserInfo();
		}
	}

	private void clearAllUserInfo() {
		UserUtil.clearAllUserInfo();
	}

	private void fillUserInfo(HttpServletRequest request) {
		// 用户信息
		String user = getUserFromSession(request);

		if (user != null) {
			UserUtil.setUser(user);
		}

		// 语言信息
		String locale = getLocaleFromCookies(request);

		// 放入到threadlocal，同一个线程任何地方都可以拿出来
		if (locale != null) {
			UserUtil.setLocale(locale);
		}
	}

	private String getLocaleFromCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();

		if (cookies == null) {
			return null;
		}

		for (int i = 0; i < cookies.length; i++) {
			if (UserUtil.KEY_LANG.equals(cookies[i].getName())) {
				return cookies[i].getValue();
			}
		}

		return null;
	}

	private String getUserFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		if (session == null) {
			return null;
		}

		// 从session中获取用户信息放到工具类中
		return (String) session.getAttribute(UserUtil.KEY_USER);
	}

	@Override
	public void destroy() {

	}

}

```

##### 编写工具类

```java
package com.hanilucky.config;

import java.util.Locale;

import org.slf4j.MDC;

/**
 * 用户工具类
 * 
 * @author 晓风轻 https://xwjie.github.io/PLMCodeTemplate/
 *
 */
public class UserUtil {

	private final static ThreadLocal<String> tlUser = new ThreadLocal<String>();

	private final static ThreadLocal<Locale> tlLocale = new ThreadLocal<Locale>() {
		protected Locale initialValue() {
			// 语言的默认值
			return Locale.CHINESE;
		};
	};

	public static final String KEY_LANG = "lang";

	public static final String KEY_USER = "user";

	public static void setUser(String userid) {
		tlUser.set(userid);

		// 把用户信息放到log4j
		MDC.put(KEY_USER, userid);
	}

	/**
	 * 如果没有登录，返回null
	 * 
	 * @return
	 */
	public static String getUserIfLogin() {
		return tlUser.get();
	}

	/**
	 * 如果没有登录会抛出异常
	 * 
	 * @return
	 */
	public static String getUser() {
		String user = tlUser.get();

		if (user == null) {
			// throw new UnloginException();
			throw new RuntimeException();
		}

		return user;
	}

	public static void setLocale(String locale) {
		setLocale(new Locale(locale));
	}

	public static void setLocale(Locale locale) {
		tlLocale.set(locale);
	}

	public static Locale getLocale() {
		return tlLocale.get();
	}

	public static void clearAllUserInfo() {
		tlUser.remove();
		tlLocale.remove();

		MDC.remove(KEY_USER);
	}
}

```

##### 修改日志配置  %X{user} 

```properties
#输出到控制台 
log4j.appender.systemOut = org.apache.log4j.ConsoleAppender 
log4j.appender.systemOut.layout = org.apache.log4j.PatternLayout 
log4j.appender.systemOut.layout.ConversionPattern =[日志打印] %d{ABSOLUTE} %5p: %X{user} %c{1}:%L - %m%n
log4j.appender.systemOut.Threshold = DEBUG 
log4j.appender.systemOut.ImmediateFlush = TRUE 
log4j.appender.systemOut.Target = System.out 
```

##### 执行效果(显示登录的用信息)

[日志打印] 09:36:39,362 DEBUG: ***Admin*** DispatcherServlet:1018 - Null ModelAndView returned to DispatcherServlet with name 'springmvc': assuming HandlerAdapter completed request handling
[日志打印] 09:36:39,362 DEBUG: ***Admin*** DispatcherServlet:996 - Successfully completed request
[日志打印] 09:36:39,362 DEBUG: ***Admin*** DefaultListableBeanFactory:247 - Returning cached instance of singleton bean 'sqlSessionFactory'