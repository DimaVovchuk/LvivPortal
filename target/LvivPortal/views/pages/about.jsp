<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><cdg:l18n key="places.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
</head>

<jsp:include page="/views/elements/css.jsp"/>
<jsp:include page="/views/elements/script.jsp"/>

<body>
<jsp:include page="/views/elements/header.jsp"/>

<h3 class="center-align"><cdg:l18n key="about.about"/></h3>

<div id="about">
    <div class="row">
        <div class="col l8 offset-l2 m10 offset-m1 s12 about-info">
            <div class="card z-depth-2">
                <div class="card-image">
                    <img src="${pageContext.request.contextPath}/upload/photo/lviv.jpg">
                </div>
                <div class="card-content">
                    <cdg:l18n key="about.text"/>
                </div>
            </div>
        </div>

        <div class="col l8 offset-l2 m10 offset-m1 s12">
            <div class="card z-depth-2">
                <div class="card-content">
                    <form action="#" id="send-about" method="post">
                        <div class="row">
                            <div class="col l8 offset-l2 m10 offset-m1 s12">
                                <h5 class="center-align"><cdg:l18n key="about.contactus"/></h5>

                                <div class="input-field">
                                    <i class="mdi-action-account-circle prefix"></i>
                                    <input id="NewName" type="text" name="name">
                                    <label for="NewName"><cdg:l18n key="about.name"/></label>
                                </div>

                                <div class="input-field">
                                    <i class="material-icons prefix">email</i>
                                    <input id="icon_prefix" type="email" name="<cdg:l18n key='message.email'/>">
                                    <label for="icon_prefix"><cdg:l18n key="about.email"/></label>
                                </div>

                                <p><i class="material-icons" style="font-size: 2em">message</i><span style="margin-left: 30px"><cdg:l18n
                                        key="message.themechoose"/></span></p>

                                <div class="input-field">
                                    <input name="theme" type="radio" id="theme1"
                                           value="<cdg:l18n key="message.theme1"/>"/>
                                    <label for="theme1"><cdg:l18n key="message.theme1"/></label>
                                    <input name="theme" type="radio" id="theme2"
                                           value="<cdg:l18n key="message.themevalue"/>"/>
                                    <label for="theme2"><cdg:l18n key="message.theme2"/></label>
                                    <input name="theme" type="radio" id="theme3"
                                           value="<cdg:l18n key="message.theme3"/>"/>
                                    <label for="theme3"><cdg:l18n key="message.theme3"/></label>
                                </div>
                                <br>

                                <div class="input-field">
                                    <i class="material-icons prefix">mode_edit</i>
                                    <textarea id="icon_prefix2" class="materialize-textarea" name="message"></textarea>
                                    <label for="icon_prefix2"><cdg:l18n key="about.message"/></label>
                                </div>

                                <div class="center-align">
                                    <button class="btn waves-effect waves-light cyan darken-2" type="submit"><cdg:l18n
                                            key="about.sendmessage"/></button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>

<script>
    $('#send-about').on('submit', function (e) {
            e.preventDefault();
            e.stopImmediatePropagation();
            $.ajax({
                type: 'post',
                url: window.location.origin + '/portal?command=send',
                data: $('#send-about').serialize(),
                success: loadTostSend
            });
        });
    var loadTostSend = function (data) {
        if (data == "1") {
            Materialize.toast('<cdg:l18n key="message.about"/>', 4000);
        }
        if (data == "0") {
            Materialize.toast('<cdg:l18n key="message.about.not.all"/>', 4000);
        }
        if (data == "2") {
            Materialize.toast('<cdg:l18n key="message.about.fail"/>', 4000);
        }
    }
</script>

</body>
</html>
