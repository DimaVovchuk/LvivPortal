/* Change localization language */
function language(lang) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "portal?command=locale&lang="+lang, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            location.reload();
        }
    };
    xhr.send();
}

$(document).ready(function ($) {
    /* Mobile sidebar */
    $(".button-collapse").sideNav();
    /* Sidebar */
    $(".button-menu").sideNav({
        menuWidth: 300,
        edge: 'right'
    });
    /* Dropdown menu */
    $(".dropdown-button").dropdown({
        constrain_width: false,
        belowOrigin: true
    });
    $(".dropdown-full").dropdown({
        constrain_width: true,
        belowOrigin: true
    });
    $(".dropdown-select").dropdown({
        constrain_width: true,
        belowOrigin: true
    });
    /* Select menu */
    $('select').material_select();
    /* Calendar */
    $('.datepicker').pickadate({
        selectMonths: true
    });
    /* Login form */
    $('.modal-trigger').leanModal({
        dismissible: true
    });
    $('.switch').click(function(){
        $('.login').animate({height: "toggle", opacity: "toggle"}, "slow");
        $('.register').animate({height: "toggle", opacity: "toggle"}, "slow");
        $('.sign').toggle();
    });
    $("#sign-in-form").validate();
    $("#sign-up-form").validate();
    /* Banner */
    $(".parallax").parallax();
    /* Truncate text */
    $(".body-content").dotdotdot({
        ellipsis: "... ",
        watch: "window"
    });
    /* Plan trip */
    $('#endDate').change(function () {
        var start = new Date($('#startDate').val());
        var end = new Date($(this).val());
        var diffDays = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24) + 1;
        if (diffDays <= 0) {
            $("#dateInfo").html('Wrong').toggleClass('error');
        }
        else $("#daysValue").html(diffDays);
    });
    $('#dontKnowDate').change(function () {
        $('#days').animate({height: "toggle", opacity: "toggle"});
    });

    $('#automatic').change(function () {
        $('#places').animate({height: "toggle", opacity: "toggle"});
    });
});