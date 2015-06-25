<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Add New Place</title>

	<jsp:include page="/views/elements/css.jsp"/>
	<jsp:include page="/views/elements/script.jsp"/>

<body>
<jsp:include page="/views/elements/header.jsp"/>
<div class="container">
	<h2>Add new Place</h2>
	<hr>
	<div class="text-center">
		<form method=post enctype=multipart/form-data action="portal/update">
			<c:set var="command" scope="session" value="newPlaceFoto"/>
			<c:set var="typePhoto" scope="session" value="placeFoto"/>
			<div class="row">
				<h6>Upload a different photo...</h6>
				<input id="fileupload" type="file" name="sendfile">
			</div>
			<div class="row">
				<label class="active" for="addPlaceNameUA">Place name in UA:</label>
				<input value="" id="addPlaceNameUA" type="text" name="addPlaceNameUA">
			</div>

			<div class="row">
				<label class="active" for="addPlaceNameEN">Place name in EN:</label>
				<input value="" id="addPlaceNameEN" type="text" name="addPlaceNameEN">
			</div>

			<div class="row">
				<label class="active" for="addPlaceDescriptionUA">Place description in UA:</label>
				<input value="" id="addPlaceDescriptionUA" type="text" name="addPlaceDescriptionUA">
			</div>

			<div class="row">
				<label class="active" for="addPlaceDescriptionEN">Place description in EN:</label>
				<input value="" id="addPlaceDescriptionEN" type="text" name="addPlaceDescriptionEN">
			</div>

			<div class="row">
				<label class="active" for="addPlaceAddress">Place address:</label>
				<input value="" id="addPlaceAddress" type="text" name="addPlaceAddress">
			</div>

			<div class="row">
				<label Place Category:</label>
				<select name="newCategory">
					<option value="1">Architectural sights</option>
					<option value="2">Churches</option>
					<option value="3">Theatres</option>
					<option value="4">Hotels</option>
					<option value="5">Restaurants</option>
				</select>
			</div>

			<div class="row">
				<label class="active" for="addPlaceTime">Minimum time what need for visiting this place:</label>
				<input value="" id="addPlaceTime" type="text" name="addPlaceTime">
			</div>

			<div class="row">
				<label class="active" for="addPlacePriceUA">Place Price in UA:</label>
				<input value="" id="addPlacePriceUA" type="text" name="addPlacePriceUA">
			</div>

			<div class="row">
				<label class="active" for="addPlacePriceEN">Place Price in EN:</label>
				<input value="" id="addPlacePriceEN" type="text" name="addPlacePriceEN">
			</div>

			<div class="row">
				<label class="active" for="addPlacePhone">Place Phone:</label>
				<input value="" id="addPlacePhone" type="text" name="addPlacePhone">
			</div>

			<div class="row">
				<label class="active" for="addPlaceLatitude">Place Latitude:</label>
				<input value="" id="addPlaceLatitude" type="text" name="addPlaceLatitude">
			</div>

			<div class="row">
				<label class="active" for="addPlacelongitude">Place Longitude:</label>
				<input value="" id="addPlacelongitude" type="text" name="addPlacelongitude">
			</div>

			<div class="form-group">
				<label class="col-md-3 control-label"></label>
				<button class="btn waves-effect waves-light" type="submit" name="save">Save Information
					<i class="mdi-content-send right"></i></button>
				<button class="btn waves-effect waves-light" type="reset" name="cancel">Cancel</button>
			</div>
	</div>
	</form>

</div>

<br>
<jsp:include page="/views/elements/footer.jsp"/>
</body>
</html>
