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
                        <th><cdg:l18n key="login.firstname"/></th>
                        <th><cdg:l18n key="login.lastname"/></th>
                        <th><cdg:l18n key="login.login"/></th>
                        <th><cdg:l18n key="login.email"/></th>
                        <th><cdg:l18n key="login.phone"/></th>
                        <th><cdg:l18n key="admin.rating"/></th>
                        <th><cdg:l18n key="admin.status"/></th>
                        <th><cdg:l18n key="admin.role"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th><cdg:l18n key="login.firstname"/></th>
                        <th><cdg:l18n key="login.lastname"/></th>
                        <th><cdg:l18n key="login.login"/></th>
                        <th><cdg:l18n key="login.email"/></th>
                        <th><cdg:l18n key="login.phone"/></th>
                        <th><cdg:l18n key="admin.rating"/></th>
                        <th><cdg:l18n key="admin.status"/></th>
                        <th><cdg:l18n key="admin.role"/></th>
                        <th></th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <c:forEach items="${requestScope.AllUsers}" var="elem">
                        <tr>
                            <td>${elem.key.name}</td>
                            <td>${elem.key.surname}</td>
                            <td>${elem.key.login}</td>
                            <td>${elem.key.mail}</td>
                            <td>${elem.key.phone}</td>
                            <td>${elem.key.rating}</td>
                            <td>
                                <c:if test="${elem.key.status == 1}">
                                    <cdg:l18n key="admin.active"/>
                                </c:if>
                                <c:if test="${elem.key.status == 3}">
                                    <cdg:l18n key="admin.disabled"/>
                                </c:if>
                            </td>
                            <td>${elem.value}</td>
                            <td class="right-align">
                                <form id="change-status${elem.key.id}" action="#" method="get">
                                    <input type="hidden" name="command" value="showAllUser">
                                    <input type="hidden" name="requestType" value="changeStatus">
                                    <input type="hidden" name="servletUserId" value="${elem.key.id}">
                                    <c:if test="${elem.key.status == 1}">
                                        <input type="hidden" name="changeStatusID" value="3">
                                    </c:if>
                                    <c:if test="${elem.key.status == 3}">
                                        <input type="hidden" name="changeStatusID" value="1">
                                    </c:if>
                                </form>

                                <c:if test="${elem.key.status == 1}">
                                    <button type="submit" form="change-status${elem.key.id}"
                                            class="btn cyan darken-2 waves-effect waves-light"><cdg:l18n key="admin.disable"/>
                                    </button>
                                </c:if>
                                <c:if test="${elem.key.status == 3}">
                                    <button type="submit" form="change-status${elem.key.id}"
                                            class="btn cyan darken-2 waves-effect waves-light"><cdg:l18n key="admin.enable"/>
                                    </button>
                                </c:if>
                                <button class="btn modal-trigger cyan darken-2" data-target="change-role"
                                        onclick="$('#user-id').val('${elem.key.id}')"><cdg:l18n key="admin.changerole"/>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div id="change-role" class="modal">
        <div class="modal-content">
            <h5 class="center-align">Change role</h5>

            <form id="change_role" action="#" method="get">
                <input type="hidden" name="command" value="showAllUser">
                <input type="hidden" name="requestType" value="changeRole">
                <input type="hidden" name="servletUserId" id="user-id">

                <p><input type="radio" name="changeRoleID" value="1" id="role1"/><label for="role1" class="black-text"><cdg:l18n key="role.admin"/></label>
                </p>

                <p><input type="radio" name="changeRoleID" value="2" id="role2"/><label for="role2" class="black-text"><cdg:l18n key="role.user"/></label>
                </p>

                <p><input type="radio" name="changeRoleID" value="3" id="role3"/><label for="role3" class="black-text"><cdg:l18n key="role.guide"/></label>
                </p>

                <p><input type="radio" name="changeRoleID" value="4" id="role4"/><label for="role4" class="black-text"><cdg:l18n key="role.company"/></label>
                </p>

                <div class="divider"></div><br>
                <button type="submit" class="btn waves-effect waves-light cyan darken-2">OK</button>
                <button type="reset" class="btn modal-close waves-effect waves-light cyan darken-2"><cdg:l18n key="button.cancel"/></button>
            </form>
        </div>
    </div>

</div>

<jsp:include page="/views/elements/footer.jsp"/>

<script>
    $('#admin-page-table').DataTable({
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
        }
    });
</script>

</body>
</html>
