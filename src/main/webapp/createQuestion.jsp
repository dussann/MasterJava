<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/QuestionServlet" method="post">
		<table style="with: 50%">
			<tr>
				<td>Header</td>
				<td><input type="text" name="header" /></td>
				<input type="hidden" value="create" name="cmd" />
			</tr>
			<tr>
				<td>Content</td>
				<td><textarea  name="content"></textarea></td>
				
			</tr>
		</table>
		<input type="submit" value="Submit" />
	</form>
</body>
</html>