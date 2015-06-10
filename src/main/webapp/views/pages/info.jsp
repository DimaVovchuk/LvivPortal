<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><cdg:l18n key="info.title"/></title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
</head>

<jsp:include page="/views/elements/head.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div class="blog">
    <div class="container">
        <div class="blog-main">
            <div class="col-md-9 blog-left">
                <div class="blog-grids">
                    <img src="/images/opera.jpg" alt="">

                    <div class="blog-detail">
                        <h3>Opera theatre</h3>
                        <p>Some information</p>
                    </div>

                    <div class="data-line">
                        <ul>
                            <li>
                                <small class="calen"></small>
                                <span class="calen">July 30, 2015</span></li>
                            <li><a href="#">
                                <small class="admin"></small>
                                <span class="calen">Admin</span></a></li>
                            <li><a href="#">
                                <small class="comme"></small>
                                <span class="calen">No comments</span></a></li>
                            <li><a href="#">
                                <small class="post"></small>
                                <span class="calen">View Posts</span></a></li>
                            <li><a href="#">
                                <small class="link"></small>
                                <span class="calen">Premalink</span></a></li>
                        </ul>
                    </div>

                    <div class="single-commemts">
                        <div class="col-md-2 user">
                            <a href="#"><img src="/images/user.png" alt=""/></a>
                        </div>
                        <div class="col-md-10 user-comment">
                            <a href="#"><h4>Some user</h4></a>
                            <p>Comment</p>
                            <a class="comme" href="#">On December 14, 2014 18:05</a>
                        </div>
                        <div class="clearfix"></div>
                    </div>

                    <div class="magsingle-contact">
                        <h3>Leave A Comment</h3>
                        <input type="text" value="Name" onfocus="this.value = '';"
                               onblur="if (this.value == '') {this.value = 'Name';}"/>
                        <input type="text" class="no-mar" value="Email" onfocus="this.value = '';"
                               onblur="if (this.value == '') {this.value = 'Email';}"/>
                        <textarea onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Message';}">Message</textarea>
                        <input type="submit" value="Submit Comment"/>
                    </div>
                </div>

            </div>
        </div>

        <div class="col-md-3 blog-right">
            <div class="blog-cate">
                <h3><cdg:l18n key="places.categories"/></h3>
                <ul>
                    <li><a href="#">Architectural sights</a></li>
                    <li><a href="#">Churches</a></li>
                    <li><a href="#">Theatres</a></li>
                    <li><a href="#">Hotels</a></li>
                    <li><a href="#">Restaurants</a></li>
                </ul>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>

</body>
</html>
