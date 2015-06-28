<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>

<div id="reset-password">
    <div id="reset-send-email" class="modal">
        <h5 class="center-align"><cdg:l18n key="reset.email"/></h5>

        <div class="divider"></div>
        <div class="section">
            <form action="portal?command=sendEmail" method="post">
                <div class="input-field">
                    <input id="reset-email" name="email" type="email"/>
                    <label for="reset-email"><cdg:l18n key="login.email"/></label>
                </div>
                <button class="btn cyan darken-2 modal-action modal-close" type="submit">
                    OK
                </button>
            </form>
        </div>

    </div>

    <div id="reset-send-email-info" class="modal">
        <p class="center-align"><cdg:l18n key="reset.email.info"/></p>

        <div class="divider"></div>
        <button class="btn cyan darken-2 modal-action modal-close">OK</button>
    </div>

    <div id="reset-confirm" class="modal">
        <h5 class="center-align"><cdg:l18n key="reset.newpass"/></h5>

        <div class="divider"></div>
        <div class="section">
            <form action="portal?command=confirmReset" method="post">
                <div class="input-field">
                    <input id="reset-new-password" name="password" type="password"/>
                    <label for="reset-new-password"><cdg:l18n key="login.password"/></label>
                </div>
                <div class="input-field">
                    <input id="reset-new-confirm" name="password" type="password"/>
                    <label for="reset-new-confirm"><cdg:l18n key="login.confirm"/></label>
                </div>
                <button class="btn cyan darken-2 modal-action modal-close" type="submit">
                    OK
                </button>
            </form>
        </div>
    </div>

    <div id="reset-confirm-info" class="modal">
        <p class="center-align"><cdg:l18n key="reset.changed"/></p>

        <div class="divider"></div>
        <button class="btn cyan darken-2 modal-action modal-close">OK</button>
    </div>
</div>
