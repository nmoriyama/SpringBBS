<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>
</head>
<body>
    <h1>${message}</h1>
    <form:form modelAttribute="testForm">
        <p><label for = "loginId">ログインID(6文字以上20文字以下)<br></label>
		<input name =  "loginId" /> <br> </p>
		
		<p><label for = "password">パスワード(6文字以上255文字以下)<br></label>
		<input name = "password" type = "password"/> <br> </p>
		
		<p><label for = "password">パスワード(確認用)<br></label>
		<input name = "checkPassword" type = "password"/> <br> </p>
		
		<p><label for = "account">アカウント名<br></label>
		<form:input path = "account"/> <br> </p>


    
	<form:select path = "branchId">
		<c:forEach items = "${ branches }" var = "branch">
			<option value = "${ branch.id }" >
			<c:out value = "${ branch.name }" /></option>
		</c:forEach>
	</form:select>
	
	<select name = "positionId">
		<c:forEach items = "${ positions }" var = "position">
			<option value = "${ position.id }" >
			<c:out value = "${ position.name }" /></option>
		</c:forEach>
	</select>
	<input type="submit">
	</form:form>
</body>
</html>