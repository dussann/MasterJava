<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/UserServlet" method="post">
		<table style="with: 50%">
			<tr>
				<td>First name</td>
				<td><input type="text" name="firstName" /></td>
				<input type="hidden" value="registration" name="cmd" />
			</tr>
			<tr>
				<td>User name</td>
				<td><input type="text" name="userName" /></td>
			</tr>
			<tr>
				<td>Country</td>
				<td><input type="text" name="country" /></td>
			</tr>
			<tr>
				<td>Job title</td>
				<td><input type="text" name="jobTitle" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" /></td>
			</tr>

		</table>
		<input type="submit" value="Submit" />
	</form>
	<div>
		<h3>Password is already in used</h3>
	</div>
</body>
</html>