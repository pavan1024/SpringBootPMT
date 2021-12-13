<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Master Login</title>
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
    <form name="master" action="account/menu" method="post">
        <h3>Master Login</h3>
        <label for="username">Username </label>
        <input name="username" type="text" placeholder="username" id="username">
        <br><br>
        <label for="password">Password </label>
        <input name="password" type="password" placeholder="password" id="password">
        <br><br>
        <button type="submit" value="Login" >Log In</button>
        <br><br>
		<a href="/master/" >Back</a>
    </form>
    </div>

</body>
</html>