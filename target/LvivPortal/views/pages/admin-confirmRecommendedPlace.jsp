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
    <div class="row">
        <div class="col l8 offset-l2 m12 s12 z-depth-2" style="padding: 20px">
            <div class="row">
                <div class="section">
                    <h3 class="center-align"><cdg:l18n key="admin.title"/></h3>

                    <div class="row">
                        <div class="col l10 offset-l1 m12 s12">
                            <table id="admin-page-table" class="display" cellspacing="0" width="100%">
                                <thead>
                                <tr>
                                    <th><cdg:l18n key="admin.edit.places.image"/></th>
                                    <th><cdg:l18n key="admin.edit.places.info"/></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th><cdg:l18n key="admin.edit.places.image"/></th>
                                    <th><cdg:l18n key="admin.edit.places.info"/></th>
                                    <th></th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="confirmRecomPlaceCencel" class="modal" style="max-width: 400px">
    <div class="modal-content">
        <h5><cdg:l18n key="admin.reject.recomendation"/></h5>
        <div class="divider" style="margin-bottom: 20px"></div>
        <div id="place-id"></div>
        <a id="delPlace" onclick="cancelPlace();" href="javascript:"
           rel="" class="btn waves-effect waves-light cyan darken-2 modal-action modal-close"> OK </a>

        <button type="reset" class="btn modal-close waves-effect waves-light cyan darken-2"><cdg:l18n
                key="button.cancel"/></button>
    </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>

<script>
    var cancelPlace = function () {
        $.ajax({
            url: window.location.origin + '/portal?command=adminCancelCommand&page=recom&id=' + currentId,
            success: cancelPlaceToast()
        });
    };
    var currentId;
    var cancelPlaceToast = function () {
        Materialize.toast('<cdg:l18n key="admin.confirm.recommended.way.was.cancel"/>', 4000);
    };

    var cancelBtnAction = function (id) {
        currentId = id;
        $('#confirmRecomPlaceCencel').openModal();
    };

    var loadPlaces = function () {
        $.ajax({
            url: window.location.origin + '/portal?command=confirmRecommendedPlaceJSON',
            success: updatePlaces,
            error: updatePlaces
        })
    };

    var updatePlaces = function (data) {
        var table = $('#admin-page-table').DataTable({
            language: {
                emptyTable: "<cdg:l18n key="datatables.emptytable"/>",
                info: "<cdg:l18n key="datatables.info"/>",
                infoEmpty: "<cdg:l18n key="datatables.infoempty"/>",
                infoFiltered: "<cdg:l18n key="datatables.infofiltered"/>",
                lengthMenu: '<span style="color: #000; font-size: 15px"><cdg:l18n key="admin.tabledisplay"/></span>' +
                '<select id="table-display-number" class="browser-default">' +
                '   <option value="10">10</option>' +
                '   <option value="25">25</option>' +
                '   <option value="50">50</option>' +
                '   <option value="100">100</option>' +
                '</select>',
                search: '<span style="color: #000; font-size: 15px"><cdg:l18n key="button.search"/></span>',
                zeroRecords: '<cdg:l18n key="datatables.zerorecords"/>',
                paginate: {
                    first: '<cdg:l18n key="datatables.paginate.first"/>',
                    last: '<cdg:l18n key="datatables.paginate.last"/>',
                    next: '<cdg:l18n key="datatables.paginate.next"/>',
                    previous: '<cdg:l18n key="datatables.paginate.previous"/>'
                }
            },
            columns: [
                {"width": "10%"},
                {"width": "50%"},
                {"width": "40%"}
            ]
        });
        for (var i = 0; i < data.length; i++) {
            var image = '<div class="center-align"><img class="circle responsive-img" src="/upload/photo/' + data[i].imageReference + '" style="width: 50px; height: 50px"></div>';
            var info = data[i].name + '<br>' + data[i].adress;
            var buttons = '<div class="right-align">' + '<a href="/portal?command=editPlace&editPlaceID=' + data[i].id + '&recomended=confirm" class="btn cyan darken-2 waves-effect waves-light" style="margin-right: 5px"><cdg:l18n key="admin.edit.places.confirm"/></a>' +
                    '<button class="btn cyan darken-2 waves-effect waves-light delete-btn" style="margin-right: 5px" onclick="cancelBtnAction(' + data[i].id + ')"><cdg:l18n key="admin.edit.places.cancel"/></button>' +
                    '</div>';
            var row = [image, info, buttons];

            table.row.add(row).draw();
        }
    };

    loadPlaces();
</script>

</body>
</html>
