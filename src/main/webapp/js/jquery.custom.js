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
});