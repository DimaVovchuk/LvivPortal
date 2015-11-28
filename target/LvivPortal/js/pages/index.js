function loadCategory(category) {
    //''window.location="/portal?command=placeJSON";
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
    }
}

function redirectPlace(category) {
    document.location.href = '/portal?command=place&category=' + category;

}


$(function() {
    loadCategory('architecture');
    loadCategory('churches');
    loadCategory('theatres');
    loadCategory('restaurants');
    loadCategory('hotels');
});
