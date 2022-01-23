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
	<%@include file="header.jsp"%>
	<div class="container">
		<h1>Create</h1>

		<h4>User</h4>
		<hr />
		<div class="row">
			<div class="col-md-4">
				<form>
					<div class="form-group">
						<label class="control-label">First name</label> <input
							class="form-control" name="firstName" /> <input type="hidden"
							value="registration" name="cmd" />
					</div>
					<div class="form-group">
						<label class="control-label">User name</label> <input
							class="form-control" name="userName" />
					</div>
					<div class="form-group">
						<label class="control-label">Country</label> <input
							class="form-control" name="country" />

					</div>
					<div class="form-group">
						<label class="control-label">Job title</label> <input
							class="form-control" name="jobTitle" />

					</div>
					<div class="form-group">
						<label class="control-label">Password</label> <input
							class="form-control" name="password" />


					</div>
					<br />
					<div class="form-group">
						<input type="submit" value="Create" class="btn btn-primary" />
					</div>
				</form>
				<br />
				<div>
					<a href="index.jsp">Back to login page</a>
				</div>
			</div>
		</div>
	</div>





	<%-- <form action="<%=request.getContextPath()%>/UserServlet" method="post">
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
	</form> --%>
</body>
</html>