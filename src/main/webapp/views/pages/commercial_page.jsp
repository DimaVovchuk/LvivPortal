<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>
<html>
<head>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<title><cdg:l18n key="commercial.header"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="keywords" content=""/>
</head>

<jsp:include page="/views/elements/css.jsp"/>
<jsp:include page="/views/elements/script.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>
<div class="full-height">
	<div class="places">
		<div class="section">
			<h3 class="center-align"><cdg:l18n key="commercial.header"/></h3>

			<div class="row">
				<div class="col l9 m8 s7">
					<div id="place-page-container" class="row z-depth-2">
						<div class="place-page-navigation z-depth-1"></div>
						<div id="commertial-info-collection" class="place-page-content"></div>
					</div>
				</div>

				<div class="col l3 m4 s5">
					<div class="collection with-header z-depth-2">
						<div class="collection-header"><h4 class="center-align"><cdg:l18n key="commercial.name"/></h4></div>
						<div id="category-place">
							<a href="portal?command=commercialJSON&comertsRole=guide"
							   class="collection-item black-text "><cdg:l18n key="commercial.guide"/></a>
							<a href="portal?command=commercialJSON&comertsRole=agency"
							   class="collection-item black-text "><cdg:l18n key="commercial.agency"/></a>
							<a href="portal?command=commercialJSON"
							   class="collection-item black-text"><cdg:l18n key="commercial.showall"/></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/pages/commercialPage.js"></script>
<script id="commertial-info-template" type="text/x-handlebars-template">
	{{#each this}}
	<div class="match-col col l4 m6 s12">
		<div class="card z-depth-2 center-align" style="padding:10px; height:95%">
			<a href="/portal?command=companyInformation&id={{id}}"><img
					class="responsive-img" src="${pageContext.request.contextPath}/upload/photo/{{reference}}"></a>
			<a href="/portal?command=companyInformation&id={{id}}">
				<c:set var="salary" scope="request" value="{{name}}"/>
				<c:choose>
				<c:when test="${salary != null}">
					<h5><c:out value="{{name}}"/></h5></a>
				</c:when>
				<c:otherwise>
					<h5><c:out value="{{companyName}}"/></h5></a>
				</c:otherwise>
				</c:choose>
		</div>
	</div>
	{{/each}}
</script>
</body>
</html>