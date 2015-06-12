<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>

<div class='login-form aniamted bounceIn'>
    <div class='switch'>
        <i class='fa fa-pencil fa-times'>
            <div class="sign"><cdg:l18n key="login.signup"/></div>
            <div class="sign" style="display: none"><cdg:l18n key="login.signin"/></div>
        </i>
    </div>
    <div class='login'>
        <h2><cdg:l18n key="login.logintxt"/></h2>

        <form action="/portal/signIn" method="post">
            <input type="hidden" name="command" value="signIn">
            <input placeholder='<cdg:l18n key="login.login"/>' type='text' name="login">
            <input placeholder='<cdg:l18n key="login.password"/>' type='password' name="password">
            <input type="submit" value="OK">
        </form>
    </div>
    <div class='register'>
        <h2><cdg:l18n key="login.createtxt"/></h2>

        <form action="/portal/signUp" method="post">
            <input type="hidden" name="command" value="signUp">
            <input class="half-left" placeholder='<cdg:l18n key="login.firstname"/>' type='text' name="first"
                   pattern="">
            <input class="half-right" placeholder='<cdg:l18n key="login.lastname"/>' type='text' name="last">
            <input placeholder='<cdg:l18n key="login.login"/>' type='text' name="login">
            <input placeholder='<cdg:l18n key="login.email"/>' type='email' name="email">
            <input placeholder='<cdg:l18n key="login.password"/>' type='password' name="password">
            <input placeholder='<cdg:l18n key="login.confirm"/>' type='password' name="confirm">
            <input placeholder='<cdg:l18n key="login.phone"/>' type='text' name="phone">
            <input type="submit" value="OK">
        </form>
    </div>
    <div class="login-footer">
        <a href="#"><cdg:l18n key="login.forgot"/></a>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/login.js"></script>