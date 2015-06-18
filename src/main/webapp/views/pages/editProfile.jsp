<%--
  Created by IntelliJ IDEA.
  User: Oleguk
  Date: 15.06.2015
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="cdg" uri="customtags" %>
<html>
<head>
    <title><cdg:l18n key="usercab.edit"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
</head>
<jsp:include page="/views/elements/css.jsp"/>
<jsp:include page="/views/elements/script.jsp"/>

<body>
<jsp:include page="/views/elements/header.jsp"/>

<c:set var="img" value="${avatar}"/>
<div class="container">
    <div class="row">
        <h1>Edit Profile</h1>
        <hr>
        <div class="col s4">
            <div class="text-center">
                <c:choose>
                    <c:when test="${empty img}">
                        <img src="${pageContext.request.contextPath}/images/default3.jpg" width = 70% class="circle responsive-img" alt="avatar">
                    </c:when>
                    <c:otherwise>
                        <img src="${pageContext.request.contextPath}/images/${img}" width = 70% class="circle responsive-img" alt="avatar">
                    </c:otherwise>
                </c:choose>
                <h6>Upload a different photo...</h6>
                <div class="file-field input-field">
                    <div class="row">
                        <div class="col s10 offset-s1">
                            <input class="file-path validate" type="text" value="" name="img"/>
                        </div>
                        <div class="col s2">
                            <div class="btn">
                                <span>Upload</span>
                                <input type="file"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col s8">
            <h3>Personal info</h3>

            <form action="portal?command=updateprofile&login=${login}" class="form-horizontal" role="form">
                <div class="row">
                    <div class="input-field col s12">
                        <i class="mdi-action-account-circle prefix"></i>
                        <input value="${name}" id="first_name" type="text" class="validate" name="name">
                        <label class="active" for="first_name">First Name:</label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s12">
                        <i class="mdi-action-account-circle prefix"></i>
                        <input value="${surname}" id="last_name" type="text" class="validate" name="surname">
                        <label class="active" for="last_name">Last Name:</label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s12">
                        <i class="mdi-action-account-circle prefix"></i>
                        <input value="${login}" id="login" type="text" class="validate" name="login">
                        <label class="active" for="login">Login:</label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s12">
                        <i class="mdi-action-account-circle prefix"></i>
                        <input value="${mail}" id="mail" type="text" class="validate" name="mail">
                        <label class="active" for="mail">Email:</label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s12">
                        <i class="mdi-communication-phone prefix"></i>
                        <input value="${phone}" id="pnumber" type="text" class="validate" name="phone">
                        <label class="active" for="pnumber">Phone number:</label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s12">
                        <i class="mdi-action-account-circle prefix"></i>
                        <input value="${about}" id="about" type="text" class="validate" name="about">
                        <label class="active" for="about">About:</label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s12">
                        <i class="mdi-action-account-circle prefix"></i>
                        <input value="${password}" id="pass" type="text" class="validate" name="password">
                        <label class="active" for="pass">Password:</label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s12">
                        <i class="mdi-action-account-circle prefix"></i>
                        <input value="" id="cpass" type="text" class="validate" name="cpassword">
                        <label class="active" for="cpass">Confirm password:</label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label"></label>
                    <div class="col-md-8">
                        <button class="btn waves-effect waves-light" type="submit" name="save">Save Changes
                            <i class="mdi-content-send right"></i>
                        </button>
                        <span></span>
                        <button class="btn waves-effect waves-light" type="reset" name="cancel">Cancel</button>
                    </div>
                </div>
                <input type="hidden" name="avatar" value="${img}">
            </form>
        </div>
    </div>
</div>
<hr>
<jsp:include page="/views/elements/footer.jsp"/>
</body>
</html>
