
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib prefix="cdg" uri="customtags" %>--%>
<%--<!DOCTYPE HTML>--%>
<%--<html>--%>
<%--<head>--%>

<%--</head>--%>
<%--<body>--%>



<%--&lt;%&ndash;<c:forEach var="placeDescriptions" items="${placeDescriptions}">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<c:forEach var="places" items="${places}">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<c:forEach var="placeImages" items="${placeImages}">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<c:if test="${places.id==placeDescriptions.place_id}">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<c:if test="${placeImages.place_id == places.id}">&ndash;%&gt;--%>
              <%--&lt;%&ndash;<h5><c:out value="${placeDescriptions.name}"/></h5><br>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<img src="${pageContext.request.contextPath}/upload/photo/${placeImages.reference}" alt="" height="100" width="100"></a><br>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<h5><c:out  value="${placeDescriptions.name}"/></h5><br>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<c:out value="${places.adress}"/><br>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
<%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>

<%--</body>--%>
<%--</html>--%>

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

<div class="places">
    <div class="section">
        <h3 class="center-align"><cdg:l18n key="places.head"/></h3>

        <div class="row">
            <div class="col l9 m8 s7">
                <div class="row z-depth-2">

                    <c:forEach var="place" items="${requestScope.places}">
                        <div class="match-col col l4 m6 s12">
                            <div class="card z-depth-2" style="padding:10px; height:95%">
                                <form action="portal?command=place&place_id=${place.id}&category=${requestScope.category}"
                                      method="post" style="position:absolute;padding:5px">
                                    <c:set var="category" scope="request" value="${requestScope.category}"/>
                                        <button class="btn modal-trigger btn-floating btn-large waves-effect waves-light red darken-2"
                                                type="submit" data-target="chooseDay"
                                                id="btn1" onclick="$('#place_id').val('${place.id}')">
                                            <i class="mdi-content-sub"></i>
                                        </button>
                                </form>


                                <a href="portal?command=placeInformation&place_id=${place.id}"><img
                                        src="${pageContext.request.contextPath}/upload/photo/${place.imageReference}"
                                        style="width: 100%"></a>
                                <a href="portal?command=placeInformation&place_id=${place.id}">
                                    <h5>
                                        <c:out
                                                value="${place.name}"/></h5></a>
                                <c:out value="${place.adress}"/>
                                <div class="right-align">
                                </div>
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



<div id="chooseDay" class="modal">
    <div class="modal-content">
        <p><cdg:l18n key="sure.delete"/></p>
        <form id="sure" action="/portal/deletePlace" method="post">
            <input type="hidden" name="command" value="deletePlace">

            <div class="input-field col s6">
                <input placeholder="Placeholder" id="place_id" name="place_id" type="hidden">
            </div>



            <button class="btn waves-effect waves-light cyan darken-2" type="submit" value="true" name="yes"
                    >OK
            </button>

            <button class="btn waves-effect waves-light cyan darken-2" type="submit" value="false" name="no"
                    >No
            </button>
        </form>

    </div>
    <div class="modal-footer">

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
