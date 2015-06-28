<%@ taglib prefix="cdg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><cdg:l18n key="index.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
</head>

<jsp:include page="/views/elements/css.jsp"/>
<jsp:include page="/views/elements/script.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div class="map">
    <div class="z-depth-2" id="map-side">
        <nav class="side-icon blue-grey darken-2 z-depth-5">
            <ul class="center-align">
                <li id="li-itinerary" class="link-process active" data-type="itinerary"><a href="#"><i class="small mdi-notification-event-note white-text"></i>
                    <span class="nav-text white-text">Itinerary</span>
                </a>
                </li>
                <li id="li-places" class="link-process" data-type="places"><a href="#"><i class="small mdi-action-account-balance white-text"></i>
                    <span class="nav-text white-text">Places</span>
                </a>
                </li>
            </ul>
        </nav>

        <div class="side-info">
            <jsp:include page="map-info.jsp"/>
        </div>
    </div>

    <div id="map-canvas"></div>
</div>

<jsp:include page="geotag.jsp"/>
<script src="${pageContext.request.contextPath}/js/pages/map.js"></script>

</body>
</html>