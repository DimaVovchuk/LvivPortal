<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vasyl
  Date: 21.06.2015
  Time: 1:01
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
  <title>Edit Place</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta name="keywords" content=""/>
</head>
<jsp:include page="/views/elements/css.jsp"/>
<jsp:include page="/views/elements/script.jsp"/>

<body>
<jsp:include page="/views/elements/header.jsp"/>

<div class="container">
  <div class="row">
    <h1>Edit Place</h1>
    <hr>
      <div class="text-center">
        <c:forEach items="${placeImageList}" var="elem">
          <td><img class="materialboxed" width="200"  height="200" src="${pageContext.request.contextPath}/upload/photo/${elem.reference}"></td>
        </c:forEach>
    </div>

      <form id="updatePlaceProfile" action="portal/updateplace" metod="post">
        <input type="hidden" name="command" value="updateprofile">
        <c:forEach items="${placeDescriptionList}" var="elem">
          <div class="row">
            <input value="${elem.name}" id="placeName" type="text" name="placeName">
            <label class="active" for="placeName">Place name:</label>
          </div>
        </c:forEach>

        <c:forEach items="${placeDescriptionList}" var="elem">
          <div class="row">
            <input value="${elem.description}" id="placeDescription" type="text" name="placeDescription">
            <label class="active" for="placeDescription">Place description:</label>
          </div>
        </c:forEach>

        <div class="row">
            <input value="${editPlace.adress}" id="placeAdress" type="text" name="placeAdress">
            <label class="active" for="placeAdress">Place address:</label>
        </div>

        <div class="row">
            <input value="${editPlace.latitude}" id="placeLatitude" type="text" name="placeLatitude">
            <label class="active" for="placeLatitude">Place latitude:</label>
        </div>

        <div class="row">
            <input value="${editPlace.longitude}" id="placeLongitude" type="text" name="placeLongitude">
            <label class="active" for="placeLongitude">Place longitude:</label>
        </div>

        <div class="row">
            <input value="${editPlace.place_time}" id="place_time" type="text" class="validate" name="place_time">
            <label class="active" for="place_time">Place time:</label>
        </div>

        <div class="row">
            <input value="${placeRating.rating}" id="placeRating" type="text" class="validate" name="placeRating">
            <label class="active" for="placeRating">Place rating:</label>
        </div>

        <div class="form-group">
          <label class="col-md-3 control-label"></label>
          <div class="col-md-8">
            <button class="btn waves-effect waves-light" type="submit" name="save">Save Changes
              <i class="mdi-content-send right"></i>way_response
            </button>
            <span></span>
            <button class="btn waves-effect waves-light" type="reset" name="cancel">Cancel</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
<hr>
<jsp:include page="/views/elements/footer.jsp"/>
</body>
</html>
