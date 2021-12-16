<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Master Login</title>
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
	height: 200px;
	padding: 50px;
}
</style>
</head>
<body>
	<div class="center">
		<form name="master" action="login" method="post">
			<h3>Master Login</h3>
			<label for="username">Username </label> <input name="username"
				type="text" placeholder="username" id="username"> <br>
			<br> <label for="password">Password </label> <input
				name="password" type="password" placeholder="password" id="password">
			<br>
			<br>
			<button type="submit" value="Login">Log In</button>
			<br>
			<br> <a href="/master/">Back</a>
		</form>
	</div>

</body>
</html>