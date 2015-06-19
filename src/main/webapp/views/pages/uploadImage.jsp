<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head></head>
<body>
<form method=post enctype=multipart/form-data stream action="portal/upload">
  <input type="hidden" name="command" value="upLoad">
  <c:set var="typePhoto" scope="session" value="avatarFoto"/>
  <input type="file" style="width:100%" name="sendfile">
  <input type=submit value="Sent file">
</form>
</body>
</html>