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

<div id="adminPlaceRestore" class="modal">
	<div class="modal-content">
		<h4><cdg:l18n key="admin.restore.place"/></h4>

		<div id="place-id"></div>
		<a id="delPlace" onclick="restorePlace();" href="javascript:"
		   rel="" class="btn waves-effect waves-light cyan darken-2 modal-action modal-close"> OK </a>
		<button type="reset" class="btn modal-close waves-effect waves-light cyan darken-2"><cdg:l18n
				key="button.cancel"/></button>
	</div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>

<script>

var restorePlace = function () {
	$.ajax({
		url: window.location.origin + '/portal?command=adminCancelCommand&page=restore&id=' + currentId,
		success: restorePlaceToast()
	});
};
var currentId;
var restorePlaceToast = function () {
	Materialize.toast('<cdg:l18n key="admin.was.restore.page.restore"/>', 4000);
};

var restoreBtnAction = function (id) {
	currentId = id;
	$('#adminPlaceRestore').openModal();
};

var loadPlaces = function () {
	$.ajax({
		url: window.location.origin + '/portal?command=restoreDeletedPlaceJSON',
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
		var buttons = '<div class="right-align">' +
				'<button class="btn cyan darken-2 waves-effect waves-light delete-btn" style="margin-right: 5px" onclick="restoreBtnAction(' + data[i].id + ')"><cdg:l18n key="admin.restore.page.restore"/></button>' +
				'</div>';
		var row = [image, info, buttons];

		table.row.add(row).draw();
	}
};

loadPlaces();
</script>

</body>
</html>
