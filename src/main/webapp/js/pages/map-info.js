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

var loadEvents = function () {
    $('#dropdown-places').on('click', 'li', function (e) {
        e.preventDefault();
        $.ajax({
            url: window.location.origin + '/' + $(e.target).attr('href'),
            success: updatePlaces,
            error: updatePlaces
        });
    });
};

var windowIDList = ['#map-itinerary', '#map-places'];

function linkProcess(id) {
    var li = '#li-' + id;
    var map = '#map-' + id;
    $(li).addClass('active');
    $(map).show();
    windowIDList.forEach(function (item) {
        if (item !== map) {
            $(item).hide();
        }
    });
}

var loadRoutes = function () {
    $.ajax({
        url: window.location.origin + '/portal?command=routesJSON',
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
};

$(function () {
    loadCategories();
    loadEvents();
    loadRoutes();

    $('.link-process').on('click', function(e) {
        var type = $(e.currentTarget).data('type');
        linkProcess(type);
        $('.link-process').removeClass('active');
        $(".link-process[data-type='"+type+"']").addClass('active');
    })

});
