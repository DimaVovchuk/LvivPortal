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
            position: relative;
            top: 5px;
            left: 50%;
            margin-left: -400px;
            z-index: 5;
            background-color: #fff;
            padding: 5px;
            border: 1px solid #999;
        }

        #directions-panel {
            height: 100%;
            float: right;
            width: 390px;
            overflow: auto;
        }

        #map-canvas {
            margin-right: 400px;
        }

        #control {
            background: #fff;
            padding: 5px;
            font-size: 14px;
            font-family: Arial;
            border: 1px solid #ccc;
            box-shadow: 0 2px 2px rgba(33, 33, 33, 0.4);
            display: none;
        }

        @media print {
            #map-canvas {
                height: 500px;
                margin: 0;
            }

            #directions-panel {
                float: none;
                width: auto;
            }
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
            var mapOptions = {
                zoom: 7,
                center: haight
            };
            var map = new google.maps.Map(document.getElementById('map-canvas'),
                    mapOptions);
            directionsDisplay.setMap(map);
            directionsDisplay.setPanel(document.getElementById('directions-panel'));

            var control = document.getElementById('control');
            control.style.display = 'block';
            map.controls[google.maps.ControlPosition.TOP_CENTER].push(control);


        }

        function calcRoute() {
            var start = document.getElementById('start').value;
            var end = document.getElementById('end').value;
            var selectedMode = document.getElementById('mode').value;
            var request = {
                origin: start,
                destination: end,
                travelMode: google.maps.TravelMode[selectedMode]
            };
            directionsService.route(request, function (response, status) {
                if (status == google.maps.DirectionsStatus.OK) {
                    directionsDisplay.setDirections(response);
                }
            });
        }
        function codeAddress() {
            var address = document.getElementById('address').value;
            geocoder.geocode({'address': address}, function (results, status) {
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
        function computeTotalDistance(result) {
            var total = 0;
            var myroute = result.routes[0];
            for (var i = 0; i < myroute.legs.length; i++) {
                total += myroute.legs[i].distance.value;
            }
            total = total / 1000.0;
            document.getElementById('total').innerHTML = total + ' km';
        }

        google.maps.event.addDomListener(window, 'load', initialize);
    </script>


</head>
<body>
<div id="panel">
    <b>Mode of Travel: </b>
    <input id="address" type="textbox" value="Lviv">
    <input type="button" value="Geocode" onclick="codeAddress()">
    <br>
    <input id="start" type="textbox" value="Lviv Svobody">
    <input id="end" type="textbox" value="Lviv Saharova 23">

    <select id="mode" onchange="">
        <option value="DRIVING">Driving</option>
        <option value="WALKING">Walking</option>
        <option value="BICYCLING">Bicycling</option>
        <option value="TRANSIT">Transit</option>
    </select>
    <input type="button" value="Way" onclick="calcRoute();">
</div>
<div id="directions-panel"></div>
<div id="map-canvas"></div>
</body>
</html>