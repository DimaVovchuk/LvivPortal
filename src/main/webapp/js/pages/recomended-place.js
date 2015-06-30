var loadPlaceData = function () {

    $.ajax({
        url: window.location.origin + '/portal?command=replaceJSON',
        success: loadPlaces,
        error: loadPlaces
    })
};

var loadPlaces = function (data) {
    if (!data) return false;
    var source = $("#place-info-template").html();
    Handlebars.registerHelper("variable_x", function(input){
        return Session.get("x");
    });
    var template = Handlebars.compile(source);
    var html = template(data);
    $('#place-info-collection').html(html);
    imgHeight();
    disabled(data);
};

function disabled(data) {
    var siz = data.length;
    for (var i = 1; i < siz; i++){
        var rating = $("#up" + i).data('rating');
        var x = $("#up" + i).data('id');
        if (rating=='1'){$("#up" + x).addClass('disabled');}
        if (rating=='0'){$("#none" + x).addClass('disabled');}
        if (rating=='-1'){$("#down" + x).addClass('disabled');}
    }
}

function like(placeholder) {
    $.ajax({
        url: $(placeholder).attr('rel'),
        type: "GET",
        success:dissable(placeholder),
        error:function (){
            alert("testing error");
        }
    });
    return false;
}

$(function () {

    $('#form-add-place').on('submit', function (e) {

        e.preventDefault();

        $.ajax({
            type: 'post',
            url: window.location.origin + '/portal?command=addplace',
            data: $('form').serialize(),
//                success: function () {
//                    alert('form was submitted');
//                }
        });

    });

});

function dissable(placeholder) {
    var x = $(placeholder).data('id');
    $("#up" + x).removeClass('disabled');
    $("#none" + x).removeClass('disabled');
    $("#down" + x).removeClass('disabled');
    $(placeholder).addClass('disabled');
}

function none(placeholder) {
    $.ajax({
        url: $(placeholder).attr('rel'),
        type: "GET",
        success:dissable(placeholder),
        error:function (){
            alert("testing error");
        }
    });
    return false;
}

function dislike(placeholder) {
    $.ajax({
        url: $(placeholder).attr('rel'),
        type: "GET",
        success:dissable(placeholder),
        error:function (){
            alert("testing error");
        }
    });
    return false;
}

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
    paginate();
    initModalWindows();
};

var imgHeight = function () {
    var img = $('.place-img');
    var width = img.width();
    img.css({
        'height': width + 'px'
    });
    matchColumn();
};

var initRangeListeners = function () {
    var timePlace = $("#timePlace");
    timePlace.mousemove(function (e) {
        $("#timeValue").html($(this).val());
    });
    timePlace.change(function (e) {
        $("#timeValue").html($(this).val());
    });
};

$(function () {
    loadPlaceData();

    initRangeListeners();
});