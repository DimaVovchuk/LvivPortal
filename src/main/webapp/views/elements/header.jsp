<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>

<head>
    <link href="/css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="/js/jquery-1.11.3.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <!-- Custom Theme files -->
    <link href="/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="/css/flexslider.css" rel="stylesheet" type="text/css" media="screen" />

    <!--Google Fonts-->
    <link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <!-- smoth-scrolling -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="/js/move-top.js"></script>
    <script type="text/javascript" src="/js/easing.js"></script>

    <script type="text/javascript">
        jQuery(document).ready(function($) {
            $(".scroll").click(function(event){
                event.preventDefault();
                $('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
            });
        });
    </script>
    <script type="application/x-javascript">
        addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); }
    </script>
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
                <a href="/views/pages/index.jsp"> <img src="/images/logo.png" alt="" title=""> </a>
            </div>
            <div class="head-right">
                <div class="top-nav">
                    <span class="menu"> <img src="/images/icon.png" alt=""/></span>
                    <ul class="res">
                        <li><a href="/views/pages/index.jsp"><cdg:l18n key="header.home"/></a></li>
                        <li><a href="/views/pages/index.jsp#places"><cdg:l18n key="header.places"/></a></li>
                        <li><a href="http://www.booking.com"><cdg:l18n key="header.hotels"/></a></li>
                        <li><a href="/views/pages/map.jsp"><cdg:l18n key="header.plan"/></a></li>
                        <li><a href="#" id="login"><cdg:l18n key="header.sign"/></a></li>
                    </ul>
                    <!-- script-for-menu -->
                    <script>
                        $( "span.menu" ).click(function() {
                            $( "ul.res" ).slideToggle( 300, function() {
                            });
                        });
                    </script>
                </div>

                <div class="local">
                    <ul >
                        <li><a href="/local?lang=EN"><img src="/images/localization/EN.png"></a></li>
                        <li><a href="/local?lang=UA"><img src="/images/localization/UA.png"></a></li>
                    </ul>
                </div>

                <div class="search-box">
                    <div id="sb-search" class="sb-search">
                        <form>
                            <input class="sb-search-input" placeholder='Search' type="search" name="search" id="search">
                            <input class="sb-search-submit" type="submit" value="">
                            <span class="sb-icon-search"> </span>
                        </form>
                    </div>
                </div>
                <div class="clearfix"> </div>
                <!-- search-scripts -->
                <script src="js/classie.js"></script>
                <script src="js/uisearch.js"></script>
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
