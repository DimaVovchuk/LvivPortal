<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 13.06.2015
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
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
<jsp:include page="/views/elements/css.jsp"/>
<body>

<div class="blog">
  <div class="container">
    <div class="blog-main">
      <div class="blog-top">
        <h3><cdg:l18n key="places.head"/></h3>
      </div>

      <div class="col-md-9 blog-left">
        <p>${user.name} ${user.surname}</p>
          <div class="blog-grids">
            <div class="blog-detail">
              <div class="blog-image">
                <a href="#"><img src="${pageContext.request.contextPath}/images/opera_sm.jpg" alt=""></a>
              </div>
              <div class="blog-btn">
              <c:forEach var="placeDescription" items="${placeDescription}">
                <h6><c:out value="${placeDescription.name}"/></h6><br>
              </c:forEach><br><br>
            </div>
              <div class="blog-btn">
              <c:forEach var="userImage" items="${userImage}">
                  <c:out value="${userImage.reference}"/><br><br>
              </c:forEach>
              </div>
              <div class="blog-btn">
                <c:forEach var="ways" items="${ways}">
                  <c:out value="${ways.name}"/><br><br>
                </c:forEach>
              </div>
            </div>
          </div>

      </div>

      <div class="col-md-3 blog-right">
        <div class="blog-cate">
          <h3><cdg:l18n key="places.categories"/></h3>
        </div>
      </div>
      <div class="clearfix"></div>
    </div>
  </div>
</div>
<jsp:include page="/views/elements/footer.jsp"/>
<jsp:include page="/views/pages/uploadImage.jsp"/>
<form id="showAllUser" action="showAllUser" method="POST">
  <input type="hidden" name="command" value="showAllUser">
  <input type="submit" value="Show User"/>
</form>

</body>
</html>
