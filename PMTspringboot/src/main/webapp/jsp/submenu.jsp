<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
.btn-group button {
	background-color: #04AA6D; /* Green background */
	border: 1px solid green; /* Green border */
	color: white; /* White text */
	padding: 10px 24px; /* Some padding */
	cursor: pointer; /* Pointer/hand icon */
	width: 50%; /* Set a width if needed */
	display: block; /* Make the buttons appear below each other */
}

.btn-group button:not(:last-child) {
	border-bottom: none; /* Prevent double borders */
}

/* Add a background color on hover */
.btn-group button:hover {
	background-color: #3e8e41;
}

body {
	background: #00C9FF; /* fallback for old browsers */
	background: -webkit-linear-gradient(to right, #92FE9D, #00C9FF);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to right, #92FE9D, #00C9FF);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}

.center {
	margin: auto;
	width: 600px;
	height: 100px;
	padding: 50px;
}
</style>
</head>
<body>
	<div class="center">
		<div class="btn-group">
			<h1>Sub Menu</h1>
			<form name="CreateAccount" action="updateAccountUsernameForm">
				<button>Update Username</button>
			</form>
			<form name="DisplayAccountPassword"
				action="updateAccountPasswordForm">
				<button>Update Password</button>
			</form>
			<br> <a href="menu">Back</a>
		</div>
	</div>
</body>
</html>