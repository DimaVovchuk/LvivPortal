<%--&lt;%&ndash;<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>&ndash;%&gt;--%>
<%--&lt;%&ndash;<html>&ndash;%&gt;--%>
<%--&lt;%&ndash;<head></head>&ndash;%&gt;--%>
<%--&lt;%&ndash;<body>&ndash;%&gt;--%>
<%--&lt;%&ndash;<form method=post enctype=multipart/form-data stream action="portal/upload">&ndash;%&gt;--%>
  <%--&lt;%&ndash;<input type="hidden" name="command" value="upLoad">&ndash;%&gt;--%>
  <%--&lt;%&ndash;<c:set var="typePhoto" scope="session" value="userFoto"/>&ndash;%&gt;--%>
  <%--&lt;%&ndash;<input type="file" style="width:100%" name="sendfile">&ndash;%&gt;--%>
  <%--&lt;%&ndash;<input type=submit value="���������">&ndash;%&gt;--%>
<%--&lt;%&ndash;</form>&ndash;%&gt;--%>
<%--&lt;%&ndash;</body>&ndash;%&gt;--%>
<%--&lt;%&ndash;</html>&ndash;%&gt;--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
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
      min-height: 400px;
      min-width: 400px;
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
    <input id="fileupload" type="file" style="width:100%" name="sendfile">
  <b>Preview</b>
  <br />
  <div id="dvPreview">
  </div>
    <input type=submit value="Sent">
  </form>
</div>
</body>
</html>
