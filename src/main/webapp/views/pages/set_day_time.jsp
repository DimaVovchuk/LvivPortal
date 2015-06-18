<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="portal?command=countDaysCount"
      method="post" style="position:absolute;padding:5px">
  Кількість днів<br>
    <select  width="auto" name="dayCount">
      <option disabled></option>
      <c:forEach var="i" begin="1" end="5">
        <option width="10px" value="${i}"><c:out value="${i}"/></option>
      </c:forEach>
    </select>
    <br>
    <input type="submit" value="${submit}">
</form>

</body>
</html>
