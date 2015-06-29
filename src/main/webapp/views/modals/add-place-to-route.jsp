<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>

<div id="chooseDay" class="modal">
    <div class="modal-content">
        <c:set var="category" scope="request" value="${requestScope.category}"/>
        <h5><cdg:l18n key="places.addtoroute"/></h5>
        <div class="divider"></div>

        <form id="choose_day" action="/portal/place" method="post">
            <input type="hidden" name="command" value="place">

            <div class="input-field col s6">
                <input id="place_id" name="place_id" type="hidden">
            </div>
            <p><cdg:l18n key="select.day.number"/></p>

            <div class="row">
                <div class="col s4">
                    <input name="dayNumber" type="number" min="1" max="${userDataTrip.dayCount}"/>
                </div>
                <div class="col s8">
                    <cdg:l18n key="map.route.daylower"/>
                </div>
            </div>
            <p><cdg:l18n key="select.place.time"/></p>

            <p class="range-field">
                <input type="range" name="timePlace" id="timePlace" min="15" value="15" step="15" max="240"/>
                <span><span id="timeValue"></span> <cdg:l18n key="map.route.minutes"/></span>
            </p>
            <br>

            <button class="btn waves-effect waves-light cyan darken-2" type="submit"
                    >OK
            </button>
        </form>

    </div>
</div>

<script src="${pageContext.request.contextPath}/js/pages/places.js"></script>