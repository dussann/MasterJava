<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Question details</title>
</head>
<body>
	<div>
		<h1>${questionHeader}</h1>
		<h2>${questionContent}</h2>
	</div>	
	<div>
		<table border="1" cellpadding="2" cellspacing="2">
			<tr>
				<th>Answers</th>
			</tr>
			<c:forEach var="answers" items="${answers }">
				<tr>
					<td>
						<h2>${answers.content }</h2>
						<h5>Author: ${answers.author.userName }</h5>
					</td>
					<td><a href="<%=request.getContextPath()%>/AnswerServlet?update=${answers.answerId }&cmd=update">Update</a></td>
					<td><a href="<%=request.getContextPath()%>/AnswerServlet?delete=${answers.answerId }&cmd=delete">Delete</a></td>			
				</tr>
			</c:forEach>
		</table>
	</div>	
	
	<div>
		<form method="post"	action="<%=request.getContextPath()%>/AnswerServlet">
			<h3>Your answer</h3>
			<textarea name="answer">${text }</textarea>
			<input type="hidden" value="answerUpdate" name="cmd"></input>
			<input type="submit" value="Post your answer"></input>
		</form>
	</div>
</body>
</html>