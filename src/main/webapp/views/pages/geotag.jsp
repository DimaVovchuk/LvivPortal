<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdg" uri="customtags" %>

<script src="${pageContext.request.contextPath}/js/markerwithlabel.js"></script>
<script>
    function initStartMarkers() {
        var markers = [];
        <c:forEach items="${places}" var="place">
        var contentString = '<div class="map-content card">' +
                '<div class="card-image waves-effect waves-light">' +
                '   <img class="responsive-img activator" src="${pageContext.request.contextPath}/upload/photo/${place.imageReference}">' +
                '</div>' +
                '<div class="card-content">' +
                '   <span class="card-title activator grey-text text-darken-4"><c:out value="${place.name}"/><i class="mdi-navigation-more-vert right"></i></span>' +
                '</div>' +
                '<div class="card-reveal">' +
                '   <span class="card-title grey-text text-darken-4"><c:out value="${place.name}"/><i class="mdi-navigation-close right"></i></span>' +
                '   <p><c:out value="${place.description}"/></p>' +
                '</div>' +
                '<div class="card-action">' +
                '   <a href="portal?command=placeInformation&place_id=${place.placeId}"><cdg:l18n key="map.readmore"/></a>' +
                '</div>' +
                '</div>' +
                '</div>';
            var infowindow = new google.maps.InfoWindow({
            content: contentString
        });
        var image = {
            url: "${pageContext.request.contextPath}/upload/photo/${place.imageReference}",
            scaledSize: new google.maps.Size(0, 0),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        };
        var myLatLng = new google.maps.LatLng(${place.latitude}, ${place.longitude});
        var pictureLabel = document.createElement("img");
        pictureLabel.src = "${pageContext.request.contextPath}/upload/photo/${place.imageReference}";
        var marker = new MarkerWithLabel({
            icon: image,
            position: myLatLng,
            info: contentString,
            labelContent: pictureLabel,
            labelAnchor: new google.maps.Point(22, 0),
            labelClass: "labels",
            labelVisible: true
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
</script>