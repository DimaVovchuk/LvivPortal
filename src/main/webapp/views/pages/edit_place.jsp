<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="keywords" content=""/>
</head>
<jsp:include page="/views/elements/css.jsp"/>
<jsp:include page="/views/elements/script.jsp"/>

<body>
<jsp:include page="/views/elements/header.jsp"/>
<div class="container">
	<h1>Edit Place</h1>
	<hr>
	<div class="text-center">
		<c:forEach items="${placeImageList}" var="elem">
			<img class="materialboxed" width="200" height="200"
			     src="${pageContext.request.contextPath}/upload/photo/${elem.reference}">
		</c:forEach>

		<form method=post enctype=multipart/form-data action="/portal/editplace">
			<c:set var="command" scope="session" value="saveEditPlace"/>
			<div class="row">
				<h6>Add new foto...</h6>
				<input id="fileupload" type="file" multiple="multiple" name="sendfile">
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
							<input value="<c:out value="${placeDescriptionList[0].description}"/>"
							       id="placeDescriptionUA" type="text" name="placeDescriptionUA">
						</c:when>
						<c:otherwise>
							<label class="active" for="placeDescriptionEN">Place description:</label>
							<input value="<c:out value="${placeDescriptionList[1].description}"/>"
							       id="placeDescriptionEN" type="text" name="placeDescriptionEN">
						</c:otherwise>
					</c:choose>
				</div>
			</c:forEach>


			<c:forEach items="${placeDescriptionList}" var="elem">
				<div class="row">
					<c:choose>
						<c:when test="${elem.locale eq 'UA'}">
							<label class="active" for="placePriceUA">Place price:</label>
							<input value="<c:out value="${placeDescriptionList[0].price}"/>" id="placePriceUA"
							       type="text"
							       name="placePriceUA">
						</c:when>
						<c:otherwise>
							<label class="active" for="placePriceEN">Place price:</label>
							<input value="<c:out value="${placeDescriptionList[1].price}"/>" id="placePriceEN"
							       type="text"
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
				<label>Current сategory:</label>
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

			<div class="row">
				<label class="active" for="newPlaceRating">Place rating:</label>
				<input value="${editPlace.rating}" id="newPlaceRating" type="hidden" name="newPlaceRating">
				<input value="${editPlace.rating}" type="text" disabled>
			</div>

			<div class="form-group">
				<label class="col-md-3 control-label"></label>
				<button class="btn waves-effect waves-light" type="submit" name="save">Save Information
					<i class="mdi-content-send right"></i></button>
				<button class="btn waves-effect waves-light" type="reset" name="cancel">Cancel</button>
			</div>
		</form>
	</div>
</div>
<hr>
<jsp:include page="/views/elements/footer.jsp"/>
</body>
</html>
