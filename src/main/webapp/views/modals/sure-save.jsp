<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdg" uri="customtags" %>

<div class="modal" id="sure-save-sign-out">
  <div id="user-trip-save-singin" class="modal-content">
    <p><cdg:l18n key="sign.out.message"/></p>

  <div class="ok-footer">
    <div class="modal-footer">

  <form action="portal?command=signOut" method="post">

    <button class="btn waves-effect waves-light cyan darken-2" type="submit" name="ok" value="ok">OK</button>
    <button class="btn waves-effect waves-light cyan darken-2" type="submit" name="no" value="no"><cdg:l18n key="no"/></button>
  </form>
      </div>
    </div>
    </div>

</div>