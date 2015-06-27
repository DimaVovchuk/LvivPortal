<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>
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
<jsp:include page="/views/elements/banner.jsp"/>

<div class="index">
    <div class="section">
        <h2 class="center-align"><cdg:l18n key="index.head"/></h2>

        <div class="row">
            <div class="col l4 m6 s12">
                <div class="section z-depth-2 center-align">
                    <a href="#">
                        <div class="hover-image">
                            <img class="responsive-img" src="${pageContext.request.contextPath}/images/places_architecture.jpg">
                        </div>
                    </a>
                    <a href="portal?command=place&category=architecture"><h5><cdg:l18n key="index.architecture"/></h5></a>
                    <p><cdg:l18n key="index.architecturetxt"/></p>
                    <a class='waves-effect dropdown-button dropdown-full btn cyan darken-2' href='#' data-activates='dropdown-architecture'><cdg:l18n
                            key="index.choose"/></a>
                    <ul id='dropdown-architecture' class='dropdown-content'></ul>
                </div>
            </div>

            <div class="col l4 m6 s12">
                <div class="section z-depth-2 center-align">
                    <a href="#">
                        <div class="hover-image">
                            <img class="responsive-img" src="${pageContext.request.contextPath}/images/places_churches.jpg">
                        </div>
                    </a>
                    <a href="portal?command=place&category=churches"><h5><cdg:l18n key="index.churches"/></h5></a>
                    <p><cdg:l18n key="index.churchestxt"/></p>
                    <a class='waves-effect dropdown-button dropdown-full btn cyan darken-2' href='#' data-activates='dropdown-churches'><cdg:l18n
                            key="index.choose"/></a>
                    <ul id='dropdown-churches' class='dropdown-content'></ul>
                </div>
            </div>

            <div class="col l4 m6 s12">
                <div class="section z-depth-2 center-align">
                    <a href="#">
                        <div class="hover-image">
                            <img class="responsive-img" src="${pageContext.request.contextPath}/images/places_theatres.jpg">
                        </div>
                    </a>
                    <a href="portal?command=place&category=theatres"><h5><cdg:l18n key="index.theatres"/></h5></a>
                    <p><cdg:l18n key="index.theatrestxt"/></p>
                    <a class='waves-effect dropdown-button btn dropdown-full cyan darken-2' href='#' data-activates='dropdown-theatres'><cdg:l18n
                            key="index.choose"/></a>
                    <ul id='dropdown-theatres' class='dropdown-content'></ul>
                </div>
            </div>

            <div class="col l4 m6 s12">
                <div class="section z-depth-2 center-align">
                    <a href="#">
                        <div class="hover-image">
                            <img class="responsive-img" src="${pageContext.request.contextPath}/images/places_restaurants.jpg">
                        </div>
                    </a>
                    <a href="portal?command=place&category=restaurants"><h5><cdg:l18n key="index.restaurants"/></h5></a>
                    <p><cdg:l18n key="index.restaurantstxt"/></p>
                    <a class='waves-effect dropdown-button btn  dropdown-full cyan darken-2' href='#' data-activates='dropdown-restaurants'><cdg:l18n
                            key="index.choose"/></a>
                    <ul id='dropdown-restaurants' class='dropdown-content'></ul>
                </div>
            </div>

            <div class="col l4 m6 s12">
                <div class="section z-depth-2 center-align">
                    <a href="#">
                        <div class="hover-image">
                            <img class="responsive-img" src="${pageContext.request.contextPath}/images/places_hotels.jpg">
                        </div>
                    </a>
                    <a href="portal?command=place&category=hotels"><h5><cdg:l18n key="index.hotels"/></h5></a>
                    <p><cdg:l18n key="index.hotels"/></p>
                    <a class='waves-effect dropdown-button btn  dropdown-full cyan darken-2' href='#' data-activates='dropdown-hotels'><cdg:l18n
                            key="index.choose"/></a>
                    <ul id='dropdown-hotels' class='dropdown-content'></ul>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/views/elements/footer-img.jsp"/>
<jsp:include page="/views/elements/footer.jsp"/>

<script id="place-template" type="text/x-handlebars-template">
    {{#each this}}
    <li><a href="portal?command=placeInformation&place_id={{id}}">{{name}}</a></li>
    {{/each}}
</script>

<script src="${pageContext.request.contextPath}/js/pages/index.js"></script>

</body>
</html>