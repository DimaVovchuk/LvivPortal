<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="map-info">
    <div id="map-itinerary" class="animated fadeInDown">
        <div id="route-info-collection" class="collection"></div>
    </div>

    <div id="map-places" class="animated fadeInDown" style="display: none">
        <div class="z-depth-2 search-box" id="search-place">
            <nav>
                <div class="nav-wrapper">
                    <form id="frmSearch" style="padding: 0 10px">
                        <div class="input-field">
                            <input id="txtSearch" type="search" name="txtSearch" alt="Search Criteria"
                                   onfocus="searchSuggestMap()" onkeyup="searchSuggestMap()" autocomplete="off"
                                   required>
                            <label for="txtSearch"><i class="material-icons">search</i></label>
                        </div>
                    </form>
                </div>
            </nav>
            <div id="search_suggest" class="z-depth-2" style="border: none"></div>
        </div>


        <a class='dropdown-select btn cyan darken-2 waves-effect waves-light' href='#' data-activates='dropdown-places'><cdg:l18n
                key="places.categories"/></a>
        <ul id='dropdown-places' class='dropdown-content' style="z-index: 1000">
            <li><a href="portal?command=placeJSON&category=architecture" class="collection-item"
                   data-category="architecture"><cdg:l18n
                    key="places.architecture"/></a></li>
            <div class="divider"></div>
            <li><a href="portal?command=placeJSON&category=churches" class="collection-item"
                   data-category="churches"><cdg:l18n
                    key="places.churches"/></a></li>
            <div class="divider"></div>
            <li><a href="portal?command=placeJSON&category=theatres" class="collection-item"
                   data-category="theatres"><cdg:l18n
                    key="places.theatres"/></a></li>
            <div class="divider"></div>
            <li><a href="portal?command=placeJSON&category=hotels" class="collection-item"
                   data-category="hotels"><cdg:l18n
                    key="places.hotels"/></a></li>
            <div class="divider"></div>
            <li><a href="portal?command=placeJSON&category=restaurants" class="collection-item"
                   data-category="restaurants"><cdg:l18n
                    key="places.restaurants"/></a></li>
            <div class="divider"></div>
            <li><a href="portal?command=placeJSON" class="collection-item"><cdg:l18n key="places.all"/></a></li>
        </ul>

        <div id="place-info-collection" class="collection"></div>
    </div>

    <div id="map-custom" class="animated fadeInDown">
        <h5 class="center-align"><cdg:l18n key="addnewcustomplace.title"/></h5>

        <div id="markerStatus"></div>
        <div class="divider"></div>
        <form method=post enctype=multipart/form-data>
            <c:set var="command" scope="session" value="saveCustomPlace"/>
            <div class="file-field input-field row map-row">
                <div class="btn cyan darken-2 waves-effect waves-light">
                    <span><cdg:l18n key="editplace.addphoto"/></span>
                    <input id="image-input" type="file" multiple name="sendfile"/>
                </div>
            </div>
            <div class="section">
                <output id="image-preview"></output>
            </div>
            <a id="image-clear" class="btn cyan darken-2 waves-effect waves-light" style="display: none"><cdg:l18n
                    key="editplace.clearimage"/></a>

            <div class="input-field row map-row">
                <input id="customPlaceName" type="text" name="customPlaceName" pattern="^[^<>/{}]+$">
                <label for="customPlaceName"><cdg:l18n key="addnewcustomplace.name"/></label>
            </div>

            <div class="input-field row map-row">
                <input type="checkbox" id="custom-additional-check"/>
                <label for="custom-additional-check" class="black-text truncate"><cdg:l18n
                        key="addnewcustomplace.additional"/></label>
            </div>

            <div id="custom-additional" style="display: none">
                <div class="input-field row map-row">
                    <input id="customPlaceDesc" type="text" name="customPlaceDesc" pattern="^[^<>/{}]+$">
                    <label for="customPlaceDesc"><cdg:l18n key="addnewcustomplace.description"/></label>
                </div>

                <b><cdg:l18n key="editplace.choseplacecategory"/></b>
                <select name="newCategory">
                    <option value="6"><cdg:l18n key="editplace.otherPlaces"/></option>
                    <option value="1"><cdg:l18n key="editplace.architecture"/></option>
                    <option value="2"><cdg:l18n key="editplace.churches"/></option>
                    <option value="3"><cdg:l18n key="editplace.theatres"/></option>
                    <option value="4"><cdg:l18n key="editplace.hotels"/></option>
                    <option value="5"><cdg:l18n key="editplace.restaurants"/></option>
                </select>

                <div class="input-field row map-row">
                    <input id="customPlacePrice" type="text" name="customPlacePrice" pattern="^[^<>/{}]+$">
                    <label for="customPlacePrice"><cdg:l18n key="addnewcustomplace.price"/></label>
                </div>

                <div class="input-field row map-row">
                    <input id="customPlacePhone" type="text" name="customPlacePhone"
                           pattern="((^(8-?|\+?7-?|\+38-?|38-?|)?(\(?\d{3}\)?)-?(\d-?){6}\d$)|^(\d-?){6}\d$)">
                    <label for="customPlacePhone"><cdg:l18n key="editplace.placephone"/></label>
                </div>

                <div class="input-field row map-row" style="margin-top: 0">
                    <cdg:l18n key="editplace.placetime"/>
                    <input id="place_time" type="number" name="place_time" min="0">
                </div>
            </div>

            <div class="input-field row map-row">
                <input value=" " id="customPlaceAdrress" type="text" name="customPlaceAdrress">
                <input id="customPlaceAdrressHid" type="hidden" name="customPlaceAdrressHid">
                <label for="customPlaceAdrress"><cdg:l18n key="addnewcustomplace.adrress"/></label>
            </div>

            <div class="row map-row">
                <div class="col s6" style="padding-right: 10px">
                    <div class="input-field">
                        <input value=" " id="latitude" type="text" name="latitude" disabled>
                        <input id="latitudeHid" type="hidden" name="latitudeHid">
                        <label for="latitude"><cdg:l18n key="editplace.placealat"/></label>
                    </div>
                </div>
                <div class="col s6" style="padding-left: 10px">
                    <div class="input-field">
                        <input value=" " id="longitude" type="text" name="longitude" disabled>
                        <input id="longitudeHid" type="hidden" name="longitudeHid">
                        <label for="longitude"><cdg:l18n key="editplace.placealon"/></label>
                    </div>
                </div>
            </div>

            <button class="btn waves-effect waves-light cyan darken-2" type="submit" name="save"
                    style="margin-bottom: 10px"><cdg:l18n key="editplace.placesavechange"/></button>
            <button class="btn waves-effect waves-light cyan darken-2" type="reset" name="cancel"><cdg:l18n
                    key="editplace.placecancel"/></button>
        </form>
    </div>
</div>

<script id="place-info-template" type="text/x-handlebars-template">
    {{#each this}}
    <a href="#" onclick="myclick('{{id}}')" class="collection-item black-text">
        <img class="circle responsive-img"
             src="${pageContext.request.contextPath}/upload/photo/{{imageReference}}">

        <div class="valign-wrapper" style="height:100%">
            <div class="valign">
                <div class="truncate"><b>{{name}}</b><br>{{adress}}</div>
            </div>
        </div>
        <div style="right: 5px; position: absolute">
            <button class="btn-floating modal-trigger waves-effect waves-light cyan darken-2"
                    data-target="chooseDayRecomended" onclick="$('#place_id').val('{{id}}')" style="top: -42px;"><i
                    class="material-icons">add</i></button>
        </div>
    </a>
    {{/each}}
</script>

<script id="route-info-template" type="text/x-handlebars-template">
    {{#each this}}
    <c:set var="dayNumber" value="{{dayNumber}}"/>
    <a href="#" data-day="${dayNumber}"
       class="day-trigger collection-item black-text blue-grey lighten-4 waves-effect waves-light">
        <cdg:l18n key="map.route.day"/> {{dayNumber}} <br>
        <cdg:l18n key="map.route.totaltime"/>: {{hours}} <cdg:l18n key="map.route.hours"/> {{minutes}} <cdg:l18n
            key="map.route.minutes"/>
    </a>
    <a href="#" id="map-day${dayNumber}" data-day="${dayNumber}" data-show="1"
       class="btn cyan darken-2 map-day-trigger"><cdg:l18n key="map.route.show"/></a>
    <div id="places-day{{dayNumber}}" class="collection animated fadeInLeft">
        {{#each places}}
        <a href="#" onclick="myclick('{{placeId}}')" class="collection-item black-text">
            <img class="circle responsive-img" src="${pageContext.request.contextPath}/upload/photo/{{imageReference}}">

            <div class="valign-wrapper" style="height:100%">
                <div class="valign">
                    <div class="truncate"><b>{{name}}</b><br><cdg:l18n key="map.route.placetime"/>: {{time}} <cdg:l18n
                            key="map.route.minutes"/></div>
                </div>
            </div>
        </a>
        {{/each}}
    </div>
    {{/each}}
</script>

<script id="route-empty-template" type="text/x-handlebars-template">
    <h5 id="map-route-addroute" name="map-route-addroute" style="padding: 10px"><cdg:l18n
            key="map.route.addroute"/></h5>
    <label for="map-route-addroute"><cdg:l18n key="first.place.route"/></label>
</script>

<script>
    var searchPlace = function () {
        var str = encode_utf8(document.getElementById('txtSearch').value);
        //alert(str);
        notActive();
        var strArr = str.split(" ");
        str = '';
        for (i = 0; i < strArr.length; i++) {
            if (i == strArr.length - 1) {
                str += strArr[i];
            } else {
                str += strArr[i] + '+';
            }
        }
        $.ajax({
            url: window.location.origin + '/portal?command=placeJSON&txtSearch=' + str,
            success: loadPlacesData,
            error: loadPlacesData
        });
    };

    $('#frmSearch').on('submit', function (e) {
        notActive();
        e.preventDefault();
        e.stopImmediatePropagation();
        $.ajax({
            type: 'post',
            url: window.location.origin + '/portal?command=placeJSON',
            data: $('#frmSearch').serialize(),
            success: loadPlacesData,
            error: loadPlacesData
        });
    });

    var loadPlacesData = function (data) {
        if (!data) return false;
        var source = $("#place-info-template").html();
        var template = Handlebars.compile(source);
        var html = template(data);
        $('#place-info-collection').html(html);
        notActive();
    };
    $(document).click(function () {
        notActive();
    });
    $("#search-place").click(function (event) {
        event.stopPropagation();
    });

    var loadWindow = function (data) {
        if (data == "1") {
            Materialize.toast('<cdg:l18n key="place.added"/>', 4000);
        }
        else {
            Materialize.toast('<cdg:l18n key="place.is.db"/>', 4000);
        }
    }

</script>

<script language="JavaScript" type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajax_search.js"></script>
