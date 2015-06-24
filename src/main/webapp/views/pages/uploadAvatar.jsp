<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

</head>
<body>
<div>
  <form method=post enctype=multipart/form-data action="portal/upload">
    <input type="hidden" name="command" value="upLoadAvatar">
    <c:set var="typePhoto" scope="session" value="avatarFoto"/>
    <b>Choose image:</b>
    <input id="fileupload" type="file" name="sendfile">
    <%--multiple="multiple"--%>
    <br>
    <b>Preview:</b>
    <br>

    <div id="dvPreview">
    </div>
    <div class="btn">
      <input type=submit value="Upload">
    </div>
  </form>
</div>
</body>
</html>
