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
			document.getElementById('info').innerHTML = [
				latLng.lat()
			];
		}
		function updateMarkerPositionLon(latLng) {
			document.getElementById('info1' ).innerHTML = [
				latLng.lng()
			];
		}

		function updateMarkerAddress(str) {
			document.getElementById('address').innerHTML = str;
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
				title: 'Point A',
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

	#infoPanel {
		float: left;
		margin-left: 10px;
	}

	#infoPanel div {
		margin-bottom: 5px;
	}
</style>
<jsp:include page="/views/elements/header.jsp"/>
<div class="container">
	<div id="mapCanvas"></div>
	<form method=post action="portal?command=saveCustomPlace">
		<%--<c:set var="command" scope="session" value="saveCustomPlace"/>--%>
		<input type="hidden" name="command" value="saveCustomPlace">

		<div id="infoPanel">
			<b>Marker status:</b>
			<div id="markerStatus"><i>Click and drag the marker.</i></div>
			<b>Current position:</b>
			<div id="info" ></div>
			<div id="info1"></div>

			<%--<div class="row">--%>
				<%--<label class="active" for="placeLatitude">latitude</label>--%>
				<%--<input value="${document}" id="placeLatitude" type="text" name="placeLatitude">--%>
			<%--</div>--%>

			<%--<div class="row">--%>
				<%--<label class="active" for="placeLongitude">longitude</label>--%>
				<%--<input value="" id="placeLongitude" type="text" name="placeLongitude">--%>
			<%--</div>--%>

			<b>Closest matching address:</b>
			<div id="address"></div>
			<button class="btn waves-effect waves-light" type="submit" name="save"><cdg:l18n
					key="usercab.savechange"/><i class="mdi-content-send right"></i></button>
			<button class="btn waves-effect waves-light" type="reset" name="cancel"><cdg:l18n
					key="usercab.cancel"/></button>
		</div>
	</form>
</div>
<%--<jsp:include page="/views/elements/footer.jsp"/>--%>
</body>
</html>