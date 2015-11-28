<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 03.07.2015
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdg" uri="customtags" %>


  <div id="place-is-added" class="modal place-in-db">
    <div class="modal-content place-db">
      <p><cdg:l18n key="place.added"/></p>
        <div class="ok-footer" id="cancel-route">
          <div class="modal-footer">
            <button class="btn modal-action modal-close waves-effect waves-light cyan darken-2">Ok
            </button>
          </div>
        </div>
    </div>
  </div>