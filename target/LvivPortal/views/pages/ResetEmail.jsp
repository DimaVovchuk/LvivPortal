<%--
  Created by IntelliJ IDEA.
  User: Dima
  Date: 15-Jun-15
  Time: 00:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<input>
<head>
    <title>Reset email</title>
</head>
<c:choose>
    <c:when test="${confirmed != 1}">
        <form action="portal?command=sendEmail" method="post">
            Email: <input name="email" type="email"/>
            <input type="submit">
        </form>
    </c:when>
    <c:when test="${confirmed == 1}">
        <form action="portal?command=confirmReset" method="post">
            New password: <input name="password" type="password"/>
            <input type="submit">
        </form>
    </c:when>
</c:choose>
</body>
</html>
