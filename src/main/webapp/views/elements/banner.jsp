<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
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
    <link href='http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet'
          type='text/css'>
</head>

<body>

<div class="banner">
    <div class="container">
        <div class="banner-main">
            <h3><cdg:l18n key="banner.head"/></h3>

            <p><cdg:l18n key="banner.text"/></p>

            <div class="bann-btn">
                <a href="view?command=showMap"><cdg:l18n key="header.plan"/></a>
            </div>
        </div>
    </div>
</div>
</body>
</html>