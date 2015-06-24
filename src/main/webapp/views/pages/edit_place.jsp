<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Edit Place</title>
	<!-- 1. Link to jQuery (1.8 or later), -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

	<!-- fotorama.css & fotorama.js. -->
	<link href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css" rel="stylesheet">
	<!-- 3 KB -->
	<script src="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.js"></script>
	<!-- 16 KB -->

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="keywords" content=""/>
</head>
<jsp:include page="/views/elements/css.jsp"/>
<jsp:include page="/views/elements/script.jsp"/>

<body>
<jsp:include page="/views/elements/header.jsp"/>

<div class="container">
	<div class="row">
		<h1>Edit Place</h1>
		<hr>
		<div class="text-center">
			<div class="fotorama" data-transition="crossfade" data-loop="true" data-autoplay="true" data-allowfullscreen="true" data-keyboard="true" >
				<c:forEach items="${placeImageList}" var="elem">

					<img class="materialboxed" width="200" height="200"
					     src="${pageContext.request.contextPath}/upload/photo/${elem.reference}">
				</c:forEach>
			</div>
			<h6>Add new foto...</h6>

			<div class="file-field input-field">
				<div class="row">
					<div class="col s10 offset-s1">
						<input class="file-path validate" type="text" value="" name="img"/>
					</div>
					<div class="col s2">
						<div class="btn">
							<span>Upload</span>
							<input type="file"/>
						</div>
					</div>
				</div>
			</div>
		</div>

		<form id="saveplace" action="/portal/editplace" method="post">
			<input type="hidden" name="command" value="saveEditPlace">

			<div class="row">
				<label class="active" for="newPlaceRating">Place rating:</label>
				<input value="${editPlace.rating}" id="newPlaceRating" type="text" name="newPlaceRating">
			</div>

			<c:forEach items="${placeDescriptionList}" var="elem">
			<div class="row">
				<c:choose>
					<c:when test="${elem.locale eq 'UA'}">
						<label class="active" for="placeNameUA">Place name:</label>
						<input value="<c:out value="${placeDescriptionList[0].name}"/>" id="placeNameUA" type="text"
						       name="placeNameUA">
					</c:when>
					<c:otherwise>
						<label class="active" for="placeNameEN">Place name:</label>
						<input value="<c:out value="${placeDescriptionList[1].name}"/>" id="placeNameEN" type="text"
						       name="placeNameEN">
					</c:otherwise>
				</c:choose>
			</div>
			</c:forEach>


			<c:forEach items="${placeDescriptionList}" var="elem">
			<div class="row">
				<c:choose>
					<c:when test="${elem.locale eq 'UA'}">
						<label class="active" for="placeDescriptionUA">Place description:</label>
						<input value="<c:out value="${placeDescriptionList[0].description}"/>" id="placeDescriptionUA"
						       type="text"
						       name="placeDescriptionUA">
					</c:when>
					<c:otherwise>
						<label class="active" for="placeDescriptionEN">Place description:</label>
						<input value="<c:out value="${placeDescriptionList[1].description}"/>" id="placeDescriptionEN"
						       type="text"
						       name="placeDescriptionEN">
					</c:otherwise>
				</c:choose>
			</div>
			</c:forEach>


			<c:forEach items="${placeDescriptionList}" var="elem">
			<div class="row">
				<c:choose>
					<c:when test="${elem.locale eq 'UA'}">
						<label class="active" for="placePriceUA">Place price:</label>
						<input value="<c:out value="${placeDescriptionList[0].price}"/>" id="placePriceUA" type="text"
						       name="placePriceUA">
					</c:when>
					<c:otherwise>
						<label class="active" for="placePriceEN">Place price:</label>
						<input value="<c:out value="${placeDescriptionList[1].price}"/>" id="placePriceEN" type="text"
						       name="placePriceEN">
					</c:otherwise>
				</c:choose>
			</div>
			</c:forEach>

			<div class="row">

				<label class="active" for="placePhone">Place Phone:</label>
				<input value="<c:out value="${editPlacePhone}"/>" id="placePhone" type="text" name="placePhone">
			</div>

			<div class="row">
				<label class="active" for="placeAdress">Place address:</label>
				<input value="${editPlace.adress}" id="placeAdress" type="text" name="placeAdress">
			</div>

			<div class="row">
				<label class="active" for="placeLatitude">Place latitude:</label>
				<input value="${editPlace.latitude}" id="placeLatitude" type="text" name="placeLatitude">
			</div>

			<div class="row">
				<label class="active" for="placeLongitude">Place longitude:</label>
				<input value="${editPlace.longitude}" id="placeLongitude" type="text" name="placeLongitude">
			</div>

			<div class="row">
				<label class="active" for="place_time">Place time:</label>
				<input value="${editPlace.place_time}" id="place_time" type="text" name="place_time">
			</div>

			<div class="row">
				<label>Category: ${categoryName}</label>
				<jsp:include page="/views/pages/show_category_for_place_edit.jsp"/>
			</div>

			<div class="row">
				<label>Current visibility:</label>
				<select name="newVisible">
					<c:choose>
						<c:when test="${editPlace.visible}">
							<option value="true">Visible</option>
							<option value="false">Unvisible</option>
						</c:when>
						<c:otherwise>
							<option value="false">Unvisible</option>
							<option value="true">Visible</option>
						</c:otherwise>
					</c:choose>
				</select>
			</div>


			<div class="row">
				<label class="active" for="place_time">Current state:</label>
				<select name="newState">
					<c:choose>
						<c:when test="${editPlace.deleted}">
							<option value="true">Deleted</option>
							<option value="false">Active</option>
						</c:when>
						<c:otherwise>
							<option value="false">Active</option>
							<option value="true">Deleted</option>
						</c:otherwise>
					</c:choose>
				</select>
			</div>


			<div class="form-group">
				<label class="col-md-3 control-label"></label>

				<div class="col-md-8">
					<button class="btn waves-effect waves-light" type="submit" name="save">Save Changes
						<i class="mdi-content-send right"></i></button>
					<button class="btn waves-effect waves-light" type="reset" name="cancel">Cancel</button>
				</div>
			</div>
	</div>
	</form>
</div>
<hr>
<jsp:include page="/views/elements/footer.jsp"/>
</body>
</html>
