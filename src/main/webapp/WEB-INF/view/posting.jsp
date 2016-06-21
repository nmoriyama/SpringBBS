<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h1>${message}</h1>
 <form:form modelAttribute="PostingForm"  method="post"> 
 
 <p><label for = "subject">件名(50文字以下)</label>
</p><input name = "subject" value = "${ subject }" /><br>
	<br>

	<p>
		<label for = "category">カテゴリー(10文字以下)</label>
	</p>
	<input name = "category" value = "${ category }" /><br>
	<br>

	<p>
		<label for = "body">本文(1000文字以下)</label>
	</p>
	<textarea name = "body" class = "input-box" cols = "20" ><c:out value = "${ body }" /></textarea>
	<p>
		<input type = "submit" value = "投稿">
	</p>
 </form:form>
</body>
</html>