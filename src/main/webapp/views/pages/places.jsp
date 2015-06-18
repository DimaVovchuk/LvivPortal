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

<>

<jsp:include page="/views/elements/header.jsp"/>

<div class="places">
    <div class="section">
        <h3 class="center-align"><cdg:l18n key="places.head"/></h3>

        <div class="row">
            <div class="col l9 m8 s7">
                <div class="row z-depth-2">
                    <c:forEach var="placeDescriptions" items="${placeDescriptions}">
                        <c:forEach var="places" items="${places}">
                            <c:forEach var="placeImages" items="${placeImages}">
                                <c:if test="${places.id==placeDescriptions.place_id}">
                                    <c:if test="${placeImages.place_id == places.id}">
                                        <div class="col l6 m6 s12">
                                            <div class="section z-depth-2">
                                                <a href="portal?command=placeInformation&place_id=${places.id}"><img
                                                        src="${pageContext.request.contextPath}/upload/photo/${placeImages.reference}"
                                                        alt="" height="100" width="100"></a>
                                                <a href="portal?command=placeInformation&place_id=${places.id}"><h5>
                                                    <c:out
                                                            value="${placeDescriptions.name}"/></h5></a>
                                                <c:out value="${places.adress}"/>
                                                <form action="portal?command=place&place_id=${places.id}&category=${category}" method="post">
                                                    <input type="submit" value="Submit">
                                                </form>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                    </c:forEach>
                </div>
            </div>
        </div>

            <div class="col l3 m4 s5">
                <div class="collection with-header z-depth-2">
                    <div class="collection-header"><h4><cdg:l18n key="places.categories"/></h4></div>
                    <a href="portal?command=place&category=architecture" class="collection-item"><cdg:l18n key="places.architecture"/></a>
                    <a href="portal?command=place&category=churches" class="collection-item"><cdg:l18n key="places.churches"/></a>
                    <a href="portal?command=place&category=theatres" class="collection-item"><cdg:l18n key="places.theatres"/></a>
                    <a href="portal?command=place&category=hotels" class="collection-item"><cdg:l18n key="places.hotels"/></a>
                    <a href="portal?command=place&category=restaurants" class="collection-item"><cdg:l18n key="places.restaurants"/></a>
                    <a href="portal?command=place" class="collection-item"><cdg:l18n key="places.all"/></a>
                </div>
            </div>
        </div>
    </div>



<jsp:include page="/views/elements/footer.jsp"/>

</body>

</html>