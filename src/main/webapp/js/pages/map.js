/* *** SIDEBAR *** */
var initSidebar = function () {
    initLinkProcess();
    loadPlaces();
    initCategoriesEvents();
    loadRoutes();
    initDayTrigger();
    initMapDayTrigger();
    initImageMultiloadPreview();
};

var linkProcess = function (id) {
    var windowIDList = ['#map-itinerary', '#map-places', '#map-custom'];
    var li = '#li-' + id;
    var map = '#map-' + id;
    if (id == 'custom') {
        customMarkerVisible();
    } else {
        customMarkerUnvisible();
    }
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
var count = 0;
var initMapDayTrigger = function () {
    $(document).on('click', '.map-day-trigger', function (e) {
        e.preventDefault();
        var show = $(e.currentTarget).data('show');

        var day = $(e.currentTarget).data('day');
        if (show === 1 && count == 0) {
            count++;
            $(e.currentTarget).data('show', 0);
            $('#map-day' + day).html('Hide from map');
            initDayMarkers(day - 1);
            hideMarkers();
            showRoutesMarkers(day - 1)
        }
        if (show === 0) {
            count--;
            $(e.currentTarget).data('show', 1);
            $('#map-day' + day).html('Show on map');
            for (var i = 0; i < directionsDisplays.length; i++) {
                directionsDisplays[i].set('directions', null);
            }
            hideMarkers();
            showMarkers();
        }
    })
};
var directionsDisplays = [];
var getdirectionsDisplays = function (directionsDisplay) {
    directionsDisplays.push(directionsDisplay);
};

/* Sidebar - Custom */
var initImageMultiloadPreview = function () {
    window.onload = function () {
        if (window.File && window.FileList && window.FileReader) {
            $('#image-input').on('change', function (event) {
                var files = event.target.files;
                var output = document.getElementById('image-preview');
                for (var i = 0; i < files.length; i++) {
                    var file = files[i];
                    if (file.type.match('image.*')) {
                        if (files[0].size < 2097152) {
                            var picReader = new FileReader();
                            picReader.addEventListener('load', function (event) {
                                var picFile = event.target;
                                var div = document.createElement("div");
                                div.innerHTML = '<img class="image-thumbnail" src="' + picFile.result + '"/>';
                                output.insertBefore(div, null);
                            });
                            $('#image-clear, #image-preview').show();
                            picReader.readAsDataURL(file);
                        } else {
                            alert('Image Size is too big. Maximum size is 2MB.');
                            $(this).val('');
                        }
                    } else {
                        alert('You can only upload image files.');
                        $(this).val('');
                    }
                }
            })
        }
    };

    $('#image-input').on('click', function () {
        $('.image-thumbnail').parent().remove();
        $('#image-preview').hide();
        $(this).val('');
    });

    $('#image-clear').on('click', function () {
        $('.image-thumbnail').parent().remove();
        $('#image-preview').hide();
        $('#image-input').val('');
        $(this).hide();
    });
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

var initDayMarkers = function (dayNumber) {
    var points = [];
    var points1 = [];
    var len = (routesData[dayNumber].places.length);
    if (len > 10) {
        for (var i = 0; i <= len - 10; i++) {
            points.push(new google.maps.LatLng(routesData[dayNumber].places[i].latitude, routesData[dayNumber].places[i].longitude));
        }
        for (i = len - 10; i < len; i++) {
            points1.push(new google.maps.LatLng(routesData[dayNumber].places[i].latitude, routesData[dayNumber].places[i].longitude));
        }
        calcRoute(points1);
        calcRoute(points);
    } else {
        for (i = 0; i < (routesData[dayNumber].places.length); i++) {
            points.push(new google.maps.LatLng(routesData[dayNumber].places[i].latitude, routesData[dayNumber].places[i].longitude));
        }
        calcRoute(points);
    }
};

var calcRoute = function (data) {
    var directionsDisplay;
    var directionsService = new google.maps.DirectionsService();
    directionsDisplay = new google.maps.DirectionsRenderer();
    directionsDisplay.setMap(map);
    directionsDisplay.setOptions({suppressMarkers: true});
    if (data.length > 1 && data.length <= 10) {
        //var start = new google.maps.LatLng(data[0].latitude, data[0].longitude);
        var start = data[0];
        var image = {
            url: "",
            scaledSize: new google.maps.Size(40, 30),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        };
        new google.maps.Marker({
            //position: new google.maps.LatLng(data[0].latitude, data[0].longitude),
            position: data[0],
            icon: image,
            map: map
        });
        //var end = new google.maps.LatLng(data[data.length - 1].latitude, data[data.length - 1].longitude);
        var end = data[data.length - 1];
        image = {
            url: "",
            scaledSize: new google.maps.Size(40, 30),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(0, 0)
        };
        new google.maps.Marker({
            //position: new google.maps.LatLng(data[data.length - 1].latitude, data[data.length - 1].longitude),
            position: data[data.length - 1],
            icon: image,
            map: map
        });
        var waypts = [];
        for (var i = 1; i < (data.length - 1); i++) {
            waypts.push({
                //location: new google.maps.LatLng(data[i].latitude, data[i].longitude)
                location: data[i]
            });
            image = {
                url: "",
                scaledSize: new google.maps.Size(40, 30),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(0, 0)
            };
            new google.maps.Marker({
                //position: new google.maps.LatLng(data[i].latitude, data[i].longitude),
                position: data[i],
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

        directionsService.route(request, function (response, status) {
            if (status == google.maps.DirectionsStatus.OK) {
                directionsDisplay.setDirections(response);
            }
        });
        getdirectionsDisplays(directionsDisplay);
    }
};

/* Geocode position */
function geocodePosition(pos) {
    var geocoder = new google.maps.Geocoder();
    geocoder.geocode({
        latLng: pos
    }, function (responses) {
        if (responses && responses.length > 0) {
            updateMarkerAddress(responses[0].formatted_address);
        } else {
            updateMarkerAddress('Cannot determine address at this location.');
        }
    });
}

function updateMarkerPositionLat(latLng) {
    document.getElementById('latitude').value = latLng.lat();
    document.getElementById('latitudeHid').value = latLng.lat();
}

function updateMarkerPositionLon(latLng) {
    document.getElementById('longitude').value = latLng.lng();
    document.getElementById('longitudeHid').value = latLng.lng();
}

function updateMarkerAddress(str) {
    document.getElementById('customPlaceAdrress').value = str;
    document.getElementById('customPlaceAdrressHid').value = str;
}

var autocomplete;

function fillInAddress() {
    var place = autocomplete.getPlace();
    geocodePosition(place.geometry.location);
    updateMarkerPositionLat(place.geometry.location);
    updateMarkerPositionLon(place.geometry.location);
    customMarker.setPosition(place.geometry.location);
    map.setCenter(place.geometry.location);
}

var customMarker;
function initCustom() {
    var options = {
        location: new google.maps.LatLng(49.8426, 24.0278),
        radius: 10000,
        types: ['geocode'],
        componentRestrictions: {country: "ua"}
    };
    autocomplete = new google.maps.places.Autocomplete((document.getElementById('customPlaceAdrress')), options);
    google.maps.event.addListener(autocomplete, 'place_changed', function () {
        fillInAddress();
    });

    var latLng = new google.maps.LatLng(49.8426, 24.0278);
    customMarker = new google.maps.Marker({
        position: latLng,
        title: 'Custom place',
        map: map,
        visible: true,
        draggable: true
    });
    // Update current position info.
    updateMarkerPositionLat(latLng);
    updateMarkerPositionLon(latLng);
    geocodePosition(latLng);
    // Add dragging event listeners.
    google.maps.event.addListener(customMarker, 'drag', function () {
        updateMarkerPositionLat(customMarker.getPosition());
        updateMarkerPositionLon(customMarker.getPosition());
    });
    google.maps.event.addListener(customMarker, 'dragend', function () {
        geocodePosition(customMarker.getPosition());
    });
    customMarkerUnvisible();
}

function customMarkerUnvisible() {
    ////showMarkers();
    customMarker.setVisible(false);
}

function customMarkerVisible() {
    // hideMarkers();
     customMarker.setVisible(true);
     map.setOptions({
         zoom: 15,
         center: customMarker.position
     });
}

/* *** MAIN *** */
$(function () {
    initBlankMap();
    google.maps.event.addDomListener(window, 'load', initStartMarkers);
    initSidebar();
    initCustom();
});
