/**
 * Created by Admin on 01.07.2015.
 */
var loadPlaceAboutData = function () {
    $.ajax({
        url: window.location.origin + '/portal?command=recomendedWayJSON',
        success: loadWaysData,
    })
};

var loadWaysData = function (data) {
    if (!data) return false;
    var source = $("#recomended-way-info-template").html();
    var template = Handlebars.compile(source);
    var html = template(data);
    $('#recomended-way-info-collection').html(html);
    disabled(data);
    setTimeout(function () {
        imgHeight();
        matchColumn();
        paginate();
    }, 200);

};

var disabled = function (data) {
    var siz = data.length;
    for (var i = 0; i <= siz - 1; i++) {
        var dat = data[i].id;
        var up_i = $("#up" + dat);
        var rating = up_i.data('rating');
        var x = up_i.data('id');
        if (rating == '1') {
            $("#up" + x).addClass('disabled');
        }
        if (rating == '0') {
            $("#none" + x).addClass('disabled');
        }
        if (rating == '-1') {
            $("#down" + x).addClass('disabled');
        }
    }
};


var like = function (placeholder) {

    $.ajax({
        url: $(placeholder).attr('rel'),
        type: "GET",
        success:dissable(placeholder),
        error:function (){
            alert("testing error");
        }
    });
    return false;
};

var dissable = function (placeholder) {

    var x = $(placeholder).data('id');
    $("#up" + x).removeClass('disabled');
    $("#none" + x).removeClass('disabled');
    $("#down" + x).removeClass('disabled');
    $(placeholder).addClass('disabled');
};

var none = function (placeholder) {
    $.ajax({
        url: $(placeholder).attr('rel'),
        type: "GET",
        success:dissable(placeholder),
        error:function (){
            alert("testing error");
        }
    });
    return false;
};



var dislike = function (placeholder) {
    $.ajax({
        url: $(placeholder).attr('rel'),
        type: "GET",
        success:dissable(placeholder),
        error:function (){
            alert("testing error");
        }
    });
    return false;
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