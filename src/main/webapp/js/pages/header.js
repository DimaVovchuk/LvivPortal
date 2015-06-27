var initCheckboxToggle = function () {
    $('#dontKnowDate').change(function () {
        $('#days').animate({height: "toggle", opacity: "toggle"});
    });
    $('#automatic').change(function () {
        $('#places').animate({height: "toggle", opacity: "toggle"});
    });
    $('#lunch').change(function () {
        $('#lunchTimeBox').animate({height: "toggle", opacity: "toggle"});
    });
};

var initRangeListener = function () {
    var dayTime = $("#dayTime");
    dayTime.mousemove(function () {
        $("#dayTimeValue").html($(this).val());
    });
    dayTime.change(function () {
        $("#dayTimeValue").html($(this).val());
    });
    var lunchTime = $("#lunchTime");
    lunchTime.mousemove(function () {
        $("#lunchTimeValue").html($(this).val());
    });
    lunchTime.change(function () {
        $("#lunchTimeValue").html($(this).val());
    });
};

$(function () {
    initCheckboxToggle();
    initRangeListener();
});
