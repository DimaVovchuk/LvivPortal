<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="map-info">
    <div id="map-itinerary" class="animated fadeInDown">
        <div id="route-info-collection" class="collection"></div>
    </div>

    <div id="map-places" class="animated fadeInDown" style="display: none">
        <a class='dropdown-select btn cyan darken-2 waves-effect waves-light' href='#' data-activates='dropdown-places'><cdg:l18n
                key="places.categories"/></a>
        <ul id='dropdown-places' class='dropdown-content'>
            <li><a href="portal?command=placeJSON&category=sights" class="collection-item"
                   data-category="architectture"><cdg:l18n
                    key="places.architecture"/></a></li>
            <div class="divider"></div>
            <li><a href="portal?command=placeJSON&category=churches" class="collection-item"
                   data-category="churches"><cdg:l18n
                    key="places.churches"/></a></li>
            <div class="divider"></div>
            <li><a href="portal?command=placeJSON&category=theatres" class="collection-item"
                   data-category="theatres"><cdg:l18n
                    key="places.theatres"/></a></li>
            <div class="divider"></div>
            <li><a href="portal?command=placeJSON&category=hotels" class="collection-item"
                   data-category="hotels"><cdg:l18n
                    key="places.hotels"/></a></li>
            <div class="divider"></div>
            <li><a href="portal?command=placeJSON&category=restaurants" class="collection-item"
                   data-category="restaurants"><cdg:l18n
                    key="places.restaurants"/></a></li>
            <div class="divider"></div>
            <li><a href="portal?command=placeJSON" class="collection-item"><cdg:l18n key="places.all"/></a></li>
        </ul>

        <div id="place-info-collection" class="collection"></div>

        <script id="place-info-template" type="text/x-handlebars-template">
            {{#each this}}
            <a href="#" class="collection-item black-text">
                <img class="circle responsive-img"
                     src="${pageContext.request.contextPath}/upload/photo/{{imageReference}}">

                <div class="valign-wrapper" style="height:100%">
                    <div class="valign">
                        <div class="truncate"><b>{{name}}</b><br>{{adress}}</div>
                    </div>
                </div>
            </a>
            {{/each}}
        </script>

        <script id="route-info-template" type="text/x-handlebars-template">
            {{#each this}}
            <c:set var="dayNumber" value="{{dayNumber}}"/>
            <a href="#" data-day="${dayNumber}" class="day-trigger collection-item black-text blue-grey lighten-4 waves-effect waves-light">
                <cdg:l18n key="map.route.day"/> {{dayNumber}}<br>
                <cdg:l18n key="map.route.totaltime"/>: {{hours}} <cdg:l18n key="map.route.hours"/> {{minutes}} <cdg:l18n key="map.route.minutes"/>
            </a>
            <div id="places-day{{dayNumber}}" class="collection animated fadeInLeft">
                {{#each places}}
                <a href="#" class="collection-item black-text">
                    <img class="circle responsive-img" src="${pageContext.request.contextPath}/upload/photo/{{imageReference}}">
                    <div class="valign-wrapper" style="height:100%">
                        <div class="valign">
                            <div class="truncate"><b>{{name}}</b><br>{{adress}}</div>
                        </div>
                    </div>
                </a>
                {{/each}}
            </div>
            {{/each}}
        </script>

        <script src="${pageContext.request.contextPath}/js/pages/map-info.js"></script>
    </div>
</div>
