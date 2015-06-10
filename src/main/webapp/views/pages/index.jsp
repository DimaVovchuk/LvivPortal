<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>
<html>

<head>
    <title><cdg:l18n key="index.title" /></title>

    <link href="/css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="/js/jquery-1.11.3.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <!-- Custom Theme files -->
    <link href="/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="/css/flexslider.css" rel="stylesheet" type="text/css" media="screen" />

    <!--Google Fonts-->
    <link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet'
          type='text/css'>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
</head>

<body>

<jsp:include page="/views/elements/header.jsp"/>
<jsp:include page="/views/elements/banner.jsp"/>

<div class="services" id="places">
    <div class="container">
        <div class="services-main">
            <div class="services-top" id="information">
                <h3><cdg:l18n key="places.head"/></h3>
            </div>
            <div class="services-bottom">
                <div class="row">

                    <div class="col-sm-6 col-md-4 ser">
                        <div class="thumbnail">
                            <a href="#"><img src="/images/places1.jpg" alt=""></a>
                            <div class="caption">
                                <a href="#"><h3><cdg:l18n key="places.architecture"/></h3></a>
                                <p><cdg:l18n key="places.architecturetxt"/></p>
                                <div class="dropup">
                                    <button class="places-dropdown btn btn-default dropdown-toggle" type="button"
                                            data-toggle="dropdown" aria-expanded="true">
                                        <cdg:l18n key="places.choose"/>
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
                            <a href="#"><img src="/images/places2.jpg" alt=""></a>
                            <div class="caption">
                                <a href="#"><h3><cdg:l18n key="places.culture"/></h3></a>
                                <p><cdg:l18n key="places.culturetxt"/></p>
                                <div class="dropup">
                                    <button class="places-dropdown btn btn-default dropdown-toggle" type="button"
                                            data-toggle="dropdown" aria-expanded="true">
                                        <cdg:l18n key="places.choose"/>
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
                            <a href="#"><img src="/images/places3.jpg" alt=""></a>
                            <div class="caption">
                                <a href="#"><h3><cdg:l18n key="places.food"/></h3></a>
                                <p><cdg:l18n key="places.foodtxt"/></p>
                                <div class="dropup">
                                    <button class="places-dropdown btn btn-default dropdown-toggle" type="button"
                                            data-toggle="dropdown" aria-expanded="true">
                                        <cdg:l18n key="places.choose"/>
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