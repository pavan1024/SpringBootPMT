<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form name="CreateAccount" action="createAccountForm">
<input type="submit" value="Create Account">
</form>
<form name="DisplayAccountPassword" action="displayPasswordForm">
<input type="submit" value="Display Account Password">
</form>
<form name="DisplayAllAccountsbyGroupname" action="displayByGroupForm">
<input type="submit" value="Display All Accounts by Groupname">
</form>
<form name="Delete Account" action="deleteAccountForm" >
<input type="submit" value="Delete Account">
</form>
<form name="UpdateAccountDetails" action="submenu">
<input type="submit" value="Update Account Details">
</form>
<form name="ModifyGroupname" action="updateGroupnameForm">
<input type="submit" value="Modify Groupname">
</form>
<form name="view All" action="viewAll" >
<input type="submit" value="View All Accounts">
</form>
<form name="Delete Group" action="deleteGroupForm">
<input type="submit" value="Delete Group">
</form>
<br>
<a href="menu" >Back</a>
</body>
</html>