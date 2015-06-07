<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Travel modes in directions</title>
    <%--<%@ taglib prefix="c"--%>
    <%--uri="http://java.sun.com/jsp/jstl/core" %>--%>
    <style>
        html, body, #map-canvas {
            height: 100%;
            margin: 0px;
            padding: 0px
        }

        #panel {
            position: absolute;
            top: 5px;
            left: 50%;
            margin-left: -180px;
            z-index: 5;
            background-color: #fff;
            padding: 5px;
            border: 1px solid #999;
        }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>

    <script>
        var directionsDisplay;
        var directionsService = new google.maps.DirectionsService();
        var map;
        var haight = new google.maps.LatLng(${from_x}, ${from_y});
        var oceanBeach = new google.maps.LatLng(${to_x}, ${to_y});

        function initialize() {
            directionsDisplay = new google.maps.DirectionsRenderer();
            geocoder = new google.maps.Geocoder();
            var mapOptions = {
                zoom: 14,
                center: haight
            }
            map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
            directionsDisplay.setMap(map);


         //   var latlng = new google.maps.LatLng(-34.397, 150.644);
//            var mapOptions = {
//                zoom: 8,
//                center: latlng
//            }
//            map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
        }

        function calcRoute() {
            var selectedMode = document.getElementById('mode').value;
            var request = {
                origin: haight,
                destination: oceanBeach,
                travelMode: google.maps.TravelMode[selectedMode]
            };
            directionsService.route(request, function (response, status) {
                if (status == google.maps.DirectionsStatus.OK) {
                    directionsDisplay.setDirections(response);
                }
            });
        }
        google.maps.event.addDomListener(window, 'load', initialize);

        function codeAddress() {
            var address = document.getElementById('address').value;
            geocoder.geocode( { 'address': address}, function(results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    map.setCenter(results[0].geometry.location);
                    var marker = new google.maps.Marker({
                        map: map,
                        position: results[0].geometry.location
                    });
                } else {
                    alert('Geocode was not successful for the following reason: ' + status);
                }
            });
        }

    </script>


</head>
<body>
<div id="panel">
    <b>Mode of Travel: </b>
    <input id="address" type="textbox" value="Lviv">
    <input type="button" value="Geocode" onclick="codeAddress()">

    <select id="mode" onchange="calcRoute();">
        <option value="DRIVING">Driving</option>
        <option value="WALKING">Walking</option>
        <option value="BICYCLING">Bicycling</option>
        <option value="TRANSIT">Transit</option>
    </select>
</div>
<div id="map-canvas"></div>
</body>
</html>