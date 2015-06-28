<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>

<div class="modal" id="sign-in">
    <div class='login-form'>
        <div class='switch'>
            <div class="sign"><cdg:l18n key="login.signup"/></div>
            <div class="sign" style="display: none"><cdg:l18n key="login.signin"/></div>
        </div>

        <div class='login'>
            <h2><cdg:l18n key="login.logintxt"/></h2>

            <form id="sign-in-form" action="/portal?command=signIn" method="post">
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

            <form id="sign-up-form" action="/portal?command=signUp" method="post">
                <input type="hidden" name="command" value="signUp">

                <div id="first-last-form" class="row animated fadeIn" style="margin-bottom: 0">
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
                <div id="company-form" class="form animated fadeIn" style="display: none">
                    <input id="companyname" placeholder='<cdg:l18n key="login.companyname"/>' type='text' name="companyname">
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
                    <input name="role" type="radio" id="user" value="2" checked="checked" onchange="changeRoleToggle('user')"/>
                    <label class="radio-label" for="user"><cdg:l18n key="role.user"/></label>
                    <input name="role" type="radio" id="guide" value="3" onchange="changeRoleToggle('guide')"/>
                    <label class="radio-label" for="guide"><cdg:l18n key="role.guide"/></label>
                    <input name="role" type="radio" id="company" value="4" onchange="changeRoleToggle('company')"/>
                    <label class="radio-label" for="company"><cdg:l18n key="role.company"/></label>
                </div>
                <div class="form">
                    <button class="btn waves-effect waves-light cyan darken-2" type="submit">OK
                    </button>
                </div>
            </form>
        </div>
        <div class="login-footer">
            <a href="#reset-send-email" class="modal-trigger"><cdg:l18n key="login.forgot"/></a>
        </div>
    </div>
</div>