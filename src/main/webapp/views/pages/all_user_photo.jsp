<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title></title>
</head>
<body>
<c:forEach items="${AllUserPhoto}" var="elem">
<tr>
	<td><img src="${pageContext.request.contextPath}/upload/photo/${elem.reference}" alt="" height="100" width="100"></td>
</tr>
</c:forEach>
</body>
</html>
