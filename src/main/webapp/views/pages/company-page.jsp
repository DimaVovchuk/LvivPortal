<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
</head>

<jsp:include page="/views/elements/css.jsp"/>
<jsp:include page="/views/elements/script.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div id="company-page">
    <div class="row">
        <div class="col l8 offset-l2 m10 offset-m1 s12 card">
            <h4>Company name</h4>

            <div class="divider"></div>
            <div class="section row">
                <div class="col s3">
                    <div class="center-align">
                        <img src="${pageContext.request.contextPath}/upload/photo/user.png" width=70%
                             class="circle responsive-img">
                    </div>
                </div>
                <div class="col s9">
                    Information
                </div>
            </div>
            <div class="card">

            </div>
        </div>
    </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>

</body>
</html>