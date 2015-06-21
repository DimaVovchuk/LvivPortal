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

      <form id="update-profile" action="portal/updateplace" metod="post">
        <input type="hidden" name="command" value="updateprofile">
        <c:forEach items="${placeDescriptionList}" var="elem">
          <div class="row">
            <input value="${elem.name}" id="placename" type="text" class="validate" name="name">
            <label class="active" for="placename">Place name:</label>
          </div>
        </c:forEach>

        <c:forEach items="${placeDescriptionList}" var="elem">
          <div class="row">
            <input value="${elem.description}" id="placedescription" type="text" class="validate" name="description">
            <label class="active" for="placedescription">Place description:</label>
          </div>
        </c:forEach>

        <div class="row">
            <input value="${editPlace.adress}" id="adress" type="text" class="validate" name="adress">
            <label class="active" for="adress">Place address:</label>
        </div>

        <div class="row">
            <input value="${editPlace.latitude}" id="latitude" type="text" class="validate" name="latitude">
            <label class="active" for="latitude">Place latitude:</label>
        </div>

        <div class="row">
            <input value="${editPlace.longitude}" id="longitude" type="text" class="validate" name="longitude">
            <label class="active" for="longitude">Place longitude:</label>
        </div>

        <div class="row">
            <input value="${editPlace.place_time}" id="place_time" type="text" class="validate" name="place_time">
            <label class="active" for="place_time">Place time:</label>
        </div>

        <div class="row">
            <input value="${placeRating.rating}" id="rating" type="text" class="validate" name="rating">
            <label class="active" for="rating">Place rating:</label>
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
