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
    /* Modal login form */
    $('.modal-trigger').leanModal({
        dismissible: true
    });
    $('#open-sign-up').on('click', function(){
        $('#sign-up').openModal();
        $('#sign-in').closeModal();
    });
    $('#open-sign-in').on('click', function(){
        $('#sign-in').openModal();
        $('#sign-up').closeModal();
    });
    /* Banner */
    $(".parallax").parallax();
});