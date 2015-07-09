<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>
<html>

<head>
    <title><cdg:l18n key="company.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
</head>

<jsp:include page="/views/elements/css.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div id="company-page" class="full-height">
    <div class="row">
        <div class="col l8 offset-l2 m10 offset-m1 s12 z-depth-2">
            <c:choose>
                <c:when test="${userInfo.role_id == 3}">
                    <h4 class="center-align"><cdg:l18n key="role.guide"/> - ${userInfo.name} ${userInfo.surname}</h4>
                </c:when>
            </c:choose>
            <c:choose>
                <c:when test="${userInfo.role_id == 4}">
                    <h4 class="center-align"><cdg:l18n key="role.company"/> "<b>${userInfo.companyName}</b>"</h4>
                </c:when>
            </c:choose>
            <div class="section">
                <div class="divider"></div>
            </div>

            <div class="row">
                <div class="col l4 m5 s6" style="margin-top: 10px">
                    <div class="center-align">
                        <c:choose>
                            <c:when test="${empty avatar}">
                                <img src="${pageContext.request.contextPath}/upload/photo/user.png"
                                     class="circle responsive-img" width="80%">
                            </c:when>
                            <c:otherwise>
                                <img src="${pageContext.request.contextPath}/upload/photo/${avatar.reference}"
                                     class="circle responsive-img" width="80%">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <div class="col l8 m7 s6">
                    <h4><cdg:l18n key="company.info"/></h4>

                    <p>
                        <c:choose>
                            <c:when test="${userInfo.role_id == 3}">
                                <cdg:l18n key="login.firstname"/>: ${userInfo.name}<br>
                                <cdg:l18n key="login.lastname"/>: ${userInfo.surname}<br>
                            </c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${userInfo.role_id == 4}">
                                <cdg:l18n key="login.companyname"/>: ${userInfo.companyName}<br>
                            </c:when>
                        </c:choose>
                        <cdg:l18n key="login.email"/>: ${userInfo.mail}<br>
                        <cdg:l18n key="login.phone"/>: ${userInfo.phone}<br>
                        <cdg:l18n key="company.about"/>: ${userInfo.about}<br><br>
                        <cdg:l18n key="company.rating"/>: ${userInfo.rating}
                    </p>

                    <p>
                        <a href="#company-response-form"
                           class="modal-trigger btn cyan darken-2 waves-effect waves-light"><cdg:l18n
                                key="company.response"/></a>
                    </p>
                </div>
            </div>

            <div class="section">
                <div class="divider"></div>
            </div>

            <h4 class="center-align"><cdg:l18n key="company.gallery"/></h4>

            <div class="card" style="padding: 10px">
                <c:forEach items="${userGalery}" var="elem">
                    <a href="" class="modal-trigger company-gallery-trigger" data-id="${elem.id}"  data-image="${elem.reference}">
                        <img class="responsive-img" width="200" src="${pageContext.request.contextPath}/upload/photo/${elem.reference}">
                    </a>
                </c:forEach>
            </div>

            <div class="section">
                <div class="divider"></div>
            </div>

            <h4 class="center-align"><cdg:l18n key="company.routes"/></h4>

            <ul class="collapsible" data-collapsible="accordion">
                <c:forEach var="mapelem" items="${allWayInfo}">
                    <li>
                        <div class="collapsible-header">
                            <div class="col s6 left-align">
                                <cdg:l18n key="company.routename"/>: ${mapelem.key.name}
                            </div>
                            <div class="col s6 right-align">
                                <a onclick="orderWaySendMail(this);" href="javascript:"
                                   rel="/portal?command=orderWaySendMail&wayId=${mapelem.key.id}&gidId=${pageContext.request.getParameter('id')}"
                                   style="text-transform: uppercase"><cdg:l18n key="company.order"/></a>
                            </div>
                        </div>
                        <div class="collapsible-body">
                            <div class="collection">
                                <c:forEach var="listelem" items="${mapelem.value}">
                                    <a href="#" class="collection-item black-text">
                                        <img class="circle responsive-img"
                                             src="${pageContext.request.contextPath}/upload/photo/${listelem.imageReference}">

                                        <div class="valign-wrapper" style="height:100%">
                                            <div class="valign">
                                                <div class="truncate">
                                                    <b>${listelem.name}</b><br>${listelem.adress}<br><cdg:l18n
                                                        key="company.rating"/>: ${listelem.rating}
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>

            <div class="section">
                <div class="divider"></div>
            </div>

            <h4 class="center-align"><cdg:l18n key="company.places"/></h4>

            <div class="row">
                <c:forEach var="place" items="${placesInfo}">
                    <div class="match-col col l4 m6 s12">
                        <div class="card z-depth-2" style="padding:10px; height:95%">
                            <div class="center-align">
                                <a href="#"><img class="responsive-img place-img"
                                                 src="${pageContext.request.contextPath}/upload/photo/${place.imageReference}"></a>
                                <a href="#"><h5><c:out value="${place.name}"/></h5></a>
                                <c:out value="${place.adress}"/><br>
                                <cdg:l18n key="company.rating"/>: ${place.rating}
                            </div>
                            <div class="section center-align">
                                <a href="#" style="text-transform: uppercase"><cdg:l18n key="company.order"/></a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>
    </div>
</div>

<div id="company-response-form" class="modal">
    <form action="">
        <div class="modal-content" style="padding: 10px 20px">
            <div class="input-field">
                <i class="material-icons prefix">mode_edit</i>
                <textarea id="icon_prefix2" class="materialize-textarea" name="message"></textarea>
                <label for="icon_prefix2"><cdg:l18n key="company.response"/></label>
            </div>
        </div>
        <div class="center-align">
            <button class="btn waves-effect waves-light cyan darken-2 modal-action modal-close" type="submit"
                    style="margin-bottom: 20px">
                <cdg:l18n key="about.sendmessage"/></button>
        </div>
    </form>
</div>

<div id="company-gallery-modal" class="modal">
    <div class="section center-align">
        <img src="" id="company-gallery-modal-image" class="responsive-img" style="max-width: 80%; max-height: 70%">
        <div align="center"></div>
    </div>

    <div class="card">
        <form name="image-send-comment" id="image-send-comment" action="" method="post">
            <div class="input-field">
                <input id="image_id" name="image_id" type="hidden"/>
            <textarea name="gallery-comment" id="gallery-comment" class="materialize-textarea" value=""></textarea>
                <label for="gallery-comment"><cdg:l18n key="place.message"/></label>
            </div>
            <div class="section">
                <div class="divider"></div>
            </div>
            <button type="submit" class="waves-effect waves-light btn cyan darken-2"><cdg:l18n
                    key="place.sendcomment"/></button>
        </form>
    </div>

    <div id="response-info-collection"></div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>

<script>
    $(".match-col").matchHeight({
        property: 'height'
    });

    $('.company-gallery-trigger').on('click', function () {
        var image = '${pageContext.request.contextPath}/upload/photo/' + $(this).data('image');
        $.ajax({
            url: window.location.origin + '/portal?command=imageResponseJSON&image_id='  + $(this).data('id'),
            success: loadResponse,
            error: loadResponse
        })
        $('#company-gallery-modal-image').attr('src', image);
        $('#image_id').attr('value', $(this).data('id'));
        $('#company-gallery-modal').openModal();
    });

    var loadResponse = function (data) {
        if (!data) return false;
        var source = $("#response-info-template").html();
        var template = Handlebars.compile(source);
        var html = template(data);
        $('#response-info-collection').html(html);
    };

    $('#image-send-comment').on('submit', function (e) {
        e.preventDefault();
        e.stopImmediatePropagation();
        $.ajax({
            type: 'post',
            url: window.location.origin + '/portal?command=addImageResponse',
            data: $('#image-send-comment').serialize(),
            clearForm: true,
            resetForm: true,
            success: loadWindow,
            error: loadWindow
        });
    });

    var loadWindow = function (data) {
        if (data == "1") {
            Materialize.toast('<cdg:l18n key="comment.sended"/>', 4000);
        }
        else {
            Materialize.toast('<cdg:l18n key="comment.not.sended"/>', 4000);
        }
        document.getElementById("image-send-comment").reset()
        $('#company-gallery-modal').closeModal();

           // $('.company-gallery-trigger').click();



    }

    var img = $('.place-img');
    var width = img.width();
    img.css({
        'height': width + 'px'
    });

    var orderWaySendMail = function (placeholder) {
        $.ajax({
            url: $(placeholder).attr('rel'),
            success:sendMail()
        });
    };

    var sendMail = function (data) {
            Materialize.toast('<cdg:l18n key="mail.sanded"/>', 4000);
    }

</script>

<script id="response-info-template" type="text/x-handlebars-template">
    {{#each this}}
    <div class="card valign-wrapper">
        <div class="valign">
            <a href="#"><img src="${pageContext.request.contextPath}/upload/photo/{{avaterReference}}" style="height:70px; weight:70px"/></a>
        </div>
        <div class="valign" style="margin-left: 20px">
            {{description}}
        </div>
    </div>
    {{/each}}
</script>

</body>
</html>