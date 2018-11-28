<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<!-- http://127.0.0.1:8080/shiro-spring/jsp/login.jsp -->
		<form action="${pageContext.request.contextPath}/test/index.action" method="post">
			用户名:<input id="username" name="username" type="text"></br> 
			密码: <input id="password" name="password" type="password">
		<input type="submit" value="提交">
		</form>
	</div>
</body>
</html>