<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<jsp:include page="/views/elements/css.jsp"/>
	<jsp:include page="/views/elements/script.jsp"/>
	<title></title>
</head>
<body>
	<jsp:include page="/views/elements/header.jsp"/>
	<c:forEach items="${AllUserPhoto}" var="elem">
		<th><img class="materialboxed" width="200"  height="200" src="${pageContext.request.contextPath}/upload/photo/${elem.reference}"></th>
	</c:forEach>
	<jsp:include page="/views/pages/uploadImage.jsp"/>


	<b>show all user test command</b>
	<form id="sign-in-form" action="portal/showalluser" method="post">
		<input type="hidden" name="command" value="showAllUser">
		<input type="hidden" name="requestType" value="showAllUser">
		<button type="submit" >Show all user
		</button>
	</form>
	<b>edit place test command</b>
	<form id="edit_place" action="portal/editplace" method="post">
		<input type="hidden" name="command" value="editPlace">
		<button type="submit" >Edit Place
		</button>
	</form>
	<jsp:include page="/views/elements/footer.jsp"/>
</body>
</html>
