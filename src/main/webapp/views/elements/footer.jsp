<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>

<div class="footer">
    <div class="copyright">
        <div class="container">
            <div class="copy-main">
                <div class="copy-right">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/views/pages/index.jsp"><cdg:l18n
                                key="header.home"/></a></li>
                        /
                        <li><a href="${pageContext.request.contextPath}/views/pages/index.jsp#places"><cdg:l18n
                                key="header.places"/></a></li>
                        /
                        <li><a href="http://www.booking.com"><cdg:l18n key="header.hotels"/></a></li>
                        /
                        <li><a href="${pageContext.request.contextPath}/views/pages/map.jsp"><cdg:l18n
                                key="header.plan"/></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
