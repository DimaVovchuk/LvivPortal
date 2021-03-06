/* Change localization language */
var language = function (lang) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "portal?command=locale&lang=" + lang, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            location.reload();
        }
    };
    xhr.send();
};

var initMobileSidebar = function () {
    $(".button-collapse").sideNav();
};

var initLoginForm = function () {
    $('.switch').click(function () {
        $('.login').animate({height: "toggle", opacity: "toggle"}, "slow");
        $('.register').animate({height: "toggle", opacity: "toggle"}, "slow");
        $('.sign').toggle();
    });
    $("#sign-in-form").validate();
    $("#sign-up-form").validate();
};

/* Login form radio toggle */
var changeRoleToggle = function (role) {
    if (role === 'user') {
        $('#first-last-form').show();
        $('#company-form').hide();
    }
    if (role === 'guide') {
        $('#first-last-form').show();
        $('#company-form').show();
    }
    if (role === 'company') {
        $('#first-last-form').hide();
        $('#company-form').show();
    }
};

var initNewRouteForm = function () {
    /* Checkbox toggle */
    var dateCheckbox = $('#dontKnowDate');
    if (dateCheckbox.is(':checked')) {
        $('#days').hide();
    }
    dateCheckbox.change(function () {
        $('#days').animate({height: "toggle", opacity: "toggle"});
    });
    var automaticCheckbox = $('#automatic');
    if (automaticCheckbox.is(':checked')) {
        $('#dateMenu').hide();
        $('#places').show();
    }
    automaticCheckbox.change(function () {
        $('#places').animate({height: "toggle", opacity: "toggle"});
        $('#dateMenu').animate({height: "toggle", opacity: "toggle"});
    });
    /* Range listeners */
    var dayTime = $("#dayTime");
    dayTime.mousemove(function () {
        $("#dayTimeValue").html($(this).val());
    });
    dayTime.change(function () {
        $("#dayTimeValue").html($(this).val());
    });
};

var activePageHeaderLink = function () {
    $('.header-link').each(function () {
        var link = $(this).data('link');
        var location = window.location.href;
        if ((/^.*(portal\?command=index).*$/.test(location) || /^(http:\/\/localhost:8080\/)$/.test(location)) && link === 'index') {
            $(this).addClass('active');
        }
        if (/^.*(portal\?command=place).*$/.test(location) && link === 'places') {
            $(this).addClass('active');
        }
        if (/^.*(portal\?command=showMap).*$/.test(location) && link === 'plan') {
            $(this).addClass('active');
        }
        if (/^.*(portal\?command=about).*$/.test(location) && link === 'about') {
            $(this).addClass('active');
        }
        if (/^.*(portal\?command=recomendedWay).*$/.test(location) && link === 'recommended') {
            $(this).addClass('active');
        }
    })
};

var checkReloadPages = function (data) {
    if (data == "success") {
        document.location.reload(true);
    }
    else {
    }
};

//var loadSignInWindow = function (data) {
//    Materialize.toast('You are loggined', 1600);
//}

var reloadPage = function () {
    $('#sign-in-form').on('submit', function (e) {
        e.preventDefault();
        e.stopImmediatePropagation();
        $.ajax({
            type: 'post',
            url: window.location.origin + '/portal?command=signIn',
            data: $('#sign-in-form').serialize(),
            success: checkReloadPages,
        });
    });
};

$(function () {
    initMobileSidebar();
    initLoginForm();
    initNewRouteForm();
    activePageHeaderLink();
    checkReloadPages();
    reloadPage();
});
