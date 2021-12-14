<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
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

.left {
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
	<div class="left">
		<h3>Delete Account</h3>
		<form name="deleteAccountForm" action="deleteAccount" method="post">
			Url : <input type="text" name="url"> <br>
			<br> <input type="submit" value="Delete Account"> <br>
			<br> <a href="menu">Back</a>
		</form>

	</div>
</body>
</html>