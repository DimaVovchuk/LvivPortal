function language() {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "view?command=locale", true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            location.reload();
        }
    };
    xhr.send();


}