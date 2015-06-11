<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>

<div class="header">
    <div class="container">
        <div class="header-main">
            <div class="logo">
                <a href="view?command=index"> <img src="/images/logo.png" alt="" title=""> </a>
            </div>
            <div class="head-right">
                <div class="top-nav">
                    <span class="menu"> <img src="/images/icon.png" alt=""/></span>
                    <ul class="res">
                        <li><a href="view?command=index"><cdg:l18n key="header.home"/></a></li>
                        <li><a href="/views/pages/index.jsp#places"><cdg:l18n key="header.places"/></a></li>
                        <li><a href="http://www.booking.com"><cdg:l18n key="header.hotels"/></a></li>
                        <li><a href="/views/pages/map.jsp"><cdg:l18n key="header.plan"/></a></li>
                        <li>
                            <div class="dropdown">
                                <a id="sign" data-target="#" href="http://example.com" data-toggle="dropdown"
                                   aria-haspopup="true"
                                   role="button" aria-expanded="false"><cdg:l18n key="header.sign"/></a>
                                <form class="dropdown-menu pop-up-menu" role="menu" aria-labelledby="sign">
                                    <div class="form-group">
                                    <input type="text" class="form-control" id="login" placeholder='<cdg:l18n key="header.login"/>'>
                                    </div>
                                    <div class="form-group">
                                    <input type="password" class="form-control" id="password" placeholder='<cdg:l18n key="header.password"/>'>
                                    </div>
                                    <div class="divider"></div>
                                    <button type="submit" class="form-btn btn btn-default"><cdg:l18n key="header.sign"/></button>
                                </form>
                            </div>
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
                        <form class="form-inline" action="view?command=locale" method="post">
                            <%--<button type="submit" name="lang" value="en">en</button>--%>
                            <%--<button type="submit" name="lang" value="ua">ua</button>--%>

                            <%--<button onclick="language('ua')">ua</button>--%>
                            <%--<button onclick="language('en')">en</button>--%>
                            <li>
                                <button type="submit" name="lang" value="en"><img
                                        src="${pageContext.request.contextPath}/images/localization/EN.png"></button>
                            </li>
                            <li>
                                <button type="submit" name="lang" value="ua"><img src="/images/localization/UA.png">
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
                <div class="clearfix"></div>
                <!-- search-scripts -->
                <script src="js/classie.js"></script>
                <script src="js/uisearch.js"></script>
                <script>
                    new UISearch(document.getElementById('sb-search'));
                </script>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
