<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Question details</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body>
	<%@include file="../header.jsp"%>
	<div class="container">
		<div>
			<a href="<%=request.getContextPath()%>/UserServlet?cmd=home">Back
				to home</a>
			<h1>${questionHeader}</h1>
			<h2>${questionContent}</h2>

		</div>
		<div>
			<table class="table">
				<tr>
					<th>Answers</th>
				</tr>
				<c:forEach var="answers" items="${answers }">
					<tr>
						<td>
							<h2>${answers.content }</h2>
							<h5>Author: ${answers.author.userName }</h5>
						</td>
						<td><a
							href="<%=request.getContextPath()%>/AnswerServlet?update=${answers.answerId }&cmd=update">Update</a></td>
						<td><a
							href="<%=request.getContextPath()%>/AnswerServlet?delete=${answers.answerId }&cmd=delete">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<div>
			<form method="post"
				action="<%=request.getContextPath()%>/AnswerServlet">
				<h3>Your answer</h3>
				<div class=row">
					<textarea  class="form-control" name="answer"></textarea>
				</div>
				<br>
				<div>
					<input type="hidden" value="answer" name="cmd"></input>
					<input class="btn btn-primary" type="submit" value="Post your answer"></input>
				</div>
			</form>
		</div>
	</div>
</body>
</html>