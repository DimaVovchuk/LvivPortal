<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
  <c:when test="${editPlace.category_id eq 1}">
    <select name="newCategory">
      <option value="1">Architectural sights</option>
      <option value="2">Churches</option>
      <option value="3">Theatres</option>
      <option value="4">Hotels</option>
      <option value="5">Restaurants</option>
    </select>
  </c:when>
  <c:when test="${editPlace.category_id eq 2}">
    <select name="newCategory">
      <option value="2">Churches</option>
      <option value="1">Architectural sights</option>
      <option value="3">Theatres</option>
      <option value="4">Hotels</option>
      <option value="5">Restaurants</option>
    </select>
  </c:when>
  <c:when test="${editPlace.category_id eq 3}">
    <select name="newCategory">
      <option value="3">Theatres</option>
      <option value="2">Churches</option>
      <option value="1">Architectural sights</option>
      <option value="4">Hotels</option>
      <option value="5">Restaurants</option>
    </select>
  </c:when>
  <c:when test="${editPlace.category_id eq 4}">
    <select name="newCategory">
      <option value="4">Hotels</option>
      <option value="3">Theatres</option>
      <option value="2">Churches</option>
      <option value="1">Architectural sights</option>
      <option value="5">Restaurants</option>
    </select>
  </c:when>
  <c:otherwise>
    <select name="newCategory">
      <option value="5">Restaurants</option>
      <option value="4">Hotels</option>
      <option value="3">Theatres</option>
      <option value="2">Churches</option>
      <option value="1">Architectural sights</option>
    </select>
  </c:otherwise>
</c:choose>
