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

$(".match-col").matchHeight({
    property: 'height'
});