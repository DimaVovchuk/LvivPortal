<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdg" uri="customtags" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
    $(document).ready(function(){
        $('#set-date-time').openModal();
    });
</script>

</head>
<jsp:include page="/views/elements/css.jsp"/>
<jsp:include page="/views/elements/script.jsp"/>

<body>
<jsp:include page="/views/elements/header.jsp"/>
<jsp:include page="/views/elements/footer.jsp"/>

</body>
</html>

<div class="modal" id="set-date-time">
    <h5><cdg:l18n key="plan.choose"/></h5>
    <form action="/portal?command=createUserData" method="post">
        <div id="days">
            <div class="row">
                <div class="col s5">
                    <input class="datepicker" id="startDate"  name="startDate" placeholder='<cdg:l18n key="plan.startdate"/>' type="date">
                </div>
                <div class="col s5 offset-s2">
                    <input class="datepicker" id="endDate" name="endDate" placeholder='<cdg:l18n key="plan.enddate"/>' type="date">
                </div>
            </div>
            <p id="dateInfo"><cdg:l18n key="plan.daynumber"/> - <span id="daysValue" name="daysValue">0</span></p>
        </div>

        <p>
            <input id="dontKnowDate" value="dontKnowDate" name="dontKnowDate" type="checkbox"/>
            <label for="dontKnowDate"><cdg:l18n key="plan.dontknowdate"/></label>
        </p>

        <p><cdg:l18n key="plan.arrive"/></p>

        <p>
           <select name="placeArrive">
                <option value="withoutPlaceArrive" disabled selected><cdg:l18n key="plan.noarrive"/></option>
                <option value="Place1">Place 1</option>
                <option value="Place2">Place 2</option>
                <option value="Place3">Place 3</option>
            </select>
        </p>

        <p>
            <input id="automatic" value="automatic" name="automatic" type="checkbox"/>
            <label for="automatic"><cdg:l18n key="plan.automatic"/></label>
        </p>

        <div id="places" style="display: none">
            <p><cdg:l18n key="plan.chooseplaces"/></p>

            <p>
                <input id="architecture" value="architecture" name="architecture" type="checkbox"/>
                <label for="architecture"><cdg:l18n key="places.architecture"/></label><br>
                <input id="churches" value="churches" name="churches" type="checkbox"/>
                <label for="churches"><cdg:l18n key="places.churches"/></label><br>
                <input id="theatres" value="theatres" name="theatres" type="checkbox"/>
                <label for="theatres"><cdg:l18n key="places.theatres"/></label><br>
            </p>
            <input id="lunch" type="checkbox" value="withLunch" name="lunch"/>
            <label for="lunch"><cdg:l18n key="plan.lunch"/></label><br>

        </div>
        <button class="btn waves-effect waves-light cyan darken-2" type="submit">OK</button>
    </form>
</div>