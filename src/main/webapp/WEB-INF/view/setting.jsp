<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>設定</title>
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
</div>

	<form:form modelAttribute="userForm">
		 ログインID(6文字以上20文字以下)<br>
		<input name = "loginId"  value = "${ user.loginId }"/><br>
		<p><label for = "password">パスワード(6文字以上255文字以下)<br></label>
		<input name = "password" type = "password" id = "password"/><br></p>
	
		<p><label for = "password">パスワード(確認用)<br></label>
		<input name = "checkPassword" type = "password"/><br></p>
	
		<p><label for = "account">アカウント名<br></label>
		<input name = "account" id = "account" value = "${ user.account }"/><br></p>
		<c:if test = "${ user.id != loginUser.id }">
		<p>支店<br><select name = "branchId">
			<c:forEach items = "${ branches }" var = "branch">
				<option value = "${ branch.id }" <c:if test = "${ user.branchId == branch.id }">selected</c:if>>
				<c:out value = "${ branch.name }" /></option>
			</c:forEach>
		</select></p>
		<p>役職<br><select name = "positionId">
			<c:forEach items = "${ positions }" var = "position">
				<option value = "${ position.id }" <c:if test = "${ user.positionId == position.id }">selected</c:if>>
				<c:out value = "${ position.name }" /></option>
			</c:forEach>
		</select></p>
		</c:if>
		<input type="submit">
	</form:form>
		<a href = "/SpringBBS/management">戻る</a>
</div>

</body>
</html>