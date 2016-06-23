<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="utf-8">
<title>ユーザー登録</title>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
<div class=signup-area>
	<div class = messages >
		<c:forEach items = "${ messages }" var = "message">
			<c:out value = "${ message }" /><br>
		</c:forEach>
	</div>

    <form:form modelAttribute="userForm">
        <p><label for = "loginId">ログインID(6文字以上20文字以下)<br></label>
		<input name =  "loginId" /> <br> </p>
		
		<p><label for = "password">パスワード(6文字以上255文字以下)<br></label>
		<input name = "password" type = "password"/> <br> </p>
		
		<p><label for = "password">パスワード(確認用)<br></label>
		<input name = "checkPassword" type = "password"/> <br> </p>
		
		<p><label for = "account">アカウント名<br></label>
		<form:input path = "account"/> <br> </p>


    
		<p>支店<br><select name = "branchId">
			<c:forEach items = "${ branches }" var = "branch">
				<option value = "${ branch.id }" >
				<c:out value = "${ branch.name }" /></option>
			</c:forEach>
		</select></p>
		<p>役職<br><select name = "positionId">
			<c:forEach items = "${ positions }" var = "position">
				<option value = "${ position.id }" >
				<c:out value = "${ position.name }" /></option>
			</c:forEach>
		</select></p>
	<input type="submit">
	</form:form>
	    <a href = "management">戻る</a>
	    </div>
</body>
</html>