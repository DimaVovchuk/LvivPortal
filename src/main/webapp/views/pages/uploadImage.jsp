<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <style type="text/css">
    body
    {
      font-family: Arial;
      font-size: 10pt;
    }
    #dvPreview
    {
      filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);
      min-height: 300px;
      min-width: 300px;
      display: none;
    }
  </style>
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
  <script language="javascript" type="text/javascript">
    $(function () {
      $("#fileupload").change(function () {
        $("#dvPreview").html("");
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp)$/;
        if (regex.test($(this).val().toLowerCase())) {
          if ($.browser.msie && parseFloat(jQuery.browser.version) <= 9.0) {
            $("#dvPreview").show();
            $("#dvPreview")[0].filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = $(this).val();
          }
          else {
            if (typeof (FileReader) != "undefined") {
              $("#dvPreview").show();
              $("#dvPreview").append("<img />");
              var reader = new FileReader();
              reader.onload = function (e) {
                $("#dvPreview img").attr("src", e.target.result);
              }
              reader.readAsDataURL($(this)[0].files[0]);
            } else {
              alert("This browser does not support FileReader.");
            }
          }
        } else {
          alert("Please upload a valid image file.");
        }
      });
    });
  </script>
</head>
<body>
<div>
  <form method=post enctype=multipart/form-data stream action="portal/upload">
    <input type="hidden" name="command" value="upLoad">
    <c:set var="typePhoto" scope="session" value="userFoto"/>
    <b>Choose image:</b>
    <input id="fileupload" type="file" multiple="multiple" name="sendfile">
    <b>Preview:</b>
    <br />
    <div id="dvPreview">
    </div>
    <input type=submit value="Upload">
  </form>
</div>
</body>
</html>