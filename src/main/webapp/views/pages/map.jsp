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

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div class="map">
    <div class="row">
        <div class="col l3 m6 s2">
            <div class="sidebar-icon blue-grey darken-2">

            </div>
        </div>
        <div class="col l9 m6 s10">
            Map
        </div>
    </div>
</div>

<jsp:include page="/views/elements/script.jsp"/>

</body>
</html>
