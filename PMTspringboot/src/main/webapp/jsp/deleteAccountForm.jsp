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
<h3>Delete Account</h3>
<form name="deleteAccountForm" action="deleteAccount" method="post">
Url : <input type="text" name="url">
<br><br>
<input type="submit" value="Delete Account">
</form>
<br>
<a href="menu" >Back</a>
</div>
</body>
</html>