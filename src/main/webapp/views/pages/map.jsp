<%@ taglib prefix="cdg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><cdg:l18n key="index.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
</head>

<jsp:include page="/views/elements/css.jsp"/>
<jsp:include page="/views/elements/script.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div class="map">
    <div class="z-depth-2" id="map-side">
        <nav class="side-icon blue-grey darken-2 z-depth-5">
            <ul class="center-align">
                <li id="li-itinerary" class="link-process active" data-type="itinerary"><a href="#"><i class="small mdi-notification-event-note white-text"></i>
                    <span class="nav-text white-text"><cdg:l18n key="map.itinerary"/></span>
                </a>
                </li>
                <li id="li-places" class="link-process" data-type="places"><a href="#"><i class="small mdi-action-account-balance white-text"></i>
                    <span class="nav-text white-text"><cdg:l18n key="map.places"/></span>
                </a>
                <li id="li-custom" class="link-process" data-type="custom"><a href="#"><i class="small material-icons">local_see</i>
                    <span class="nav-text white-text"><cdg:l18n key="map.custom"/></span>
                </a>
                </li>
            </ul>
        </nav>

        <div class="side-info">
            <jsp:include page="map-info.jsp"/>
        </div>
    </div>

    <div id="map-canvas"></div>
</div>

<jsp:include page="/views/pages/geotag.jsp"/>
<jsp:include page="/views/modals/addPlaceToRoute.jsp"/>

<script>
    var initMapDayTrigger = function () {
        $(document).on('click', '.map-day-trigger', function (e) {
            e.preventDefault();
            var show = $(e.currentTarget).data('show');

            var day = $(e.currentTarget).data('day');
            if (show === 1 && count == 0) {
                count++;
                $(e.currentTarget).data('show', 0);
                $('#map-day' + day).html('<cdg:l18n key="map.route.hide"/>');
                initDayMarkers(day - 1);
                hideMarkers();
                showRoutesMarkers(day - 1)
            }
            if (show === 0) {
                count--;
                $(e.currentTarget).data('show', 1);
                $('#map-day' + day).html('<cdg:l18n key="map.route.show"/>');
                for (var i = 0; i < directionsDisplays.length; i++) {
                    directionsDisplays[i].set('directions', null);
                }
                hideMarkers();
                showMarkers();
            }
        })
    };
</script>

<script src="${pageContext.request.contextPath}/js/pages/map.js"></script>

</body>
</html>