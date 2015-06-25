<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style type="text/css">
    .labels img {
        width: 50px;
        height: 50px;
        border: 2px solid black;
        border-radius: 50%;
        /*animation: pulse 1s ease 1s 1;*/
    }

    /* ANIMATIONS */
    @-webkit-keyframes pulse {
        40% {
            width: 40px;
            height: 40px;
            /*-webkit-transform: scale(2);*/
            /*-moz-transform: scale(2);*/
            /*transform: scale(2);*/
        }

        100% {
            width: 80px;
            height: 80px;
            /*-webkit-transform: scale(1);*/
            /*-moz-transform: scale(1);*/
            /*transform: scale(1);*/
        }
    }

    @-moz-keyframes pulse {
        40% {
            width: 40px;
            height: 40px;
            /*-webkit-transform: scale(2);*/
            /*-moz-transform: scale(2);*/
            /*transform: scale(2);*/
        }

        100% {
            width: 80px;
            height: 80px;
            /*-webkit-transform: scale(1);*/
            /*-moz-transform: scale(1);*/
            /*transform: scale(1);*/
        }
    }

    @keyframes pulse {
        40% {
            width: 40px;
            height: 40px;
            /*-webkit-transform: scale(2);*/
            /*-moz-transform: scale(2);*/
            /*transform: scale(2);*/
        }

        100% {
            width: 80px;
            height: 80px;
            /*-webkit-transform: scale(1);*/
            /*-moz-transform: scale(1);*/
            /*transform: scale(1);*/
        }
    }

</style>
<script src="http://google-maps-utility-library-v3.googlecode.com/svn/tags/markerwithlabel/1.1.9/src/markerwithlabel.js"
        type="text/javascript"></script>
<script>

    var markers = [];

    function myclick(image) {
        <c:forEach var="i" begin="0" end="${fn:length(places)-1}">
        if("${places[i].imageReference}" == image){
            google.maps.event.trigger(markers[${i}], "click");
        }
        </c:forEach>
    }
    mapOptions = {
        zoom: 14,
        center: new google.maps.LatLng(49.8426, 24.0278)
    };
    function initialize() {
        var map = new google.maps.Map(document.getElementById('map-canvas'),
                mapOptions);
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
            labelClass: "labels", // the CSS class for the label
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
        var markerClusterer = new MarkerClusterer(map, markers,
                {
                    maxZoom: 16,
                    gridSize: 50,
                    styles: null
                });
    }
    google.maps.event.addDomListener(window, 'load', initialize);

</script>


<div id="map-canvas"></div>