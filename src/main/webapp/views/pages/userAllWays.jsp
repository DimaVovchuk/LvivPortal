<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 24.06.2015
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>
<html>
<head>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <title><cdg:l18n key="way.place"/></title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta name="keywords" content=""/>
</head>

<jsp:include page="/views/elements/css.jsp"/>
<jsp:include page="/views/elements/script.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<h3 class="center-align"><cdg:l18n key="way.head"/></h3>

<div class="row">
  <div class="col l9 m8 s7">

    <c:forEach var="way" items="${requestScope.waysPlaceImage}">
      <div class="match-col col l4 m6 s9" style="display:none">
        <div class="card z-depth-2" style="padding:10px; height:95%">


          <div class="card-image waves-effect waves-block waves-light">
            <img class="activator"
                 src="${pageContext.request.contextPath}/upload/photo/${way.imageReference}"
                 style="width: 100%">
          </div>
          <div class="card-content">
            <span class="card-title activator grey-text text-darken-4"><div align="center"><h6>${way.beginDate} - ${way.endDate}</h6></div></span>
          </div>
          <div class="card-reveal">
            <span class="card-title grey-text text-darken-4"><cdg:l18n key="day.number"/></span>
            <p>
              <c:forEach var="place" items="${way.place}">
            <div class=" match-colum col l6 m9 s18">
              <a href="portal?command=placeInformation&place_id=${place.place_id}"><c:out value="${place.name}"/></a>
            </div>
            <div class="match-colum col l6 m9 s18">
              <%--<a href="portal?command=placeInformation&place_id=${place_image.place_id}">--%>
                <%--<c:out value="${place_image.name}"/></a>--%>
            </div>
            </c:forEach>
            </p>
          </div>



        </div>
      </div>

    </c:forEach>
  </div>


  <div class="col l3 m4 s5">

    <div class="collection with-header z-depth-2">
      <div class="collection-header"><h4><cdg:l18n key="places.categories"/></h4></div>
      <a href="/portal?command=userWays"
         class="collection-item black-text ${requestScope.active_architecture}"><cdg:l18n
              key="current.route"/></a>
      <a href="/portal?command=userAllWay"
         class="collection-item black-text ${requestScope.active_churches}"><cdg:l18n
              key="get.all.route"/></a>
      <a href="/portal?command=saveWay"
         class="collection-item black-text ${requestScope.active_theatres}"><cdg:l18n
              key="save.way"/></a>
      <a href=""
         class="collection-item black-text ${requestScope.active_hotels}"><cdg:l18n
              key="set.route"/></a>
    </div>


  </div>


</div>

<jsp:include page="/views/elements/footer.jsp"/>

<script>
  $(".match-col").matchHeight({
    property: 'height'
  });
</script>

<script>
  $(".match-colum").matchHeight({
    property: 'height'
  });
</script>

<script>
  $(".match-wei").matchWeight({
    property: 'weight'
  });
</script>

<script>
  $('.datepicker').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 15 // Creates a dropdown of 15 years to control year
  });
</script>

<script>
  function myFunction() {
    var x = document.getElementById("ID").value;
    document.getElementById("demo").innerHTML = x;
  }
</script>

<script src="${pageContext.request.contextPath}/js/pages/places.js"></script>

</body>
</html>
