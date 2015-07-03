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
</head>

<jsp:include page="/views/elements/css.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div class="places">
    <div class="section">
        <h3 class="center-align"><cdg:l18n key="places.head"/></h3>


        <div class="row">
            <div class="col l9 m8 s7">
                <div id="place-page-container" class="row z-depth-2">

                    <div class="place-page-navigation z-depth-1"></div>

                    <div id="place-info-collection" class="place-page-content">

                        <%--<div ></div>--%>

                    </div>

                </div>
            </div>

            <div class="col l3 m4 s5">
                <div class="collection with-header z-depth-2" id="search-place">
                    <form id="frmSearch" class="form-wrapper">
                        <input type="search" id="txtSearch" name="txtSearch" alt="Search Criteria"
                               onkeyup="searchSuggest();"
                               autocomplete="off" />
                        <input type="submit" id="cmdSearch" name="cmdSearch" value="Search" alt="Run Search" />

                    </form>
                    <br><p><div id="search_suggest" style="border-color: #ffffff;">
                </div></p>

                        <%--<form id="frmSearch">--%>
                            <%--<input type="text" id="txtSearch" name="txtSearch" alt="Search Criteria"--%>
                                   <%--onkeyup="searchSuggest();"  requiredautocomplete="off" />--%>
                            <%--<input type="submit" id="cmdSearch" name="cmdSearch" value="Search" alt="Run Search" />--%>
                            <%--<div id="search_suggest">--%>
                            <%--</div>--%>

                        <%--</form>--%>
                </div>

                <div class="collection with-header z-depth-2">
                    <div class="collection-header"><h4><cdg:l18n key="places.categories"/></h4></div>
                    <div id="category-place">

                        <a href="portal?command=placeJSON&category=architecture" data-category="architectture"
                           class="collection-item black-text ${requestScope.active_architecture}"><cdg:l18n
                                key="places.architecture"/></a>
                        <a href="portal?command=placeJSON&category=churches" data-category="churches"
                           class="collection-item black-text ${requestScope.active_churches}"><cdg:l18n
                                key="places.churches"/></a>
                        <a href="portal?command=placeJSON&category=theatres" data-category="theatres"
                           class="collection-item black-text ${requestScope.active_theatres}"><cdg:l18n
                                key="places.theatres"/></a>
                        <a href="portal?command=placeJSON&category=hotels" data-category="hotels"
                           class="collection-item black-text ${requestScope.active_hotels}"><cdg:l18n
                                key="places.hotels"/></a>
                        <a href="portal?command=placeJSON&category=restaurants" data-category="restaurants"
                           class="collection-item black-text ${requestScope.active_restaurants}"><cdg:l18n
                                key="places.restaurants"/></a>
                        <a href="portal?command=placeJSON"
                           class="collection-item black-text ${requestScope.active_allplaces}"><cdg:l18n
                                key="places.all"/></a>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>
<jsp:include page="/views/modals/add-place-to-route-recomended.jsp"/>
<jsp:include page="/views/modals/place-is-added.jsp"/>
<jsp:include page="/views/modals/place-is-in-db.jsp"/>
<script src="${pageContext.request.contextPath}/js/pages/places.js"></script>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/js/ajax_search.js"></script>
<%--<jsp:include page="/views/modals/add-place-to-route.jsp"/>--%>

<script id="place-info-template" type="text/x-handlebars-template">
    {{#each this}}
    <div class="match-col col l4 m6 s12">
        <div class="card z-depth-2" style="padding:10px; height:95%">
            <c:if test="${userDataTrip!=null}">

                <a class="btn-on-img modal-trigger btn-floating btn-large waves-effect waves-light cyan darken-2" data-target="chooseDayRecomended"
                   name onclick="$('#place_id').val('{{id}}')" style="position: absolute; margin: 10px">
                    <i class="mdi-content-add"></i>
                </a>
            </c:if>

            <div class="center-align">
                <a href="/portal?command=placeInformation&place_id={{id}}"><img
                        class="responsive-img place-img"
                        src="${pageContext.request.contextPath}/upload/photo/{{imageReference}}"></a>
                <a href="/portal?command=placeInformation&place_id={{id}}">
                    <h5><c:out value="{{name}}"/></h5></a>
                <span><c:out value="{{adress}}"/></span>
                <c:if test="${login!=null}">
                    <div style="height: 40px"></div>
                    <div class="bottom-right-btn">
                        <a onClick="like(this);" data-id="{{id}}" data-rating="{{rating}}" id="up{{id}}" class="up{{id}} btn-floating btn-floating btn-small"
                           href="javascript:" rel="/portal?command=rectRating&rating=1&place_id={{id}}">
                            <i class="material-icons">thumb_up</i>
                        </a>

                        <a onClick="none(this);" data-id="{{id}}" id="none{{id}}"class="none{{id}} btn-floating btn-floating btn-small"
                           href="javascript:" rel="/portal?command=rectRating&rating=0&place_id={{id}}">
                            <i class="material-icons">thumbs_up_down</i>
                        </a>


                        <a onClick="dislike(this);" data-id="{{id}}" id="down{{id}}" class="down{{id}} btn-floating btn-floating btn-small"
                           href="javascript:" rel="/portal?command=rectRating&rating=-1&place_id={{id}}">
                            <i class="material-icons">thumb_down</i>
                        </a>

                    </div>
                </c:if>
            </div>
        </div>
    </div>
    {{/each}}
</script>

<script>
    $(document).click(function() {
        notActive();
    });
    $("#search-place").click(function(event) {
        event.stopPropagation();
    });
</script>


</body>
</html>