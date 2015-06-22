// var dataSet = null;
var loadCategories = function() {
    $.ajax({
        url: window.location.origin + '/portal?command=placeJSON',
        success: updatePlaces,
        error: updatePlaces
    });
};

var updatePlaces = function(data) {
    if(!data) return false;
    // dataSet = data;
    var source = $("#place-info-template").html();
    var template = Handlebars.compile(source);
    var html = template(data);
    $('#place-info-collection').html(html);
};

var loadEvents = function() {
    $('#dropdown-places').on('click', 'li', function(e){
        e.preventDefault();
        // if($(e.target).data('category') === 'all') {
        //  var sortedData= dataSet'
        // }
        // var sortedData = dataSet.filter(function(el){
        //  return el.category === $(e.target).data('category')
        // });
        // updateLocations(sortedData);
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