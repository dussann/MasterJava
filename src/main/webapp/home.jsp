<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<div>
		<h1>Welcome ${userName}</h1>
		<a href="<%=request.getContextPath()%>/UserServlet?cmd=logout">Log out</a>
	</div>
	
	<a href="createQuestion.jsp"><h3>Ask question</h3></a>
	<table border="1" cellpadding="2" cellspacing="2">
		<tr>
			<th>Questions</th>			
		</tr>
		<c:forEach var="questions" items="${questions }">
			<tr>
				<td><h3>${questions.header }</h3></td>
				<td><a href="<%=request.getContextPath()%>/QuestionServlet?details=${questions.questionId }&cmd=details">Details</a></td>
				<td><a href="<%=request.getContextPath()%>/QuestionServlet?delete=${questions.questionId }&cmd=delete">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>