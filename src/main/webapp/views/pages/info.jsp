<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><cdg:l18n key="info.title"/></title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
</head>

<jsp:include page="/views/elements/head.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div class="blog">
    <div class="container">
        <div class="blog-main">
            <div class="col-md-9 blog-left">
                <div class="blog-grids">
                    <img src="${pageContext.request.contextPath}/images/opera.jpg" alt="">

                    <div class="blog-detail">
                        <h3>${placeDescription.name}</h3>

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
                                                <a href="#"><img src="${pageContext.request.contextPath}/images/user.png" alt=""/></a>
                                            </div>
                                            <div class="col-md-10 user-comment">
                                                <a href="#"><h4><c:out value="${users.login}"/></h4></a>

                                                <p><c:out value="${placeResponse.description}"/></p>
                                                <a class="comme" href="#">On December 14, 2014 18:05</a>
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                    </c:if>

                            </c:forEach>
                        </c:forEach>

                    <div class="magsingle-contact">
                        <h3>Leave A Comment</h3>
                        <form action="portal/placeInformation&place_id=${places.id}" method="post">
                            <input type="hidden" name="command" value="placeInformation">
                        <input type="text" value="Name" onfocus="this.value = '';"
                               onblur="if (this.value == '') {this.value = 'Name';}"/>
                        <input type="text" class="no-mar" value="Email" onfocus="this.value = '';"
                               onblur="if (this.value == '') {this.value = 'Email';}"/>
                        <textarea value="Message" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Message';}">Message</textarea>
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
                    <li><a href="#">Architectural sights</a></li>
                    <li><a href="#">Churches</a></li>
                    <li><a href="#">Theatres</a></li>
                    <li><a href="#">Hotels</a></li>
                    <li><a href="#">Restaurants</a></li>
                </ul>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>

</body>
</html>
