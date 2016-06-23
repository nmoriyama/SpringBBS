<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホーム</title>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
	<h1>${ message }</h1>
	<div class = messages >
		<c:forEach items = "${ messages }" var = "message">
			<c:out value = "${ message }" /><br>
		</c:forEach>
	</div>
	<a href = "posting">新規投稿</a>
	<a href = "management">ユーザー管理</a>
	<a href = "logout">ログアウト</a>
	<br><c:out value = "${ loginUser.account }" />がログイン中
	<c:forEach items = "${ postings }" var = "posting">
		<div style="border-style: double  ; border-width: 3px;" align="center">
			<div Align = "left">
				カテゴリー:
				<c:out value = "${ posting.category }" />
				&nbsp; 投稿者:
				<c:out value = "${ posting.account }" />
				&nbsp; 件名:
				<c:out value = "${ posting.subject }" />
			</div>
			<div Align = "left">
				日付:<fmt:formatDate value = "${ posting.date }" pattern = "yyyy年MM月dd日 HH時mm分" />
			</div>
			<br>
			<div Align = "left" class = big-text>本文<br>
				<c:forEach items = "${ fn: split(posting.body,'<br>') }" var = "body">
					&nbsp;<c:out value = "${body}" />
					<br>
				</c:forEach>
			</div>
		</div>
		<br>

		<div style="border-style: solid ; border-width: 1px;" align="center">
			<c:forEach items = "${ comments }" var = "comment">
				<c:choose>
					<c:when test = "${ comment.postingId == posting.id }">
						<div Align = "left">
							名前:<c:out value = "${ comment.account }" />
							&nbsp; コメント<br>
							<c:forEach items = "${ fn: split(comment.body, '<br>') }" var = "body">
								<div class = "comments">
								&nbsp;<c:out value = "${ comment.body }" /><br>
								</div>
							</c:forEach>
						</div>

					</c:when>
				</c:choose>
			</c:forEach>
		</div>
	<form:form action="comment" method="post">
			<div Align = "left">
				コメント(500文字以下)<br><input type = "hidden" name = postingId value = "${ posting.id }">
				<input type = "hidden" name = userId value = "${ loginUser.id }">
				<textarea name = "body" class = "comment-box"></textarea>
				<br><input type = "submit" value = "コメント">
			</div>
			</form:form><br>
	</c:forEach>
	<div class = "copyright">Copyright(c)Moriyama Naoki</div>
	</body>
</body>
</html>