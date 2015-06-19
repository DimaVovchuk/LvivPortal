<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>

<div id="map-info">
    <div id="map-places">
        <a class='dropdown-select btn cyan darken-2' href='#' data-activates='dropdown-places'><cdg:l18n
                key="places.categories"/></a>
        <ul id='dropdown-places' class='dropdown-content'>
            <li><a href="portal?command=place&category=architecture" class="collection-item"><cdg:l18n
                    key="places.architecture"/></a></li>
            <div class="divider"></div>
            <li><a href="portal?command=place&category=churches" class="collection-item"><cdg:l18n
                    key="places.churches"/></a></li>
            <div class="divider"></div>
            <li><a href="portal?command=place&category=theatres" class="collection-item"><cdg:l18n
                    key="places.theatres"/></a></li>
            <div class="divider"></div>
            <li><a href="portal?command=place&category=hotels" class="collection-item"><cdg:l18n
                    key="places.hotels"/></a></li>
            <div class="divider"></div>
            <li><a href="portal?command=place&category=restaurants" class="collection-item"><cdg:l18n
                    key="places.restaurants"/></a></li>
            <div class="divider"></div>
            <li><a href="portal?command=place" class="collection-item"><cdg:l18n key="places.all"/></a></li>
        </ul>

        <div class="collection ">
            <a href="#" class="collection-item black-text"><img class="circle responsive-img" src="${pageContext.request.contextPath}/upload/photo/leopolis.jpg">Hotel "Leopolis"</a>
            <a href="#" class="collection-item black-text"><img class="circle responsive-img" src="${pageContext.request.contextPath}/upload/photo/leopolis.jpg">Hotel "Leopolis"</a>
            <a href="#" class="collection-item black-text"><img class="circle responsive-img" src="${pageContext.request.contextPath}/upload/photo/leopolis.jpg">Hotel "Leopolis"</a>
        </div>

    </div>
</div>
