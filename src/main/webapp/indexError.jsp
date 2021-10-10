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
		<table>
			<tr>
				<td>User name</td>
				<td><input type="text" name="userName"></td>
				<input type="hidden" value="login" name="cmd" />
			</tr>
			<tr>
				<td>Passwod</td>
				<td><input type="password" name="password"></td>
			</tr>
		</table>		
		<input type="submit" value="Submit">
	</form>
	<a href="registration.jsp">Register</a>
	<div>
		<h3 style="color: red">Wrong user name or password</h3>
	</div>
</body>
</html>