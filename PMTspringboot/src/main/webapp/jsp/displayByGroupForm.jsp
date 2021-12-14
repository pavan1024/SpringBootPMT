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
<h3>Display Accounts By Groupname</h3>
<form name="Display By Group" action="displayByGroup" method="post">
Groupname : <input type="text" name="groupname">
<br><br>
<input type="submit" value="Display Group Accounts">
<br>
</form>
<br>
<a href="menu" >Back</a>
</div>
</body>
</html>