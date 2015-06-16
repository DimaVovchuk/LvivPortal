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
                    <c:forEach items="${wayPlaceImages}" var="wayPlaceImages">
                    <c:forEach items="${way_place}" var="way_place">
                    <c:if test="${ways.id == way_place.key}">
                    <c:if test="${ways.id == wayPlaceImages.key}">
                    <div class="col-md-4 about-text-left">

                                    <div class="hover-image">
                                        <a class="hover-image-left" href="#">
                                            <cdg:l18n key="usercab.edit"/>
                                        </a>
                                        <a class="hover-image-right" href="#">
                                            <cdg:l18n key="usercab.delete"/>
                                        </a>
                                        <img src="${pageContext.request.contextPath}/upload/photo/${wayPlaceImages.value.reference}" alt="">

                                    </div>

                        <c:forEach items="${way_place.value}" var="place">
                                    <a href="portal?command=placeInformation&place_id=${place.place_id}"><h5>${place.name}</h5></a><i class="fa fa-arrow-right"></i>
                                    </c:forEach>


                    </div>
                        </c:if>
                        </c:if>
                        </c:forEach>
                        </c:forEach>
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