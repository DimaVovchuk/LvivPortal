<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>

<head>

    <link href="${pageContext.request.contextPath}/css/fotorama.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/fotorama.js"></script>
    <title><cdg:l18n key="places.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
</head>

<jsp:include page="/views/elements/script.jsp"/>
<jsp:include page="/views/elements/css.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div class="blog">
    <div class="container">
        <div class="blog-main">
            <div class="col-md-9 blog-left">
                <div class="blog-grids">
                    <h3>${placeDescription.name}</h3>
                    <div class="fotorama" data-transition="crossfade" data-nav="thumbs" data-loop="true" data-autoplay="true" data-allowfullscreen="true" data-keyboard="true" >
                    <c:forEach items="${place_referenceList}" var="elem">
                        <img class="materialboxed" width="200" height="200"
                             src="${pageContext.request.contextPath}/upload/photo/${elem.reference}">
                    </c:forEach>
                </div>
                    <div class="blog-detail">
                        ${placeDescription.description}<br>

                        <c:if test="${placeDescription.phone!=null}">
                            <div class="blog-btn">
                                <h4>Phone</h4>
                                <c:forEach var="infoPlacePhone" items="${infoPlacePhone}">
                                <c:out value="${infoPlacePhone}"/>
                            </c:forEach>
                            </div>
                        </c:if>
                        <c:if test="${placeDescription.price!=null}">
                            <div class="blog-btn">
                                <h4>Price</h4>
                                <c:forEach var="infoPlacePrice" items="${infoPlacePrice}">
                                    <c:out value="${infoPlacePrice}"/><br>
                                </c:forEach>
                            </div>
                        </c:if>
                        <div class="blog-btn">
                            <h4>Adress</h4>
                            ${place.adress}
                        </div>

                        <a class="waves-effect waves-light btn modal-trigger" href="#chooseDay">Choose day</a><br>
                        <a class="waves-effect waves-light btn modal-trigger" href="portal?command=savePlace&place_id=${place_id}">Favorite place</a>
                        <button class="btn modal-trigger" type="submit" data-target="editplace" id="btn1" onclick="$('editPlaceID').val('${place_id}')"> Edit place </button>


                        <div id="editplace" class="modal">
                            <div class="modal-content">
                                <h4>Do you want to edit this place?</h4>
                                <form id="edit_place" action="/portal/editplace" method="post">
                                    <input type="hidden" name="command" value="editPlace">
                                     <input placeholder="Placeholder" id="editPlaceID" name="editPlaceID" type="hidden" value="${place.id}">
                                    <button class="btn waves-effect waves-light cyan darken-2" type="submit">YES</button>
                                    <button type="reset" class="btn modal-close waves-effect waves-light cyan darken-2">NO</button>
                                </form>

                            </div>
                        </div>
                        <div id="chooseDay" class="modal">
                            <div class="modal-content">
                                <h4>Choose day</h4>
                                <form id="choose_day" action="/portal?command=placeInformation" method="post">
                                    <input type="hidden" name="command" value="placeInformation">
                                    <div class="input-field col s6">
                                        <input placeholder="Placeholder" id="place_id" name="place_id" type="hidden" value="${place.id}">
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
                                </form><br>
                                <a class="waves-effect waves-light btn" href="portal?command=savePlace&place_id=${place_id}">Favorite Place</a>

                                <form action="portal?command=savePlace&place_id=${place_id}" method="post">
                                <button class="btn waves-effect waves-light cyan darken-2" type="submit"
                                        >Favorite Place
                                </button>
                                    </form>
                            </div>
                            <div class="modal-footer">

                            </div>
                        </div>
                    <div class="data-line">
                        <ul>
                            <li>
                                <small class="calen"></small>
                                <span class="calen">July 30, 2015</span></li>
                            <li><a href="#">
                                <small class="admin"></small>
                                <span class="calen">Admin</span></a></li>
                            <li><a href="#">
                                <small class="comme"></small>
                                <span class="calen">No comments</span></a></li>
                            <li><a href="#">
                                <small class="post"></small>
                                <span class="calen">View Posts</span></a></li>
                            <li><a href="#">
                                <small class="link"></small>
                                <span class="calen">Premalink</span></a></li>
                        </ul>
                    </div>
                    <c:forEach var="users" items="${users}">
                        <c:forEach var="placeResponse" items="${placeResponse}">
                                <c:if test="${users.id==placeResponse.user_id}">

                                        <div class="single-commemts">
                                            <div class="col-md-2 user">
                                                <c:forEach var="userImages" items="${userImages}">
                                                    <c:if test="${users.id==userImages.user_id}">
                                                        <a href="#"><img src="${pageContext.request.contextPath}/upload/photo/${userImages.reference}" alt="" height="100" width="100"/></a>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                            <div class="col-md-10 user-comment">
                                                <a href="#"><h4><c:out value="${users.login}"/></h4></a>

                                                <p><c:out value="${placeResponse.description}"/></p>
                            <c:forEach var="placeRatings" items="${placeRatings}">
                                <c:if test="${users.id==placeRatings.user_id}">
                                    <c:out value="${placeRatings.rating}"/>
                                </c:if>
                            </c:forEach>
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                    </c:if>

                            </c:forEach>
                        </c:forEach>

                    <div class="magsingle-contact">
                        <h3>Leave A Comment</h3>
                        <form action="portal/placeInformation" method="post">
                            <input type="hidden" name="command" value="placeInformation">
                            <input type="hidden" name="place_id" value="${place_id}">
                            ${not_login}
                            <textarea name="message" value="${message}" placeholder="Message"></textarea>
                            <input type="radio" name="rating" value="1">1
                            <br>
                            <input type="radio" name="rating" value="-1">-1<br>
                        <input type="submit" value="Submit Comment"/>
                            </form>
                    </div>
                </div>

            </div>
        </div>

        <div class="col-md-3 blog-right">
            <div class="blog-cate">
                <h3><cdg:l18n key="places.categories"/></h3>
                <ul>
                    <li><a href="portal?command=place&category=architecture">Architectural sights</a></li>
                    <li><a href="portal?command=place&category=churches">Churches</a></li>
                    <li><a href="portal?command=place&category=theatres">Theatres</a></li>
                    <li><a href="portal?command=place&category=hotels">Hotels</a></li>
                    <li><a href="portal?command=place&category=restaurants">Restaurants</a></li>
                </ul>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>



</body>
</html>
