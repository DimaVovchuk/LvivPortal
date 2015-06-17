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
                            <img class="responsive-img" src="${pageContext.request.contextPath}/images/places1.jpg">
                        </div>
                    </a>
                    <a href="#"><h5><cdg:l18n key="index.architecture"/></h5></a>
                    <p><cdg:l18n key="index.architecturetxt"/></p>
                    <a class='waves-effect dropdown-button dropdown-full btn cyan darken-2' href='#' data-activates='dropdown-arch'><cdg:l18n
                            key="index.choose"/></a>
                    <ul id='dropdown-arch' class='dropdown-content'>
                        <li><a href="#">one</a></li>
                        <li><a href="#">two</a></li>
                        <li class="divider"></li>
                        <li><a href="#">three</a></li>
                    </ul>
                </div>
            </div>

            <div class="col l4 m6 s12">
                <div class="section z-depth-2 center-align">
                    <a href="#">
                        <div class="hover-image">
                            <img class="responsive-img" src="${pageContext.request.contextPath}/images/places2.jpg">
                        </div>
                    </a>
                    <a href="#"><h5><cdg:l18n key="index.culture"/></h5></a>
                    <p><cdg:l18n key="index.culturetxt"/></p>
                    <a class='waves-effect dropdown-button btn dropdown-full cyan darken-2' href='#' data-activates='dropdown-cult'><cdg:l18n
                            key="index.choose"/></a>
                    <ul id='dropdown-cult' class='dropdown-content'>
                        <li><a href="#">one</a></li>
                        <li><a href="#">two</a></li>
                        <li class="divider"></li>
                        <li><a href="#">three</a></li>
                    </ul>
                </div>
            </div>

            <div class="col l4 m6 s12">
                <div class="section z-depth-2 center-align">
                    <a href="#">
                        <div class="hover-image">
                            <img class="responsive-img" src="${pageContext.request.contextPath}/images/places3.jpg">
                        </div>
                    </a>
                    <a href="#"><h5><cdg:l18n key="index.culture"/></h5></a>
                    <p><cdg:l18n key="index.culturetxt"/></p>
                    <a class='waves-effect dropdown-button btn  dropdown-full cyan darken-2' href='#' data-activates='dropdown-food'><cdg:l18n
                            key="index.choose"/></a>
                    <ul id='dropdown-food' class='dropdown-content'>
                        <li><a href="#">one</a></li>
                        <li><a href="#">two</a></li>
                        <li class="divider"></li>
                        <li><a href="#">three</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>

</body>
</html>