var loadCategories = function() {
    $.ajax({
        url: window.location.origin + '/portal?command=placeJSON',
        success: updatePlaces,
        error: updatePlaces
    });
};

var updatePlaces = function(data) {
    if(!data) return false;
    var source = $("#place-info-template").html();
    var template = Handlebars.compile(source);
    var html = template(data);
    $('#place-info-collection').html(html);
};

var loadEvents = function() {
    $('#dropdown-places').on('click', 'li', function(e){
        e.preventDefault();
        $.ajax({
            url: window.location.origin + '/' + $(e.target).attr('href'),
            success: updatePlaces,
            error: updatePlaces
        });
    });
};

$(function() {
    loadCategories();
    loadEvents();
});
