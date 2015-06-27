<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Company Page</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
</head>

<jsp:include page="/views/elements/css.jsp"/>
<jsp:include page="/views/elements/script.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div id="company-page">
    <div class="row">
        <div class="col l8 offset-l2 m10 offset-m1 s12 card">
            <h4>Company name</h4>

            <div class="divider"></div>
            <div class="section row">
                <div class="col s3">
                    <div class="center-align">
                        <c:choose>
                            <c:when test="${empty avatar}">
                                <img src="${pageContext.request.contextPath}/upload/photo/user.png" width=70%
                                     class="circle responsive-img">
                            </c:when>
                            <c:otherwise>
                                <img src="${pageContext.request.contextPath}/upload/photo/${avatar.reference}" width=70%
                                     class="circle responsive-img">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="col s9">
                    <h3>Information</h3>
                    name: ${userInfo.name}<br>
                    surname:${userInfo.surname}<br>
                    Company name:${userInfo.companyName}<br>
                    mail:${userInfo.mail}<br>
                    rating:${userInfo.rating}<br>
                    phone:${userInfo.phone}<br>
                    about:${userInfo.about}<br>
                    <br>
                    <br>
                    <br>
                    <c:forEach var="place" items="${placesInfo}">
                        place id:${place.id}<br>
                        place name:${place.name}<br>
                        place description:${place.imageReference}<br>

                    </c:forEach>
                </div>
            </div>
            <div class="blog-grids">
                <div class="fotorama" data-transition="crossfade" data-nav="thumbs" data-loop="true" data-autoplay="true" data-allowfullscreen="true" data-keyboard="true" >
                    <c:forEach items="${userGalery}" var="elem">
                        <img class="materialboxed" width="200" height="200"
                             src="${pageContext.request.contextPath}/upload/photo/${elem.reference}">
                    </c:forEach>
                </div>
            <div class="card">

            </div>
        </div>
    </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>

</body>
</html>