
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>
<html>
<head>

</head>
<body>
<c:forEach var="placeDescriptions" items="${placeDescriptions}">
    <c:forEach var="places" items="${places}">
        <c:forEach var="placeImages" items="${placeImages}">
            <c:if test="${places.id==placeDescriptions.place_id}">
            <c:if test="${placeImages.place_id == places.id}">
              <h5><c:out value="${placeDescriptions.name}"/></h5><br>
            <img src="${pageContext.request.contextPath}/upload/photo/${placeImages.reference}" alt="" height="100" width="100"></a><br>
            <h5><c:out  value="${placeDescriptions.name}"/></h5><br>
            <c:out value="${places.adress}"/><br>
            </c:if>
            </c:if>
        </c:forEach>
    </c:forEach>
</c:forEach>
</body>
</html>
