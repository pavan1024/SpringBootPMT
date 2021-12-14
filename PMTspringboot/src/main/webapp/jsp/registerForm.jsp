<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body{
background-color: white;
}
.left {
  margin: left;
  width: 50%;
  border: 3px solid #73AD21;
  padding: 10px;
}
</style>
</head>
<body>
<div class="left">
<form name="masterRegister" action="register" method="post">
Username : <input placeholder="username"  type="text" name="username">
<br><br>
Password : <input placeholder="password" type="password" name="password">
<br><br>
<input type="submit" value="Login">
<br><br>
<a href="/master/" >Menu</a>
</form>
</div>
</body>
</html>