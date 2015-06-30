<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<jsp:include page="/views/elements/css.jsp"/>
	<jsp:include page="/views/elements/script.jsp"/>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
	<script type="text/javascript">
		var geocoder = new google.maps.Geocoder();

		function geocodePosition(pos) {
			geocoder.geocode({
				latLng: pos
			}, function (responses) {
				if (responses && responses.length > 0) {
					updateMarkerAddress(responses[0].formatted_address);
				} else {
					updateMarkerAddress('Cannot determine address at this location.');
				}
			});
		}

		function updateMarkerStatus(str) {
			document.getElementById('markerStatus').innerHTML = str;
		}

		function updateMarkerPositionLat(latLng) {
			document.getElementById('info').value = latLng.lat();
		}
		function updateMarkerPositionLon(latLng) {
			document.getElementById('info1').value = latLng.lng();
		}

		function updateMarkerAddress(str) {
			document.getElementById('address').value = str;
		}

		function initialize() {
			var latLng = new google.maps.LatLng(49.8426, 24.0278);
			var map = new google.maps.Map(document.getElementById('mapCanvas'), {
				zoom: 12,
				center: latLng,
				mapTypeId: google.maps.MapTypeId.ROADMAP
			});
			var marker = new google.maps.Marker({
				position: latLng,
				title: 'Marker',
				map: map,
				draggable: true
			});

			// Update current position info.
			updateMarkerPositionLat(latLng);
			updateMarkerPositionLon(latLng);
			geocodePosition(latLng);

			// Add dragging event listeners.
			google.maps.event.addListener(marker, 'dragstart', function () {
				updateMarkerAddress('Dragging...');
			});

			google.maps.event.addListener(marker, 'drag', function () {
				updateMarkerStatus('Dragging...');
				updateMarkerPositionLat(marker.getPosition());
				updateMarkerPositionLon(marker.getPosition());
			});

			google.maps.event.addListener(marker, 'dragend', function () {
				updateMarkerStatus('Drag ended');
				geocodePosition(marker.getPosition());
			});
		}

		// Onload handler to fire off the app.
		google.maps.event.addDomListener(window, 'load', initialize);
		var abc = document.getElementsByTagName("info1");
		var value_to_store = abc[0].firstChild.nodeValue;
	</script>
</head>
<body>
<style>
	#mapCanvas {
		width: 500px;
		height: 400px;
		float: left;
	}
	#infoPanel div {
		margin-bottom: 5px;
	}
</style>
<jsp:include page="/views/elements/header.jsp"/>
<div class="container">
	<div id="edit-place">
		<div class="row">
			<div class="col l8 offset-l2 m12 s12" style="padding: 20px">
				<div class="divider"></div>
				<h4 class="center-align">Add Place</h4>
				<div id="mapCanvas"></div>
				<div id="markerStatus"></div>
				<%--<div id="address"></div>--%>
				<form method=post enctype=multipart/form-data action="/portal/editplace">
					<c:set var="command" scope="session" value="saveCastomPlace"/>
					<div class="center-align">
						<h5>Add Photo...</h5>

						<div class="file-field input-field" style="height: 45px">
							<div class="btn cyan darken-2">
								<span>Select image</span>
								<input id="image-input" type="file" multiple name="sendfile"/>
							</div>
							<button id="image-clear" class="btn cyan darken-2">Clear image</button>
						</div>
						<div class="card z-depth-2">
							<output id="image-preview"></output>
						</div>
					</div>

					<p><b>Name</b></p>
					<input value="" id="placeNameUA" type="text" name="placeNameUA">
					<p><b>Desription</b></p><input value="" id="placeNameUA" type="text" name="placeNameUA">
					<p><b>Category</b></p>
					<c:choose>
						<c:when test="${editPlace.category_id eq 1}">
							<select name="newCategory">
								<option value="1"><cdg:l18n key="editplace.architecture"/></option>
								<option value="2"><cdg:l18n key="editplace.architecture"/></option>
								<option value="3"><cdg:l18n key="editplace.theatres"/></option>
								<option value="4"><cdg:l18n key="editplace.hotels"/></option>
								<option value="5"><cdg:l18n key="editplace.restaurants"/></option>
							</select>
						</c:when>
						<c:when test="${editPlace.category_id eq 2}">
							<select name="newCategory">
								<option value="2"><cdg:l18n key="editplace.architecture"/></option>
								<option value="1"><cdg:l18n key="editplace.churches"/></option>
								<option value="3"><cdg:l18n key="editplace.theatres"/></option>
								<option value="4"><cdg:l18n key="editplace.hotels"/></option>
								<option value="5"><cdg:l18n key="editplace.restaurants"/></option>
							</select>
						</c:when>
						<c:when test="${editPlace.category_id eq 3}">
							<select name="newCategory">
								<option value="3"><cdg:l18n key="editplace.theatres"/></option>
								<option value="2"><cdg:l18n key="editplace.architecture"/></option>
								<option value="1"><cdg:l18n key="editplace.churches"/></option>
								<option value="4"><cdg:l18n key="editplace.hotels"/></option>
								<option value="5"><cdg:l18n key="editplace.restaurants"/></option>
							</select>
						</c:when>
						<c:when test="${editPlace.category_id eq 4}">
							<select name="newCategory">
								<option value="4"><cdg:l18n key="editplace.hotels"/></option>
								<option value="3"><cdg:l18n key="editplace.theatres"/></option>
								<option value="2"><cdg:l18n key="editplace.architecture"/></option>
								<option value="1"><cdg:l18n key="editplace.churches"/></option>
								<option value="5"><cdg:l18n key="editplace.restaurants"/></option>
							</select>
						</c:when>
						<c:otherwise>
							<select name="newCategory">
								<option value="5"><cdg:l18n key="editplace.restaurants"/></option>
								<option value="4"><cdg:l18n key="editplace.hotels"/></option>
								<option value="3"><cdg:l18n key="editplace.theatres"/></option>
								<option value="2"><cdg:l18n key="editplace.architecture"/></option>
								<option value="1"><cdg:l18n key="editplace.churches"/></option>
							</select>
						</c:otherwise>
					</c:choose>
					<p><b>Price</b></p><input value="" id="placePriceUA" type="text" name="placePriceUA">
					<p><b>Phone</b></p><input value="" id="placePhone" type="text" name="placePhone">
					<p><b>Adrress</b></p><input value="" id="address" type="text" name="address">
					<div class="row">
						<div class="col s6">
							<p><b>Latitute</b></p><input value="" id="info" type="text" name="info">
						</div>
						<div class="col s6">
							<p><b>Longitute</b></p>
							<input value="" id="info1" type="text" name="info1">
						</div>
					</div>
					<p><b>Minimum time what need for visiting this place(IN MINUTE):</b></p>
					<input value="" id="place_time" type="text" name="place_time">
					<br>
					<div>
						<button class="btn waves-effect waves-light cyan darken-2" type="submit" name="save">
							<cdg:l18n key="editplace.placesavechange"/></button>
						<button class="btn waves-effect waves-light cyan darken-2" type="reset" name="cancel">
							<cdg:l18n key="editplace.placecancel"/></button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/views/elements/footer.jsp"/>

<script>
	window.onload = function () {
		if (window.File && window.FileList && window.FileReader) {
			$('#image-input').on('change', function (event) {
				var files = event.target.files;
				var output = document.getElementById('image-preview');
				for (var i = 0; i < files.length; i++) {
					var file = files[i];
					if (file.type.match('image.*')) {
						if (files[0].size < 2097152) {
							var picReader = new FileReader();
							picReader.addEventListener('load', function (event) {
								var picFile = event.target;
								var div = document.createElement("div");
								div.innerHTML = '<img class="image-thumbnail" src="' + picFile.result + '"/>';
								output.insertBefore(div, null);
							});
							$('#image-clear, #image-preview').show();
							picReader.readAsDataURL(file);
						} else {
							alert('Image Size is too big. Maximum size is 2MB.');
							$(this).val('');
						}
					} else {
						alert('You can only upload image.');
						$(this).val('');
					}
				}
			});
		}
	};

	$('#image-input').on('click', function () {
		$('.image-thumbnail').parent().remove();
		$('#image-preview').hide();
		$(this).val('');
	});

	$('#image-clear').on('click', function () {
		$('.image-thumbnail').parent().remove();
		$('#image-preview').hide();
		$('#image-input').val('');
		$(this).hide();
	});

	var placeDescriptionUA = $('#placeDescriptionUA');
	placeDescriptionUA.html(placeDescriptionUA.html().trim());
	var placeDescriptionEN = $('#placeDescriptionEN');
	placeDescriptionEN.html(placeDescriptionEN.html().trim());
</script>

</body>
</html>
