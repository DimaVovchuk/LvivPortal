/* *** SIDEBAR *** */
var initSidebar = function () {
    initLinkProcess();
    loadPlaces();
    initCategoriesEvents();
    loadRoutes();
    initDayTrigger();
    initMapDayTrigger();
};

var linkProcess = function (id) {
    var windowIDList = ['#map-itinerary', '#map-places'];
    var li = '#li-' + id;
    var map = '#map-' + id;
    $(li).addClass('active');
    $(map).show();
    windowIDList.forEach(function (item) {
        if (item !== map) {
            $(item).hide();
        }
    });
};

var initLinkProcess = function () {
    $('.link-process').on('click', function (e) {
        var type = $(e.currentTarget).data('type');
        linkProcess(type);
        $('.link-process').removeClass('active');
        $(".link-process[data-type='" + type + "']").addClass('active');
    })
};

/* Sidebar - Places */
var loadPlaces = function () {
    $.ajax({
        url: window.location.origin + '/portal?command=placeJSON',
        success: updatePlaces,
        error: updatePlaces
    });
};

var updatePlaces = function (data) {
    if (!data) return false;
    var source = $("#place-info-template").html();
    var template = Handlebars.compile(source);
    var html = template(data);
    $('#place-info-collection').html(html);
};

var initCategoriesEvents = function () {
    $('#dropdown-places').on('click', 'li', function (e) {
        e.preventDefault();
        $.ajax({
            url: window.location.origin + '/' + $(e.target).attr('href'),
            success: updatePlaces,
            error: updatePlaces
        });
    });
};

/* Sidebar - Routes */
var loadRoutes = function () {
    $.ajax({
        url: window.location.origin + '/portal?command=routes',
        success: updateRoutes,
        error: updateRoutes
    });
};

var updateRoutes = function (data) {
    if (!data) return false;
    if (data.length > 0) {
        var source = $("#route-info-template").html();
    } else {
        source = $("#route-empty-template").html();
    }
    var template = Handlebars.compile(source);
    var html = template(data);
    $('#route-info-collection').html(html);
    routesData = data;
};

var initDayTrigger = function () {
    $(document).on('click', '.day-trigger', function (e) {
        e.preventDefault();
        var day = $(e.currentTarget).data('day');
        $('#places-day' + day).toggle();
    })
};

var initMapDayTrigger = function () {
    $(document).on('click', '.map-day-trigger', function (e) {
        e.preventDefault();
        var show = $(e.currentTarget).data('show');
        var day = $(e.currentTarget).data('day');
        if (show === 1) {
            $(e.currentTarget).data('show', 0);
            $('#map-day' + day).html('Hide from map');
            initDayMarkers(day - 1);
            hideMarkers();
            showRoutesMarkers(day - 1)
        }
        if (show === 0) {
            $(e.currentTarget).data('show', 1);
            $('#map-day' + day).html('Show on map');
            directionsDisplay.set('directions', null);
            hideMarkers();
            showMarkers();
        }
    })
};

/* *** MAP *** */

var map;
var lvivMap = new google.maps.LatLng(49.8426, 24.0278);

var routesData;

var initBlankMap = function () {
    var mapOptions = {
        zoom: 15,
        center: lvivMap
    };
    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
};

var directionsDisplay;
var directionsService = new google.maps.DirectionsService();

var initDayMarkers = function (dayNumber) {
    directionsDisplay = new google.maps.DirectionsRenderer();
    directionsDisplay.setMap(map);
    directionsDisplay.setOptions({suppressMarkers: true});
    calcRoute(routesData[dayNumber].places);
};

var calcRoute = function (data) {
    //console.log(data.length );
    //
    //if (data.length > 10) {
    //    var poly = [];
    //    var polyOptions = {
    //        strokeColor: '#000000',
    //        strokeOpacity: 1.0,
    //        strokeWeight: 3
    //    };
    //    for (var i = 1; i < (data.length - 1); i++) {
    //        poly.push(new google.maps.LatLng(data[i].latitude, data[i].longitude));
    //    }
    //
    //    poly = new google.maps.Polyline(polyOptions);
    //    poly.setMap(map);
    //}

    if (data.length > 1 /*&& data.length < 10*/) {
        var start = new google.maps.LatLng(data[0].latitude, data[0].longitude);
        var image = {
            url: "",
            scaledSize: new google.maps.Size(40, 30),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        };
        new google.maps.Marker({
            position: new google.maps.LatLng(data[0].latitude, data[0].longitude),
            icon: image,
            map: map
        });
        var end = new google.maps.LatLng(data[data.length - 1].latitude, data[data.length - 1].longitude);
        image = {
            url: "",
            scaledSize: new google.maps.Size(40, 30),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        };
        new google.maps.Marker({
            position: new google.maps.LatLng(data[data.length - 1].latitude, data[data.length - 1].longitude),
            icon: image,
            map: map
        });
        var waypts = [];

        for (var i = 1; i < (data.length - 1); i++) {
            waypts.push({
                location: new google.maps.LatLng(data[i].latitude, data[i].longitude)
            });
            image = {
                url: "",
                scaledSize: new google.maps.Size(40, 30),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(0, 0)
            };
            new google.maps.Marker({
                position: new google.maps.LatLng(data[i].latitude, data[i].longitude),
                icon: image,
                map: map
            });
        }
        var request = {
            origin: start,
            destination: end,
            waypoints: waypts,
            optimizeWaypoints: true,
            travelMode: google.maps.TravelMode.WALKING
        };
    }
    directionsService.route(request, function (response, status) {
        if (status == google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(response);
        }
    });
};

/* *** MAIN *** */

$(function () {
    initSidebar();
    /* MAP */
    initBlankMap();
    google.maps.event.addDomListener(window, 'load', initStartMarkers);

    //loadDayData();
    //google.maps.event.addDomListener(window, 'load', initDayMarkers);
});
