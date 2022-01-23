<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<body>
	<%@include file="header.jsp" %>
	<div class="container">
		<form action="<%=request.getContextPath()%>/UserServlet" method="post">
			<div class="col-md-4">
				<input type="text" name="userName" class="form-control"
					placeholder="Username"> <input type="hidden" value="login"
					name="cmd" />
			</div>
			<br />
			<div class="col-md-4">
				<input type="password" name="password" class="form-control"
					placeholder="Password">
			</div>
			<br />
			<div class="col-md-4">
				<button type="submit" class="btn btn-primary">Submit</button>

			</div>
			<br>
		</form>
		<form action="<%=request.getContextPath()%>/UserServlet" method="get">
			<div class="col-md-4">
				<button type="submit" class="btn btn-primary">Register user</button>
				<input type="hidden" value="registrationRedirect" name="cmd" />
			</div>
		</form>
	</div>

	<%-- <form action="<%=request.getContextPath()%>/UserServlet" method="post">
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
	<a href="registration.jsp">Register</a> --%>
</body>
</html>