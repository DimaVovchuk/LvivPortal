<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<html>
<head>
  <title><cdg:l18n key="addnewplace.title"/></title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta name="keywords" content=""/>
</head>
<jsp:include page="/views/elements/css.jsp"/>

<body>
<jsp:include page="/views/elements/header.jsp"/>
<div id="edit-place">
  <h4 class="center-align"><cdg:l18n key="addnewplace.title"/></h4>

  <div class="row">
    <div class="col l8 offset-l2 m12 s12 z-depth-2" style="padding: 20px">
      <div class="divider"></div>
      <form method=post enctype=multipart/form-data >
        <c:set var="command" scope="session" value="saveNewPlace"/>
        <c:set var="typePhoto" scope="session" value="placeFoto"/>
        <div class="center-align">
          <h5><cdg:l18n key="editplace.addphoto"/></h5>

          <div class="file-field input-field" style="height: 45px">
            <div class="btn cyan darken-2">
              <span><cdg:l18n key="editplace.selectimage"/></span><input id="image-input" type="file" multiple name="sendfile"/>
            </div>
            <button id="image-clear" class="btn cyan darken-2"><cdg:l18n key="editplace.clearimage"/></button>
          </div>
          <div class="card z-depth-2">
            <output id="image-preview"></output>
          </div>
        </div>

        <p><b><cdg:l18n key="editplace.placenameUA"/></b></p>
        <input value="" id="addPlaceNameUA" type="text" name="addPlaceNameUA">


        <p><b><cdg:l18n key="editplace.placenameEN"/></b></p>
        <input value="" id="addPlaceNameEN" type="text" name="addPlaceNameEN">


        <p><b><cdg:l18n key="editplace.placediscUA"/></b></p>

        <div class="input-field">
					<textarea id="addPlaceDescriptionUA" name="addPlaceDescriptionUA"
                              class="materialize-textarea"></textarea>
        </div>

        <p><b><cdg:l18n key="editplace.placenameEN"/></b></p>

        <div class="input-field">
					<textarea id="addPlaceDescriptionEN" name="addPlaceDescriptionEN"
                              class="materialize-textarea"></textarea>
        </div>

        <p><b><cdg:l18n key="editplace.placepricUA"/></b></p>
        <input value="" id="addPlacePriceUA" type="text" name="addPlacePriceUA">

        <p><b><cdg:l18n key="editplace.placepriceEN"/></b></p>
        <input value="" id="addPlacePriceEN" type="text" name="addPlacePriceEN">

        <p><b><cdg:l18n key="editplace.choseplacecategory"/></b></p>
        <select name="newCategory">
          <option value="6"><cdg:l18n key="editplace.otherPlaces"/></option>
          <option value="1"><cdg:l18n key="editplace.architecture"/></option>
          <option value="2"><cdg:l18n key="editplace.churches"/></option>
          <option value="3"><cdg:l18n key="editplace.theatres"/></option>
          <option value="4"><cdg:l18n key="editplace.hotels"/></option>
          <option value="5"><cdg:l18n key="editplace.restaurants"/></option>
        </select>

        <p><b><cdg:l18n key="editplace.placephone"/></b></p>
        <input value="" id="addPlacePhone" type="text" name="addPlacePhone">

        <p><b><cdg:l18n key="editplace.placetime"/></b></p>
        <input value="" id="addPlaceTime" type="text" name="addPlaceTime">

        <p><b><cdg:l18n key="editplace.placeaddressUA"/></b></p>
        <input value="" id="addPlaceAddressUA" type="text" name="addPlaceAddressUA">

        <p><b><cdg:l18n key="editplace.placeaddressEN"/></b></p>
        <input value="" id="addPlaceAddressEN" type="text" name="addPlaceAddressEN">

        <div class="row">
          <div class="col s6">
            <p><b><cdg:l18n key="editplace.placealat"/></b></p>
            <input value="" id="addPlaceLatitude" type="text" name="addPlaceLatitude">
          </div>
          <div class="col s6">
            <p><b><cdg:l18n key="editplace.placealon"/></b></p>
            <input value="" id="addPlacelongitude" type="text" name="addPlacelongitude">
          </div>
        </div>
        <br>

        <div class="center-align">
          <button class="btn waves-effect waves-light cyan darken-2" type="submit" name="save"><cdg:l18n
                  key="editplace.placesavechange"/></button>
          <button class="btn waves-effect waves-light cyan darken-2" type="reset" name="cancel"><cdg:l18n
                  key="editplace.placecancel"/></button>
        </div>
      </form>
    </div>
  </div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>
<script>
  window.onload = function () {
    if (window.File && window.FileList && window.FileReader) {
      $('#image-input').on('change', function (event) {
        var files = event.target.files;
        var output = document.getElementById('image-preview');
        for (var i = 0; i < files.length; i++) {
          var file = files[i];
          if (file.type.match('image.*')) {
            if (files[0].size < 2097152) {
              var picReader = new FileReader();
              picReader.addEventListener('load', function (event) {
                var picFile = event.target;
                var div = document.createElement("div");
                div.innerHTML = '<img class="image-thumbnail" src="' + picFile.result + '"/>';
                output.insertBefore(div, null);
              });
              $('#image-clear, #image-preview').show();
              picReader.readAsDataURL(file);
            } else {
              //alert('Image Size is too big. Maximum size is 2MB.');
              Materialize.toast('<cdg:l18n key="image.size.big"/>', 4000);
              $(this).val('');
            }
          } else {
            //alert('You can only upload image.');
            Materialize.toast('<cdg:l18n key="load.image.only"/>', 4000);
            $(this).val('');
          }
        }
      });
    }
  };

  $('#image-input').on('click', function () {
    $('.image-thumbnail').parent().remove();
    $('#image-preview').hide();
    $(this).val('');
  });

  $('#image-clear').on('click', function () {
    $('.image-thumbnail').parent().remove();
    $('#image-preview').hide();
    $('#image-input').val('');
    $(this).hide();
  });

  var placeDescriptionUA = $('#placeDescriptionUA');
  var placeDescriptionEN = $('#placeDescriptionEN');
</script>
</body>
</html>
