var loadPlaceData = function () {
    $.ajax({
        url: window.location.origin + '/portal?command=placeJSON',
        success: loadPlaces,
        error: loadPlaces
    })
};

var loadPlaces = function (data) {
    if (!data) return false;
    var source = $("#place-info-template").html();
    Handlebars.registerHelper("variable_x", function(){
        return Session.get("x");
    });
    var template = Handlebars.compile(source);
    var html = template(data);
    $('#place-info-collection').html(html);
    disabled(data);
};

var disabled = function (data) {
    var siz = data.length;
    for (var i = 1; i < siz; i++){
        var up_i = $("#up" + i);
        var rating = up_i.data('rating');
        var x = up_i.data('id');
        if (rating=='1'){$("#up" + x).addClass('disabled');}
        if (rating=='0'){$("#none" + x).addClass('disabled');}
        if (rating=='-1'){$("#down" + x).addClass('disabled');}
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

$(function () {
    initRangeListeners();
    loadPlaceData();

    setTimeout(function () {
        imgHeight();
        matchColumn();
        paginate();
    }, 500);

/*    $('#form-add-place').on('submit', function (e) {

        e.preventDefault();

        $.ajax({
            type: 'post',
            url: window.location.origin + '/portal?command=addplace',
            data: $('form').serialize(),
//                success: function () {
//                    alert('form was submitted');
//                }
        });

    });*/
});