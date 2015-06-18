<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>

<header>
    <div class="navbar-fixed">
        <nav>
            <div class="nav-wrapper blue-grey darken-4">
                <a href="/portal?command=index" class="brand-logo"><img class="logo"
                                                                       src="${pageContext.request.contextPath}/images/logo.png"></a>
                <a href="#" data-activates="mobile-navbar" class="button-collapse"><i
                        class="mdi-navigation-menu"></i></a>

                <c:choose>
                    <c:when test="${login!=null}">
                        <ul class="right">
                            <li><a href="#" data-activates="user-navbar" class="button-menu"><i class="mdi-action-account-circle left"></i></a></li>
                        </ul>

                        <ul class="right hide-on-med-and-down">
                            <li><a href="/portal?command=index"><cdg:l18n key="header.home"/></a></li>
                            <li><a href="/portal?command=place"><cdg:l18n key="header.places"/></a></li>
                            <li><a href="/portal?command=showMap"><cdg:l18n key="header.plan"/></a></li>
                            <li><a class="dropdown-button" href="#" data-activates="lang-dropdown"><cdg:l18n key="header.lang"/><i
                                    class="mdi-navigation-arrow-drop-down right"></i></a></li>
                            </li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <ul class="right hide-on-med-and-down">
                            <li><a href="/portal?command=index"><cdg:l18n key="header.home"/></a></li>
                            <li><a href="/portal?command=place"><cdg:l18n key="header.places"/></a></li>
                            <li><a href="/portal?command=showMap"><cdg:l18n key="header.plan"/></a></li>
                            <li><a class="dropdown-button" href="#" data-activates="lang-dropdown"><cdg:l18n key="header.lang"/><i
                                    class="mdi-navigation-arrow-drop-down right"></i></a></li>
                            <li><a class="modal-trigger" href="#sign-in"><i class="mdi-action-account-circle left"></i><cdg:l18n
                                    key="header.sign"/></a>
                            </li>
                        </ul>

                    </c:otherwise>
                </c:choose>

                <ul class="side-nav" id="mobile-navbar">
                    <li><a href="/portal?command=index"><cdg:l18n key="header.home"/></a></li>
                    <li><a href="/portal?command=place"><cdg:l18n key="header.places"/></a></li>
                    <li><a href="/portal?command=#"><cdg:l18n key="header.plan"/></a></li>
                    <li class="divider"></li>
                    <li><a href="#sign-in"><i class="mdi-action-account-circle right"></i><cdg:l18n key="header.sign"/></a>
                    </li>
                </ul>

                <ul class="side-nav" id="user-navbar">
                    <li style="padding-top: 10px"><img class="circle responsive-img" src="images/places1.jpg"></li>
                    <li><a href="/portal?command=userWays"><i class="mdi-maps-directions-bike left"></i>My routes</a></li>
                    <li><a href="/portal?command=userPlace"><i class="mdi-action-account-balance left"></i>My places</a></li>
                    <li><a href="/portal?command=allUserPhoto"><i class="mdi-image-photo left"></i>Gallery</a></li>
                    <li><a href="/portal?command=edit"><i class="mdi-action-settings left"></i>Settings</a></li>
                    <li class="divider"></li>
                    <li><a href="/portal?command=signOut"><i class="mdi-navigation-close left"></i>Sign out</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>

    <ul id="lang-dropdown" class="dropdown-content">
        <li>
            <a href="#" onclick="language('en')">
                <img class="text-middle" src="${pageContext.request.contextPath}/images/lang/EN.png"> <cdg:l18n
                    key="header.lang.en"/>
            </a>
        </li>
        <li>
            <a href="#" onclick="language('ua')">
                <img class="text-middle" src="${pageContext.request.contextPath}/images/lang/UA.png"> <cdg:l18n
                    key="header.lang.ua"/>
            </a>
        </li>
    </ul>
</header>

<jsp:include page="/views/elements/login.jsp"/>