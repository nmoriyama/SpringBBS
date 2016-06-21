<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホーム</title>
</head>
<body>
 <h1>${message}</h1>
<c:forEach items = "${ postings }" var = "posting">
		<div class = "body-text">
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
			<div Align = "left" class = big-text>
				本文<br>
				<c:forEach items = "${ fn: split(posting.body,'<br>') }" var = "body">
					&nbsp;<c:out value = "${body}" />
					<br>
				</c:forEach>
			</div>
			<form action = "deletePosting" method = "post">
				<input type = "hidden" name = "id" value = "${ posting.id }">
				<c:if test = "${ loginUser.branchId != 1 && loginUser.positionId == 3 && loginUser.branchId == posting.branchId }">
					<input type = "submit" value = "削除">
				</c:if>
				<c:if test = "${ loginUser.positionId == 2 }">
					<input type = "submit" value = "削除">
				</c:if>
			</form>
		</div>
		<br>
		
		<div class = "comment-text"> 
			<c:forEach items = "${ comments }" var = "comment">
				<c:choose>
					<c:when test = "${ comment.postingId == posting.id }">
						<div Align = "left">
							名前:<c:out value = "${ comment.account }" />
							&nbsp; コメント<br>
							<c:forEach items = "${ fn: split(comment.body, '<br>') }" var = "body">
								<div class = "comments">
								&nbsp;<c:out value = "${body}" /><br>
								</div>
							</c:forEach>
						</div>
						<form action = "deleteComment" method = "post">
							<input type = "hidden" name = "id" value = "${ comment.id }">
							<c:if test = "${ loginUser.branchId !=  1 && loginUser.positionId == 3 && loginUser.branchId == comment.branchId }">
								<input type = "submit" value = "削除">
							</c:if>
							<c:if test = "${ loginUser.positionId == 2 }">
								<input type = "submit" value = "削除">
							</c:if>
						</form><br>
					</c:when>
				</c:choose>
			</c:forEach>
		</div>

		<form action = "comment" method = "post">
			<div Align = "left">
				コメント(500文字以下)<br><input type = "hidden" name = postingId value = "${ posting.id }">
				<textarea name = "comment" class = "comment-box"></textarea>
				<br><input type = "submit" value = "コメント">
			</div>
		</form><br>
	</c:forEach>
	<div class = "copyright">Copyright(c)Moriyama Naoki</div>
	</body>
</body>
</html>