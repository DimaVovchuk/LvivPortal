<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<html>
<head>
	<title><cdg:l18n key="addnewplace.title"/></title>
	<jsp:include page="/views/elements/css.jsp"/>
	<jsp:include page="/views/elements/script.jsp"/>

<body>
<jsp:include page="/views/elements/header.jsp"/>
<div class="container">
	<h2><cdg:l18n key="addnewplace.title"/></h2>
	<hr>
	<div class="text-center">
		<form method=post enctype=multipart/form-data action="portal/newplace">
			<c:set var="command" scope="session" value="saveNewPlace"/>
			<c:set var="typePhoto" scope="session" value="placeFoto"/>
			<div class="row">
				<h6><cdg:l18n key="editplace.addphoto"/></h6>
				<input id="fileupload" type="file" multiple="multiple" name="sendfile">
			</div>
			<div class="row">
				<label class="active" for="addPlaceNameUA"><cdg:l18n key="editplace.placenameUA"/></label>
				<input value="" id="addPlaceNameUA" type="text" name="addPlaceNameUA">
			</div>

			<div class="row">
				<label class="active" for="addPlaceNameEN"><cdg:l18n key="editplace.placenameEN"/></label>
				<input value="" id="addPlaceNameEN" type="text" name="addPlaceNameEN">
			</div>

			<div class="row">
				<label class="active" for="addPlaceDescriptionUA"><cdg:l18n key="editplace.placediscUA"/></label>
				<input value="" id="addPlaceDescriptionUA" type="text" name="addPlaceDescriptionUA">
			</div>

			<div class="row">
				<label class="active" for="addPlaceDescriptionEN"><cdg:l18n key="editplace.placediscEN"/></label>
				<input value="" id="addPlaceDescriptionEN" type="text" name="addPlaceDescriptionEN">
			</div>

			<div class="row">
				<label class="active" for="addPlaceAddressUA"><cdg:l18n key="editplace.placeaddressUA"/></label>
				<input value="" id="addPlaceAddressUA" type="text" name="addPlaceAddressUA">
			</div>

			<div class="row">
				<label class="active" for="addPlaceAddressEN"><cdg:l18n key="editplace.placeaddressEN"/></label>
				<input value="" id="addPlaceAddressEN" type="text" name="addPlaceAddressEN">
			</div>

			<div class="row">
				<label><cdg:l18n key="editplace.choseplacecategory"/></label>
				<select name="newCategory">
					<option value="6"><cdg:l18n key="editplace.otherPlaces"/></option>
					<option value="1"><cdg:l18n key="editplace.architecture"/></option>
					<option value="2"><cdg:l18n key="editplace.churches"/></option>
					<option value="3"><cdg:l18n key="editplace.theatres"/></option>
					<option value="4"><cdg:l18n key="editplace.hotels"/></option>
					<option value="5"><cdg:l18n key="editplace.restaurants"/></option>
				</select>
			</div>

			<div class="row">
				<label class="active" for="addPlaceTime"><cdg:l18n key="editplace.placetime"/></label>
				<input value="" id="addPlaceTime" type="text" name="addPlaceTime">
			</div>

			<div class="row">
				<label class="active" for="addPlacePriceUA"><cdg:l18n key="editplace.placepricUA"/></label>
				<input value="" id="addPlacePriceUA" type="text" name="addPlacePriceUA">
			</div>

			<div class="row">
				<label class="active" for="addPlacePriceEN"><cdg:l18n key="editplace.placepriceEN"/></label>
				<input value="" id="addPlacePriceEN" type="text" name="addPlacePriceEN">
			</div>

			<div class="row">
				<label class="active" for="addPlacePhone"><cdg:l18n key="editplace.placephone"/></label>
				<input value="" id="addPlacePhone" type="text" name="addPlacePhone">
			</div>

			<div class="row">
				<label class="active" for="addPlaceLatitude"><cdg:l18n key="editplace.placealat"/></label>
				<input value="" id="addPlaceLatitude" type="text" name="addPlaceLatitude">
			</div>

			<div class="row">
				<label class="active" for="addPlacelongitude"><cdg:l18n key="editplace.placealon"/></label>
				<input value="" id="addPlacelongitude" type="text" name="addPlacelongitude">
			</div>

			<div class="form-group">
				<label class="col-md-3 control-label"></label>
				<button class="btn waves-effect waves-light" type="submit" name="save"><cdg:l18n key="editplace.placesavechange"/>
					<i class="mdi-content-send right"></i></button>
				<button class="btn waves-effect waves-light" type="reset" name="cancel"><cdg:l18n key="editplace.placecancel"/></button>
			</div>
		</form>
	</div>
</div>

<br>
<jsp:include page="/views/elements/footer.jsp"/>
</body>
</html>
