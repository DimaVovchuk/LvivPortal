<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>

<div id="reset-password">
    <div id="reset-send-email" class="modal">
        <h5 class="center-align"><cdg:l18n key="reset.email"/></h5>

        <div class="divider"></div>
        <div class="section">
            <div class="form">
                <input id="reset-send-email-email" placeholder="<cdg:l18n key="login.email"/>" name="email"
                       type="email"/>
            </div>
            <div class="section">
                <button id="reset-send-email-submit" class="btn cyan darken-2">OK</button>
            </div>
        </div>
    </div>

    <div id="reset-confirm" class="modal">
        <h5 class="center-align"><cdg:l18n key="reset.newpass"/></h5>

        <div class="divider"></div>
        <div class="section">
            <form id="reset-confirm-form">
                <div class="form">
                    <input id="resetpassword" placeholder="<cdg:l18n key="login.password"/>" name="password" type="password"/>
                </div>
                <div class="form">
                    <input placeholder="<cdg:l18n key="login.confirm"/>" name="confirm" type="password"/>
                </div>
                <div class="section">
                    <a id="reset-confirm-submit" class="btn cyan darken-2" type="submit">OK</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    var sendResetPasswordEmail = function (email) {
        $('#reset-send-email').closeModal();
        $('#sign-in').closeModal();
        Materialize.toast('<cdg:l18n key="login.mailsent"/> ' + email, 5000);
    };

    $('#reset-send-email-submit').on('click', function () {
        var email = $('#reset-send-email-email').val();
        $.ajax({
            url: "portal?command=sendEmail&email=" + email,
            success: sendResetPasswordEmail(email)
        });
    });

    $('#reset-confirm-submit').on('click', function () {
        var form = $('#reset-confirm-form');
        if (form.valid()) {
            $.ajax({
                type: 'post',
                url: 'portal?command=confirmReset',
                data: {password: $('#resetpassword').val()},
                success:  Materialize.toast('<cdg:l18n key="login.passwordreset"/>', 5000)
            });
            $('#reset-confirm').closeModal();
        }
    });
</script>