<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Add Account Form</title>
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
	width: 300px;
	height: 250px;
	padding: 50px;
}
</style>
</head>
<body>
	<div class="center">
		<h3>Add Account</h3>
		<form name="Add Account" action="addAccount" method="post">
			Url : <input type="text" name="url"> <br>
			<br> Username : <input type="text" name="username"> <br>
			<br> Password : <input type="password" name="password">
			<br>
			<br> Groupname : <input type="text" name="groupname"> <br>
			<br> <input type="submit" value="Add Account"> <br>
			<br> <a href="menu">Back</a>
	</div>
	</form>
</body>
</html>