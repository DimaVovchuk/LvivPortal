/**
 * Created by Admin on 02.07.2015.
 */
function getXmlHttpRequestObject() {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    } else if(window.ActiveXObject) {
        return new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        alert("Your Browser Sucks!\nIt's about time to upgrade don't you think?");
    }
}
var searchReq = getXmlHttpRequestObject();
function searchSuggest() {

    if (searchReq.readyState == 4 || searchReq.readyState == 0) {
        var str = encode_utf8(document.getElementById('txtSearch').value);
        if(str == ''){
            notActive();
        }else{
        searchReq.open("POST", '/portal?command=search&search=' + str, true);
        searchReq.onreadystatechange = handleSearchSuggest;
        searchReq.send(null);
        }
    }
}

function searchSuggestMap() {

    if (searchReq.readyState == 4 || searchReq.readyState == 0) {
        var str = encode_utf8(document.getElementById('txtSearch').value);
        if(str == ''){
            notActive();
        }else{
            searchReq.open("POST", '/portal?command=search&search=' + str, true);
            searchReq.onreadystatechange = handleSearchSuggestMap;
            searchReq.send(null);
        }
    }
}

function searchSuggestRoute() {

    if (searchReq.readyState == 4 || searchReq.readyState == 0) {
        var str = encode_utf8(document.getElementById('txtSearchRoute').value);
        if(str == ''){
            notActiveRoute();
        }else{
            searchReq.open("POST", '/portal?command=search&search=' + str, true);
            searchReq.onreadystatechange = handleSearchSuggestRoute;
            searchReq.send(null);
        }
    }
}

function encode_utf8(s) {
    return unescape(encodeURIComponent(s));
}

function decode_utf8(s) {
    return decodeURIComponent(escape(s));
}

function handleSearchSuggest() {
    if (searchReq.readyState == 4) {
        var ss = document.getElementById('search_suggest')
        ss.innerHTML = '';
        var str = searchReq.responseText.split("\n");
        for(i=0; i < str.length; i++) {
            if (str[i] != '') {
                var strImg = str[i].split("*");
                var suggest = '<div onmouseover="javascript:suggestOver(this);" ';
                suggest += 'onmouseout="javascript:suggestOut(this);" ';
                suggest += 'onclick="javascript:setSearch('+i+');" ';
                suggest += 'class="suggest_link">'
                suggest += '<div class="collection">';//'<div class="row">';
                suggest += '<div class="collection-item valign-wrapper" style="height: 60px">';//'<div class=" match-colum col l3 m5 s9">'
                suggest += '<div class="valign">';
                suggest += '<img class="circle responsive-img" src="/upload/photo/' + strImg[1] + '"style="width: ;"></div>';
                suggest += '<div class="valign" id="nameOLOLOLO'+i+'">';
                suggest += strImg[0];
                suggest +=  '</div>';
                suggest += '</div></div>';
            ss.innerHTML += suggest;
        }
        }
    }
}

function handleSearchSuggestMap() {
    if (searchReq.readyState == 4) {
        var ss = document.getElementById('search_suggest')
        ss.innerHTML = '';
        var str = searchReq.responseText.split("\n");
        for(i=0; i < str.length; i++) {
            if (str[i] != '') {
                var strImg = str[i].split("*");
                var suggest = '<div onmouseover="javascript:suggestOver(this);" ';
                suggest += 'onmouseout="javascript:suggestOut(this);" ';
                suggest += 'onclick="javascript:setSearchMap('+i+');" ';
                suggest += 'class="suggest_link">'
                suggest += '<div class="collection">';//'<div class="row">';
                suggest += '<div class="collection-item valign-wrapper" style="height: 60px">';//'<div class=" match-colum col l3 m5 s9">'
                suggest += '<div class="valign">';
                suggest += '<img class="circle responsive-img" src="/upload/photo/' + strImg[1] + '"style="width: ;"></div>';
                suggest += '<div class="valign" id="nameOLOLOLO'+i+'">';
                suggest += strImg[0];
                suggest +=  '</div>';
                suggest += '</div></div>';
                ss.innerHTML += suggest;
            }
        }
    }
}



function handleSearchSuggestRoute() {
    if (searchReq.readyState == 4) {
        var ss = document.getElementById('search_suggest_route')
        ss.innerHTML = '';
        var str = searchReq.responseText.split("\n");
        for(i=0; i < str.length; i++) {
            if (str[i] != '') {
                var strImg = str[i].split("*");
                var suggest = '<div onmouseover="javascript:suggestOver(this);" ';
                suggest += 'onmouseout="javascript:suggestOut(this);" ';
                suggest += 'onclick="javascript:setSearchRoute('+i+');" ';
                suggest += 'class="suggest_link">'
                suggest += '<div class="collection">';//'<div class="row">';
                suggest += '<div class="collection-item valign-wrapper" style="height: 60px">';//'<div class=" match-colum col l3 m5 s9">'
                suggest += '<div class="valign">';
                suggest += '<img class="circle responsive-img" src="/upload/photo/' + strImg[1] + '"style="width: ;"></div>';
                suggest += '<div class="valign" id="nameOLO'+i+'">';
                suggest += strImg[0];
                suggest +=  '</div>';
                suggest += '</div></div>';
                ss.innerHTML += suggest;
            }
        }
    }
}


function suggestOver(div_value) {
    div_value.className = 'suggest_link_over';
}

function suggestOut(div_value) {
    div_value.className = 'suggest_link';
}
    function setSearch(value) {
        var val = document.getElementById('nameOLOLOLO'+value).innerHTML;
        if (val != ''){
           // document.getElementById('txtSearch').value = val;
           // document.getElementById('search_suggest').innerHTML = '';
           // searchPlace();
            document.location.href = window.location.origin + '/portal?command=placeInformation&txtSearch=' + val;
        }
}

function setSearchMap(value) {
    var val = document.getElementById('nameOLOLOLO'+value).innerHTML;
    if (val != ''){
         document.getElementById('txtSearch').value = val;
         document.getElementById('search_suggest').innerHTML = '';
        //alert(val);
         searchPlace();
        //document.location.href = window.location.origin + '/portal?command=placeInformation&txtSearch=' + val;
    }
}

function setSearchRoute(value) {
    var val = document.getElementById('nameOLO'+value).innerHTML;
    if (val != ''){
        document.getElementById('txtSearchRoute').value = val;
        document.getElementById('search_suggest_route').innerHTML = '';
        searchPlace();
    }
}

function notActive() {
    document.getElementById('search_suggest').innerHTML = '';
}

function notActiveRoute() {
    document.getElementById('search_suggest_route').innerHTML = '';
}

