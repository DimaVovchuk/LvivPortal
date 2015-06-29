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

          <div id="recomended-place-info-collection" class="place-page-content">

            <%--<div ></div>--%>

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


<%--<script src="${pageContext.request.contextPath}/js/pages/places.js"></script>--%>
<script src="${pageContext.request.contextPath}/js/pages/recomended-place.js"></script>



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

<script id="recomended-place-info-template" type="text/x-handlebars-template">
  {{#each this}}
  <div class="match-col col l4 m6 s12">
    <div class="card z-depth-2" style="padding:10px; height:95%">
      {{#with userDataTrip}}
      <button class="btn modal-trigger btn-floating btn-large waves-effect waves-light cyan darken-2"
              type="submit" data-target="chooseDay"
              id="btn1" onclick="$('#place_id').val('{{id}}')">
        <i class="mdi-content-add"></i>
      </button>
      {{/with}}

      <div class="center-align">
        <a href="portal?command=placeInformation&place_id={{id}}"><img
                class="responsive-img"
                src="${pageContext.request.contextPath}/upload/photo/{{imageReference}}"></a>
        <a href="portal?command=placeInformation&place_id={{id}}">
          <h5><c:out value="{{name}}"/></h5></a>
        <span><c:out value="{{adress}}"/></span>
      </div>
    </div>
  </div>
  {{/each}}
</script>


</body>
</html>