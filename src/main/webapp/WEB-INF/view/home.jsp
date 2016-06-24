<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホーム</title>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>

	<div class = messages >
		<c:forEach items = "${ messages }" var = "message">
			<p><c:out value = "${ message }" /><br></p>
		</c:forEach>
	</div>
	<form:form action="search" method="get">
		<c:forEach items = "${ date }" var = "date">
			<c:set var="firstYear"><fmt:formatDate value = "${ date.firstDate }" pattern = "yyyy" /></c:set>
			<c:set var="firstMonth"><fmt:formatDate value = "${ date.firstDate }" pattern = "MM" /></c:set>
			<c:set var="firstDay"><fmt:formatDate value = "${ date.firstDate }" pattern = "dd" /></c:set>
			
			<c:set var="lastYear"><fmt:formatDate value = "${ date.lastDate }" pattern = "yyyy" /></c:set>
			<c:set var="lastMonth"><fmt:formatDate value = "${ date.lastDate }" pattern = "MM" /></c:set>
			<c:set var="lastDay"><fmt:formatDate value = "${ date.lastDate }" pattern = "dd" /></c:set>
			</c:forEach>
			<select name="fromYear">
				<c:forEach begin = "${ firstYear }" end = "${ lastYear }" var = "i">
					<option <c:if test = "${ i == firstYear }">selected</c:if>>
					<c:out value = "${ i }" /></option>
				</c:forEach>
			</select>年 
			<select name="fromMonth">
				<c:forEach begin = "1" end = "12" var = "i">
					<option <c:if test = "${ i == firstMonth }">selected</c:if>>
				<c:out value = "${ i }" /></option>
			</c:forEach>
			</select>月 <select name="fromDay">
				<c:forEach begin = "1" end = "31" var = "i">
					<option <c:if test = "${ i == firstDay }">selected</c:if>>
					<c:out value = "${ i }" /></option>
				</c:forEach>
			</select>日 から 
			<select name = "toYear">
				<c:forEach begin = "${ firstYear }" end = "${ lastYear }" var = "i">
					<option <c:if test = "${ i == lastYear }">selected</c:if>>
					<c:out value = "${ i + currentYear }" /></option>
				</c:forEach>
			</select>年 
			<select name = "toMonth">
				<c:forEach begin = "1" end = "12" var = "i">
					<option <c:if test = "${ i == lastMonth }">selected</c:if>>
					<c:out value = "${ i }" /></option>
				</c:forEach>
			</select>月 
			<select name = "toDay">
				<c:forEach begin = "1" end = "31" var = "i">
					<option <c:if test = "${ i == lastDay }">selected</c:if>>
					<c:out value = "${ i }" /></option>
				</c:forEach>
			</select>日
			
			<c:set var="toDate"><c:out value = "${ toYear }-${ toMonth }-${ toDay }" /></c:set>
			<c:out value = "${ fromDate }" />
	 <select name = "category">
			<option value = "">全てを表示</option>
			<c:forEach items = "${ category }" var = "category">
				<option >
				<c:out value = "${ category.category }" /></option>
			</c:forEach>
		</select>
		<input type = "submit" value = "検索">
	</form:form>
	<a href = "posting">新規投稿</a>
	<a href = "management">ユーザー管理</a>
	<a href = "logout">ログアウト</a>
	<br><c:out value = "${ loginUser.positionId }" />がログイン中
	<c:forEach items = "${ postings }" var = "posting">
	<div Align = "left" class = "body-area">
		<div style="border-style: double  ; border-width: 3px;" align="center">
			
				カテゴリー:
				<c:out value = "${ posting.category }" />
				&nbsp; 投稿者:
				<c:out value = "${ posting.account }" />
				&nbsp; 件名:
				<c:out value = "${ posting.subject }" />
	
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
			<form:form action="postingDelete" method="post">
						<input type = "hidden" name = "id" value = "${ posting.id }">
				<c:if test = "${ loginUser.branchId !=  1 && loginUser.positionId == 3 && loginUser.branchId == comment.branchId }">
					<input type = "submit" value = "削除">
				</c:if>
				<c:if test = "${ loginUser.positionId == 2 }">
					<input type = "submit" value = "削除">
				</c:if>
				</form:form>
						</div>
		</div>
		<br>
	<div Align = "right" class = "comment-area">
		<div style="border-style: solid ; border-width: 1px;" align="right">
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
						<form:form action="commentDelete" method="post">
						<input type = "hidden" name = "commentId" value = "${ comment.id }">
						<c:if test = "${ loginUser.branchId !=  1 && loginUser.positionId == 3 && loginUser.branchId == comment.branchId }">
								<input type = "submit" value = "削除">
							</c:if>
							<c:if test = "${ loginUser.positionId == 2 }">
								<input type = "submit" value = "削除">
							</c:if>
							</form:form>
					</c:when>
				</c:choose>
			</c:forEach>
		</div>	
		<form:form action="comment" method="post">
<div Align = "left">コメント(500文字以下)</div><input type = "hidden" name = postingId value = "${ posting.id }">
				<input type = "hidden" name = userId value = "${ loginUser.id }">
				<textarea name = "body" class = "comment-box"></textarea>
				<br><div Align = "left" class = "comment-area"><input type = "submit" value = "コメント"></div>
			
			</form:form><br>
			</div>
	</c:forEach>
	<div class = "copyright">Copyright(c)Moriyama Naoki</div>
	</body>
</body>
</html>