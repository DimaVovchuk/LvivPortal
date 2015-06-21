<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
	<title>Show All Users</title>

	<link href="https://cdn.datatables.net/1.10.7/css/jquery.dataTables.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
	<jsp:include page="/views/elements/css.jsp"/>
	<jsp:include page="/views/elements/script.jsp"/>
	<script src="https://cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

	<script>
		$(document).ready(function () {
			$('#example').dataTable({
				"language": {
					"lengthMenu": "Display _MENU_ records per page",
					"zeroRecords": "Nothing found - sorry",
					"info": "Showing page _PAGE_ of _PAGES_",
					"infoEmpty": "No records available",
					"infoFiltered": "(filtered from _MAX_ total records)"
				}
			});
		});</script>

</head>
<body>
<table id="example" class="display" cellspacing="0" width="100%">
	<thead>
	<tr>
		<th>User Id</th>
		<th>Rating</th>
		<th>Name</th>
		<th>Surname</th>
		<th>Login</th>
		<th>Mail</th>
		<th>Phone</th>
		<th>Status</th>
		<th>Role</th>
	</tr>
	</thead>

	<tfoot>
	<tr>
		<th>User Id</th>
		<th>Rating</th>
		<th>Name</th>
		<th>Surname</th>
		<th>Login</th>
		<th>Mail</th>
		<th>Phone</th>
		<th>Status</th>
		<th>Role</th>
	</tr>
	</tfoot>
	<tbody>
	<c:forEach items="${AllUsers}" var="elem">
		<tr>
			<td>${elem.key.id}</td>
			<td>${elem.key.rating}</td>
			<td>${elem.key.name}</td>
			<td>${elem.key.surname}</td>
			<td>${elem.key.login}</td>
			<td>${elem.key.mail}</td>
			<td>${elem.key.phone}</td>
			<td><button class="btn modal-trigger type="submit" data-target="changestatus"
				id="btn1" onclick="$('#uuserStatusID').val('${elem.key.id}')"> ${elem.key.status} </button></td></td>

			<td><button class="btn modal-trigger type="submit" data-target="changerole"
				id="btn1" onclick="$('#uid').val('${elem.key.id}')"> ${elem.value} </button></td>

				<%--<td>--%>
				<%--<label>--%>
				<%--<button onclick="<c:set var="salary" scope="request" value="${elem.key.id}"/>">--%>
				<%--<a class="waves-effect waves-light btn modal-trigger" href="#changerole">${elem.value}</a>--%>
				<%--</button>--%>
				<%--</label>--%>
				<%--</td>--%>
		</tr>
	</c:forEach>
	</tbody>

</table>
<div id="changestatus" class="modal">
	<div class="modal-content">
		<h4>Modal Header</h4>
		<form id="change_status" action="/portal/showalluser" method="post">
			<input type="hidden" name="command" value="showAllUser">
			<input type="hidden" name="requestType" value="changeStatus">
			<input placeholder="Placeholder" id="uuserStatusID" name="servletUserId" type="hidden">

			<input name="changeStatucID" type="radio" value="1" id="1"/><label for="1">Active</label>
			<input name="changeStatucID" type="radio" value="3" id="2"/><label for="2">Bloked</label>

			<button class="btn waves-effect waves-light cyan darken-2" type="submit">OK</button>
		</form>
	</div>
</div>

<div id="changerole" class="modal">
	<div class="modal-content">
		<h4>Change Role</h4>
		<form id="change_role" action="/portal/showalluser" method="post">
			<input type="hidden" name="command" value="showAllUser">
			<input type="hidden" name="requestType" value="changeRole">
			<input placeholder="Placeholder" id="uid" name="servletUserId" type="hidden">

			<p><input name="changeRoleID" type="radio" value="1" id="3"/><label for="3">Admin</label></p>
			<p><input name="changeRoleID" type="radio" value="2" id="4"/><label for="4">User</label></p>
			<p><input name="changeRoleID" type="radio" value="3" id="5"/><label for="5">Guide</label></p>
			<p><input name="changeRoleID" type="radio" value="4" id="6"/><label for="6">Company</label></p>

			<button class="btn waves-effect waves-light cyan darken-2" type="submit">OK</button>
		</form>
	</div>
</div>
</body>
</html>
