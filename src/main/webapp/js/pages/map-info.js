/* *** SIDEBAR *** */

var windowIDList = ['#map-itinerary', '#map-places'];

var linkProcess = function (id) {
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

/* *** MAP PLACES *** */

var loadCategories = function () {
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

var loadCategoriesEvents = function () {
    $('#dropdown-places').on('click', 'li', function (e) {
        e.preventDefault();
        $.ajax({
            url: window.location.origin + '/' + $(e.target).attr('href'),
            success: updatePlaces,
            error: updatePlaces
        });
    });
};

/* *** ROUTES *** */

var loadRoutes = function () {
    $.ajax({
        url: window.location.origin + '/portal?command=routes',
        success: updateRoutes,
        error: updateRoutes
    });
};

var updateRoutes = function (data) {
    if (!data) return false;
    var source = $("#route-info-template").html();
    var template = Handlebars.compile(source);
    var html = template(data);
    $('#route-info-collection').html(html);
    routesData = data;
};

var setupDayTrigger = function () {
    $(document).on('click', '.day-trigger', function (e) {
        e.preventDefault();
        var day = $(e.currentTarget).data('day');
        $('#places-day' + day).toggle();
    })
};

var setupMapDayTrigger = function () {
    $(document).on('click', '.map-day-trigger', function (e) {
        e.preventDefault();
        var show = $(e.currentTarget).data('show');
        var day = $(e.currentTarget).data('day');
        if (show === 1) {
            $(e.currentTarget).data('show', 0);
            $('#map-day' + day).html('Hide from map');
            initDayMarkers(day - 1);
            hideMarkers();
        }
        if (show === 0) {
            $(e.currentTarget).data('show', 1);
            $('#map-day' + day).html('Show on map');
            directionsDisplay.setMap(null);
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
    if (data.length > 1) {
        var start = new google.maps.LatLng(data[0].latitude, data[0].longitude);
        var image = {
            url: "${pageContext.request.contextPath}/upload/photo/" + data[0].imageReference,
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
            url: "${pageContext.request.contextPath}/upload/photo/" + data[data.length - 1].imageReference,
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
            console.log("${pageContext.request.contextPath}/upload/photo/" + data[i].imageReference);
            waypts.push({
                location: new google.maps.LatLng(data[i].latitude, data[i].longitude)
            });
            image = {
                url: "${pageContext.request.contextPath}/upload/photo/" + data[i].imageReference,
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
    /* SIDEBAR */
    initBlankMap();
    initLinkProcess();
    loadCategories();
    loadCategoriesEvents();
    loadRoutes();
    setupDayTrigger();
    setupMapDayTrigger();
    /* MAP */

    google.maps.event.addDomListener(window, 'load', initStartMarkers);

    //loadDayData();
    //google.maps.event.addDomListener(window, 'load', initDayMarkers);
});
