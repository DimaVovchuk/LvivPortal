<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<h4>Choose Criteries</h4>
<form action="portal?command=createUserData"
      method="post" style="position:absolute;padding:5px">
  Кількість днів<br>
  <select  width="auto" name="dayCount">
    <option disabled></option>
    <c:forEach var="i" begin="1" end="5">
      <option width="10px" value="${i}"><c:out value="${i}"/></option>
    </c:forEach>
  </select>
  <br>
  Кількість годин<br>
  <select  width="auto" name="dayTime">
    <option disabled></option>
    <c:forEach var="i" begin="1" end="10">
      <option width="10px" value="${i}"><c:out value="${i}"/></option>
    </c:forEach>
  </select>
  <br>
  <input type="checkbox" name="dontNowTime" value="dontNow">I dont know a time<br>
    <input type="checkbox" value="architectural"><c:out value="Architectural sights"/>
    <input type="checkbox" value="churches"><c:out value="Churches"/>
    <input type="checkbox" value="theatres"><c:out value="Theatres"/>
    <input type="checkbox" value="hotels"><c:out value="Hotels"/>
    <input type="checkbox" value="restaurants"><c:out value="restaurants"/>
  <br>
  <input type="checkbox" name="dontBegin" value="dontBegin">Without begin<br>
  <input type="text" name="begin" value="${begin}">Begin place<br>
  <input type="checkbox" name="dontEnd" value="dontEnd">With out end<br>
  <input type="text" name="end" value="${end}">End place<br>
  <input type="checkbox" name="isCaffees" value="isCaffees" checked>With caffee<br>
  <input type="checkbox" name="isHotel" value="isCaffees" checked>With hotel<br>
  <select  width="auto" name="timeBetweenCaffees">Time for caffee
    <option disabled></option>
    <c:forEach var="i" begin="2" end="5">
      <option width="10px" value="${i}"><c:out value="${i}"/></option>
    </c:forEach>
  </select>
  <br>
  <input type="submit" value="Button">
</form>

</body>
</html>
