<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<link href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.js"></script>
	<jsp:include page="/views/elements/css.jsp"/>
	<jsp:include page="/views/elements/script.jsp"/>
	<title>Gallery</title>
</head>
<body>
<jsp:include page="/views/elements/header.jsp"/>

<div class="fotorama" data-transition="crossfade" data-nav="thumbs" data-loop="true" data-autoplay="true" data-allowfullscreen="true" data-keyboard="true" >
	<c:forEach items="${AllUserPhoto}" var="elem">

		<img class="materialboxed" width="200" height="200"
		     src="${pageContext.request.contextPath}/upload/photo/${elem.reference}">
	</c:forEach>
</div>
<br>
<jsp:include page="/views/pages/uploadImage.jsp"/>

<jsp:include page="/views/elements/footer.jsp"/>
</body>
</html>
