<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>
<html>

<head>
    <title><cdg:l18n key="company.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
</head>

<jsp:include page="/views/elements/css.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div id="company-page" class="full-height">
    <div class="row">
        <div class="col l8 offset-l2 m10 offset-m1 s12 z-depth-2">
            <c:choose>
                <c:when test="${userInfo.role_id == 3}">
                    <h4 class="center-align"><cdg:l18n key="role.guide"/> - ${userInfo.name} ${userInfo.surname}</h4>
                </c:when>
            </c:choose>
            <c:choose>
                <c:when test="${userInfo.role_id == 4}">
                    <h4 class="center-align"><cdg:l18n key="role.company"/> "<b>${userInfo.companyName}</b>"</h4>
                </c:when>
            </c:choose>
            <div class="section">
                <div class="divider"></div>
            </div>

            <div class="row">
                <div class="col l4 m5 s6" style="margin-top: 10px">
                    <div class="center-align">
                        <c:choose>
                            <c:when test="${empty avatar}">
                                <img src="${pageContext.request.contextPath}/upload/photo/user.png"
                                     class="circle responsive-img" width="80%">
                            </c:when>
                            <c:otherwise>
                                <img src="${pageContext.request.contextPath}/upload/photo/${avatar.reference}"
                                     class="circle responsive-img" width="80%">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <div class="col l8 m7 s6">
                    <h4><cdg:l18n key="company.info"/></h4>

                    <p>
                        <c:choose>
                            <c:when test="${userInfo.role_id == 3}">
                                <cdg:l18n key="login.firstname"/>: ${userInfo.name}<br>
                                <cdg:l18n key="login.lastname"/>: ${userInfo.surname}<br>
                            </c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${userInfo.role_id == 4}">
                                <cdg:l18n key="login.companyname"/>: ${userInfo.companyName}<br>
                            </c:when>
                        </c:choose>
                        <cdg:l18n key="login.email"/>: ${userInfo.mail}<br>
                        <cdg:l18n key="login.phone"/>: ${userInfo.phone}<br>
                        <cdg:l18n key="company.about"/>: ${userInfo.about}<br><br>
                        <cdg:l18n key="company.rating"/>: ${userInfo.rating}
                    </p>
                </div>
            </div>

            <div class="section">
                <div class="divider"></div>
            </div>

            <h4 class="center-align"><cdg:l18n key="company.gallery"/></h4>

            <div class="card" style="padding: 10px">
                <div class="center-align">
                    <div class="fotorama" data-transition="crossfade" data-nav="thumbs" data-loop="true"
                         data-autoplay="true" data-allowfullscreen="true" data-keyboard="true" data-width="100%"
                         data-height="50%" data-fit="scaledown">
                        <c:forEach items="${userGalery}" var="elem">
                            <img class="materialboxed" width="200" height="200"
                                 src="${pageContext.request.contextPath}/upload/photo/${elem.reference}">
                        </c:forEach>
                    </div>
                </div>
            </div>

            <div class="section">
                <div class="divider"></div>
            </div>

            <h4 class="center-align"><cdg:l18n key="company.routes"/></h4>

            <ul class="collapsible" data-collapsible="accordion">
                <c:forEach var="mapelem" items="${allWayInfo}">
                    <li>
                        <div class="collapsible-header row">
                            <div class="col s6 left-align">
                                <cdg:l18n key="company.routename"/>: ${mapelem.key.name}
                            </div>
                            <div class="col s6 right-align">
                                <a href="#" style="text-transform: uppercase"><cdg:l18n key="company.order.route"/></a>
                            </div>
                        </div>
                        <div class="collapsible-body">
                            <div class="collection">
                                <c:forEach var="listelem" items="${mapelem.value}">
                                    <a href="#" class="collection-item black-text">
                                        <img class="circle responsive-img"
                                             src="${pageContext.request.contextPath}/upload/photo/${listelem.imageReference}">

                                        <div class="valign-wrapper" style="height:100%">
                                            <div class="valign">
                                                <div class="truncate">
                                                    <b>${listelem.name}</b><br>${listelem.adress}<br><cdg:l18n
                                                        key="company.rating"/>: ${listelem.rating}
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>

            <div class="section">
                <div class="divider"></div>
            </div>

            <h4 class="center-align"><cdg:l18n key="company.places"/></h4>

            <div class="row">
                <c:forEach var="place" items="${placesInfo}">
                    <div class="match-col col l4 m6 s12">
                        <div class="card z-depth-2" style="padding:10px; height:95%">
                            <div class="center-align">
                                <a href="#"><img class="responsive-img place-img"
                                                 src="${pageContext.request.contextPath}/upload/photo/${place.imageReference}"></a>
                                <a href="#"><h5><c:out value="${place.name}"/></h5></a>
                                <c:out value="${place.adress}"/><br>
                                <cdg:l18n key="company.rating"/>: ${place.rating}
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>
    </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>

<script>
    $(".match-col").matchHeight({
        property: 'height'
    });

    var img = $('.place-img');
    var width = img.width();
    img.css({
        'height': width + 'px'
    });
</script>

</body>
</html>