<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><cdg:l18n key="places.title"/></title>

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
            <div class="blog-top">
                <h3><cdg:l18n key="places.head"/></h3>
            </div>

            <div class="col-md-9 blog-left">

                <div class="blog-grids">
                    <div class="blog-detail">
                        <div class="blog-image">
                        <a href="#"><img src="/images/opera_sm.jpg" alt=""></a>
                        </div>
                        <a href="#"><h3>Opera theatre</h3></a>
                        <p>Some information</p>
                        <div class="blog-btn"><a href="#">Read More</a></div>
                    </div>
                </div>

                <div class="blog-grids">
                    <div class="blog-detail">
                        <div class="blog-image">
                            <a href="#"><img src="/images/opera_sm.jpg" alt=""></a>
                        </div>
                        <a href="#"><h3>Opera theatre</h3></a>
                        <p>Some information</p>
                        <div class="blog-btn"><a href="#">Read More</a></div>
                    </div>
                </div>

                <div class="blog-grids">
                    <div class="blog-detail">
                        <div class="blog-image">
                            <a href="#"><img src="/images/opera_sm.jpg" alt=""></a>
                        </div>
                        <a href="#"><h3>Opera theatre</h3></a>
                        <p>Some information</p>
                        <div class="blog-btn"><a href="#">Read More</a></div>
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
</div>

<jsp:include page="/views/elements/footer.jsp"/>

</body>
</html>