/**
 * Created by Admin on 05.07.2015.
 */
var recommendWay = function (placeholder) {
    $.ajax({
        url: $(placeholder).attr('rel'),
        type: "POST",
        success:recommendResultWay,
        error:function (){
            alert("testing error");
        }
    });
    return false;
};