<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>

<head>
    <title><cdg:l18n key="places.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
</head>

<jsp:include page="/views/elements/css.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div id="place-info">
    <div class="section">
        <div class="row" style="margin: 0">
            <div class="col l9 m8 s7">
                <h3 class="center-align">${placeDescription.name}</h3>
            </div>
        </div>
        <div class="row" style="margin-left: 20px">
            <div class="col l9 m8 s7">

                <div class="card">
                    <div class="fotorama" data-transition="crossfade" data-nav="thumbs" data-loop="true"
                         data-autoplay="true" data-allowfullscreen="true" data-keyboard="true" data-width="100%"
                         data-height="70%">
                        <c:forEach items="${place_referenceList}" var="elem">
                            <c:choose>
                                <c:when test="${not empty elem.reference}">
                                    <img class="materialboxed" width="200" height="200"
                                         src="${pageContext.request.contextPath}/upload/photo/${elem.reference}">
                                </c:when>
                                <c:otherwise>
                                    <img class="materialboxed" width="200" height="200"
                                         src="${pageContext.request.contextPath}/upload/photo/default_building.jpg">
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                    </div>
                    <div class="section">
                        <p>${placeDescription.description}</p>
                        <c:if test="${placeDescription.phone!=null}">
                            <h5><cdg:l18n key="place.phone"/></h5>
                            <c:forEach var="infoPlacePhone" items="${infoPlacePhone}">
                                <c:out value="${infoPlacePhone}"/>
                            </c:forEach>
                        </c:if>
                        <c:if test="${placeDescription.price!=null}">
                            <h5><cdg:l18n key="place.prise"/></h5>
                            <c:forEach var="infoPlacePrice" items="${infoPlacePrice}">
                                <c:out value="${infoPlacePrice}"/><br>
                            </c:forEach>
                        </c:if>
                        <h5><cdg:l18n key="place.address"/></h5>

                        <p>${placeDescription.adress}</p>
                    <c:if test="${login!=null}">
                        <div class="bottom-right-btn form" id="like-place-info" align="right">
                            <a onClick="like(this);" data-id="${placeDescription.place_id}" data-rating="${rating}" id="up" class="up btn-floating btn-floating btn-small"
                               href="javascript:" rel="/portal?command=rectRating&rating=1&place_id=${placeDescription.place_id}">
                                <i class="material-icons">thumb_up</i>
                            </a>

                            <a onClick="none(this);" data-id="${placeDescription.place_id}" id="none"class="none btn-floating btn-floating btn-small"
                               href="javascript:" rel="/portal?command=rectRating&rating=0&place_id=${placeDescription.place_id}">
                                <i class="material-icons">thumbs_up_down</i>
                            </a>

                            <a onClick="dislike(this);" data-id="${placeDescription.place_id}" id="down" class="down btn-floating btn-floating btn-small"
                               href="javascript:" rel="/portal?command=rectRating&rating=-1&place_id=${placeDescription.place_id}">
                                <i class="material-icons">thumb_down</i>
                            </a>
                        </div>
</c:if>
                    </div>
                </div>
                <c:if test="${sessionScope.userDataTrip != null}">
                    <a class="waves-effect waves-light btn modal-trigger cyan darken-2" href="#chooseDayRecomended"
                       onclick="$('#place_id').val('${place.id}')">
                        <cdg:l18n key="place.add2route"/>
                    </a>
                </c:if>
                <c:if test="${sessionScope.login != null}">
                    <a class="waves-effect waves-light btn modal-trigger cyan darken-2"
                       href="portal?command=savePlace&place_id=${place_id}"><cdg:l18n key="place.favorite"/></a>
                </c:if>
                <c:if test="${sessionScope.role == 1}">
                    <button class="waves-effect waves-light btn modal-trigger cyan darken-2" data-target="editplace"
                            onclick="$('editPlaceID').val('${place_id}')">
                        <cdg:l18n key="button.edit"/>
                    </button>
                </c:if>
            <c:if test="${login!=null}">
                <div class="card">
                    <h5><cdg:l18n key="place.comment"/></h5>

                    <form action="portal/placeInformation" method="post">
                        <input type="hidden" name="command" value="placeInformation">
                        <input type="hidden" name="place_id" value="${place_id}">
                        ${not_login}
                        <div class="input-field">
                            <textarea id="textarea" name="message" value="${message}"
                                      class="materialize-textarea"></textarea>
                            <label for="textarea"><cdg:l18n key="place.message"/></label>
                        </div>
                        <%--<div class="form">--%>
                            <%--<span><cdg:l18n key="place.rating"/></span>--%>
                            <%--<input type="radio" name="rating" value="1" id="rating-up">--%>
                            <%--<label for="rating-up">--%>
                                <%--<span class="btn-floating btn-small waves-effect waves-light">--%>
                                    <%--<i class="material-icons">thumb_up</i>--%>
                                <%--</span>--%>
                            <%--</label>--%>
                            <%--<input type="radio" name="rating" value="-1" id="rating-down">--%>
                            <%--<label for="rating-down">--%>
                                <%--<span class="btn-floating btn-small waves-effect waves-light">--%>
                                    <%--<i class="material-icons">thumb_down</i>--%>
                                <%--</span>--%>
                            <%--</label>--%>
                        <%--</div>--%>
                        <div class="section">
                            <div class="divider"></div>
                        </div>
                        <button type="submit" class="waves-effect waves-light btn cyan darken-2"><cdg:l18n
                                key="place.sendcomment"/></button>
                    </form>
                </div>
            </c:if>

                <c:forEach var="response" items="${userImageDescription}">

                <div class="card">
                <div class="valign-wrapper">
                <div class="valign">

                <a href="#"><img
                src="${pageContext.request.contextPath}/upload/photo/${response.reference}"
                class="avatar"/></a>

                </div>
                <div class="valign">
                <a href="#"><h5><c:out value="${response.login}"/></h5></a>

                <p><c:out value="${response.description}"/></p>

                </div>
                </div>
                </div>

                </c:forEach>



            </div>

        </div>
    </div>
</div>


<div id="editplace" class="modal">
    <div class="modal-content">
        <h5><cdg:l18n key="info.edit.title"/></h5>

        <form id="edit_place" action="/portal?command=editPlace" method="post">
            <input type="hidden" name="command" value="editPlace">
            <input placeholder="Placeholder" id="editPlaceID" name="editPlaceID" type="hidden" value="${place.id}">
            <button class="btn waves-effect waves-light cyan darken-2" type="submit"><cdg:l18n key="yes"/></button>
            <button type="reset" class="btn modal-close waves-effect waves-light cyan darken-2"><cdg:l18n key="no"/></button>
        </form>

    </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>
<%--<jsp:include page="/views/modals/add-place-to-route.jsp"/>--%>
<jsp:include page="/views/modals/add-place-to-route-recomended.jsp"/>
<script src="${pageContext.request.contextPath}/js/pages/info.js"></script>

<script>
    var loadWindow = function (data) {
        if (data == "1"){
            Materialize.toast('<cdg:l18n key="place.added"/>', 4000);
        }
        else{
            Materialize.toast('<cdg:l18n key="place.is.db"/>', 4000);
        }
    }
</script>

</body>
</html>
