<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<jsp:include page="/views/elements/css.jsp"/>
	<jsp:include page="/views/elements/script.jsp"/>
	<title></title>
</head>
<body>
<c:forEach items="${AllUserPhoto}" var="elem">
	<th><img class="materialboxed" width="200"  height="200" src="${pageContext.request.contextPath}/upload/photo/${elem.reference}"></th>
</c:forEach>

	<form id="sign-in-form" action="portal/showalluser" method="post">
		<input type="hidden" name="command" value="showAllUser">
		<button type="submit" >OK
		</button>
	</form>
<jsp:include page="/views/pages/uploadTest.jsp"/>

</body>
</html>
