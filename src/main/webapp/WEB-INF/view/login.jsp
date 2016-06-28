<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン</title>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
<div align="center">
	<h1>${message}</h1>
	<div class = messages >
		<c:forEach items = "${ messages }" var = "message">
			<p><c:out value = "${ message }" /><br></p>
		</c:forEach>
		<c:remove var = "messages" scope = "session" />
	</div>
	<form:form modelAttribute="userForm">
		<p>ログインID
		<input name = "loginId" value = "${ loginId }" /><br></p>
		
		<p>パスワード
		<input name = "password" type = "password"  /><br></p>
		<input type="submit" value = "ログイン">
	</form:form>
</div>
</body>
</html>