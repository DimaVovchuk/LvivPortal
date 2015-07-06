<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="cdg" uri="customtags" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<html>--%>
<%--<head>--%>
	<%--<jsp:include page="/views/elements/css.jsp"/>--%>
	<%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
	<%--<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>--%>
	<%--<meta name="keywords" content=""/>--%>
<%--</head>--%>
<%--<body>--%>
<%--<jsp:include page="/views/elements/header.jsp"/>--%>
<%--<div id="edit-place">--%>
	<%--<div class="row">--%>
		<%--<div class="col l8 offset-l2 m12 s12 z-depth-2" style="padding: 20px">--%>
			<%--<h5 class="center-align"><cdg:l18n key="addnewcustomplace.title"/></h5>--%>

			<%--<div class="row">--%>
				<%--<div id="mapCanvas"></div>--%>
				<%--<div id="markerStatus"></div>--%>
			<%--</div>--%>
			<%--<div class="divider"></div>--%>

			<%--<form method=post enctype=multipart/form-data>--%>
				<%--<c:set var="command" scope="session" value="saveCustomPlace"/>--%>
				<%--<div class="center-align">--%>
					<%--<h5><cdg:l18n key="editplace.addphoto"/></h5>--%>

					<%--<div class="file-field input-field" style="height: 45px">--%>
						<%--<div class="btn cyan darken-2">--%>
							<%--<span><cdg:l18n key="editplace.selectimage"/></span>--%>
							<%--<input id="image-input" type="file" multiple name="sendfile"/>--%>
						<%--</div>--%>
						<%--<button id="image-clear" class="btn cyan darken-2"><cdg:l18n key="editplace.clearimage"/></button>--%>
					<%--</div>--%>
					<%--<div class="card z-depth-2">--%>
						<%--<output id="image-preview"></output>--%>
					<%--</div>--%>
				<%--</div>--%>

				<%--<p><b><cdg:l18n key="addnewcustomplace.name"/></b></p><input value="" id="customPlaceName" type="text" name="customPlaceName">--%>

				<%--<p><b><cdg:l18n key="addnewcustomplace.description"/></b></p><input value="" id="customPlaceDesc" type="text" name="customPlaceDesc">--%>

				<%--<p><b><cdg:l18n key="editplace.choseplacecategory"/></b></p>--%>
				<%--<select name="newCategory">--%>
					<%--<option value="6"><cdg:l18n key="editplace.otherPlaces"/></option>--%>
					<%--<option value="1"><cdg:l18n key="editplace.architecture"/></option>--%>
					<%--<option value="2"><cdg:l18n key="editplace.churches"/></option>--%>
					<%--<option value="3"><cdg:l18n key="editplace.theatres"/></option>--%>
					<%--<option value="4"><cdg:l18n key="editplace.hotels"/></option>--%>
					<%--<option value="5"><cdg:l18n key="editplace.restaurants"/></option>--%>
				<%--</select>--%>

				<%--<p><b><cdg:l18n key="addnewcustomplace.price"/></b></p><input value="" id="customPlacePrice" type="text" name="customPlacePrice">--%>

				<%--<p><b><cdg:l18n key="editplace.placephone"/></b></p><input value="" id="customPlacePhone" type="text" name="customPlacePhone">--%>

				<%--<p><b><cdg:l18n key="editplace.placetime"/></b></p>--%>
				<%--<input value="" id="place_time" type="text" name="place_time">--%>

				<%--<p><b><cdg:l18n key="addnewcustomplace.adrress"/></b></p>--%>
				<%--<input value="" id="customPlaceAdrress" type="text" name="customPlaceAdrress" disabled>--%>
				<%--<input value="" id="customPlaceAdrressHid" type="hidden" name="customPlaceAdrressHid">--%>

				<%--<div class="row">--%>
					<%--<div class="col s6">--%>
						<%--<p><b><cdg:l18n key="editplace.placealat"/></b></p>--%>
						<%--<input value="" id="latitude" type="text" name="latitude" disabled>--%>
						<%--<input value="" id="latitudeHid" type="hidden" name="latitudeHid">--%>
					<%--</div>--%>
					<%--<div class="col s6">--%>
						<%--<p><b><cdg:l18n key="editplace.placealon"/></b></p>--%>
						<%--<input value="" id="longitude" type="text" name="longitude" disabled>--%>
						<%--<input value="" id="longitudeHid" type="hidden" name="longitudeHid">--%>
					<%--</div>--%>
				<%--</div>--%>

				<%--<br>--%>
				<%--<div>--%>
					<%--<button class="btn waves-effect waves-light cyan darken-2" type="submit" name="save">--%>
						<%--<cdg:l18n key="editplace.placesavechange"/></button>--%>
					<%--<button class="btn waves-effect waves-light cyan darken-2" type="reset" name="cancel">--%>
						<%--<cdg:l18n key="editplace.placecancel"/></button>--%>
				<%--</div>--%>
			<%--</form>--%>
		<%--</div>--%>
	<%--</div>--%>
<%--</div>--%>
<%--<jsp:include page="/views/elements/footer.jsp"/>--%>

<%--<script>--%>
	<%--window.onload = function () {--%>
		<%--if (window.File && window.FileList && window.FileReader) {--%>
			<%--$('#image-input').on('change', function (event) {--%>
				<%--var files = event.target.files;--%>
				<%--var output = document.getElementById('image-preview');--%>
				<%--for (var i = 0; i < files.length; i++) {--%>
					<%--var file = files[i];--%>
					<%--if (file.type.match('image.*')) {--%>
						<%--if (files[0].size < 2097152) {--%>
							<%--var picReader = new FileReader();--%>
							<%--picReader.addEventListener('load', function (event) {--%>
								<%--var picFile = event.target;--%>
								<%--var div = document.createElement("div");--%>
								<%--div.innerHTML = '<img class="image-thumbnail" src="' + picFile.result + '"/>';--%>
								<%--output.insertBefore(div, null);--%>
							<%--});--%>
							<%--$('#image-clear, #image-preview').show();--%>
							<%--picReader.readAsDataURL(file);--%>
						<%--} else {--%>
							<%--alert('Image Size is too big. Maximum size is 2MB.');--%>
							<%--$(this).val('');--%>
						<%--}--%>
					<%--} else {--%>
						<%--alert('You can only upload image.');--%>
						<%--$(this).val('');--%>
					<%--}--%>
				<%--}--%>
			<%--});--%>
		<%--}--%>
	<%--};--%>

	<%--$('#image-input').on('click', function () {--%>
		<%--$('.image-thumbnail').parent().remove();--%>
		<%--$('#image-preview').hide();--%>
		<%--$(this).val('');--%>
	<%--});--%>

	<%--$('#image-clear').on('click', function () {--%>
		<%--$('.image-thumbnail').parent().remove();--%>
		<%--$('#image-preview').hide();--%>
		<%--$('#image-input').val('');--%>
		<%--$(this).hide();--%>
	<%--});--%>

	<%--var placeDescriptionUA = $('#placeDescriptionUA');--%>
	<%--placeDescriptionUA.html(placeDescriptionUA.html().trim());--%>
	<%--var placeDescriptionEN = $('#placeDescriptionEN');--%>
	<%--placeDescriptionEN.html(placeDescriptionEN.html().trim());--%>
<%--</script>--%>

<%--<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>--%>
<%--<script type="text/javascript">--%>
	<%--var geocoder = new google.maps.Geocoder();--%>

	<%--function geocodePosition(pos) {--%>
		<%--geocoder.geocode({--%>
			<%--latLng: pos--%>
		<%--}, function (responses) {--%>
			<%--if (responses && responses.length > 0) {--%>
				<%--updateMarkerAddress(responses[0].formatted_address);--%>
			<%--} else {--%>
				<%--updateMarkerAddress('Cannot determine address at this location.');--%>
			<%--}--%>
		<%--});--%>
	<%--}--%>

	<%--function updateMarkerStatus(str) {--%>
		<%--document.getElementById('markerStatus').innerHTML = str;--%>
	<%--}--%>

	<%--function updateMarkerPositionLat(latLng) {--%>
		<%--document.getElementById('latitude').value = latLng.lat();--%>
		<%--document.getElementById('latitudeHid').value = latLng.lat();--%>
	<%--}--%>
	<%--function updateMarkerPositionLon(latLng) {--%>
		<%--document.getElementById('longitude').value = latLng.lng();--%>
		<%--document.getElementById('longitudeHid').value = latLng.lng();--%>
	<%--}--%>

	<%--function updateMarkerAddress(str) {--%>
		<%--document.getElementById('customPlaceAdrress').value = str;--%>
		<%--document.getElementById('customPlaceAdrressHid').value = str;--%>
	<%--}--%>

	<%--function initialize() {--%>
		<%--var latLng = new google.maps.LatLng(49.8426, 24.0278);--%>
		<%--var map = new google.maps.Map(document.getElementById('mapCanvas'), {--%>
			<%--zoom: 12,--%>
			<%--center: latLng,--%>
			<%--mapTypeId: google.maps.MapTypeId.ROADMAP--%>
		<%--});--%>
		<%--var marker = new google.maps.Marker({--%>
			<%--position: latLng,--%>
			<%--title: 'Marker',--%>
			<%--map: map,--%>
			<%--draggable: true--%>
		<%--});--%>

		<%--// Update current position info.--%>
		<%--updateMarkerPositionLat(latLng);--%>
		<%--updateMarkerPositionLon(latLng);--%>
		<%--geocodePosition(latLng);--%>

		<%--// Add dragging event listeners.--%>
		<%--google.maps.event.addListener(marker, 'dragstart', function () {--%>
			<%--updateMarkerAddress('Dragging...');--%>
		<%--});--%>

		<%--google.maps.event.addListener(marker, 'drag', function () {--%>
			<%--updateMarkerStatus('Dragging...');--%>
			<%--updateMarkerPositionLat(marker.getPosition());--%>
			<%--updateMarkerPositionLon(marker.getPosition());--%>
		<%--});--%>

		<%--google.maps.event.addListener(marker, 'dragend', function () {--%>
			<%--updateMarkerStatus('Drag ended');--%>
			<%--geocodePosition(marker.getPosition());--%>
		<%--});--%>
	<%--}--%>

	<%--// Onload handler to fire off the app.--%>
	<%--google.maps.event.addDomListener(window, 'load', initialize);--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>
