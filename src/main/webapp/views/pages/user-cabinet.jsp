<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
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

                <div class="col-md-4 about-text-left">
                    <div class="hover-image">
                        <a class="hover-image-left" href="#">
                            <cdg:l18n key="usercab.edit"/>
                        </a>
                        <a class="hover-image-right" href="#">
                            <cdg:l18n key="usercab.delete"/>
                        </a>
                        <img src="${pageContext.request.contextPath}/images/places1.jpg" alt="">
                    </div>
                    <a href="#"><h5>Itinerary name</h5></a>

                    <p>Some information</p>
                </div>

                <div class="col-md-4 about-text-left">
                    <div class="hover-image">
                        <a class="hover-image-left" href="#">
                            <cdg:l18n key="usercab.edit"/>
                        </a>
                        <a class="hover-image-right" href="#">
                            <cdg:l18n key="usercab.delete"/>
                        </a>
                        <img src="${pageContext.request.contextPath}/images/places1.jpg" alt="">
                    </div>
                    <a href="#"><h5>Itinerary name</h5></a>

                    <p>Some information</p>
                </div>

                <div class="col-md-4 about-text-left">
                    <div class="hover-image">
                        <a class="hover-image-left" href="#">
                            <cdg:l18n key="usercab.edit"/>
                        </a>
                        <a class="hover-image-right" href="#">
                            <cdg:l18n key="usercab.delete"/>
                        </a>
                        <img src="${pageContext.request.contextPath}/images/places1.jpg" alt="">
                    </div>
                    <a href="#"><h5>Itinerary name</h5></a>

                    <p>Some information</p>
                </div>

                <div class="clearfix">
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>


</body>
</html>