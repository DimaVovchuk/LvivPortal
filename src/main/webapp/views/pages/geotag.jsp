<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="${pageContext.request.contextPath}/js/markerwithlabel.js"></script>
<script>
    var markers = [];
    var markerClusterer;

    function myclick(id) {
        <c:forEach var="i" begin="0" end="${fn:length(places)-1}">
        if ("${places[i].placeId}" == id) {
            google.maps.event.trigger(markers[${i}], "click");
        }
        </c:forEach>
    }

    function hideMarkers() {
        markerClusterer.clearMarkers();
    }

    function showMarkers() {
        markerClusterer.addMarkers(markers);
    }

    function showRoutesMarkers(dayNumber) {
        var routesMarkers = [];
        for (var i = 0; i < routesData[dayNumber].places.length; i++) {
            var pos = new google.maps.LatLng(routesData[dayNumber].places[i].latitude, routesData[dayNumber].places[i].longitude);
            for (var j = 0; j < markers.length; j++) {
                if (pos.equals(markers[j].position)) {
                    routesMarkers.push(markers[j]);
                }
            }
            <%--var content = '<div class="map-content card">' +--%>
            <%--'<div class="card-image waves-effect waves-light">' +--%>
            <%--'   <img class="responsive-img activator" src="${pageContext.request.contextPath}/upload/photo/' + routesData[dayNumber].places[i].imageReference + '">' +--%>
            <%--'</div>' +--%>
            <%--'<div class="card-content">' +--%>
            <%--'   <span class="card-title activator grey-text text-darken-4">' + routesData[dayNumber].places[i].name + '<i class="mdi-navigation-more-vert right"></i></span>' +--%>
            <%--'</div>' +--%>
            <%--'<div class="card-reveal">' +--%>
            <%--'   <span class="card-title grey-text text-darken-4">' + routesData[dayNumber].places[i].name + '<i class="mdi-navigation-close right"></i></span>' +--%>
            <%--'   <p>' + routesData[dayNumber].places[i].description + '</p>' +--%>
            <%--'</div>' +--%>
            <%--'<div class="card-action">' +--%>
            <%--'   <a href="portal?command=placeInformation&place_id=' + routesData[dayNumber].places[i].placeId + '"><cdg:l18n key="map.readmore"/></a>' +--%>
            <%--'</div>' +--%>
            <%--'</div>' +--%>
            <%--'</div>';--%>

            <%--var inf = new google.maps.InfoWindow({--%>
            <%--content: content--%>
            <%--});--%>

            <%--var img = {--%>
            <%--url: "",--%>
            <%--scaledSize: new google.maps.Size(0, 0),--%>
            <%--origin: new google.maps.Point(0, 0),--%>
            <%--anchor: new google.maps.Point(0, 0)--%>
            <%--};--%>
            <%--var myLatLng = new google.maps.LatLng(routesData[dayNumber].places[i].latitude, routesData[dayNumber].places[i].longitude);--%>
            <%--var pictureLabel = document.createElement("img");--%>
            <%--pictureLabel.src = "${pageContext.request.contextPath}/upload/photo/" + routesData[dayNumber].places[i].imageReference + "";--%>
            <%--var marker = new MarkerWithLabel({--%>
            <%--icon: img,--%>
            <%--position: myLatLng,--%>
            <%--info: content,--%>
            <%--labelContent: pictureLabel,--%>
            <%--labelAnchor: new google.maps.Point(22, 0),--%>
            <%--labelClass: "labels",--%>
            <%--labelVisible: true--%>
            <%--});--%>

            <%--google.maps.event.addListener(marker, 'click', function () {--%>
            <%--inf.setContent(this.info);--%>
            <%--inf.open(map, this);--%>
            <%--map.setZoom(17);--%>
            <%--map.setCenter(this.position);--%>

            <%--});--%>
//            routesMarkers.push(marker);
        }
        markerClusterer.addMarkers(routesMarkers);
    }

    function initStartMarkers() {
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
            url: "",
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

        google.maps.event.addListener(marker, 'click', function () {
            infowindow.setContent(this.info);
            infowindow.open(map, this);
            map.setZoom(17);
            map.setCenter(this.position);
        });

        markers.push(marker);
        </c:forEach>

        markerClusterer = new MarkerClusterer(map, markers,
                {
                    ignoreHidden: true,
                    maxZoom: 16,
                    gridSize: 50,
                    styles: null
                });
    }
</script>