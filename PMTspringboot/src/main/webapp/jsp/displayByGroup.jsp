<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h2>Group Accounts</h2>

<table border=1>
<tr>
<th>URL</th>
 <th>Name </th>
 <th>Password</th>
 <th>Groupname</th>
</tr>

 <c:forEach items="${accounts}" var="account">
<tr>
 <td> <c:out value="${account.url}" ></c:out> </td>
 <td> <c:out value="${account.username}" ></c:out> </td>
 <td> <c:out value="${account.password}" ></c:out> </td>
 <td> <c:out value="${account.groupname}" ></c:out> </td>
</tr>

</c:forEach>

</table>

<a href="/master/account/menu/" >Menu</a>
</body>
</html>