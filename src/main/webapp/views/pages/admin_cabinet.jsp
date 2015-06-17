<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Show All Users</title>
  <link href="https://cdn.datatables.net/1.10.7/css/jquery.dataTables.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
  <script src="https://cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
  <script>
  $(document).ready(function() {
    $('#example').dataTable( {
      "language": {
        "lengthMenu": "Display _MENU_ records per page",
        "zeroRecords": "Nothing found - sorry",
        "info": "Showing page _PAGE_ of _PAGES_",
        "infoEmpty": "No records available",
        "infoFiltered": "(filtered from _MAX_ total records)"
      }
    } );
  } );</script>
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
      <td>${elem.key.status}</td>
      <td>${elem.value}</td>
  </tr>
  </c:forEach>
  </tbody>

</table>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">change role</button>

<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Change role</h4>
      </div>

      <form id="change-role" method="post" action="change-role">
        <div class="modal-body">
          <label><input type="text" class="form-control" name="login" placeholder="Input login"></label>
          <br>
          <label><input type="radio" name="role" value="admin"> administrator</label>
          <label><input type="radio" name="role" value="user"> user</label>
          <label><input type="radio" name="role" value="kitchen"> kitchen</label>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-primary">Submit</button>
          <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
