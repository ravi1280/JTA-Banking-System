<%--
  Created by IntelliJ IDEA.
  User: ravis
  Date: 7/5/2025
  Time: 9:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form method="POST" action="${pageContext.request.contextPath}/login" >
    <table>

        <tr>
            <th>Email</th>
            <td><input type="text" name="email" /></td>
        </tr>

        <tr>
            <th>Password</th>
            <td><input type="password" name="password" /></td>
        </tr>
        <tr>
            <td><input type="submit" name="login" /></td>
        </tr>
    </table>
</form>

</body>
</html>
