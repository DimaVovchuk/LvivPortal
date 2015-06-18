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
    <div class="row">
        <div class="col l4 m6 s6">

            <nav class="side-icon blue-grey darken-2">
                <ul class="center-align">
                    <li><a href="#"><i class="small mdi-notification-event-note white-text"></i>
                        <span class="nav-text white-text">Itinerary</span>
                    </a>
                    </li>
                    <li><a href="#"><i class="small mdi-action-account-balance white-text"></i>
                        <span class="nav-text white-text">Places</span>
                    </a>
                    </li>
                </ul>
            </nav>

            <div class="side-info z-depth-2">
                Information
            </div>
        </div>

        <div class="col l8 m6 s6">
            <jsp:include page="geotag.jsp"/>
        </div>
    </div>
</div>

</body>
</html>
