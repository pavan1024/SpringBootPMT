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
<h3>Update Groupname</h3>
<form name="update Groupname" action="updateGroupname" method="post">
Current Groupname : <input type="text" name="currentGroupname">
<br><br>
New Groupname : <input type="text" name="newGroupname">
<br><br>
<input type="submit" value="Update Groupname">
</form>
<br>
<a href="menu" >Back</a>
</div>
</body>
</html>