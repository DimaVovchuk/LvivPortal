<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdg" uri="customtags" %>

<script>
    function initialize() {
        var map = new google.maps.Map(document.getElementById('map-canvas'),
                mapOptions);
        var markers = [];
        <c:forEach items="${places}" var="place">
        var contentString = '<div class="map-content card">' +
                '<h5 class="cyan-text darken-4"><c:out value="${place.name}"/></h5>' +
                '<div class="body-content">' +
                '   <div class="card-image waves-effect waves-block waves-light">' +
                '       <img class="activator responsive-img" src="${pageContext.request.contextPath}/upload/photo/${place.imageReference}">' +
                '   </div>' +
                '   <div class="card-content">' +
                '       <span class="card-title activator grey-text text-darken-4">Description <i class="mdi-navigation-more-vert right"></i></span>' +
                '   </div>' +
                '   <div class="card-reveal">' +
                '       <span class="card-title grey-text text-darken-4">Description <i class="mdi-navigation-close right"></i></span>' +
                '       <p><c:out value="${place.description}"/></p>' +

                '   </div>' +
                '   <p><a href="portal?command=placeInformation&place_id=${place.placeId}"><cdg:l18n key="map.readmore"/></a></p>' +
                '   </div>' +
                '</div>' +
                '</div>';

        var infowindow = new google.maps.InfoWindow({
            content: contentString
        });


        var image = {
            url: "${pageContext.request.contextPath}/upload/photo/${place.imageReference}",
            scaledSize: new google.maps.Size(60, 40),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        };
        var myLatLng = new google.maps.LatLng(${place.latitude}, ${place.longitude});
        var marker = new google.maps.Marker({
            position: myLatLng,
            info: contentString,
            animation: google.maps.Animation.DROP,
            icon: image,
        });

        markers.push(marker);
        google.maps.event.addListener(marker, 'click', function () {
            infowindow.setContent(this.info);
            infowindow.open(map, this);
        });
        </c:forEach>
        var markerClusterer = new MarkerClusterer(map, markers,
                {
                    maxZoom: 16,
                    gridSize: 50,
                    styles: null
                });
    }
    var mapOptions;
    mapOptions = {
        zoom: 15,
        center: new google.maps.LatLng(49.8426, 24.0278)
    };
    google.maps.event.addDomListener(window, 'load', initialize);
</script>

<div id="map-canvas"></div>