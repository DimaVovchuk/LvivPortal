<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 24.06.2015
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<div class="full-height">
<div class="user-places" id="user-places">
    <div class="places">
        <div class="section">
    <h3 class="center-align"><cdg:l18n key="way.head"/></h3>

    <div class="row">
        <div class="col l9 m8 s7">

            <c:choose>
                <c:when test="${waysPlaceImage != null}">

                    <%--<c:forEach var="way" items="${requestScope.waysPlaceImage}">--%>
                    <c:forEach var="i" begin="0" end="${fn:length(waysPlaceImage) - 1}">
                        <div class="match-col col l4 m6 s9">
                            <div class="card z-depth-2" style="padding:10px; height:95%">


                                <div class="card-image waves-effect waves-block waves-light">
                                    <a onClick="recommendWay(this);" class="btn-on-img btn-floating btn-large waves-effect waves-light yellow darken-2"
                                       href="javascript:" rel="/portal?command=recommendWay&way_id=${waysPlaceImage[i].id}">
                                        <i class="material-icons">grade</i>
                                    </a>
                                    <img class="activator responsive-img place-img"
                                         src="${pageContext.request.contextPath}/upload/photo/${waysPlaceImage[i].imageReference}"
                                         style="width: 100%">
                                </div>
                                <div class="card-content">
                                    <span class="card-title activator grey-text text-darken-4"><div align="center">
                                        <h6>${waysPlaceImage[i].beginDate} - ${waysPlaceImage[i].endDate}</h6>
                                    </div></span>
                                    <p><a a class="modal-trigger" onclick="$('#way_id').val('${waysPlaceImage[i].id}')" href="#get-direction"><cdg:l18n key="get.directions"/></a></p>
                                </div>
                                <div class="card-reveal">
                                    <span class="card-title grey-text text-darken-4"><cdg:l18n key="places.way"/></span>

                                    <p>
                                        <c:forEach var="place" items="${waysPlaceImage[i].place}">

                                    <div class=" match-colum col l6 m9 s18">
                                        <a href="portal?command=placeInformation&place_id=${place.place_id}"><c:out
                                                value="${place.name}"/></a>
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
                </c:when>
                <c:otherwise>
                    <div align="center">
                        <h3><cdg:l18n key="any.way.db"/></h3>
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
</div>

        <div id="get-direction" class="modal">
            <div class="modal-content">
                <p><cdg:l18n key="sure.get.direction"/></p>
                <form id="sure" action="/portal/createUserDataFromDB" method="post">
                    <input type="hidden" name="command" value="createUserDataFromDB">

                    <div class="input-field col s6">
                        <input placeholder="Placeholder" id="way_id" name="way_id" type="hidden">
                    </div>

                    <div class="ok-footer" id="cancel-route">
                        <div class="modal-footer">
                            <button class="btn waves-effect waves-light cyan darken-2" type="submit">Ok
                            </button>
                            <a class="modal-action modal-close btn waves-effect waves-light cyan darken-2"
                                    ><cdg:l18n key="cancel"/>
                            </a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
</div>
<jsp:include page="/views/elements/footer.jsp"/>
<jsp:include page="/views/modals/sure-save-way.jsp"/>
<jsp:include page="/views/modals/name-way.jsp"/>

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
    var recommendResultWay = function (data) {
        alert(data);
        if (data == "1"){
            Materialize.toast('<cdg:l18n key="way.recommended"/>', 4000);
        }
        else{
            Materialize.toast('<cdg:l18n key="way.not.recommended"/>', 4000);
        }
    }
</script>

<script src="${pageContext.request.contextPath}/js/pages/places.js"></script>
<script src="${pageContext.request.contextPath}/js/pages/userAllWay.js"></script>

</body>
</html>
