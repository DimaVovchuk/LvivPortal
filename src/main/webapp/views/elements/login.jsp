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
        <form>
            <input placeholder='<cdg:l18n key="login.login"/>' type='text'>
            <input placeholder='<cdg:l18n key="login.password"/>' type='password'>
            <button>OK</button>
        </form>
    </div>
    <div class='register'>
        <h2><cdg:l18n key="login.createtxt"/></h2>
        <form>
            <input class="half-left" placeholder='<cdg:l18n key="login.firstname"/>' type='text'>
            <input class="half-right" placeholder='<cdg:l18n key="login.lastname"/>' type='text'>
            <input placeholder='<cdg:l18n key="login.login"/>' type='text'>
            <input placeholder='<cdg:l18n key="login.email"/>' type='email'>
            <input placeholder='<cdg:l18n key="login.password"/>' type='password'>
            <input placeholder='<cdg:l18n key="login.confirm"/>' type='password'>
            <input placeholder='<cdg:l18n key="login.phone"/>' type='text'>
            <button>OK</button>
        </form>
    </div>
    <div class="login-footer">
        <a href="#"><cdg:l18n key="login.forgot"/></a>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/login.js"></script>