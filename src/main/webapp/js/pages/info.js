/**
 * Created by Admin on 05.07.2015.
 */
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



$(function () {
    disabled();
});