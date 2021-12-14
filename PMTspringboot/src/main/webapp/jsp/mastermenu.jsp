<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!Doctype html>
<html>
<style>
body{
	background-image: linear-gradient(red, blue);
}
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
</style>
<body>

<h1>Master User</h1>

<div class="btn-group">
<form name="masterLogin" action="loginForm" >
<button>Login</button>
</form>
<form name="masterRegister" action="registerForm" >
<button>Register</button>
</form>
<br>
</div>


</body>
</html>
