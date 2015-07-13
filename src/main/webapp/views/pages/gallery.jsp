<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<html>
<head>
    <title><cdg:l18n key="gallery.title"/></title>
</head>

<jsp:include page="/views/elements/css.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div id="gallery" class="full-height">
    <div class="row">
        <div class="col l8 offset-l2 m10 offset-m1 s12 z-depth-2" style="padding: 20px">
            <h3 class="center-align"><cdg:l18n key="gallery.title"/></h3>

            <div class="divider" style="margin-bottom: 20px"></div>

            <div class="fotorama" data-transition="crossfade" data-nav="thumbs" data-loop="true" data-autoplay="true"
                 data-allowfullscreen="true" data-keyboard="true" data-width="100%" data-fit="scaledown">
                <c:forEach items="${AllUserPhoto}" var="elem">

                    <a href="${pageContext.request.contextPath}/upload/photo/${elem.reference}">
                        <img width="200" height="200"
                             src="${pageContext.request.contextPath}/upload/photo/${elem.reference}">
                    </a>
                </c:forEach>
            </div>

            <div class="divider" style="margin: 20px 0"></div>

            <form action="portal/upload" enctype="multipart/form-data" method="post">
                <c:set var="command" scope="session" value="upLoad"/>
                <div class="center-align">
                    <div class="file-field input-field" style="height: 45px">
                        <div class="btn cyan darken-2">
                            <span><cdg:l18n key="editplace.addphoto"/></span>
                            <input id="image-input" type="file" multiple name="sendfile"/>
                        </div>
                        <a id="image-clear" class="btn cyan darken-2" style="display: none"><cdg:l18n
                                key="editplace.clearimage"/></a>
                        <button id="image-submit" class="btn cyan darken-2" style="display: none" type="submit">
                            <cdg:l18n key="gallery.submit"/></button>
                    </div>
                    <div class="card z-depth-2">
                        <output id="image-preview"></output>
                    </div>
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
                            $('#image-clear, #image-submit, #image-preview').show();
                            picReader.readAsDataURL(file);
                        } else {
                            alert('Image Size is too big. Maximum size is 2MB.');
                            $(this).val('');
                        }
                    } else {
                        alert('You can only upload image.');
                        $(this).val('');
                    }
                }
            });
        }
    };

    $('#image-input').on('click', function () {
        $('.image-thumbnail').parent().remove();
        $('#image-submit').hide();
        $('#image-preview').hide();
        $(this).val('');
    });

    $('#image-clear').on('click', function () {
        $('.image-thumbnail').parent().remove();
        $('#image-submit').hide();
        $('#image-preview').hide();
        $('#image-input').val('');
        $(this).hide();
    });
</script>

</body>
</html>
