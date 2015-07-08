<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 01.07.2015
  Time: 14:52
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
<div class="full-height">
  <jsp:include page="/views/elements/header.jsp"/>

  <div class="user-places" id="user-places">
    <div class="places">
      <div class="section">
        <h3 class="center-align"><cdg:l18n key="recomended.way"/></h3>

        <div class="row">
          <div class="col l12 m18 s36">
            <div id="recomended-way-info-collection">

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


  <jsp:include page="/views/elements/footer.jsp"/>
</div>

<script id="recomended-way-info-template" type="text/x-handlebars-template">
  {{#each this}}
  <div class="match-col col l3 m4 s9">
    <div class="card z-depth-2" style="padding:10px; height:95%">
      <div class="card-image waves-effect waves-block waves-light">

        <img class="activator responsive-img place-img"
             src="${pageContext.request.contextPath}/upload/photo/{{imageReference}}"
             style="width: 100%">

      </div>
      <div class="card-content">
                                    <span class="card-title activator grey-text text-darken-4"><div align="center">
                                      <p><h6><cdg:l18n key="way.name"/> - {{name}}</h6></p>
                                      <p><h6><a a class="modal-trigger" onclick="$('#way_id').val('{{id}}')"
                                                href="#get-direction"><cdg:l18n key="get.directions"/></a></h6></p>
                                    </div></span>
        <div>
          <%--<p><cdg:l18n key="rating.way"/>: <c:out value="{{rating_way}}"/></p>--%>
        </div>
<c:if test="${login!=null}">
        <div style="height: 40px"></div>
        <div class="bottom-right-btn">
          <a onClick="like(this);" data-id="{{id}}" data-rating="{{rating}}" id="up{{id}}" class="up{{id}} btn-floating btn-floating btn-small"
             href="javascript:" rel="/portal?command=rectRatingWay&rating=1&way_id={{id}}">
            <i class="material-icons">thumb_up</i>
          </a>

          <a onClick="none(this);" data-id="{{id}}" id="none{{id}}"class="none{{id}} btn-floating btn-floating btn-small"
             href="javascript:" rel="/portal?command=rectRatingWay&rating=0&way_id={{id}}">
            <i class="material-icons">thumbs_up_down</i>
          </a>


          <a onClick="dislike(this);" data-id="{{id}}" id="down{{id}}" class="down{{id}} btn-floating btn-floating btn-small"
             href="javascript:" rel="/portal?command=rectRatingWay&rating=-1&way_id={{id}}">
            <i class="material-icons">thumb_down</i>
          </a>

        </div>
      </div>
  </c:if>
      <div class="card-reveal">
        <span class="card-title grey-text text-darken-4"><cdg:l18n key="places.way"/></span>
        <p>
          {{#each place}}


          <p><a href="portal?command=placeInformation&place_id={{place_id}}"><c:out
                  value="{{name}}"/></a></p>

        {{/each}}
        </p>
      </div>
    </div>
  </div>
  {{/each}}
</script>


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

<script src="${pageContext.request.contextPath}/js/pages/recomended-way.js"></script>

</body>
</html>

