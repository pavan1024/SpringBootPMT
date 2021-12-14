<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body {
	background: #00C9FF; /* fallback for old browsers */
	background: -webkit-linear-gradient(to right, #92FE9D, #00C9FF);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to right, #92FE9D, #00C9FF);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}

.center {
	margin-top: 150px;
	margin-left: 400px;
	border: 1px solid white;
	width: 500px;
	height: 300px;
	padding: 50px;
}
</style>
</head>
<body>
	<div class="center">
		<h2>User Accounts</h2>

		<table border=1 >
		<caption>All Accounts</caption>
			<tr>
				<th id="url">URL</th>
				<th id="username">Username</th>
				<th id="password">Password</th>
				<th id="groupname">Groupname</th>
			</tr>

			<c:forEach items="${accounts}" var="account">
				<tr>
					<td><c:out value="${account.url}"></c:out></td>
					<td><c:out value="${account.username}"></c:out></td>
					<td><c:out value="${account.password}"></c:out></td>
					<td><c:out value="${account.groupname}"></c:out></td>
				</tr>

			</c:forEach>

		</table>
		<br>
		<br> <a href="menu">Back</a>
	</div>
</body>
</html>