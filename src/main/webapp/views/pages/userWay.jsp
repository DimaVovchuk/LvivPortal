<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<div class="full-height">
<div class="user-places" id="user-places">
    <div class="places">
        <div class="section">
            <h3 class="center-align"><cdg:l18n key="way.head"/></h3>

            <div class="row">
                <div class="col l9 m8 s7">
                    <c:choose>
                        <c:when test="${requestScope.daysPlaceImage != null}">
                            <%--<c:choose>--%>
                            <%--<c:when test="${size > 1}">--%>

                            <%--<c:forEach var="day" items="${daysPlaceImage}">--%>
                            <c:forEach var="i" begin="0" end="${fn:length(daysPlaceImage)- 1}">
                                <div class="match-col col l4 m6 s9">
                                    <div class="card z-depth-2" style="padding:10px; height:95%">


                                        <div class="card-image waves-effect waves-block waves-light">
                                            <a class="btn-floating btn-large waves-effect waves-light red darken-2"
                                               href="/portal?command=deleteDay&day=${daysPlaceImage[i].day}" >
                                                <i class="material-icons">delete</i>
                                            </a>
                                            <img class="activator"
                                                 src="${pageContext.request.contextPath}/upload/photo/${daysPlaceImage[i].imageReference}"
                                                 style="width: 100%">
                                        </div>
                                        <div class="card-content">
                                            <span class="card-title activator grey-text text-darken-4"><div
                                                    align="center"><h5><cdg:l18n key="day.number"/>
                                                - ${daysPlaceImage[i].day}</h5></div></span>
                                        </div>
                                        <div class="card-reveal">
                                            <span class="card-title grey-text text-darken-4"><cdg:l18n
                                                    key="day.number"/> - ${daysPlaceImage[i].day}</span>

                                            <p>
                                                <c:forEach var="place_image" items="${daysPlaceImage[i].placeImage}">

                                            <div class=" match-colum col l6 m9 s18">
                                                <a href="/portal?command=placeInformation&place_id=${place_image.place_id}"><img
                                                        src="${pageContext.request.contextPath}/upload/photo/${place_image.imageReference}"
                                                        style="width: 100%"></a>
                                            </div>
                                            <div class="match-colum col l4 m6 s12">
                                                <a href="/portal?command=placeInformation&place_id=${place_image.place_id}">
                                                    <c:out value="${place_image.name}"/></a>
                                            </div>
                                            <div class="match-colum col l2 m3 s6" id="close-png">
                                                <a href="/portal?command=deletePlaceFromTrip&place_id=${place_image.place_id}&day=${daysPlaceImage[i].day}">
                                                    <img src="${pageContext.request.contextPath}/upload/photo/close.png"
                                                            style="width: 20px; height: 20px; float: right;">
                                                    </a>
                                            </div>
                                            </c:forEach>
                                            <div class="bottom-left-btn">
                                                <a class="btn-floating btn-tiny waves-effect waves-light cyan darken-2" href="/portal?command=place">
                                                    <i class="mdi-content-add"></i>
                                                </a>
                                                </div>
                                            </p>
                                        </div>


                                    </div>
                                </div>

                            </c:forEach>
                        <div class="match-col col l4 m6 s9">
                            <a class="btn-floating btn-large waves-effect waves-light cyan darken-2"
                                    href="/portal?command=addDay">
                                <i class="mdi-content-add"></i>
                            </a>
                        </div>

                        </c:when>
                        <c:otherwise>
                            <div align="center">
                                <h3><cdg:l18n key="any.way"/></h3>
                            </div>
                        </c:otherwise>
                    </c:choose>


                </div>


                <div class="col l3 m4 s5">

                    <div class="collection with-header z-depth-2">
                        <div class="collection-header"><h4><cdg:l18n key="ways"/></h4></div>
                        <a href="/portal?command=userWays"
                           class="collection-item black-text ${requestScope.active_architecture}"><cdg:l18n
                                key="current.route"/></a>
                        <a href="/portal?command=userAllWay"
                           class="collection-item black-text ${requestScope.active_churches}"><cdg:l18n
                                key="get.all.route"/></a>
                        <a href="#sure-save"
                           class="modal-trigger collection-item black-text ${requestScope.active_theatres}"><cdg:l18n
                                key="save.way"/></a>


                    </div>

                </div>

            </div>
        </div>
    </div>

    <div id="sure-save" class="modal">
        <div id="user-trip-save" class="modal-content">
            <c:choose>
                <c:when test="${userDataTrip.isSaved == false && userDataTrip.isFull != false}">
                    <p><cdg:l18n key="sure.save"/></p>

                    <div class="ok-footer">
                        <div class="modal-footer">
                            <a class="btn waves-effect waves-light cyan darken-2" href="/portal?command=saveWay">Ok
                            </a>
                        </div>
                    </div>
                </c:when>
                <c:when test="${userDataTrip == null}">
                    <p><cdg:l18n key="any.current.route"/></p>

                    <div class="ok-footer">
                        <div class="modal-footer">
                            <a class="modal-action modal-close btn waves-effect waves-light cyan darken-2">Ok
                            </a>
                        </div>
                    </div>
                </c:when>
                <c:when test="${userDataTrip.isFull == false}">
                    <p><cdg:l18n key="any.place.way"/></p>

                    <div class="ok-footer">
                        <div class="modal-footer">
                            <a class="modal-action modal-close btn waves-effect waves-light cyan darken-2">Ok
                            </a>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="ok-footer">
                        <div class="modal-footer">
                            <p><cdg:l18n key="sure.already.saved"/></p>
                            <a class="btn waves-effect waves-light cyan darken-2" href="/portal?command=updateWay">
                                <cdg:l18n key="update"/>
                            </a>
                            <a class="btn waves-effect waves-light cyan darken-2" href="/portal?command=saveWay">
                                <cdg:l18n key="save"/>
                            </a>
                            <a class="modal-action modal-close btn waves-effect waves-light cyan darken-2">
                                <cdg:l18n key="cancel"/>
                            </a>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
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

    <script>
        $(".match-colum").matchHeight({
            property: 'height'
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

