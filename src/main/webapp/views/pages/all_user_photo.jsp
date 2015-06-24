<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<!-- 1. Link to jQuery (1.8 or later), -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

	<!-- fotorama.css & fotorama.js. -->
	<link href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css" rel="stylesheet">
	<!-- 3 KB -->
	<script src="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.js"></script>
	<!-- 16 KB -->
	<jsp:include page="/views/elements/css.jsp"/>
	<jsp:include page="/views/elements/script.jsp"/>
	<title>Gallery</title>
</head>
<body>
<jsp:include page="/views/elements/header.jsp"/>

<div class="fotorama" data-transition="crossfade" data-loop="true" data-autoplay="true" data-allowfullscreen="true" data-keyboard="true" >
	<c:forEach items="${AllUserPhoto}" var="elem">

		<img class="materialboxed" width="200" height="200"
		     src="${pageContext.request.contextPath}/upload/photo/${elem.reference}">
	</c:forEach>
</div>
<jsp:include page="/views/pages/uploadImage.jsp"/>


<b>show all user test command</b>

<form id="sign-in-form" action="/portal/showalluser" method="post">
	<input type="hidden" name="command" value="showAllUser">
	<input type="hidden" name="requestType" value="showAllUser">
	<button type="submit">Show all user
	</button>
</form>
<b>edit place test command</b>

<form id="edit_place" action="/portal/editplace" method="post">
	<input type="hidden" name="command" value="editPlace">
	<button type="submit">Edit Place
	</button>
</form>
<jsp:include page="/views/elements/footer.jsp"/>
</body>
</html>
