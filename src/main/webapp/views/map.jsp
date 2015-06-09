<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Waypoints in directions</title>
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
        var rendererOptions = {
            draggable: true
        };
        var directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);;
        var directionsService = new google.maps.DirectionsService();
        var map;

        function initialize() {
            directionsDisplay = new google.maps.DirectionsRenderer();
            var myCenter=new google.maps.LatLng(49.8426,24.0278);
            var mapOptions = {
                zoom: 6,
                center: myCenter
            }
            map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
            directionsDisplay.setMap(map);
            directionsDisplay.setPanel(document.getElementById('directionsPanel'));

            google.maps.event.addListener(directionsDisplay, 'directions_changed', function() {
                computeTotalDistance(directionsDisplay.getDirections());
            });

            calcRoute();
        }

        function calcRoute() {
            var start = document.getElementById('start').value;
            var end = document.getElementById('end').value;
            var mode = document.getElementById('mode').value;
            var waypts = [];
            var checkboxArray = document.getElementById('waypoints');
            for (var i = 0; i < checkboxArray.length; i++) {
                if (checkboxArray.options[i].selected == true) {
                    waypts.push({
                        location:checkboxArray[i].value,
                        stopover:true});
                }
            }

            var request = {
                origin: start,
                destination: end,
                waypoints: waypts,
                optimizeWaypoints: true,
                travelMode: google.maps.TravelMode[mode]
            };
            directionsService.route(request, function(response, status) {
                if (status == google.maps.DirectionsStatus.OK) {
                    directionsDisplay.setDirections(response);
                    var route = response.routes[0];
                    var summaryPanel = document.getElementById('directions_panel');
                    summaryPanel.innerHTML = '';
                    // For each route, display summary information.
                    for (var i = 0; i < route.legs.length; i++) {
                        var routeSegment = i + 1;
                        summaryPanel.innerHTML += '<b>Route Segment: ' + routeSegment + '</b><br>';
                        summaryPanel.innerHTML += route.legs[i].start_address + ' to ';
                        summaryPanel.innerHTML += route.legs[i].end_address + '<br>';
                        summaryPanel.innerHTML += route.legs[i].distance.text + '<br><br>';
                    }
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
<div id="map-canvas" style="float:left;width:70%;height:100%;"></div>
<div id="control_panel" style="float:right;width:30%;text-align:left;padding-top:20px">
    <div style="margin:20px;border-width:2px;">
        <b>Start:</b>
        <select id="start"> <br>
            <option value="Lviv dvirceva 1">Rainway Station</option>
            <option value="Lviv stryjska 109">Bus station</option>
        </select>
        <br>
        <b>Waypoints:</b> <br>
        <i>(Ctrl-Click for multiple selection)</i> <br>
        <select multiple id="waypoints">
            <option value="Lviv Rynok Square 1">Rynok Square</option>
            <option value="Lviv Kopernyka 15">The Palace of Counts Potockis</option>
            <option value="Lviv Cathedral Square 1">The Chapel of the Boim family</option>
            <option value="Lviv Mechnikova 33">Lychakiv Cemetery</option>
        </select>
        <br>
        <b>End:</b>
        <select id="end"> <br>
            <option value="Lviv stryjska 109">Bus station</option>
            <option value="Lviv dvirceva 1">Rainway Station</option>
        </select>
        <br>

        <select id="mode">
            <option value="DRIVING">Driving</option>
            <option value="WALKING">Walking</option>
            <option value="BICYCLING">Bicycling</option>
            <option value="TRANSIT">Transit</option>
        </select>
        <input type="button" value="Way" onclick="calcRoute();">
    </div>
    <div id="directions_panel" style="margin:20px;background-color:#FFEE77;"></div>

    <div id="directionsPanel">
        <p>Total Distance: <span id="total"></span></p>
    </div>
</div>
</body>
</html>
<!--<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Travel modes in directions</title>
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
</html>-->