<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	margin-left: 500px;
	border: 1px solid white;
	width: 350px;
	height: 200px;
	padding: 50px;
}
</style>
</head>
<body>
	<div class="center">
		<h3>Update Groupname</h3>
		<form name="update Groupname" action="updateGroupname" method="post">
			Current Groupname : <input type="text" name="currentGroupname">
			<br>
			<br> New Groupname : <input type="text" name="newGroupname">
			<br>
			<br> <input type="submit" value="Update Groupname">
		</form>
		<br> <a href="menu">Back</a>
	</div>
</body>
</html>