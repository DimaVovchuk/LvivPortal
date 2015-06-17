<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>

<div class="modal" id="sign-in">
    <div class="modal-content center-align">
        <h5 class="cyan-text darken-5"><cdg:l18n key="login.logintxt"/></h5>
        <br>
        <form id="sign-in-form" action="portal/signin" method="post">
            <input type="hidden" name="command" value="signIn">
            <div class="input-field">
                <input class="validate" id="login-in" type="text" name="login">
                <label for="login-in"><cdg:l18n key="login.login"/></label>
            </div>
            <div class="input-field">
                <input class="validate" id="password-in" type="password" name="password">
                <label for="password-in"><cdg:l18n key="login.password"/></label>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <a href="#" id="open-sign-up" class="waves-effect waves-green btn-flat"><cdg:l18n
                key="login.signup"/></a>
        <button class="modal-action modal-close btn waves-effect waves-light cyan darken-2" type="submit"
                form="sign-in-form">OK
        </button>
    </div>
    <div class="divider"></div>
    <div class="modal-footer">
        <a href="portal?command=resetEmail" class="waves-effect waves-green btn-flat center-align"><cdg:l18n key="login.forgot"/></a>
    </div>
</div>

<div class="modal" id="sign-up">
    <div class="modal-content center-align">
        <h5 class="cyan-text darken-5"><cdg:l18n key="login.createtxt"/></h5>
        <br>

        <form id="sign-up-form" action="portal/signup" method="post">
            <input type="hidden" name="command" value="signUp">
            <div class="row">
                <div class="input-field col s6">
                    <input class="validate" id="first" type="text" name="first">
                    <label for="first"><cdg:l18n key="login.firstname"/></label>
                </div>
                <div class="input-field col s6">
                    <input class="validate" id="last" type="text" name="last">
                    <label for="last"><cdg:l18n key="login.lastname"/></label>
                </div>
            </div>
            <div class="input-field">
                <input class="validate" id="login-up" type="text" name="login">
                <label for="login-up"><cdg:l18n key="login.login"/></label>
            </div>
            <div class="input-field">
                <input class="validate" id="email" type="email" name="email">
                <label for="email"><cdg:l18n key="login.email"/></label>
            </div>
            <div class="row">
                <div class="input-field  col s6">
                    <input class="validate" id="password-up" type="password" name="password">
                    <label for="password-up"><cdg:l18n key="login.password"/></label>
                </div>
                <div class="input-field  col s6">
                    <input class="validate" id="confirm" type="password" name="confirm">
                    <label for="confirm"><cdg:l18n key="login.confirm"/></label>
                </div>
            </div>
            <div class="input-field">
                <input class="validate" id="phone" type="text" name="phone">
                <label for="phone"><cdg:l18n key="login.phone"/></label>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <a href="#sign-in" id="open-sign-in" class="modal-trigger-sign-in waves-effect waves-green btn-flat"
                ><cdg:l18n
                key="login.signin"/></a>
        <button class="modal-action modal-close btn waves-effect waves-light cyan darken-2" type="submit"
                form="sign-up-form">OK
        </button>
    </div>
</div>

<script>
    $("#sign-in-form").validate();
    $("#sign-up-form").validate();
</script>

<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script src='${pageContext.request.contextPath}/js/<cdg:l18n key="validator.js"/>'></script>