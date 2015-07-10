<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>

<head>
    <title><cdg:l18n key="admin.title"/></title>
</head>

<jsp:include page="/views/elements/css.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div id="admin-page">

    <div class="section">
        <h3 class="center-align"><cdg:l18n key="admin.title"/></h3>

        <div class="row">
            <div class="col l10 offset-l1 m12 s12">
                <table id="admin-page-table" class="display" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>Image</th>
                        <th>Info</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>Image</th>
                        <th>Info</th>
                        <th></th>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>

<div id="adminPlaceDelete" class="modal">
    <div class="modal-content">
        <h4>Modal Header</h4>

        <div id="place-id"></div>
        <p>A bunch of text</p>
    </div>
    <div class="modal-footer">
        <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat">Agree</a>
    </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>

<script>
    var deleteBtnAction = function (id) {
        $('#place-id').html(id);
        $('#adminPlaceDelete').openModal();
    };

    var loadPlaces = function () {
        $.ajax({
            url: window.location.origin + '/portal?command=placeJSON',
            success: updatePlaces,
            error: updatePlaces
        })
    };

    var updatePlaces = function (data) {
        var table = $('#admin-page-table').DataTable({
            language: {
                lengthMenu: '<span style="color: #000; font-size: 15px"><cdg:l18n key="admin.tabledisplay"/></span>' +
                '<select id="table-display-number" class="browser-default">' +
                '   <option value="10">10</option>' +
                '   <option value="25">25</option>' +
                '   <option value="50">50</option>' +
                '   <option value="100">100</option>' +
                '</select>',
                search: '<span style="color: #000; font-size: 15px"><cdg:l18n key="button.search"/></span>',
                zeroRecords: "Nothing found - sorry",
                info: "Showing page _PAGE_ of _PAGES_",
                infoEmpty: "No records available",
                infoFiltered: "(filtered from _MAX_ total records)"
            },
            columns : [
                {"width": "10%"},
                {"width": "50%"},
                {"width": "40%"}
            ]
        });
        for (var i = 0; i < data.length; i++) {
            var image = '<div class="center-align"><img class="circle responsive-img" src="/upload/photo/' + data[i].imageReference + '" style="width: 50px; height: 50px"></div>';
            var info = data[i].name + '<br>' + data[i].adress;
            var buttons = '<div class="right-align">' +
                    '<a href="/portal?command=editPlace&editPlaceID=' + data[i].id + '" class="btn cyan darken-2 waves-effect waves-light" style="margin-right: 5px"><cdg:l18n key="admin.edit.places.edit"/></a>' +
                    '<button class="btn cyan darken-2 waves-effect waves-light delete-btn" style="margin-right: 5px" onclick="deleteBtnAction(' + data[i].id + ')"><cdg:l18n key="admin.edit.places.delete"/></button>' +
                    '</div>';
            var row = [image, info, buttons];

            table.row.add(row).draw();
        }
    };

    loadPlaces();
</script>

</body>
</html>
