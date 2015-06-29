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

    $('form').on('submit', function (e) {

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
    disabled(data);
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
    initModalWindows();

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

var initModalWindows = function () {
    $('.modal-trigger').leanModal({
        dismissible: true
    });
};

$(function () {
    loadRecomendedPlaceData();
});
