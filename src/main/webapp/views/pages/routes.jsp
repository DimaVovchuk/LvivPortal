<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Draggable directions</title>
    <style>
        html, body, #map-canvas {
            height: 100%;
            margin: 0px;
            padding: 0px
        }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>
    <script>

        var rendererOptions = {
            draggable: true
        };
        var directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
        var directionsService = new google.maps.DirectionsService();
        var map;

        var lviv = new google.maps.LatLng(49.8426, 24.0278);

        function initialize() {

            var mapOptions = {
                zoom: 7,
                center: lviv
            };
            map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
            directionsDisplay.setMap(map);
            directionsDisplay.setPanel(document.getElementById('directionsPanel'));

            google.maps.event.addListener(directionsDisplay, 'directions_changed', function () {
                computeTotalDistance(directionsDisplay.getDirections());
            });

            calcRoute();
        }

        function calcRoute() {
            var request = {
                origin: 'Lviv, Ukraine',
                destination: 'Lviv, Ukraine',
                waypoints: [
                    <c:forEach items="${wayPlaces}" var="place">
                    {location: new google.maps.LatLng(${place.latitude}, ${place.longitude})},
                    </c:forEach>
                ],
                travelMode: google.maps.TravelMode.WALKING
            };
            directionsService.route(request, function (response, status) {
                if (status == google.maps.DirectionsStatus.OK) {
                    directionsDisplay.setDirections(response);
                }
            });
        }

        //        function computeTotalDistance(result) {
        //            var total = 0;
        //            var myroute = result.routes[0];
        //            for (var i = 0; i < myroute.legs.length; i++) {
        //                total += myroute.legs[i].distance.value;
        //            }
        //            total = total / 1000.0;
        //            document.getElementById('total').innerHTML = total + ' km';
        //        }

        google.maps.event.addDomListener(window, 'load', initialize);

    </script>
</head>
<body>
<div id="map-canvas" style="float:left;width:70%; height:100%"></div>
<%--<div id="directionsPanel" style="float:right;width:30%;height 100%">--%>
<%--<p>Total Distance: <span id="total"></span></p>--%>
<%--</div>--%>
</body>
</html>