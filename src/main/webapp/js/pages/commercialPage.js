var loadPlaceAboutData = function () {
    $.ajax({
        url: window.location.origin + '/portal?command=commercialJSON',
        success: loadPlacesData,
        error: loadPlacesData
    })
};

var loadPlacesData = function (data) {
    if (!data) return false;
    var source = $("#commertial-info-template").html();
    var template = Handlebars.compile(source);
    var html = template(data);
    $('#commertial-info-collection').html(html);
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

var initRangeListeners = function () {
    var timePlace = $("#timePlace");
    timePlace.mousemove(function () {
        $("#timeValue").html($(this).val());
    });
    timePlace.change(function () {
        $("#timeValue").html($(this).val());
    });
};


var initCategoriesEventsPlace = function () {
    $('#category-place').on('click', function (e) {
        e.preventDefault();
        e.stopImmediatePropagation();
       // alert(window.location.origin + '/' + $(e.target).attr('href'));
        $.ajax({
            url: window.location.origin + '/' + $(e.target).attr('href'),
            success: loadPlacesData,
            error: loadPlacesData
        });
    });
};

$(function () {
    initRangeListeners();
    loadPlaceAboutData();
    initCategoriesEventsPlace();
});