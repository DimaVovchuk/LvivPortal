<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
  <c:when test="${editPlace.category_id eq 1}">
    <select name="newCategory">
      <option value="1"><cdg:l18n key="editplace.architecture"/></option>
      <option value="2"><cdg:l18n key="editplace.architecture"/></option>
      <option value="3"><cdg:l18n key="editplace.theatres"/></option>
      <option value="4"><cdg:l18n key="editplace.hotels"/></option>
      <option value="5"><cdg:l18n key="editplace.restaurants"/></option>
    </select>
  </c:when>
  <c:when test="${editPlace.category_id eq 2}">
    <select name="newCategory">
      <option value="2"><cdg:l18n key="editplace.architecture"/></option>
      <option value="1"><cdg:l18n key="editplace.churches"/></option>
      <option value="3"><cdg:l18n key="editplace.theatres"/></option>
      <option value="4"><cdg:l18n key="editplace.hotels"/></option>
      <option value="5"><cdg:l18n key="editplace.restaurants"/></option>
    </select>
  </c:when>
  <c:when test="${editPlace.category_id eq 3}">
    <select name="newCategory">
      <option value="3"><cdg:l18n key="editplace.theatres"/></option>
      <option value="2"><cdg:l18n key="editplace.architecture"/></option>
      <option value="1"><cdg:l18n key="editplace.churches"/></option>
      <option value="4"><cdg:l18n key="editplace.hotels"/></option>
      <option value="5"><cdg:l18n key="editplace.restaurants"/></option>
    </select>
  </c:when>
  <c:when test="${editPlace.category_id eq 4}">
    <select name="newCategory">
      <option value="4"><cdg:l18n key="editplace.hotels"/></option>
      <option value="3"><cdg:l18n key="editplace.theatres"/></option>
      <option value="2"><cdg:l18n key="editplace.architecture"/></option>
      <option value="1"><cdg:l18n key="editplace.churches"/></option>
      <option value="5"><cdg:l18n key="editplace.restaurants"/></option>
    </select>
  </c:when>
  <c:otherwise>
    <select name="newCategory">
      <option value="5"><cdg:l18n key="editplace.restaurants"/></option>
      <option value="4"><cdg:l18n key="editplace.hotels"/></option>
      <option value="3"><cdg:l18n key="editplace.theatres"/></option>
      <option value="2"><cdg:l18n key="editplace.architecture"/></option>
      <option value="1"><cdg:l18n key="editplace.churches"/></option>
    </select>
  </c:otherwise>
</c:choose>
