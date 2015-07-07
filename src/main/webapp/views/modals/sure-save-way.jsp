<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07.07.2015
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdg" uri="customtags" %>

<div id="sure-save" class="modal">
  <div id="user-trip-save" class="modal-content">
    <c:choose>
      <c:when test="${userDataTrip.isSaved == false && userDataTrip.isFull != false}">
        <p><cdg:l18n key="sure.save"/></p>

        <div class="ok-footer">
          <div class="modal-footer">
            <a class="btn modal-action modal-close modal-trigger waves-effect waves-light cyan darken-2" data-target="name-way">Ok
            </a>
          </div>
        </div>
      </c:when>
      <c:when test="${userDataTrip == null}">
        <p><cdg:l18n key="any.current.route"/></p>

        <div class="ok-footer">
          <div class="modal-footer">
            <a class="modal-action modal-close btn waves-effect waves-light cyan darken-2">Ok
            </a>
          </div>
        </div>
      </c:when>
      <c:when test="${userDataTrip.isFull == false}">
        <p><cdg:l18n key="any.place.way"/></p>

        <div class="ok-footer">
          <div class="modal-footer">
            <a class="modal-action modal-close btn waves-effect waves-light cyan darken-2">Ok
            </a>
          </div>
        </div>
      </c:when>
      <c:otherwise>
        <div class="ok-footer">
          <div class="modal-footer">
            <p><cdg:l18n key="sure.already.saved"/></p>
            <a class="btn modal-trigger waves-effect waves-light cyan darken-2"  href="/portal?command=updateWay">
              <cdg:l18n key="update"/>
            </a>
            <a class="btn modal-action modal-close modal-trigger waves-effect waves-light cyan darken-2" data-target="name-way">
              <cdg:l18n key="save"/>
            </a>
            <a class="modal-action modal-close btn waves-effect waves-light cyan darken-2">
              <cdg:l18n key="cancel"/>
            </a>
          </div>
        </div>
      </c:otherwise>
    </c:choose>
  </div>
</div>
