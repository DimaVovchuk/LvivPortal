<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>
<html>
<head>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<title><cdg:l18n key="places.title"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="keywords" content=""/>
</head>

<jsp:include page="/views/elements/css.jsp"/>
<jsp:include page="/views/elements/script.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div class="places">
	<div class="section">
		<h3 class="center-align">Commertial page</h3>

		<div class="row">
			<div class="col l9 m8 s7">
				<div id="place-page-container" class="row z-depth-2">
					<div class="place-page-navigation z-depth-1"></div>
					<div class="place-page-content">
						<c:forEach items="${allUserAvatarMap}" var="elem">
							<div class="match-col col l4 m6 s12" style="display:none">
								<div class="card z-depth-2">
									<div class="center-align">
										<a href="portal?command=companyInformation&id=${elem.value.id}"><img
												class="responsive-img"
												src="${pageContext.request.contextPath}/upload/photo/${elem.key.reference}"></a>
									</div>
									<a href="portal?command=companyInformation&id=${elem.value.id}">
										<h5><c:out value="${elem.value.name}"/></h5></a>
										<%--<span><c:out value="${elem.value.about}"/></span>--%>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>

			<div class="col l3 m4 s5">
				<div class="collection with-header z-depth-2">
					<div class="collection-header"><h4>Commertial</h4></div>
					<a href="/portal?command=commercial&role=guide"
					   class="collection-item black-text ">Travel guide</a>
					<a href="/portal?command=commercial&role=agency"
					   class="collection-item black-text ">Travel agency</a>
					<a href="/portal?command=commercial"
					   class="collection-item black-text">Show all</a>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>



<script src="${pageContext.request.contextPath}/js/pages/places.js"></script>
</body>
</html>