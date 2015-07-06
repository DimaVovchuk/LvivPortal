/**
 * Created by Admin on 02.07.2015.
 */
function getXmlHttpRequestObject() {
    //alert("getXmlHttpRequestObject");
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
  //  alert("searchSuggest");

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

function encode_utf8(s) {
    return unescape(encodeURIComponent(s));
}

function decode_utf8(s) {
    return decodeURIComponent(escape(s));
}

function handleSearchSuggest() {
  //  alert("handleSearchSuggest");
    if (searchReq.readyState == 4) {
        var ss = document.getElementById('search_suggest')
        ss.innerHTML = '';
        var str = searchReq.responseText.split("\n");
        //alert(str);
        //ss.innerHTML += str;
        for(i=0; i < str.length; i++) {
            if (str[i] != '') {
                var strImg = str[i].split("*");
            var suggest = '<div class="row"> <div class=" match-colum col l6 m9 s18">';
            suggest += '<img src="/upload/photo/' + strImg[1] + '"style="width: 100%"></div>';
            suggest += '<div class=" match-colum col l6 m9 s18">';
            suggest += '<div onmouseover="javascript:suggestOver(this);" ';
            suggest += 'onmouseout="javascript:suggestOut(this);" ';
            suggest += 'onclick="javascript:setSearch(this.innerHTML);" ';
            suggest += 'class="suggest_link">' + strImg[0] + '</div>';
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
        if (value != ''){
            document.getElementById('txtSearch').value = value;
            document.getElementById('search_suggest').innerHTML = '';
        }
}
function notActive() {
    document.getElementById('search_suggest').innerHTML = '';
}

