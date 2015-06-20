<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdg" uri="customtags" %>

<div class="modal" id="set-date-time">
    <h5><cdg:l18n key="plan.choose"/></h5>

    <form action="portal?command=createUserData" method="post">
        <div id="days">
            <div class="row">
                <div class="col s5">
                    <input class="datepicker" id="startDate" placeholder='<cdg:l18n key="plan.startdate"/>' type="date">
                </div>
                <div class="col s5 offset-s2">
                    <input class="datepicker" id="endDate" placeholder='<cdg:l18n key="plan.enddate"/>' type="date">
                </div>
            </div>
            <p id="dateInfo"><cdg:l18n key="plan.daynumber"/> - <span id="daysValue">0</span></p>
        </div>

        <p>
            <input id="dontKnowDate" type="checkbox"/>
            <label for="dontKnowDate"><cdg:l18n key="plan.dontknowdate"/></label>
        </p>

        <p>
            <input id="automatic" type="checkbox"/>
            <label for="automatic"><cdg:l18n key="plan.automatic"/></label>
        </p>

        <div id="places" style="display: none">
            <p><cdg:l18n key="plan.chooseplaces"/></p>

            <p>
                <input id="architecture" type="checkbox"/>
                <label for="architecture"><cdg:l18n key="places.architecture"/></label><br>
                <input id="churches" type="checkbox"/>
                <label for="churches"><cdg:l18n key="places.churches"/></label><br>
                <input id="theatres" type="checkbox"/>
                <label for="theatres"><cdg:l18n key="places.theatres"/></label><br>
            </p>
            <input id="lunch" type="checkbox"/>
            <label for="lunch"><cdg:l18n key="plan.lunch"/></label><br>

            <p><cdg:l18n key="plan.arrive"/></p>
            <select>
                <option value="" disabled selected><cdg:l18n key="plan.noarrive"/></option>
                <option value="">Place 1</option>
                <option value="">Place 2</option>
                <option value="">Place 3</option>
            </select>
        </div>
        <button class="btn waves-effect waves-light cyan darken-2" type="submit">OK</button>
    </form>

</div>