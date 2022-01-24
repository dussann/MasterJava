<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="container">
		<div>
			<h1>Welcome ${userName}</h1>
			<%-- <a href="<%=request.getContextPath()%>/UserServlet?cmd=logout">Log out</a> --%>
		</div>

		<a href="Questions/createQuestion.jsp"><h3>Ask question</h3></a>
		<table class="table">
			<tr>
				<th>Questions</th>
			</tr>
			<c:forEach var="questions" items="${questions }">
				<tr>
					<td><h3>${questions.header }</h3></td>
					<td><a
						href="<%=request.getContextPath()%>/QuestionServlet?details=${questions.questionId }&cmd=details">Details</a></td>
					<td><a
						href="<%=request.getContextPath()%>/QuestionServlet?delete=${questions.questionId }&cmd=delete">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>