/**
 * Created by Admin on 27.06.2015.
 */

var loadRecomendedPlaceData = function () {
    $.ajax({
        url: window.location.origin + '/portal?command=recomendedPlace',
        success: loadPlaces,
        error: loadPlaces
    })
};

var loadPlaces = function (data) {
    if (!data) return false;
    var source = $("#recomended-place-info-template").html();
    Handlebars.registerHelper("variable_x", function(input){
        return Session.get("x");
    });
    var template = Handlebars.compile(source);
    var html = template(data);
    $('#recomended-place-info-collection').html(html);
    heightLoad();
    loadAddButton(data);
};

var loadAddButton = function (data) {
    if (!data) return false;
    var source = $("#recomended-add-button").html();
    var template = Handlebars.compile(source);
    var html = template(data);
    $('#recomended-add-button-collection').html(html);
    heightLoad();
};

var heightLoad = function () {
    $(".match-col").matchHeight({
        property: 'height'
    })
    paginator();

};

var paginator = function () {
    $('#place-page-container').pajinate({
        items_per_page: 6,
        item_container_id: '.place-page-content',
        nav_panel_id: '.place-page-navigation',
        start_page: 0,
        nav_label_first: '',
        nav_label_prev: '',
        nav_label_next: '',
        nav_label_last: ''
    });

};

$(function () {
    loadRecomendedPlaceData();

});
