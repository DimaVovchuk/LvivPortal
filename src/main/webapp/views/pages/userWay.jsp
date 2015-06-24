<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib prefix="cdg" uri="customtags" %>--%>
<%--<!DOCTYPE HTML>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">--%>
    <%--<title><cdg:l18n key="places.title"/></title>--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>--%>
    <%--<meta name="keywords" content=""/>--%>
    <%--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>--%>
<%--</head>--%>

<%--<jsp:include page="/views/elements/css.jsp"/>--%>
<%--<jsp:include page="/views/elements/script.jsp"/>--%>

<%--<body>--%>

<%--<jsp:include page="/views/elements/header.jsp"/>--%>


<%--<c:forEach var="ways" items="${ways}">--%>
  <%--<c:forEach items="${wayPlaceImages}" var="wayPlaceImages">--%>
    <%--<c:forEach items="${way_place}" var="way_place">--%>
      <%--<c:if test="${ways.id == way_place.key}">--%>
        <%--<c:if test="${ways.id == wayPlaceImages.key}">--%>
                <%--<a href="#">--%>
                  <%--<img src="${pageContext.request.contextPath}/upload/photo/${wayPlaceImages.value.reference}"--%>
                       <%--alt="" height="100" width="100">--%>
                <%--</a>--%>
              <%--<c:forEach items="${way_place.value}" var="place">--%>
                <%--<a href="portal?command=placeInformation&place_id=${place.place_id}">--%>
                  <%--<h5>${place.name}</h5></a><i class="fa fa-arrow-right"></i>--%>
              <%--</c:forEach>--%>
          <%--</div>--%>
        <%--</c:if>--%>
      <%--</c:if>--%>
    <%--</c:forEach>--%>
  <%--</c:forEach>--%>
<%--</c:forEach>--%>

<%--</body>--%>
<%--</html>--%>


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

<div class="places">
    <div class="section">
        <h3 class="center-align"><cdg:l18n key="way.head"/></h3>

        <div class="row">
            <div class="col l9 m8 s7">
                <div class="row z-depth-2">

                    <c:forEach var="day" items="${requestScope.daysPlaceImage}">
                        <div class="match-col col l12 m18 s36">

                            <c:forEach var="place_image" items="${day.placeImage}">
                                <div class="match-col col l3 m4 s9">
                                    <div class="card" style="padding:5px; height:90%">
                                        <div class="routs">
                                            <a class="modal-trigger" href="place-info"><img
                                                    src="${pageContext.request.contextPath}/upload/photo/${place_image.imageReference}"
                                                    style="width: 100%"></a>
                                            <div align="center">
                                            <a class="modal-trigger" href="place-info"
                                               onclick="$('place_description').val('${place_image.description}');
                                                       $('image').val('${pageContext.request.contextPath}/upload/photo/${place_image.imageReference}')">
                                                <p><c:out value="${place_image.name}"/></p></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                                <div class="right-align">
                                </div>

                        </div>
                    </c:forEach>
                </div>
            </div>

        </div>
    </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>

<script>
    $(".match-col").matchHeight({
        property: 'height'
    });
</script>



<div id="place-info" class="modal">
    <div class="modal-content">
        <div class="card">
            <div class="card-image waves-effect waves-block waves-light">
                <img class="activator" id="image">
            </div>
            <div class="card-content">
                <%--<span class="card-title activator grey-text text-darken-4">Card Titlemore_vert</i></span>--%>
                <p><a href="#">This is a link</a></p>
            </div>
            <div class="card-reveal">
                <%--<span class="card-title grey-text text-darken-4">Card Titleclose</i></span>--%>
                <p>Here is some more information about this product that is only revealed once clicked on.</p>
            </div>
        </div>
            <div class="modal-footer">

            </div>
    </div>
</div>



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

</body>
</html>

