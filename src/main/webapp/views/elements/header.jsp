<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>Lviv tourism portal</title>

    <link href="../../resources/css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../../resources/js/jquery-1.11.0.min.js"></script>
    <!-- Custom Theme files -->
    <link href="../../resources/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link rel="stylesheet" href="../../resources/css/flexslider.css" type="text/css" media="screen" />

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="application/x-javascript">
        addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); }
    </script>
    <meta name="keywords" content="" />
    <!--Google Fonts-->
    <link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <!-- smoth-scrolling -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="../../resources/js/move-top.js"></script>
    <script type="text/javascript" src="../../resources/js/easing.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function($) {
            $(".scroll").click(function(event){
                event.preventDefault();
                $('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
            });
        });
    </script>
    <script>
        $(function() {
            $('a.page-scroll').bind('click', function(event) {
                var $anchor = $(this);
                $('html, body').stop().animate({
                    scrollTop: $($anchor.attr('href')).offset().top
                }, 1500, 'easeInOutExpo');
                event.preventDefault();
            });
        });
    </script>
</head>

<body>

<div class="header">
    <div class="container">
        <div class="header-main">
            <div class="logo">
                <a href="../index.jsp"> <img src="../../resources/images/logo.png" alt="" title=""> </a>
            </div>
            <div class="head-right">
                <div class="top-nav">
                    <span class="menu"> <img src="../../resources/images/icon.png" alt=""/></span>
                    <ul class="res">
                        <li><a class="active" href="../index.jsp">Home</a></li>
                        <li><a class="page-scroll" href="../index.jsp#places">Places</a></li>
                        <li><a href="http://www.booking.com">Hotels</a></li>
                         <li><a href="#">Plan trip</a></li>
                        <li><a href="#" id="login">Sign in</a></li>
                        <%--<div class="clearfix"> </div>--%>
                    </ul>
                    <!-- script-for-menu -->
                    <script>
                        $( "span.menu" ).click(function() {
                            $( "ul.res" ).slideToggle( 300, function() {
                                // Animation complete.
                            });
                        });
                    </script>
                </div>

                <div class="search-box">
                    <div id="sb-search" class="sb-search">
                        <form>
                            <input class="sb-search-input" placeholder="Search" type="search" name="search" id="search">
                            <input class="sb-search-submit" type="submit" value="">
                            <span class="sb-icon-search"> </span>
                        </form>
                    </div>
                </div>
                <div class="clearfix"> </div>
                <!-- search-scripts -->
                <script src="../../resources/js/classie.js"></script>
                <script src="../../resources/js/uisearch.js"></script>
                <script>
                    new UISearch( document.getElementById( 'sb-search' ) );
                </script>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>

</body>
</html>
