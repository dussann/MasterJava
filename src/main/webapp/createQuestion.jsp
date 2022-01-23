<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create question</title>
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

		<h4>Question</h4>
		<hr />
		<div class="row">
			<div class="col-md-4">
				<form action="<%=request.getContextPath()%>/QuestionServlet">

					<div class="form-group">
						<label class="control-label">Header</label> <input
							class="form-control" /> <input type="hidden" value="create"
							name="cmd" />
					</div>
					<div class="form-group">
						<label class="control-label">Content</label>
						<textarea name="content" class="form-control"></textarea>
					</div>
					<br />
					<div class="form-group">
						<input type="submit" value="Create" class="btn btn-primary" />
					</div>
				</form>
			</div>
		</div>
		<br />
		<div>
			<a href="">Back to List</a>
		</div>
	</div>

	<%-- <form action="<%=request.getContextPath()%>/QuestionServlet"
		method="post">
		<table style="with: 50%">
			<tr>
				<td>Header</td>
				<td><input type="text" name="header" /></td>
				<input type="hidden" value="create" name="cmd" />
			</tr>
			<tr>
				<td>Content</td>
				<td><textarea name="content"></textarea></td>
			</tr>
		</table>
		<input type="submit" value="Submit" />
	</form> --%>
</body>
</html>