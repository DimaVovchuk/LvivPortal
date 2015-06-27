<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>

<html>

<head>
    <title><cdg:l18n key="usercab.title"/></title>
</head>

<jsp:include page="/views/elements/css.jsp"/>
<jsp:include page="/views/elements/script.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div class="section">
    <h2 class="center-align"><cdg:l18n key="usercab.itinerary"/></h2>
    <h5 class="center-align"><cdg:l18n key="usercab.itinerarytxt"/></h5>
    <div class="row">

        <c:forEach var="ways" items="${ways}">
            <c:forEach items="${wayPlaceImages}" var="wayPlaceImages">
                <c:forEach items="${way_place}" var="way_place">
                    <c:if test="${ways.id == way_place.key}">
                        <c:if test="${ways.id == wayPlaceImages.key}">
                            <div class="col l3 m6 s12">
                                <div class="section">
                                <div class="hover-image">
                                    <a href="#">
                                    <img src="${pageContext.request.contextPath}/upload/photo/${wayPlaceImages.value.reference}"
                                         alt="">
                                    </a>
                                </div>
                                <c:forEach items="${way_place.value}" var="place">
                                    <a href="portal?command=placeInformation&place_id=${place.place_id}">
                                        <h5>${place.name}</h5></a><i class="fa fa-arrow-right"></i>
                                </c:forEach>
                                </div>
                            </div>
                        </c:if>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </c:forEach>

    </div>

    <b>show all user test command</b>

    <form id="sign-in-form" action="/portal/showalluser" method="post">
        <input type="hidden" name="command" value="showAllUser">
        <input type="hidden" name="requestType" value="showAllUser">
        <button type="submit">Show all user
        </button>
    </form>
    <b>edit place test command</b>

    <form id="edit_place" action="/portal/editplace" method="post">
        <input type="hidden" name="command" value="editPlace">
        <button type="submit">Edit Place
        </button>
    </form>
</div>

<li><a href="/portal?command=addNewPlace"></i>Add new place TEST</a></li>
<br>

<jsp:include page="/views/elements/footer.jsp"/>


</body>
</html>