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
    <script>
        $("#timePlace").mousemove( function(e){
            $("#timeValue").html($(this).val());
        });
        $("#timePlace").change( function(e){
            $("#timeValue").html($(this).val());
        });
    </script>
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

                    <div class="place-page-content">

                        <c:forEach var="place" items="${requestScope.places}">
                            <div class="match-col col l4 m6 s12" style="display:none">
                                <div class="card z-depth-2" style="padding:10px; height:95%">
                                    <form action="portal?command=place&place_id=${place.id}&category=${requestScope.category}"
                                          method="post" style="position:absolute;padding:5px">
                                        <c:set var="category" scope="request" value="${requestScope.category}"/>
                                        <c:if test="${userDataTrip!=null}">
                                            <button class="btn modal-trigger btn-floating btn-large waves-effect waves-light cyan darken-2"
                                                    type="submit" data-target="chooseDay"
                                                    id="btn1" onclick="$('#place_id').val('${place.id}')">
                                                <i class="mdi-content-add"></i>
                                            </button>
                                        </c:if>
                                    </form>
                                    <a href="portal?command=placeInformation&place_id=${place.id}"><img
                                            src="${pageContext.request.contextPath}/upload/photo/${place.imageReference}"
                                            style="width: 100%"></a>
                                    <a href="portal?command=placeInformation&place_id=${place.id}">
                                        <h5><c:out value="${place.name}"/></h5></a>
                                    <c:out value="${place.adress}"/>
                                    <c:if test="${login!=null}">
                                        <div class="right-align">
                                            <c:if test="${place.rating!=1}">
                                        <a class="btn-floating btn-large waves-effect waves-light cyan darken-2"
                                           href="portal?command=rectRating&place_id=${place.id}&category=${requestScope.category}">
                                            <i class="material-icons">thumb_up</i>
                                        </a>
                                            </c:if>
                                            <c:if test="${place.rating!=-1}">
                                            <a class="btn-floating btn-large waves-effect waves-light cyan darken-2"
                                           href="portal?command=rectRating&place_id=${place.id}&category=${requestScope.category}">
                                            <i class="material-icons">thumb_down</i>
                                        </a>
                                            </c:if>
                                            </div>
                                </c:if>

                                </div>
                            </div>
                        </c:forEach>

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

<div id="chooseDay" class="modal">
    <div class="modal-content">
        <h4 id="ID"></h4>
        <c:set var="category" scope="request" value="${requestScope.category}"/>
        <h4>Choose day</h4>

        <form id="choose_day" action="/portal/place" method="get">
            <input type="hidden" name="command" value="place">

            <div class="input-field col s6">
                <input placeholder="Placeholder" id="place_id" name="place_id" type="hidden">
            </div>

        <label><cdg:l18n key="select.day.number"/></label>
        <select name="dayNumber" class="browser-default">
                <c:forEach var="i" begin="1" end="${userDataTrip.dayCount}">
                    <option width="10px" value="${i}"><c:out value="${i}"/></option>
                </c:forEach>
            </select>
        <label><cdg:l18n key="select.place.time"/></label>
        <p class="range-field">
            <input type="range" name="timePlace" id="timePlace" min="15" value="15" step="15" max="240" />
        <p id="timeValue"></p>
        </p>

        <button class="btn waves-effect waves-light cyan darken-2" type="submit"
                >OK
        </button>
    </form>

</div>
<div class="modal-footer">

</div>
    </div>

<script src="${pageContext.request.contextPath}/js/pages/places.js"></script>

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

<script>
    $("#timePlace").mousemove(function (e) {
        $("#timeValue").html($(this).val());
    });
    $("#timePlace").change(function (e) {
        $("#timeValue").html($(this).val());
    });
</script>

</body>
</html>