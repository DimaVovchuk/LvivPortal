<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>

<html>
<head>
    <title><cdg:l18n key="usercab.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
</head>

<jsp:include page="/views/elements/css.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div id="edit-profile">
    <div class="row">
        <h3 class="center-align"><cdg:l18n key="usercab.editprofil"/></h3>

        <form method=post enctype=multipart/form-data action="portal?command=updateprofile">
            <c:set var="command" scope="session" value="updateprofile"/>
            <div class="row">
                <div class="col l8 offset-l2 m10 offset-m1 s12 z-depth-2">
                    <div class="row">
                        <div class="col l4 m5 s6">
                            <div class="section center-align">
                                <c:choose>
                                    <c:when test="${not empty ava and not empty avatar_id}">
                                        <img src="${pageContext.request.contextPath}/upload/photo/${ava}" width=70%
                                             class="circle responsive-img" name="newAvatar">
                                    </c:when>
                                    <c:when test="${not empty ava and not empty vk_id and empty avatar_id}">
                                        <img src="${ava}" width=70%
                                             class="circle responsive-img" name="newAvatar">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${pageContext.request.contextPath}/upload/photo/user.png" width=70%
                                             class="circle responsive-img" name="newAvatar">
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <p><cdg:l18n key="usercab.uploadphoto"/></p>

                            <div class="file-field input-field">
                                <input class="file-path validate" type="text"/>

                                <div class="btn cyan darken-2 waves-effect waves-light">
                                    <span><cdg:l18n key="button.photo"/></span>
                                    <input id="fileupload" type="file" name="sendfile"/>
                                </div>
                            </div>
                        </div>

                        <div class="col l8 m7 s6">
                            <h4 class="center-align"><cdg:l18n key="usercab.info"/></h4>

                            <div class="divider"></div>

                            <div class="section">
                                <div class="input-field">
                                    <i class="mdi-action-account-circle prefix black-text"></i>
                                    <label class="active" for="NewName"><cdg:l18n key="usercab.name"/></label>
                                    <input value="<c:out value="${userForEdit.name}"/>" id="NewName" type="text"
                                           name="NewName">
                                </div>

                                <div class="input-field">
                                    <i class="mdi-action-account-circle prefix black-text"></i>
                                    <label class="active" for="surname"><cdg:l18n key="usercab.lastname"/></label>
                                    <input value="<c:out value="${userForEdit.surname}"/>" id="surname" type="text"
                                           name="surname">
                                </div>

                                <c:if test="${role != 1 and role != 2}">
                                <div class="input-field">
                                    <i class="mdi-action-account-circle prefix black-text"></i>
                                    <label class="active" for="companyName"><cdg:l18n
                                            key="usercab.companyname"/></label>
                                    <%--<input value="${userForEdit.role}" id="role" type="text" type="hidden" name="role">--%>
                                    <input value="${userForEdit.companyName}" id="companyName" type="text"
                                           name="companyName">
                                </div>
                                </c:if>

                                <div class="input-field">
                                    <i class="mdi-action-account-circle prefix black-text"></i>
                                    <label class="active" for="login"><cdg:l18n key="usercab.login"/></label>
                                    <input value="${userForEdit.login}" id="login" type="text" name="login" disabled>
                                </div>

                                <div class="input-field">
                                    <i class="material-icons prefix black-text">email</i>
                                    <label class="active" for="mail"><cdg:l18n key="usercab.mail"/></label>
                                    <input value="${userForEdit.mail}" id="mail" type="text" name="mail" disabled>
                                    <input type="hidden" id="hiddenMail" value="${userForEdit.mail}">
                                </div>

                                <div class="input-field">
                                    <i class="mdi-communication-phone prefix black-text"></i>
                                    <label class="active" for="phone"><cdg:l18n key="usercab.pnumber"/></label>
                                    <input value="${userForEdit.phone}" id="phone" type="text" name="phone">
                                </div>

                                <div class="input-field">
                                    <i class="mdi-action-account-circle prefix black-text"></i>
                                    <label class="active" for="about"><cdg:l18n key="usercab.about"/></label>
                                    <input value="<c:out value="${userForEdit.about}"/>" id="about" type="text"
                                           name="about">
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="divider"></div>

                    <p class="center-align"><cdg:l18n key="usercab.youmay"/> <a id="reset-send-email-link" href="#"><cdg:l18n
                            key="usercab.change"/></a> <cdg:l18n key="usercab.yourpas"/></p>

                    <div class="section center-align">
                        <button class="btn waves-effect waves-light cyan darken-2" type="submit" name="save"><cdg:l18n
                                key="usercab.savechange"/></button>
                        <button class="btn waves-effect waves-light cyan darken-2" type="reset" name="cancel"><cdg:l18n
                                key="usercab.cancel"/></button>
                    </div>

                </div>
            </div>
        </form>
    </div>
</div>

<script>
    var sendResetPasswordEmail = function (email) {
        Materialize.toast('<cdg:l18n key="login.mailsent"/> ' + email, 5000);
    };

    $('#reset-send-email-link').on('click', function () {
        var email = $('#hiddenMail').val();
        $.ajax({
            url: "portal?command=sendEmail&email=" + email,
            success: sendResetPasswordEmail(email)
        });
    });
</script>

<jsp:include page="/views/elements/footer.jsp"/>

</body>
</html>