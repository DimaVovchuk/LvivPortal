<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Simple icons</title>
    <style>
        html, body, #map-canvas {
            height: 100%;
            margin: 0px;
            padding: 0px
        }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>
    <script>
        // This example adds a marker to indicate the position
        // of Bondi Beach in Sydney, Australiav

        function initialize() {
            var map = new google.maps.Map(document.getElementById('map-canvas'),
                    mapOptions);
            <c:forEach items="${places}" var="place">
            var image = "${pageContext.request.contextPath}/images/localization/UA.png";
            var myLatLng = new google.maps.LatLng(${place.latitude}, ${place.longitude});
            var beachMarker = new google.maps.Marker({
                position: myLatLng,
                map: map,
                icon: image
            });
            </c:forEach>
        }

        var mapOptions = {
            zoom: 4,
            center: new google.maps.LatLng(49.8426, 24.0278)
        }


        google.maps.event.addDomListener(window, 'load', initialize);

    </script>
</head>
<body>
<div id="map-canvas"></div>
</body>
</html>