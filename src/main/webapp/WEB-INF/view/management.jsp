<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script type = "text/javascript">
	<!--

	function check(){
		if(window.confirm('実行してよろしいですか？')){ // 確認ダイアログを表示
			return true; // 「OK」時は送信を実行
		}
		else{ // 「キャンセル」時の処理
			return false; // 送信を中止
		}
	
	}
	-->
</script>
<meta charset="utf-8">
        		
<title>ユーザー管理画面</title>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
    
<body>
<div class = management-area>
<div align="left">

<h1>${message}</h1>
	<div class = messages >
		<c:forEach items = "${ messages }" var = "message">
			<p><c:out value = "${ message }" /><br></p>
		</c:forEach>
	</div>
</div>
<a href = "signup">ユーザー登録</a>
<a href = "home">戻る</a>

<div align="center">

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
			<td><c:out value = "${ user.loginId }" /></td>
			<td><c:out value = "${ user.account }" /></td>
			<td><c:out value = "${ user.branchName }" /></td>
			<td><c:out value = "${ user.positionName }" /></td>
			
			<td><a href="${pageContext.request.contextPath}/management/setting/${user.id}">
		<input  type = "submit" value = "編集"></a>

			</td>
			
		<td><c:if test = "${ user.id != loginUser.id }">
			<form:form action="status" method="post" onClick = "return check()"> 
				<input type = "hidden" name = "id" value = "${user.id}">
				<c:if test = "${ user.status == 1 }">
					<input type = "hidden" name = "status" value = 2>
					<input type = "submit" value = "停止中" >
				</c:if>
				<c:if test = "${ user.status == 2 }">
					<input type = "hidden" name = "status" value = 1>
					<input type = "submit" value = "利用可能" >
				</c:if>
			</form:form>
		</c:if></td>
			
		<td><c:if test = "${ user.id != loginUser.id }">
			<form:form action="delete" method="post" onClick = "return check()">
			<input type = "hidden" name = "id" value = "${user.id}">
			<input  type = "submit" value = "削除">
			</form:form></c:if></td>
		</tr>
	</c:forEach>
</table>
</div>
</div>

    </body>
</html>