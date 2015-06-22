<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>

<div class="banner">
    <div class="parallax-container">
        <div class="parallax"><img class="responsive-img" src="${pageContext.request.contextPath}/images/banner_top.jpg">
        </div>
        <div class="container valign-wrapper">
            <div class="row">
                <div class="col l6 offset-l3 s10 offset-s1 banner-text center-align">

                    <h2 class="white-text"><cdg:l18n key="index.title"/></h2>
                    <h4 class="white-text"><cdg:l18n key="banner.head"/></h4>
                    <h5 class="white-text"><cdg:l18n key="banner.text"/></h5>
                    <br>
                    <a class="waves-effect waves-light btn-large cyan darken-2"><cdg:l18n key="header.plan"/></a>
                </div>
            </div>
        </div>
    </div>
</div>