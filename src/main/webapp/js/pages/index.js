function loadCategory(category) {
    $.ajax({
        url: window.location.origin + '/portal?command=placeJSON&category=' + category,
        success: updatePlaces,
        error: updatePlaces
    });

    function updatePlaces(data) {
        if(!data) return false;
        var source = $("#place-template").html();
        var template = Handlebars.compile(source);
        var html = template(data);
        $('#dropdown-' + category).html(html);
    };
};


$(function() {
    loadCategory('architecture');
    loadCategory('churches');
    loadCategory('theatres');
    loadCategory('restaurants');
    loadCategory('hotels');
});
