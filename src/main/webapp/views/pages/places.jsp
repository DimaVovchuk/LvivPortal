<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><cdg:l18n key="places.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
</head>

<jsp:include page="/views/elements/css.jsp"/>
<jsp:include page="/views/elements/script.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div class="places">
    <div class="section">
        <h3 class="center-align"><cdg:l18n key="places.head"/></h3>

        <div class="row">
            <div class="col l9 m8 s7">
                <div id="place-page-container" class="row z-depth-2">

                    <div class="place-page-navigation z-depth-1"></div>

                    <div class="place-page-content">

                        <c:forEach var="place" items="${requestScope.places}">
                            <div class="match-col col l4 m6 s12" style="display:none">
                                <div class="card z-depth-2">
                                    <form action="portal?command=place&place_id=${place.id}&category=${requestScope.category}"
                                          method="post" style="position:absolute;padding:5px">
                                        <c:set var="category" scope="request" value="${requestScope.category}"/>
                                        <c:if test="${requestScope.userDataTrip!=null}">
                                            <button class="btn modal-trigger btn-floating btn-large waves-effect waves-light cyan darken-2"
                                                    type="submit" data-target="chooseDay"
                                                    id="btn1" onclick="$('#place_id').val('${place.id}')">
                                                <i class="mdi-content-add"></i>
                                            </button>
                                        </c:if>
                                    </form>
                                    <div class="center-align">
                                        <a href="portal?command=placeInformation&place_id=${place.id}"><img
                                                class="responsive-img place-img"
                                                src="${pageContext.request.contextPath}/upload/photo/${place.imageReference}"></a>
                                    </div>
                                    <a href="portal?command=placeInformation&place_id=${place.id}">
                                        <h5><c:out value="${place.name}"/></h5></a>
                                    <c:if test="${login!=null}">
                                        <div style="height: 40px"></div>
                                        <div class="bottom-right-btn">
                                                <c:choose>
                                                <c:when test="${place.rating==1}">
                                                <a class="btn-floating disabled btn-small">
                                                    <i class="material-icons">thumb_up</i>
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <a class="btn-floating btn-small waves-effect waves-light cyan darken-2"
                                                   href="portal?command=rectRating&rating=1&place_id=${place.id}&category=${requestScope.category}">
                                                    <i class="material-icons">thumb_up</i>
                                                </a>
                                            </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                            <c:when test="${place.rating==0}">
                                                <a class="btn-floating disabled btn-small">
                                                <i class="material-icons">thumbs_up_down</i>
                                            </a>
                                            </c:when>
                                                <c:otherwise>
                                                    <a class="btn-floating btn-small waves-effect waves-light cyan darken-2"
                                                       href="portal?command=rectRating&rating=0&place_id=${place.id}&category=${requestScope.category}">
                                                        <i class="material-icons">thumbs_up_down</i>
                                                    </a>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                            <c:when test="${place.rating==-1}">
                                                <a class="btn-floating disabled btn-small">
                                                    <i class="material-icons">thumb_down</i>
                                                </a>
                                            </c:when>
                                                <c:otherwise>
                                                    <a class="btn-floating btn-small waves-effect waves-light cyan darken-2"
                                                       href="portal?command=rectRating&rating=-1&place_id=${place.id}&category=${requestScope.category}">
                                                        <i class="material-icons">thumb_down</i>
                                                    </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>

                    </div>

                </div>
            </div>

            <div class="col l3 m4 s5">
                <div class="collection with-header z-depth-2">
                    <div class="collection-header"><h4><cdg:l18n key="places.categories"/></h4></div>
                    <a href="portal?command=place&category=architecture"
                       class="collection-item black-text ${requestScope.active_architecture}"><cdg:l18n
                            key="places.architecture"/></a>
                    <a href="portal?command=place&category=churches"
                       class="collection-item black-text ${requestScope.active_churches}"><cdg:l18n
                            key="places.churches"/></a>
                    <a href="portal?command=place&category=theatres"
                       class="collection-item black-text ${requestScope.active_theatres}"><cdg:l18n
                            key="places.theatres"/></a>
                    <a href="portal?command=place&category=hotels"
                       class="collection-item black-text ${requestScope.active_hotels}"><cdg:l18n
                            key="places.hotels"/></a>
                    <a href="portal?command=place&category=restaurants"
                       class="collection-item black-text ${requestScope.active_restaurants}"><cdg:l18n
                            key="places.restaurants"/></a>
                    <a href="portal?command=place"
                       class="collection-item black-text ${requestScope.active_allplaces}"><cdg:l18n
                            key="places.all"/></a>


                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>
<jsp:include page="/views/modals/add-place-to-route.jsp"/>

</body>
</html>