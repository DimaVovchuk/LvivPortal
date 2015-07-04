var loadPlaces = function () {
    $.ajax({
        url: window.location.origin + '/portal?command=placeJSON',
        success: updatePlaces,
        error: updatePlaces
    })
};

var updatePlaces = function (data) {
    if (!data) return false;
    var source = $("#admin-places-template").html();
    var template = Handlebars.compile(source);
    var html = template(data);
    $('#admin-places-table').html(html);
};