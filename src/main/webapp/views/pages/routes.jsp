<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

        var directionsDisplay;
        var directionsService = new google.maps.DirectionsService();
        var map;

        var lviv = new google.maps.LatLng(49.8426, 24.0278);

        function initialize() {
            directionsDisplay = new google.maps.DirectionsRenderer();
            var mapOptions = {
                zoom: 14,
                center: lviv
            };
            map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
            directionsDisplay.setMap(map);
            calcRoute();
        }

        function calcRoute() {
            var start = new google.maps.LatLng(${wayPlaces[0].latitude}, ${wayPlaces[0].longitude});
            var end =  new google.maps.LatLng(${wayPlaces[fn:length(wayPlaces)-1].latitude}, ${wayPlaces[fn:length(wayPlaces)-1].longitude});
            var waypts = [
                <c:forEach var="i" begin="1" end="${fn:length(wayPlaces)-2}">
                <%--<c:forEach items="${wayPlaces}" var="place">--%>
                {location: new google.maps.LatLng(${wayPlaces[i].latitude}, ${wayPlaces[i].longitude})},
                </c:forEach>
            ];

            var request = {
                origin: start,
                destination: end,
                waypoints: waypts,
                optimizeWaypoints: true,
                travelMode: google.maps.TravelMode.WALKING
            };
            directionsService.route(request, function(response, status) {
                if (status == google.maps.DirectionsStatus.OK) {
                    directionsDisplay.setDirections(response);
                }
            });
        }

            google.maps.event.addDomListener(window, 'load', initialize);

    </script>
</head>
<body>
<div id="map-canvas"></div>
</body>
</html>