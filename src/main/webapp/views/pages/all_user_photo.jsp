<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

	<form id="sign-in-form" action="portal/showalluser" method="post">
		<input type="hidden" name="command" value="showAllUser">
		<button type="submit" >OK
		</button>
	</form>
<jsp:include page="/views/pages/uploadTest.jsp"/>

</body>
</html>
