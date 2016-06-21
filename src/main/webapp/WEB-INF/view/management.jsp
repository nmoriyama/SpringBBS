<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>Welcome</title>
    </head>
    <body>
               <h1>${message}</h1>
    <table>
	<tr>
    	<td>ログインID</td>
   		<td>アカウント</td>
		<td>支店</td>
		<td>部署・役職</td>
		<td>編集</td>
		<td>状態</td>
		<td>削除</td>
	</tr>

	<c:forEach items = "${ users }" var = "user">
		<tr>
			<td><c:out value = "${ user.login_id }" /></td>
			<td><c:out value = "${ user.account }" /></td>
			<td><c:out value = "${ user.branchName }" /></td>
			<td><c:out value = "${ user.positionName }" /></td>
			
			<td><a href="${pageContext.request.contextPath}/management/setting/${user.id}/">
					<input  type = "submit" value = "編集"></a>

			</td>
			
			<td><c:if test = "${ user.id != loginUser.id }">
				<form action = "management" method = "post" onClick = "return check()">
					<input type = "hidden" name = "id" value = "${user.id}">
					<c:if test = "${ user.status == 1 }">
						<input type = "hidden" name = "status" value = 2>
						<p><input type = "submit" value = "停止中" ></p> 
					</c:if>
					<c:if test = "${ user.status == 2 }">
						<input type = "hidden" name = "status" value = 1>
						<p><input type = "submit" value = "利用可能" ></p>
					</c:if>
				</form>
			</c:if></td>
			
			<td><form:form modelAttribute="testForm" action="delete" method="post">
				<input type = "hidden" name = "id" value = "${user.id}">
				<p><input  type = "submit" value = "削除"></p>
			</form:form></td>
		</tr>
	</c:forEach>
</table>



    </body>
</html>