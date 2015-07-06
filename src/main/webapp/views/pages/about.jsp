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
<div class="row">
    <div class="col s12 m8 l8">
        <h3 class="center-align"><cdg:l18n key="about.about"/></h3>

        <div class="row">
            <div class="offset-m1 col s12 m11">
                <div class="card">
                    <div class="card-image">
                        <img src="${pageContext.request.contextPath}/upload/photo/lviv.jpg">
                    </div>
                    <div class="card-content">
                        <p>
                            <cdg:l18n key="about.text"/>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="col s12 m4 l4">
        <br><br>

        <form action="/portal?command=send" method="post">

            <div class="collection with-header z-depth-2">
                <div class="row">

                    <div class="col s12 m10 l11">
                        <h4 class="center-align"><cdg:l18n key="about.contactus"/></h4>

                        <div class="row">
                            <div class="input-field col s12">
                                <i class="mdi-action-account-circle prefix"></i>
                                <label class="active" for="NewName"><cdg:l18n key="about.name"/></label>
                                <input id="NewName" type="text" name="name">
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">email</i>
                                <input id="icon_prefix" type="text" class="validate" name="email">
                                <label for="icon_prefix"><cdg:l18n key="about.email"/></label>
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">mode_edit</i>
                                <textarea id="icon_prefix2" class="materialize-textarea" name="message"></textarea>
                                <label for="icon_prefix2"><cdg:l18n key="about.message"/></label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group center-align">
                        <label class="col-md-3 control-label"></label>
                        <button class="btn waves-effect waves-light" type="submit"><cdg:l18n
                                key="about.sendmessage"/>
                            <i class="mdi-content-send right"></i>
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>
</body>
</html>
