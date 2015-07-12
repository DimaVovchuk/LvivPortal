<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07.07.2015
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdg" uri="customtags" %>

<div class="modal" id="ololololololo">
  <div id="user-trip-save-singin" class="modal-content">
    <p><cdg:l18n key="name.route"/></p>
    <form id="save-route-after-json" action="#" method="post">
      <input value="way" id="name1" name="name" type="text"/>
      <label for="name1"><cdg:l18n key="plan.name"/></label>
      <div class="ok-footer">
        <div class="modal-footer">
          <button class="btn modal-action modal-close waves-effect waves-light cyan darken-2" type="submit">OK</button>
          <a class="btn modal-action modal-close waves-effect waves-light cyan darken-2">Cancel</a>
        </div>
      </div>
    </form>
  </div>

</div>
