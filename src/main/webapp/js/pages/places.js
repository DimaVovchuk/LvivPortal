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
    var width = $('.place-img').width();
    $('.place-img').css({
        'height': width + 'px'
    });
};

$(function () {
    matchColumn();
    imgHeight();
    paginate();
});