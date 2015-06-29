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
                            <img class="materialboxed" width="200" height="200"
                                 src="${pageContext.request.contextPath}/upload/photo/${elem.reference}">
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

                        <p>${place.adress}</p>
                    </div>
                </div>
                <div class="card">
                    <a class="waves-effect waves-light btn modal-trigger cyan darken-2" href="#choose-day">Choose
                        day</a>
                    <a class="waves-effect waves-light btn modal-trigger cyan darken-2"
                       href="portal?command=savePlace&place_id=${place_id}">Favorite place</a>
                    <button class="waves-effect waves-light btn modal-trigger cyan darken-2" data-target="editplace"
                            onclick="$('editPlaceID').val('${place_id}')">
                        Edit place
                    </button>
                </div>

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
                        <div class="form">
                            <span><cdg:l18n key="place.rating"/></span>
                            <input type="radio" name="rating" value="1" id="rating-up">
                            <label for="rating-up">
                                <span class="btn-floating btn-small waves-effect waves-light">
                                    <i class="material-icons">thumb_up</i>
                                </span>
                            </label>
                            <input type="radio" name="rating" value="-1" id="rating-down">
                            <label for="rating-down">
                                <span class="btn-floating btn-small waves-effect waves-light">
                                    <i class="material-icons">thumb_down</i>
                                </span>
                            </label>
                        </div>
                        <div class="section">
                            <div class="divider"></div>
                        </div>
                        <button type="submit" class="waves-effect waves-light btn cyan darken-2"><cdg:l18n key="place.sendcomment"/></button>
                    </form>
                </div>

                <c:forEach var="users" items="${users}">
                    <c:forEach var="placeResponse" items="${placeResponse}">
                        <c:if test="${users.id==placeResponse.user_id}">
                            <div class="card">
                                <div class="valign-wrapper">
                                    <div class="valign">
                                        <c:forEach var="userImages" items="${userImages}">
                                            <c:if test="${users.id==userImages.user_id}">
                                                <a href="#"><img
                                                        src="${pageContext.request.contextPath}/upload/photo/${userImages.reference}"
                                                        class="avatar"/></a>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                    <div class="valign">
                                        <a href="#"><h5><c:out value="${users.login}"/></h5></a>

                                        <p><c:out value="${placeResponse.description}"/></p>
                                        <c:forEach var="placeRatings" items="${placeRatings}">
                                            <c:if test="${users.id==placeRatings.user_id}">
                                                <p><c:out value="${placeRatings.rating}"/></p>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:forEach>
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

<div id="choose-day" class="modal">
    <div class="modal-content">
        <h5 class="center-align">Choose day</h5>

        <form id="choose_day" action="/portal?command=placeInformation" method="post">
            <input type="hidden" name="command" value="placeInformation">
            <input type="hidden" name="place_id" value="${place.id}">
            <select name="dayNumber">
                <option disabled selected>Choose your option</option>
                <c:forEach var="i" begin="1" end="${userDataTrip.dayCount}">
                    <option width="10px" value="${i}"><c:out value="${i}"/></option>
                </c:forEach>
            </select>
            <br>
            <button class="btn waves-effect waves-light cyan darken-2" type="submit">
                OK
            </button>
        </form>
        <br>
        <a class="waves-effect waves-light btn"
           href="portal?command=savePlace&place_id=${place_id}">Favorite Place</a>

        <form action="portal?command=savePlace&place_id=${place_id}" method="post">
            <button class="btn waves-effect waves-light cyan darken-2" type="submit"
                    >Favorite Place
            </button>
        </form>
    </div>
    <div class="modal-footer">

    </div>
</div>

<div id="editplace" class="modal">
    <div class="modal-content">
        <h4>Do you want to edit this place?</h4>

        <form id="edit_place" action="/portal/editplace" method="post">
            <input type="hidden" name="command" value="editPlace">
            <input placeholder="Placeholder" id="editPlaceID" name="editPlaceID" type="hidden"
                   value="${place.id}">
            <button class="btn waves-effect waves-light cyan darken-2" type="submit">YES</button>
            <button type="reset" class="btn modal-close waves-effect waves-light cyan darken-2">NO
            </button>
        </form>

    </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>

</body>
</html>
