<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>

<head>
    <title><cdg:l18n key="editplace.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
</head>

<jsp:include page="/views/elements/css.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div id="edit-place">
    <h4 class="center-align"><cdg:l18n key="editplace.editPlace"/></h4>

    <div class="row">
        <div class="col l8 offset-l2 m12 s12 z-depth-2" style="padding: 20px">
            <h5 class="center-align">Photo gallery</h5>

            <div class="row">
                <c:forEach items="${placeImageList}" var="elem">
                    <c:if test="${elem.deleted eq false}">
                        <div class="col l3 m4 s6" style="height: 200px">
                            <img class="materialboxed responsive-img"
                                 src="${pageContext.request.contextPath}/upload/photo/${elem.reference}">
                            <div class="center-align" style="margin-top: -35px">
                                <button class="btn btn-floating modal-trigger cyan darken-2" type="submit" data-target="deleteImage"
                                        id="btn1" onclick="$('#deleteImageID').val('${elem.id}')"><i
                                        class="material-icons">delete</i>
                                </button>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>

            <div id="deleteImage" class="modal">
                <div class="modal-content">
                    <h4>Modal Header</h4>

                    <form id="change_status" action="#" method="get">
                        <input type="hidden" name="command" value="deleteImageByDB">
                        <input placeholder="Placeholder" id="deleteImageID" name="deleteImageID" type="hidden">
                        <button class="btn waves-effect waves-light cyan darken-2" type="submit">YES</button>
                        <button class="modal-action modal-close btn waves-effect waves-light cyan darken-2"
                                type="reset">
                            NO
                        </button>
                    </form>
                </div>
            </div>


            <div class="divider"></div>

            <form method=post enctype=multipart/form-data action="/portal/editplace">
                <c:set var="command" scope="session" value="saveEditPlace"/>
                <div class="center-align">
                    <h5><cdg:l18n key="editplace.addphoto"/></h5>

                    <div class="file-field input-field" style="height: 45px">
                        <div class="btn cyan darken-2">
                            <span>Select image</span>
                            <input id="image-input" type="file" multiple name="sendfile"/>
                        </div>
                        <button id="image-clear" class="btn cyan darken-2">Clear image</button>
                    </div>
                    <div class="card z-depth-2">
                        <output id="image-preview"></output>
                    </div>
                </div>

                <c:forEach items="${placeDescriptionList}" var="elem">
                    <c:choose>
                        <c:when test="${elem.locale eq 'UA'}">
                            <p><b><cdg:l18n key="editplace.placenameUA"/></b></p>
                            <input value="<c:out value="${placeDescriptionList[0].name}"/>" id="placeNameUA"
                                   type="text"
                                   name="placeNameUA">
                        </c:when>
                        <c:otherwise>
                            <p><b><cdg:l18n key="editplace.placenameEN"/></b></p>
                            <input value="<c:out value="${placeDescriptionList[1].name}"/>" id="placeNameEN"
                                   type="text"
                                   name="placeNameEN">
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:forEach items="${placeDescriptionList}" var="elem">
                    <c:choose>
                        <c:when test="${elem.locale eq 'UA'}">
                            <p><b><cdg:l18n key="editplace.placediscUA"/></b></p>

                            <div class="input-field">
                                <textarea id="placeDescriptionUA" name="placeDescriptionUA"
                                          class="materialize-textarea"><c:out
                                        value="${placeDescriptionList[0].description}"/></textarea>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <p><b><cdg:l18n key="editplace.placediscEN"/></b></p>

                            <div class="input-field">
                                <textarea id="placeDescriptionEN" name="placeDescriptionEN"
                                          class="materialize-textarea"><c:out
                                        value="${placeDescriptionList[1].description}"/></textarea>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:forEach items="${placeDescriptionList}" var="elem">
                    <c:choose>
                        <c:when test="${elem.locale eq 'UA'}">
                            <p><b><cdg:l18n key="editplace.placepricUA"/></b></p>
                            <input value="<c:out value="${placeDescriptionList[0].price}"/>" id="placePriceUA"
                                   type="text"
                                   name="placePriceUA">
                        </c:when>
                        <c:otherwise>
                            <p><b><cdg:l18n key="editplace.placepriceEN"/></b></p>
                            <input value="<c:out value="${placeDescriptionList[1].price}"/>" id="placePriceEN"
                                   type="text"
                                   name="placePriceEN">
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:forEach items="${placeDescriptionList}" var="elem">
                    <c:choose>
                        <c:when test="${elem.locale eq 'UA'}">
                            <p><b><cdg:l18n key="editplace.editPlaceAddressUA"/></b></p>
                            <input value="<c:out value="${placeDescriptionList[0].adress}"/>" id="editPlaceAddressUA"
                                   type="text"
                                   name="editPlaceAddressUA">
                        </c:when>
                        <c:otherwise>
                            <p><b><cdg:l18n key="editplace.editPlaceAddressEN"/></b></p>
                            <input value="<c:out value="${placeDescriptionList[1].adress}"/>" id="editPlaceAddressEN"
                                   type="text"
                                   name="editPlaceAddressEN">
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <p><b><cdg:l18n key="editplace.placephone"/></b></p>
                <input value="<c:out value="${editPlacePhone}"/>" id="placePhone" type="text" name="placePhone">

                <div class="row">
                    <div class="col s6">
                        <p><b><cdg:l18n key="editplace.placealat"/></b></p>
                        <input value="${editPlace.latitude}" id="placeLatitude" type="text" name="placeLatitude">
                    </div>
                    <div class="col s6">
                        <p><b><cdg:l18n key="editplace.placealon"/></b></p>
                        <input value="${editPlace.longitude}" id="placeLongitude" type="text" name="placeLongitude">
                    </div>
                </div>

                <p><b><cdg:l18n key="editplace.placetime"/></b></p>
                <input value="${editPlace.place_time}" id="place_time" type="text" name="place_time">

                <p><b><cdg:l18n key="editplace.choseplacecategory"/></b></p>
                <c:choose>
                    <c:when test="${editPlace.category_id eq 1}">
                        <select name="newCategory">
                            <option value="1"><cdg:l18n key="editplace.architecture"/></option>
                            <option value="2"><cdg:l18n key="editplace.architecture"/></option>
                            <option value="3"><cdg:l18n key="editplace.theatres"/></option>
                            <option value="4"><cdg:l18n key="editplace.hotels"/></option>
                            <option value="5"><cdg:l18n key="editplace.restaurants"/></option>
                            <option value="6"><cdg:l18n key="editplace.otherPlaces"/></option>
                        </select>
                    </c:when>
                    <c:when test="${editPlace.category_id eq 2}">
                        <select name="newCategory">
                            <option value="2"><cdg:l18n key="editplace.architecture"/></option>
                            <option value="1"><cdg:l18n key="editplace.churches"/></option>
                            <option value="3"><cdg:l18n key="editplace.theatres"/></option>
                            <option value="4"><cdg:l18n key="editplace.hotels"/></option>
                            <option value="5"><cdg:l18n key="editplace.restaurants"/></option>
                            <option value="6"><cdg:l18n key="editplace.otherPlaces"/></option>
                        </select>
                    </c:when>
                    <c:when test="${editPlace.category_id eq 3}">
                        <select name="newCategory">
                            <option value="3"><cdg:l18n key="editplace.theatres"/></option>
                            <option value="2"><cdg:l18n key="editplace.architecture"/></option>
                            <option value="1"><cdg:l18n key="editplace.churches"/></option>
                            <option value="4"><cdg:l18n key="editplace.hotels"/></option>
                            <option value="5"><cdg:l18n key="editplace.restaurants"/></option>
                            <option value="6"><cdg:l18n key="editplace.otherPlaces"/></option>
                        </select>
                    </c:when>
                    <c:when test="${editPlace.category_id eq 4}">
                        <select name="newCategory">
                            <option value="4"><cdg:l18n key="editplace.hotels"/></option>
                            <option value="3"><cdg:l18n key="editplace.theatres"/></option>
                            <option value="2"><cdg:l18n key="editplace.architecture"/></option>
                            <option value="1"><cdg:l18n key="editplace.churches"/></option>
                            <option value="5"><cdg:l18n key="editplace.restaurants"/></option>
                            <option value="6"><cdg:l18n key="editplace.otherPlaces"/></option>
                        </select>
                    </c:when>
                    <c:when test="${editPlace.category_id eq 5}">
                        <select name="newCategory">
                            <option value="5"><cdg:l18n key="editplace.restaurants"/></option>
                            <option value="6"><cdg:l18n key="editplace.otherPlaces"/></option>
                            <option value="4"><cdg:l18n key="editplace.hotels"/></option>
                            <option value="3"><cdg:l18n key="editplace.theatres"/></option>
                            <option value="2"><cdg:l18n key="editplace.architecture"/></option>
                            <option value="1"><cdg:l18n key="editplace.churches"/></option>
                        </select>
                    </c:when>
                    <c:otherwise>
                        <select name="newCategory">
                            <option value="6"><cdg:l18n key="editplace.otherPlaces"/></option>
                            <option value="5"><cdg:l18n key="editplace.restaurants"/></option>
                            <option value="4"><cdg:l18n key="editplace.hotels"/></option>
                            <option value="3"><cdg:l18n key="editplace.theatres"/></option>
                            <option value="2"><cdg:l18n key="editplace.architecture"/></option>
                            <option value="1"><cdg:l18n key="editplace.churches"/></option>
                        </select>
                    </c:otherwise>
                </c:choose>


                <div class="row">
                    <div class="col s4">
                        <p><b><cdg:l18n key="editplace.placevis"/></b></p>

                        <div class="switch">
                            <c:choose>
                                <c:when test="${editPlace.visible}">
                                    <label>
                                        <cdg:l18n key="editplace.placeunvisible"/>
                                        <input name="newVisible" type="checkbox" checked="checked">
                                        <span class="lever"></span>
                                        <cdg:l18n key="editplace.placevisible"/>
                                    </label>
                                </c:when>
                                <c:otherwise>
                                    <label>
                                        <cdg:l18n key="editplace.placeunvisible"/>
                                        <input name="newVisible" type="checkbox">
                                        <span class="lever"></span>
                                        <cdg:l18n key="editplace.placevisible"/>
                                    </label>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="col s4">
                        <p><b><cdg:l18n key="editplace.placestat"/></b></p>

                        <div class="switch">
                            <c:choose>
                                <c:when test="${editPlace.deleted}">
                                    <label>
                                        <cdg:l18n key="editplace.placestatdelete"/>
                                        <input name="newState" type="checkbox">
                                        <span class="lever"></span>
                                        <cdg:l18n key="editplace.placestatactive"/>
                                    </label>
                                </c:when>
                                <c:otherwise>
                                    <label>
                                        <cdg:l18n key="editplace.placestatdelete"/>
                                        <input name="newState" type="checkbox" checked="checked">
                                        <span class="lever"></span>
                                        <cdg:l18n key="editplace.placestatactive"/>
                                    </label>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="col s4">
                        <p><b><cdg:l18n key="editplace.placerating"/></b> ${editPlace.rating}</p>
                        <input value="${editPlace.rating}" id="newPlaceRating" type="hidden" name="newPlaceRating">
                    </div>
                </div>

                <div class="divider"></div>
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
    placeDescriptionUA.html(placeDescriptionUA.html().trim());
    var placeDescriptionEN = $('#placeDescriptionEN');
    placeDescriptionEN.html(placeDescriptionEN.html().trim());


</script>

</body>
</html>
