var loadPlaceAboutData = function () {

    $.ajax({
        url: window.location.origin + '/portal?command=userPlaceJSONCommand',
        success: loadPlacesData,
        error: loadPlacesData
    })
};

var loadPlacesData = function (data) {
    if (!data) return false;
    var source = $("#userPlace-info-template").html();
    var template = Handlebars.compile(source);
    var html = template(data);
    $('#userPlace-info-collection').html(html);
    setTimeout(function () {
        imgHeight();
        matchColumn();
        paginate();
    }, 0);

};

var paginate = function () {
    $('#place-page-container').pajinate({
        items_per_page: 6,
        item_container_id: '.place-page-content',
        nav_panel_id: '.place-page-navigation',
        nav_label_first: '',
        nav_label_prev: '',
        nav_label_next: '',
        nav_label_last: ''
    });
};

var matchColumn = function () {
    $(".match-col").matchHeight({
        property: 'height'
    });
    initModalWindows();
};

var imgHeight = function () {
    var img = $('.place-img');
    var width = img.width();
    img.css({
        'height': width + 'px'
    });
};

var initCategoriesEventsPlace = function () {
    $('#userPlacecategory-place').on('click', function (e) {
        e.preventDefault();
        e.stopImmediatePropagation();
        $.ajax({
            url: window.location.origin + '/' + $(e.target).attr('href'),
            success: loadPlacesData,
            error: loadPlacesData
        });
    });
};
var initRangeListeners = function () {
    var timePlace = $("#timePlace");
    timePlace.mousemove(function () {
        $("#timeValue").html($(this).val());
    });
    timePlace.change(function () {
        $("#timeValue").html($(this).val());
    });
};
var addPlace = function () {
    $('#form-add-place').on('submit', function (e) {
        e.preventDefault();
       // e.stopImmediatePropagation();
        $.ajax({
            type: 'post',
            url: window.location.origin + '/portal?command=addplace',
            data: $('form').serialize(),
        });
    });
};

var recommendPlace = function (str) {
        e.preventDefault();
        // e.stopImmediatePropagation();
        $.ajax({
            type: 'post',
            url: window.location.origin + '/portal?command=recommendPlace&place_id' + str,
            data: $('form').serialize(),
        });
};

var deletedFavorPlace = function () {
    $('#delete-favor-place').on('submit', function (e) {
        e.preventDefault();
        e.stopImmediatePropagation();
        $.ajax({
            url: window.location.origin + '/portal?command=deletePlace',
            data: $('#delete-favor-place').serialize(),
            success: loadPlaceAboutData,
            error: loadPlaceAboutData
        });
    });
};
$(function () {
    initRangeListeners();
    loadPlaceAboutData();
    initCategoriesEventsPlace();
    addPlace();
});
