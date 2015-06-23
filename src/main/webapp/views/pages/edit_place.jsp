<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Edit Place</title>
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
			<c:forEach items="${placeImageList}" var="elem">
			<td><img class="materialboxed" width="200" height="200"
			         src="${pageContext.request.contextPath}/upload/photo/${elem.reference}"></td>
			<td>
				<input type="checkbox" id="test5"/>
				<label for="test5">Delete</label>
				</c:forEach>
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
			<c:forEach items="${placeDescriptionList}" var="elem">
			<div class="row">
				<label class="active" for="placeName">Place name:</label>
				<input value="<c:out value="${elem.name}"/>" id="placeName" type="text" name="placeName">
			</div>
			</c:forEach>

			<c:forEach items="${placeDescriptionList}" var="elem">
			<div class="row">
				<label class="active" for="placeDescription">Place description:</label>
				<input value="<c:out value="${elem.description}"/>" id="placeDescription" type="text"
				       name="placeDescription">
			</div>
			</c:forEach>

			<c:forEach items="${placeDescriptionList}" var="elem">
			<div class="row">
				<label class="active" for="placePrice">Place price:</label>
				<input value="<c:out value="${elem.price}"/>" id="placePrice" type="text" name="placePrice">
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
				<input value="${editPlace.place_time}" id="place_time" type="text" class="validate" name="place_time">
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
				<label class="active" for="place_time">Current state: ${editPlace.deleted}</label>
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
				<label class="active" for="placeRating">Place rating:</label>
				<input value="${placeRating}" id="placeRating" type="text" class="validate" name="placeRatings" disabled>
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
