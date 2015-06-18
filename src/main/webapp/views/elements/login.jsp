<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>

<div class="modal" id="sign-in">
<div class='login-form aniamted bounceIn'>
    <div class='switch'>
        <div class="sign"><cdg:l18n key="login.signup"/></div>
        <div class="sign" style="display: none"><cdg:l18n key="login.signin"/></div>
    </div>
    <div class='login'>
        <h2><cdg:l18n key="login.logintxt"/></h2>

        <form id="sign-in-form" action="portal/signIn" method="post">
            <input type="hidden" name="command" value="signIn">
            <div class="form">
                <input id="login-in" placeholder='<cdg:l18n key="login.login"/>' type='text' name="login">
            </div>
            <div class="form">
                <input placeholder='<cdg:l18n key="login.password"/>' type='password' name="password">
            </div>
            <div class="form">
                <button class="btn waves-effect waves-light cyan darken-2" type="submit"
                        >OK
                </button>
            </div>
        </form>
    </div>
    <div class='register'>
        <h2><cdg:l18n key="login.createtxt"/></h2>

        <form id="sign-up-form" action="portal/signUp" method="post">
            <input type="hidden" name="command" value="signUp">
            <div class="row" style="margin-bottom: 0">
                <div class="col s6" style="padding-right: 10px">
                    <div class="form">
                        <input id="first" placeholder='<cdg:l18n key="login.firstname"/>' type='text' name="first">
                    </div>
                </div>
                <div class="col s6" style="padding-left: 10px">
                    <div class="form">
                        <input id="last" placeholder='<cdg:l18n key="login.lastname"/>' type='text' name="last">
                    </div>
                </div>
            </div>
            <div class="form">
                <input id="login" placeholder='<cdg:l18n key="login.login"/>' type='text' name="login">
            </div>
            <div class="form">
                <input id="email" placeholder='<cdg:l18n key="login.email"/>' type='email' name="email">
            </div>
            <div class="form">
                <input id="password" placeholder='<cdg:l18n key="login.password"/>' type='password' name="password">
            </div>
            <div class="form">
                <input id="confirm" placeholder='<cdg:l18n key="login.confirm"/>' type='password' name="confirm">
            </div>
            <div class="form">
                <input id="phone" placeholder='<cdg:l18n key="login.phone"/>' type='text' name="phone">
            </div>
            <div class="form">
                <button class="btn waves-effect waves-light cyan darken-2" type="submit">OK
                </button>
            </div>
        </form>
    </div>
    <div class="login-footer">
        <a href="#"><cdg:l18n key="login.forgot"/></a>
    </div>
</div>
</div>

<script>
    $("#sign-in-form").validate();
    $("#sign-up-form").validate();
</script>

<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script src='${pageContext.request.contextPath}/js/<cdg:l18n key="validator.js"/>'></script>
