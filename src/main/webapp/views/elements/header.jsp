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
                            <li><a href="#" data-activates="user-navbar" class="button-menu"><i
                                    class="mdi-action-account-circle left"></i></a></li>
                        </ul>

                        <ul class="right hide-on-med-and-down">
                            <li class="${requestScope.active_home}"><a href="/portal?command=index"><cdg:l18n key="header.home"/></a></li>
                            <li class="${requestScope.active_places}"><a href="/portal?command=place"><cdg:l18n key="header.places"/></a></li>
                            <c:choose>
                                <c:when test="${userDataTrip != null }">
                                    <li class="${requestScope.active_plan}"><a href="/portal?command=showMap"><cdg:l18n key="header.plan"/></a></li>
                                </c:when>
                                <c:otherwise>
                                    <li  class="${requestScope.active_plan}"><a class="modal-trigger" href="#set-date-time"><cdg:l18n key="header.plan"/></a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                            <c:set var="pageNumber" scope="request" value="1"/>
                            <li><a class="dropdown-button" href="#" data-activates="lang-dropdown"><cdg:l18n
                                    key="header.lang"/><i
                                    class="mdi-navigation-arrow-drop-down right"></i></a></li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <ul class="right hide-on-med-and-down">
                            <li class="${requestScope.active_home}"><a href="/portal?command=index"><cdg:l18n key="header.home"/></a></li>
                            <li class="${requestScope.active_places}"><a href="/portal?command=place"><cdg:l18n key="header.places"/></a></li>
                            <c:choose>
                                <c:when test="${userDataTrip != null }">
                                    <li class="${requestScope.active_plan}"><a href="/portal?command=showMap"><cdg:l18n key="header.plan"/></a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="${requestScope.active_plan}"><a class="modal-trigger" href="#set-date-time"><cdg:l18n key="header.plan"/></a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                            <c:set var="pageNumber" scope="request" value="1"/>
                            <li><a class="dropdown-button" href="#" data-activates="lang-dropdown"><cdg:l18n
                                    key="header.lang"/><i
                                    class="mdi-navigation-arrow-drop-down right"></i></a></li>
                            <li><a class="modal-trigger" href="#sign-in"><i
                                    class="mdi-action-account-circle left"></i><cdg:l18n
                                    key="header.sign"/></a>
                            </li>
                        </ul>
                    </c:otherwise>
                </c:choose>

                <ul class="side-nav" id="mobile-navbar">
                    <li class="${requestScope.active_home}"><a href="/portal?command=index"><cdg:l18n key="header.home"/></a></li>
                    <li class="${requestScope.active_places}"><a href="/portal?command=place"><cdg:l18n key="header.places"/></a></li>
                    <li class="${requestScope.active_plan}"><a class="modal-trigger" href="#set-date-time"><cdg:l18n key="header.plan"/></a></li>
                    <c:set var="pageNumber" scope="request" value="1"/>
                    <li class="divider"></li>
                    <li><a class="modal-trigger" href="#sign-in"><i
                            class="mdi-action-account-circle right"></i><cdg:l18n key="header.sign"/></a>
                    </li>
                </ul>

                <ul class="side-nav" id="user-navbar">
                    <c:if test="${usedID != null and avatar != null}">
                        <li class="center-align" style="padding-top: 10px"><img class="circle responsive-img"
                                                                                src="${pageContext.request.contextPath}/upload/photo/${avatar}"
                                                                                width="160"></li>
                    </c:if>
                    <c:if test="${avatar == null}">
                        <li class="center-align" style="padding-top: 10px"><img class="circle responsive-img"
                                                                                src="${pageContext.request.contextPath}/upload/photo/user.png"
                                                                                width="160"></li>
                    </c:if>
                    <li><a href="/portal?command=userWays"><i class="mdi-maps-directions-bike left"></i>My routes</a>
                    </li>
                    <li><a href="/portal?command=userPlace"><i class="mdi-action-account-balance left"></i>My places</a>
                    </li>
                    <li><a href="/portal?command=allUserPhoto"><i class="mdi-image-photo left"></i>Gallery</a></li>
                    <li><a href="/portal?command=edit"><i class="mdi-action-settings left"></i>Settings</a></li>
                    <li class="divider"></li>
                    <c:choose>
                        <c:when test="${userDataTrip != null && userDataTrip.isSaved == false && userDataTrip.isFull == true}">
                            <li><a class="modal-trigger" href="#sure-save-sign-out"><i class="mdi-navigation-close left"></i>Sign out</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="/portal?command=signOut"><i class="mdi-navigation-close left"></i>Sign out</a></li>
                        </c:otherwise>
                    </c:choose>
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

<jsp:include page="/views/modals/login.jsp"/>
<jsp:include page="/views/modals/reset-password.jsp"/>
<jsp:include page="/views/modals/new-route.jsp"/>
<jsp:include page="/views/modals/sure-save.jsp"/>

<script src="${pageContext.request.contextPath}/js/pages/header.js"></script>
<script src='${pageContext.request.contextPath}/js/<cdg:l18n key="validator.js"/>'></script>
<div id="start-modal" data-modal="${requestScope.modal}"></div>

