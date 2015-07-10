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

<div class="modal" id="name-way">
  <div id="user-trip-save-singin" class="modal-content">
    <p><cdg:l18n key="name.route"/></p>
    <form action="/portal?command=saveWay" method="post">
      <input id="name" value="way" name="name" type="text"/>
      <label for="name"><cdg:l18n key="plan.name"/></label>
    <div class="ok-footer">
      <div class="modal-footer">
          <button class="btn waves-effect waves-light cyan darken-2" type="submit" name="ok" value="ok">OK</button>
        <a class="btn modal-action modal-close  waves-effect waves-light cyan darken-2">Cancel</a>
      </div>
    </div>
    </form>
  </div>

</div>
