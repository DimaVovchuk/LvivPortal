<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><cdg:l18n key="places.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            $("#btn1").click(function(){
                $("#category").text("");
            });
            $("#btn2").click(function(){
                $("#test2").html("<b>Hello world!</b>");
            });
            $("#btn3").click(function(){
                $("#test3").val("Dolly Duck");
            });
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
                <div class="row z-depth-2">

                    <c:forEach var="place" items="${requestScope.places}">
                        <div class="match-col col l4 m6 s12">
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

                                <form action="/portal/saveWay" method="get">
                                    <input type="hidden" name="command" value="saveWay">
                                    <button class="btn waves-effect waves-light cyan darken-2" type="submit"
                                            >OK
                                    </button>
                                </form>


                                <a href="portal?command=placeInformation&place_id=${place.id}"><img
                                        src="${pageContext.request.contextPath}/upload/photo/${place.imageReference}"
                                        style="width: 100%"></a>
                                <a href="portal?command=placeInformation&place_id=${place.id}"><h5>
                                    <c:out
                                            value="${place.name}"/></h5></a>
                                <c:out value="${place.adress}"/>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div class="col l3 m4 s5">
                <div class="collection with-header z-depth-2">
                    <div class="collection-header"><h4><cdg:l18n key="places.categories"/></h4></div>
                    <a href="portal?command=place&category=architecture" class="collection-item black-text"><cdg:l18n
                            key="places.architecture"/></a>
                    <a href="portal?command=place&category=churches" class="collection-item black-text"><cdg:l18n
                            key="places.churches"/></a>
                    <a href="portal?command=place&category=theatres" class="collection-item black-text"><cdg:l18n
                            key="places.theatres"/></a>
                    <a href="portal?command=place&category=hotels" class="collection-item black-text"><cdg:l18n
                            key="places.hotels"/></a>
                    <a href="portal?command=place&category=restaurants" class="collection-item black-text"><cdg:l18n
                            key="places.restaurants"/></a>
                    <a href="portal?command=place" class="collection-item black-text"><cdg:l18n key="places.all"/></a>
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
<h4 id="ID"></h4>
    <c:set var="category" scope="request" value="${requestScope.category}"/>
<h4>Choose day</h4>
    <form id="choose_day" action="/portal/place" method="get">
        <input type="hidden" name="command" value="place">
        <div class="input-field col s6">
            <input placeholder="Placeholder" id="place_id" name="place_id" type="hidden">
        </div>
        <div class="input-field col s6">
            <input placeholder="Placeholder" name="category" type="hidden" value="${requestScope.category}">
        </div>
        <label>Browser Select</label>
        <select name="dayNumber" class="browser-default">
            <option value="" disabled selected>Choose your option</option>
                <c:forEach var="i" begin="1" end="${userDataTrip.dayCount}">
                    <option width="10px" value="${i}"><c:out value="${i}"/></option>
                </c:forEach>
            </select>
        <button class="btn waves-effect waves-light cyan darken-2" type="submit"
                >OK
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