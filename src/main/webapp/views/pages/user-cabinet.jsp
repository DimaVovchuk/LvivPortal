<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>

<html>

<head>
    <title><cdg:l18n key="usercab.title"/></title>
</head>

<jsp:include page="/views/elements/head.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div class="about">
    <div class="container">
        <div class="about-main">
            <div class="about-top">
                <h3><cdg:l18n key="usercab.itinerary"/></h3>
                <h5><cdg:l18n key="usercab.itinerarytxt"/></h5>
            </div>
            <div class="about-text">
                <c:forEach var="ways" items="${ways}">
                    <div class="col-md-4 about-text-left">
                        <c:forEach items="${wayPlaceImages}" var="wayPlaceImages">
                        <div class="hover-image">
                            <a class="hover-image-left" href="#">
                                <cdg:l18n key="usercab.edit"/>
                            </a>
                            <a class="hover-image-right" href="#">
                                <cdg:l18n key="usercab.delete"/>
                            </a>
                            <c:if test="${ways.id == wayPlaceImages.key}">
                                <img src="${pageContext.request.contextPath}/upload/photo/${image.value.reference}" alt="">
                            </c:if>
                        </div>
                        <a href="#"><h5>Itinerary name</h5></a>

                        <p>Some information</p>
                        </c:forEach>
                    </div>
                </c:forEach>
                <div class="clearfix">
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>


</body>
</html>