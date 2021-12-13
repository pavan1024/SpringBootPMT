<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body{
background-color: "green";
}
.center {
  margin: left;
  width: 50%;
  border: 3px solid #73AD21;
  padding: 10px;
}
</style>
</head>
<body>
<div class="center">
<h3>Create Account</h3>
<form name="CreateAccount" action="createAccount" method="post">
Url : <input type="text" name="url">
<br><br>
Username : <input type="text" name="username">
<br><br>
Password : <input type="password" name="password">
<br><br>
Groupname : <input type="text" name="groupname">
<br><br>
<input type="submit" value="Create Account">
<br><br>
<a href="menu" >Back</a>
</div>
</form>
</body>
</html>