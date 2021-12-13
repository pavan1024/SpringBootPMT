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
<form name="deleteGroupForm" action="deleteGroup" method="post">
Groupname : <input type="text" name="groupname">
<br><br>
<input type="submit" value="Delete Group">
<br><br>
<a href="menu" >Back</a>
</form>
</div>
</body>
</html>