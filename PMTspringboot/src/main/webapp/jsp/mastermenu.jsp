<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!Doctype html>
<html>
<style>
.btn-group button {
	background-color: #04AA6D; /* Green background */
	border: 1px solid green; /* Green border */
	color: white; /* White text */
	padding: 10px 24px; /* Some padding */
	cursor: pointer; /* Pointer/hand icon */
	width: 100%; /* Set a width if needed */
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
	margin-top: 150px;
	margin-left: 500px;
	border: 1px solid white;
	width: 300px;
	height: 200px;
	padding: 50px;
}
</style>
<body>
	<div class="center">
		<h1>Master User</h1>

		<div class="btn-group">
			<form name="masterLogin" action="loginForm">
				<button>Login</button>
			</form>
			<form name="masterRegister" action="registerForm">
				<button>Register</button>
			</form>
			<br>
		</div>
	</div>


</body>
</html>


