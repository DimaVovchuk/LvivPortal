<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>

<div class="header">
    <div class="container">
        <div class="header-main">
            <div class="logo">
                <a href="portal?command=index"> <img src="${pageContext.request.contextPath}/images/logo.png" alt=""
                                                     title=""> </a>
            </div>
            <div class="head-right">
                <div class="top-nav">
                    <span class="menu"> <img src="${pageContext.request.contextPath}/images/icon.png" alt=""/></span>
                    <ul class="res">
                        <li><a href="portal?command=index"><cdg:l18n
                                key="header.home"/></a></li>
                        <li><a href="portal?command=place"><cdg:l18n
                                key="header.places"/></a></li>
                        <li><a href="http://www.booking.com"><cdg:l18n key="header.hotels"/></a></li>
                        <li><a href="${pageContext.request.contextPath}/views/pages/map.jsp"><cdg:l18n
                                key="header.plan"/></a></li>
                        <li>
                            <a class="login" data-toggle="modal" href="#loginform"><cdg:l18n key="header.sign"/></a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="login dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> username<b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a class="dropdown" href="#"><i class="fa fa-university fa-fw"></i> My places</a>
                                </li>
                                <li>
                                    <a class="dropdown" href="#"><i class="fa fa-bus fa-fw"></i> Routes</a>
                                </li>
                                <li>
                                    <a class="dropdown" href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                                </li>
                                <li class="dropdown divider"></li>
                                <li>
                                    <a class="dropdown" href="#"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <!-- script-for-menu -->
                    <script>
                        $("span.menu").click(function () {
                            $("ul.res").slideToggle(300, function () {
                            });
                        });
                    </script>
                </div>

                <div class="local">
                    <ul>
                        <form class="form-inline" action="portal?command=locale" method="post">
                            <li>
                                <button onclick="language('en')"><img
                                        src="${pageContext.request.contextPath}/images/localization/EN.png"></button>
                            </li>
                            <li>
                                <button onclick="language('ua')"><img
                                        src="${pageContext.request.contextPath}/images/localization/UA.png">
                                </button>
                            </li>
                        </form>
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
                <!-- search-scripts -->
                <script src="${pageContext.request.contextPath}/js/classie.js"></script>
                <script src="${pageContext.request.contextPath}/js/uisearch.js"></script>
                <script>
                    new UISearch(document.getElementById('sb-search'));
                </script>
                <div class="clearfix"></div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>

<div class="modal fade" id="loginform">
    <jsp:include page="/views/elements/login.jsp"/>
</div>