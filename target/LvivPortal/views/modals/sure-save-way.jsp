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

      <c:when test="${userDataTrip == null}">
        <%--<p><cdg:l18n key="sure.save"/></p>--%>
        <p><cdg:l18n key="any.way"/></p>
        <div class="ok-footer">
          <div class="modal-footer">
              <%--<a class="btn modal-action modal-close modal-trigger waves-effect waves-light cyan darken-2" data-target="name-way">Ok--%>
              <%--</a>--%>
            <a class="btn modal-action modal-close  waves-effect waves-light cyan darken-2">Ok</a>

          </div>
        </div>
      </c:when>

      <c:when test="${userDataTrip.isFull == false}">
        <%--<p><cdg:l18n key="sure.save"/></p>--%>
        <p><cdg:l18n key="any.place.route"/></p>
        <div class="ok-footer">
          <div class="modal-footer">
              <%--<a class="btn modal-action modal-close modal-trigger waves-effect waves-light cyan darken-2" data-target="name-way">Ok--%>
              <%--</a>--%>
            <a class="btn modal-action modal-close  waves-effect waves-light cyan darken-2">Ok</a>

          </div>
        </div>
      </c:when>

      <c:when test="${userDataTrip.isSaved == false && userDataTrip.isFull != false}">
        <%--<p><cdg:l18n key="sure.save"/></p>--%>
        <p><cdg:l18n key="name.route"/></p>
        <form id="save-route-json" action="#" method="post">
          <input id="name" value="way" name="name" type="text"/>
          <label for="name"><cdg:l18n key="plan.name"/></label>
        <div class="ok-footer">
          <div class="modal-footer">
            <%--<a class="btn modal-action modal-close modal-trigger waves-effect waves-light cyan darken-2" data-target="name-way">Ok--%>
            <%--</a>--%>
              <button class="btn modal-action modal-close waves-effect waves-light cyan darken-2" type="submit" name="ok" value="ok">OK</button>
              <a class="btn modal-action modal-close  waves-effect waves-light cyan darken-2">Cancel</a>

          </div>
        </div>
        </form>
      </c:when>

      <c:when test="${userDataTrip.recommended == true}">
        <%--<p><cdg:l18n key="sure.save"/></p>--%>
        <p><cdg:l18n key="name.route"/></p>
        <form id="save-route-json" action="#" method="post">
          <input value="way" name="name" type="text"/>
          <label for="name"><cdg:l18n key="plan.name"/></label>
          <div class="ok-footer">
            <div class="modal-footer">
                <%--<a class="btn modal-action modal-close modal-trigger waves-effect waves-light cyan darken-2" data-target="name-way">Ok--%>
                <%--</a>--%>
              <button class="btn modal-action modal-close waves-effect waves-light cyan darken-2" type="submit" name="ok" value="ok">OK</button>
              <a class="btn modal-action modal-close  waves-effect waves-light cyan darken-2">Cancel</a>

            </div>
          </div>
        </form>
      </c:when>

      <c:otherwise>
        <div class="ok-footer">
          <div class="modal-footer">
            <p><cdg:l18n key="sure.already.saved"/></p>
            <a onClick="updateWay(this);" class="btn modal-action modal-close modal-trigger waves-effect waves-light cyan darken-2" href="javascript:"  rel="portal?command=updateWay">
              <cdg:l18n key="update"/>
            </a>
            <a class="btn modal-trigger waves-effect waves-light cyan darken-2" data-target="ololololololo">
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

<div class="modal" id="ololololololo">
  <div id="user-trip-save-singin" class="modal-content">
    <p><cdg:l18n key="name.route"/></p>
    <form id="save-route-after-json" action="#" method="post">
      <input value="way" id="name1" name="name" type="text"/>
      <label for="name1"><cdg:l18n key="plan.name"/></label>
      <div class="ok-footer">
        <div class="modal-footer">
          <button class="btn waves-effect waves-light cyan darken-2" type="submit" id="create-name-saved">OK</button>
          <a class="btn waves-effect waves-light cyan darken-2" onclick="cancelSavePlace();">Cancel</a>
        </div>
      </div>
    </form>
  </div>

</div>

<script>

  var savePlace = function () {
    $('#save-route-json').on('submit', function (e) {
      e.preventDefault();
      e.stopImmediatePropagation();
      $.ajax({
        type: 'post',
        url: window.location.origin + '/portal?command=saveWay',
        data: $('#save-route-json').serialize(),
        success: loadWindow,
        error: loadWindow
      });
    });
  };
  var cancelSavePlace = function () {
    $('#ololololololo').closeModal();
    $('#sure-save').closeModal();
  };

  var savePlaceafter = function () {
    $('#save-route-after-json').on('submit', function (e) {
      $('#ololololololo').closeModal();
      $('#sure-save').closeModal();
      e.preventDefault();
      e.stopImmediatePropagation();
      $.ajax({
        type: 'post',
        url: window.location.origin + '/portal?command=saveWay',
        data: $('#save-route-after-json').serialize(),
        success: loadWindow,
        error: loadWindow
      });
    });
  };

  var updateWay = function (placeholder) {
//      e.preventDefault();
//      e.stopImmediatePropagation();
    $('#sure-save').closeModal();
      $.ajax({
        url: $(placeholder).attr('rel'),
        success: loadWindow
      });
  };

  var saveWay = function (placeholder) {
//      e.preventDefault();
//      e.stopImmediatePropagation();
    $('#sure-save').closeModal();
    $.ajax({
      url: $(placeholder).attr('rel'),
      success: loadWindow
    });
  };

  var loadWindow = function (data) {
    if (data == "1") {
      Materialize.toast('<cdg:l18n key="one.place"/>', 4000);
    }
    if (data == "2") {
      Materialize.toast('<cdg:l18n key="equals.way.db"/>', 4000);
    }
    if (data == "0") {
      Materialize.toast('<cdg:l18n key="way.is.saved"/>', 4000);
    }
    if (data == "3") {
      Materialize.toast('<cdg:l18n key="way.is.unupdated"/>', 4000);
    }
    if (data == "4") {
      Materialize.toast('<cdg:l18n key="way.is.updated"/>', 4000);
    }
  }

  $(function () {
    savePlace();
    savePlaceafter();
    //initCategoriesEventsPlace();
  });

</script>
