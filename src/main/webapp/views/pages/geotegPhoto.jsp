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
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&language=ua"></script>
    <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
    <script>


        function initialize() {
            var map = new google.maps.Map(document.getElementById('map-canvas'),
                    mapOptions);
            <c:forEach items="${places}" var="place">

            var contentString = '<div id="content">' +
                    '<div id="siteNotice">' +
                    '</div>' +
                    '<h1 id="firstHeading" class="firstHeading"><c:out value="${place.name}"/></h1>' +
                    '<div style{backround }>"${pageContext.request.contextPath}/upload/photo/${place.imageReference}"</div>'+
                    '<div id="bodyContent">' +
                    ' <c:out value="${place.description}"/>'+
                    '<p>Attribution: <a href="portal?command=placeInformation&place_id=${place.placeId}">link</a></p>' +
                    '</div>' +
                    '</div>';

            var infowindow = new google.maps.InfoWindow({
                content: contentString
            });

            var image = {
                url: "${pageContext.request.contextPath}/upload/photo/${place.imageReference}",
                scaledSize: new google.maps.Size(50, 40), // scaled size
                origin: new google.maps.Point(0, 0), // origin
                anchor: new google.maps.Point(0, 0) // anchor
            }
            var myLatLng = new google.maps.LatLng(${place.latitude}, ${place.longitude});

            var marker = new google.maps.Marker({
                position: myLatLng,
                map: map,
                info: contentString,
                icon: image
            });

            google.maps.event.addListener(marker, 'click', function () {
                infowindow.setContent(this.info);
                infowindow.open(map, this);
            });
            </c:forEach>
        }

        var mapOptions = {
            zoom: 15,
            center: new google.maps.LatLng(49.8426, 24.0278)
        }


        google.maps.event.addDomListener(window, 'load', initialize);

    </script>
</head>
<body>
<div id="map-canvas"></div>
</body>
</html>