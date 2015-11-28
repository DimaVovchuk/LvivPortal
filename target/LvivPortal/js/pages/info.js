/**
 * Created by Admin on 05.07.2015.
 */
var loadPlacesData = function (data) {
    if (!data) return false;
    var source = $("#place-info-template").html();
    var template = Handlebars.compile(source);
    var html = template(data);
    $('#place-info-collection').html(html);
    setTimeout(function () {
        imgHeight();
        matchColumn();
        paginate();
    }, 0);
    disabled(data);

};

var addPlace = function () {
    $('#form-add-place').on('submit', function (e) {
        e.preventDefault();
        e.stopImmediatePropagation();
        $.ajax({
            type: 'post',
            url: window.location.origin + '/portal?command=addplace',
            data: $('form').serialize(),
            success: loadWindow,
            error: loadWindow,
        });
    });
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


var disabled = function () {
        var up_i = $("#up");
        var rating = up_i.data('rating');
        var x = up_i.data('id');
        if (rating == '1') {
            $("#up").addClass('disabled');
        }
        if (rating == '0') {
            $("#none").addClass('disabled');
        }
        if (rating == '-1') {
            $("#down").addClass('disabled');
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
  //  var x = $(placeholder).data('id');
    $("#up").removeClass('disabled');
    $("#none").removeClass('disabled');
    $("#down").removeClass('disabled');
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

var addToFavorite = function (placeholder) {
    $.ajax({
        url: $(placeholder).attr('rel'),
        type: "POST",
        success: loadAddPlaceFavorite,
        error: loadAddPlaceFavorite,
    });
    return false;
};

//var addToFavorite = function () {
//    $('#add-to-favorite').on('click', function (e) {
//        e.preventDefault();
//        e.stopImmediatePropagation();
//        $.ajax({
//            url: window.location.origin + '/' + $(e.target).attr('href'),
//            success: loadPlacesData,
//            error: loadPlacesData
//        });
//    });
//};


$(function () {
    disabled();
    addPlace();
    //addToFavorite;
});