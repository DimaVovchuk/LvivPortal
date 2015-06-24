<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Routes</title>
    <style>
        html, body, #map-canvas {
            height: 100%;
            margin: 0px;
            padding: 0px
        }

        /*.labels {*/
            /*color: red;*/
            /*background-color: white;*/
            /*font-family: "Lucida Grande", "Arial", sans-serif;*/
            /*font-size: 10px;*/
            /*font-weight: bold;*/
            /*text-align: center;*/
            /*width: 60px;*/
            /*border: 2px solid black;*/
            /*white-space: nowrap;*/
        /*}*/
    </style>
    <%--<script src="http://google-maps-utility-library-v3.googlecode.com/svn/tags/markerwithlabel/1.1.9/src/markerwithlabel.js" type="text/javascript"></script>--%>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyCL9BI_9U8ba_Zf_ldHd9KrYFtBtK7cTzI"
            type="text/javascript">
    </script>
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
            directionsDisplay.setOptions({suppressMarkers: true});

            calcRoute();

        }


        function calcRoute() {
            <c:choose>
            <c:when test="${wayPlaces.size() > 1}">
            var start = new google.maps.LatLng(${wayPlaces[0].latitude}, ${wayPlaces[0].longitude});
            var image = {
                url: "${pageContext.request.contextPath}/upload/photo/${wayPlaces[0].imageReference}",
                scaledSize: new google.maps.Size(40, 30),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(0, 0)
            };

            <%--new MarkerWithLabel({--%>
                <%--position: new google.maps.LatLng(${wayPlaces[0].latitude}, ${wayPlaces[0].longitude}),--%>
                <%--map: map,--%>
                <%--labelContent: "$425K",--%>
                <%--labelAnchor: new google.maps.Point(22, 0),--%>
                <%--labelClass: "labels", // the CSS class for the label--%>
                <%--labelStyle: {opacity: 0.75}--%>
            <%--});--%>

            new google.maps.Marker({
                position: new google.maps.LatLng(${wayPlaces[0].latitude}, ${wayPlaces[0].longitude}),
                icon: image,
                map: map
            });
            var end = new google.maps.LatLng(${wayPlaces[fn:length(wayPlaces)-1].latitude}, ${wayPlaces[fn:length(wayPlaces)-1].longitude});
            var image = {
                url: "${pageContext.request.contextPath}/upload/photo/${wayPlaces[fn:length(wayPlaces)-1].imageReference}",
                scaledSize: new google.maps.Size(40, 30),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(0, 0)
            };
            new google.maps.Marker({
                position: new google.maps.LatLng(${wayPlaces[fn:length(wayPlaces)-1].latitude}, ${wayPlaces[fn:length(wayPlaces)-1].longitude}),
                icon: image,
                map: map
            });
            var waypts = [];
            <c:forEach var="i" begin="1" end="${fn:length(wayPlaces)-2}">
            waypts.push({location: new google.maps.LatLng(${wayPlaces[i].latitude}, ${wayPlaces[i].longitude})});
            var image = {
                url: "${pageContext.request.contextPath}/upload/photo/${wayPlaces[i].imageReference}",
                scaledSize: new google.maps.Size(40, 30),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(0, 0)
            };
            new google.maps.Marker({
                position: new google.maps.LatLng(${wayPlaces[i].latitude}, ${wayPlaces[i].longitude}),
                icon: image,
                map: map

            });
            </c:forEach>


            var request = {
                origin: start,
                destination: end,
                waypoints: waypts,
                optimizeWaypoints: true,
                travelMode: google.maps.TravelMode.WALKING

            };
            </c:when>
            </c:choose>
            directionsService.route(request, function (response, status) {
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