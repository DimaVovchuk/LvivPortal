<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>

<div class='login-form aniamted bounceIn'>
    <div class='switch'>
            <div class="sign"><cdg:l18n key="login.signup"/></div>
            <div class="sign" style="display: none"><cdg:l18n key="login.signin"/></div>
    </div>
    <div class='login'>
        <h2><cdg:l18n key="login.logintxt"/></h2>

        <form id="sign-in-form" action="portal?command=signIn" method="post">
            <div class="form">
                <input placeholder='<cdg:l18n key="login.login"/>' type='text' name="login">
            </div>
            <div class="form">
                <input placeholder='<cdg:l18n key="login.password"/>' type='password' name="password">
            </div>
            <div class="form">
                <input type="submit" value="OK">
            </div>
        </form>
    </div>
    <div class='register'>
        <h2><cdg:l18n key="login.createtxt"/></h2>

        <form id="sign-up-form" action="portal?command=signUp" method="post">
            <div class="form">
                <input id="first" placeholder='<cdg:l18n key="login.firstname"/>' type='text' name="first">
            </div>
            <div class="form">
                <input id="last" placeholder='<cdg:l18n key="login.lastname"/>' type='text' name="last">
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
                <input type="submit" value="OK">
            </div>
        </form>
    </div>
    <div class="login-footer">
        <a href="portal?command=resetEmail"><cdg:l18n key="login.forgot"/></a>
    </div>
</div>

<script>
    $("#sign-in-form").validate();
    $("#sign-up-form").validate();
</script>
<script>
    $('.switch').click(function(){
        $('.login').animate({height: "toggle", opacity: "toggle"}, "slow");
        $('.register').animate({height: "toggle", opacity: "toggle"}, "slow");
        $('.sign').toggle();
    });
</script>

<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script src='${pageContext.request.contextPath}/js/<cdg:l18n key="validator.js"/>'></script>
