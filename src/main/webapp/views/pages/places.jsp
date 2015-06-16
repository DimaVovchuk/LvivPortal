<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><cdg:l18n key="places.title"/></title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
</head>

<jsp:include page="/views/elements/head.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div class="blog">
    <div class="container">
        <div class="blog-main">
            <div class="blog-top">
                <h3><cdg:l18n key="places.head"/></h3>
            </div>

            <div class="col-md-9 blog-left">
                <c:forEach var="placeDescriptions" items="${placeDescriptions}">
                    <c:forEach var="places" items="${places}">
                        <c:forEach var="placeImages" items="${placeImages}">
                        <c:if test="${places.id==placeDescriptions.place_id}">
                         <div class="blog-grids">
                            <div class="blog-detail">
                                <div class="blog-image">

                            <c:if test="${placeImages.place_id == places.id}">
                                    <a href="portal?command=placeInformation&place_id=${places.id}"><img src="${pageContext.request.contextPath}/upload/photo/${placeImages.reference}" alt="" height="100" width="100"></a>
                            </c:if>

                                </div>
                                <a href="portal?command=placeInformation&place_id=${places.id}"><h3><c:out value="${placeDescriptions.name}"/></h3></a>


                                    <c:out value="${places.adress}"/>
                                    <div class="blog-btn"><a href="portal?command=placeInformation&place_id=${places.id}">Read More</a></div>


                             </div>
                         </div></c:if>
                        </c:forEach>
                    </c:forEach>
                </c:forEach>

            </div>

            <div class="col-md-3 blog-right">
                <div class="blog-cate">
                    <h3><cdg:l18n key="places.categories"/></h3>
                    <ul>
                        <li><a href="portal?command=place&category=churches">Churches</a></li>
                        <li><a href="portal?command=place&category=theatres">Theatres</a></li>
                        <li><a href="portal?command=place&category=hotels">Hotels</a></li>
                        <li><a href="portal?command=place&category=restaurants">Restaurants</a></li>
                        <li><a href="portal?command=place">All</a></li>
                    </ul>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>

</body>
</html>