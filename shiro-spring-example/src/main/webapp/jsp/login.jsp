<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/user/login" method="post">
		账号:<input id="username" name="username" type="text"/>
		密码:<input id="password" name="password" type="password"/>
		<button type="submit">登录</button>
	</form>
</body>
</html>