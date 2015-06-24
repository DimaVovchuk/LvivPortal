<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>
<html>
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <title><cdg:l18n key="places.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</head>

<jsp:include page="/views/elements/css.jsp"/>
<jsp:include page="/views/elements/script.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>


<c:forEach var="ways" items="${ways}">
  <c:forEach items="${wayPlaceImages}" var="wayPlaceImages">
    <c:forEach items="${way_place}" var="way_place">
      <c:if test="${ways.id == way_place.key}">
        <c:if test="${ways.id == wayPlaceImages.key}">
                <a href="#">
                  <img src="${pageContext.request.contextPath}/upload/photo/${wayPlaceImages.value.reference}"
                       alt="" height="100" width="100">
                </a>
              <c:forEach items="${way_place.value}" var="place">
                <a href="portal?command=placeInformation&place_id=${place.place_id}">
                  <h5>${place.name}</h5></a><i class="fa fa-arrow-right"></i>
              </c:forEach>
          </div>
        </c:if>
      </c:if>
    </c:forEach>
  </c:forEach>
</c:forEach>

</body>
</html>
