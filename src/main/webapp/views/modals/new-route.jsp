<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdg" uri="customtags" %>

<div class="modal" id="set-date-time">
    <h5><cdg:l18n key="plan.choose"/></h5>

    <form id="new-route-form" action="portal?command=createUserData" method="post">
        <div id="dateMenu">
            <div id="days">
                <div class="row">
                    <div class="col s5">
                        <input class="datepicker" id="startDate" name="startDate"
                               placeholder='<cdg:l18n key="plan.startdate"/>' type="date">
                    </div>
                    <div class="col s5 offset-s2">
                        <input class="datepicker" id="endDate" name="endDate"
                               placeholder='<cdg:l18n key="plan.enddate"/>'
                               type="date">
                    </div>
                </div>
                <p><cdg:l18n key="plan.daynumber"/> - <span id="daysValue">0</span></p>
            </div>

            <div class="section">
                <input id="dontKnowDate" value="dontKnowDate" name="dontKnowDate" type="checkbox"/>
                <label for="dontKnowDate"><cdg:l18n key="plan.dontknowdate"/></label>
            </div>
        </div>

        <input id="automatic" value="automatic" name="automatic" type="checkbox"/>
        <label for="automatic"><cdg:l18n key="plan.automatic"/></label>

        <div id="places" style="display: none">
            <p><cdg:l18n key="plan.daytime"/></p>

            <p class="range-field">
                <input id="dayTime" type="range" name="dayTime" min="1" max="24" step="0.5"/>
                <span id="dayTimeValue">0</span>
                <cdg:l18n key="map.route.hours"/>

            <p>

            <p><cdg:l18n key="plan.chooseplaces"/></p>

            <p>
                <input id="architecture" value="architecture" name="architecture" type="checkbox"/>
                <label for="architecture"><cdg:l18n key="places.architecture"/></label><br>
                <input id="churches" value="churches" name="churches" type="checkbox"/>
                <label for="churches"><cdg:l18n key="places.churches"/></label><br>
                <input id="theatres" value="theatres" name="theatres" type="checkbox"/>
                <label for="theatres"><cdg:l18n key="places.theatres"/></label><br>
            </p>
        </div>

        <div class="section">
            <button class="btn waves-effect waves-light cyan darken-2" type="submit">OK</button>
        </div>
    </form>

</div>

<script>
    $('#startDate').change(function () {
        var start = new Date($('#startDate').val());
        var end = new Date($('#endDate').val());
        console.log(start + " " + end);
        var diffDays = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24) + 1;
        if (diffDays > 0) {
            $("#daysValue").html(diffDays).removeClass('error');
        }
        else {
            $("#daysValue").html('<cdg:l18n key="plan.wrongdate"/>').addClass('error');
        }
    });

    $('#endDate').change(function () {
        var start = new Date($('#startDate').val());
        var end = new Date($('#endDate').val());
        console.log(start + " " + end);
        var diffDays = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24) + 1;
        if (diffDays > 0) {
            $("#daysValue").html(diffDays).removeClass('error');
        }
        else {
            $("#daysValue").html('<cdg:l18n key="plan.wrongdate"/>').addClass('error');
        }
    });
</script>