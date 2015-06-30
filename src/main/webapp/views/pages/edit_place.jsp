<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title><cdg:l18n key="editplace.title"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="keywords" content=""/>
</head>
<jsp:include page="/views/elements/css.jsp"/>
<jsp:include page="/views/elements/script.jsp"/>

<body>
<jsp:include page="/views/elements/header.jsp"/>
<div class="container">
	<h1><cdg:l18n key="editplace.editPlace"/></h1>
	<hr>
	<div class="text-center">
		<c:forEach items="${placeImageList}" var="elem">
			<c:if test="${elem.deleted eq false}">
				<img class="materialboxed" width="200" height="200"
				     src="${pageContext.request.contextPath}/upload/photo/${elem.reference}">
				<button class="btn modal-trigger" type="submit" data-target="deleteImage"
				        id="btn1" onclick="$('#deleteImageID').val('${elem.id}')"> DELETE
				</button>
			</c:if>
		</c:forEach>

		<div id="deleteImage" class="modal">
			<div class="modal-content">
				<h4>Modal Header</h4>

				<form id="change_status" action="#" method="get">
					<input type="hidden" name="command" value="deleteImageByDB">
					<input placeholder="Placeholder" id="deleteImageID" name="deleteImageID" type="hidden">
					<button class="btn waves-effect waves-light cyan darken-2" type="submit">YES</button>
					<button class="modal-action modal-close btn waves-effect waves-light cyan darken-2" type="reset">
						NO
					</button>
				</form>
			</div>
		</div>


		<form method=post enctype=multipart/form-data action="/portal/editplace">
			<c:set var="command" scope="session" value="saveEditPlace"/>
			<div class="row">
				<h6><cdg:l18n key="editplace.addphoto"/></h6>
				<input id="fileupload" type="file" multiple="multiple" name="sendfile">
			</div>

			<c:forEach items="${placeDescriptionList}" var="elem">
				<div class="row">
					<c:choose>
						<c:when test="${elem.locale eq 'UA'}">
							<label class="active" for="placeNameUA"><cdg:l18n key="editplace.placenameUA"/></label>
							<input value="<c:out value="${placeDescriptionList[0].name}"/>" id="placeNameUA" type="text"
							       name="placeNameUA">
						</c:when>
						<c:otherwise>
							<label class="active" for="placeNameEN"><cdg:l18n key="editplace.placenameEN"/></label>
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
							<label class="active" for="placeDescriptionUA"><cdg:l18n
									key="editplace.placediscUA"/></label>
							<input value="<c:out value="${placeDescriptionList[0].description}"/>"
							       id="placeDescriptionUA" type="text" name="placeDescriptionUA">
						</c:when>
						<c:otherwise>
							<label class="active" for="placeDescriptionEN"><cdg:l18n
									key="editplace.placediscEN"/></label>
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
							<label class="active" for="placePriceUA"><cdg:l18n key="editplace.placepricUA"/></label>
							<input value="<c:out value="${placeDescriptionList[0].price}"/>" id="placePriceUA"
							       type="text"
							       name="placePriceUA">
						</c:when>
						<c:otherwise>
							<label class="active" for="placePriceEN"><cdg:l18n key="editplace.placepriceEN"/></label>
							<input value="<c:out value="${placeDescriptionList[1].price}"/>" id="placePriceEN"
							       type="text"
							       name="placePriceEN">
						</c:otherwise>
					</c:choose>
				</div>
			</c:forEach>

			<div class="row">
				<label class="active" for="placePhone"><cdg:l18n key="editplace.placephone"/></label>
				<input value="<c:out value="${editPlacePhone}"/>" id="placePhone" type="text" name="placePhone">
			</div>

			<div class="row">
				<label class="active" for="placeAdress"><cdg:l18n key="editplace.placeaddress"/></label>
				<input value="${editPlace.adress}" id="placeAdress" type="text" name="placeAdress">
			</div>

			<div class="row">
				<label class="active" for="placeLatitude"><cdg:l18n key="editplace.placealat"/></label>
				<input value="${editPlace.latitude}" id="placeLatitude" type="text" name="placeLatitude">
			</div>

			<div class="row">
				<label class="active" for="placeLongitude"><cdg:l18n key="editplace.placealon"/></label>
				<input value="${editPlace.longitude}" id="placeLongitude" type="text" name="placeLongitude">
			</div>

			<div class="row">
				<label class="active" for="place_time"><cdg:l18n key="editplace.placetime"/></label>
				<input value="${editPlace.place_time}" id="place_time" type="text" name="place_time">
			</div>

			<div class="row">
				<label><cdg:l18n key="editplace.choseplacecategory"/></label>

				<jsp:include page="/views/pages/show_category_for_place_edit.jsp"/>
			</div>

			<div class="row">
				<label><cdg:l18n key="editplace.placevis"/></label>
				<select name="newVisible">
					<c:choose>
						<c:when test="${editPlace.visible}">
							<option value="true"><cdg:l18n key="editplace.placevisible"/></option>
							<option value="false"><cdg:l18n key="editplace.placeunvisible"/></option>
						</c:when>
						<c:otherwise>
							<option value="false"><cdg:l18n key="editplace.placeunvisible"/></option>
							<option value="true"><cdg:l18n key="editplace.placevisible"/></option>
						</c:otherwise>
					</c:choose>
				</select>
			</div>


			<div class="row">
				<label class="active" for="place_time"><cdg:l18n key="editplace.placestat"/></label>
				<select name="newState">
					<c:choose>
						<c:when test="${editPlace.deleted}">
							<option value="true"><cdg:l18n key="editplace.placestatdelete"/></option>
							<option value="false"><cdg:l18n key="editplace.placestatactive"/></option>
						</c:when>
						<c:otherwise>
							<option value="false"><cdg:l18n key="editplace.placestatactive"/></option>
							<option value="true"><cdg:l18n key="editplace.placestatdelete"/></option>
						</c:otherwise>
					</c:choose>
				</select>
			</div>

			<div class="row">
				<label class="active" for="newPlaceRating"><cdg:l18n key="editplace.placerating"/></label>
				<input value="${editPlace.rating}" id="newPlaceRating" type="hidden" name="newPlaceRating">
				<input value="${editPlace.rating}" type="text" disabled>
			</div>

			<div class="form-group">
				<label class="col-md-3 control-label"></label>
				<button class="btn waves-effect waves-light" type="submit" name="save"><cdg:l18n
						key="editplace.placesavechange"/><i class="mdi-content-send right"></i></button>
				<button class="btn waves-effect waves-light" type="reset" name="cancel"><cdg:l18n
						key="editplace.placecancel"/></button>
			</div>
		</form>
	</div>
</div>
<hr>
<jsp:include page="/views/elements/footer.jsp"/>
</body>
</html>
