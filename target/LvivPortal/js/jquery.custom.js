var initDropdownMenu = function () {
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
};

var initSelectMenu = function () {
    $('select').material_select();
};

var initCalendar = function () {
    $('.datepicker').pickadate({
        selectMonths: true
    });
};

var initModalWindows = function () {
    $('.modal-trigger').leanModal({
        dismissible: true
    });
};

var initParallax = function () {
    $(".parallax").parallax();
};

var initUserSidebar = function () {
    $(".button-menu").sideNav({
        menuWidth: 300,
        edge: 'right'
    });
};

var initImgMaterialBox = function () {
    $('.materialboxed').materialbox();
};

var initFotorama = function () {
    $('.fotorama').fotorama();
};

var initCollapsible = function () {
  $('.collapsible').collapsible();
};

var initStartModalWindow = function () {
    var startModal = $('#start-modal').data('modal');
    console.log(startModal);
    if (startModal == 'resetSendEmail') {
        $('#reset-send-email').openModal();
    }
    if (startModal == 'resetConfirm') {
        $('#reset-confirm').openModal();
    }
    if (startModal == 'signUpForm') {
        $('.login').hide();
        $('.register').show();
        $('#sign-up-switch').hide();
        $('#sign-in-switch').show();
        $('#sign-in').openModal();
    }
};

$(document).ready(function () {
    initDropdownMenu();
    initSelectMenu();
    initCalendar();
    initModalWindows();
    initParallax();
    initUserSidebar();
    initImgMaterialBox();
    initFotorama();
    initCollapsible();
    initStartModalWindow();
});