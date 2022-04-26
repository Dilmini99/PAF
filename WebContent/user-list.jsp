<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
	<style><%@include file="/Style.css"%></style>
	
</head>
<body>

	<ul class="nav-bar">
<li id="login"><a href="#">MadusankaR</a></li>
<li><a href="#">Home</a></li>
<li><a href="#">User Management</a></li>
<li><a href="#">Billing Management</a></li>
<li><a href="#">Payment Management</a></li>
<li><a class="active" href="#">User Power Management</a></li>
</ul>

	<div class="row1" style="margin-left:15%;padding:1px 16px;">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

<form action="search"method="post">
			<label>User Name</label> <input type="text" name="username">
			<label>User ID </label> <input type="text" name="employeeID"><br>
			<input type="submit" name="submit" value="Search">
			<button formaction="default" formmethod="post">Clear</button>
		</form>
		
		
		<div class="container">
			<h3 class="text-center">List of Users</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New User</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>userid</th>
						<th>username</th>
						<th>email</th>
						<th>month</th>
						<th>units</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="user" items="${listUser}">

						<tr>
							<td><c:out value="${user.userid}" /></td>
							<td><c:out value="${user.username}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${user.employeeID}" /></td>
							<td><c:out value="${user.employeeID}" /></td>
							<td><a href="edit?userid=<c:out value='${user.userid}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?userid=<c:out value='${user.userid}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
