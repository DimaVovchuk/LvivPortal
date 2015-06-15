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

<jsp:include page="/views/elements/head.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>
<jsp:include page="/views/elements/banner.jsp"/>

<div class="services" id="places">
    <div class="container">
        <div class="services-main">
            <div class="services-top" id="information">
                <h3><cdg:l18n key="index.head"/></h3>
            </div>
            <div class="services-bottom">
                <div class="row">

                    <div class="col-sm-6 col-md-4 ser">
                        <div class="thumbnail">
                            <a href="#">
                                <div class="hover-image">
                                    <img src="${pageContext.request.contextPath}/images/places1.jpg" alt="">
                                </div>
                            </a>

                            <div class="caption">
                                <a href="#"><h3><cdg:l18n key="index.architecture"/></h3></a>

                                <p><cdg:l18n key="index.architecturetxt"/></p>

                                <div class="dropup">
                                    <button class="places-dropdown btn btn-default dropdown-toggle" type="button"
                                            data-toggle="dropdown" aria-expanded="true">
                                        <cdg:l18n key="index.choose"/>
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Item 1</a></li>
                                        <li><a href="#">Item 2</a></li>
                                        <li class="divider"></li>
                                        <li><a href="#">Item 3</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6 col-md-4 ser">
                        <div class="thumbnail">
                            <a href="#">
                                <div class="hover-image">
                                    <img src="${pageContext.request.contextPath}/images/places2.jpg" alt="">
                                </div>
                            </a>

                            <div class="caption">
                                <a href="#"><h3><cdg:l18n key="index.culture"/></h3></a>

                                <p><cdg:l18n key="index.culturetxt"/></p>

                                <div class="dropup">
                                    <button class="places-dropdown btn btn-default dropdown-toggle" type="button"
                                            data-toggle="dropdown" aria-expanded="true">
                                        <cdg:l18n key="index.choose"/>
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Item 1</a></li>
                                        <li><a href="#">Item 2</a></li>
                                        <li class="divider"></li>
                                        <li><a href="#">Item 3</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6 col-md-4 ser">
                        <div class="thumbnail">
                            <a href="#">
                                <div class="hover-image">
                                    <img src="${pageContext.request.contextPath}/images/places3.jpg" alt="">
                                </div>
                            </a>

                            <div class="caption">
                                <a href="#"><h3><cdg:l18n key="index.food"/></h3></a>

                                <p><cdg:l18n key="index.foodtxt"/></p>

                                <div class="dropup">
                                    <button class="places-dropdown btn btn-default dropdown-toggle" type="button"
                                            data-toggle="dropdown" aria-expanded="true">
                                        <cdg:l18n key="index.choose"/>
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Item 1</a></li>
                                        <li><a href="#">Item 2</a></li>
                                        <li class="divider"></li>
                                        <li><a href="#">Item 3</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="clearfix"><br></div>

                </div>

            </div>
        </div>
    </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>

</body>
</html>