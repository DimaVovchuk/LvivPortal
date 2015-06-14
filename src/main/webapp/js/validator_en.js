$.validator.addMethod(
    "regex",
    function(value, element, regex) {
        return regex.test(value);
    }
);

var validator = $("#sign-up-form").validate({
    rules: {
        first: {
            required: true,
            regex: /^[^\<\>\]*$/,
        },
        last: {
            required: true,
            regex: /^[\pL-]*$/,
        }
    },
    messages: {
        first: {
            required: "Please enter first name",
            regex: "Incorrect input data format",
        },
        last: {
            required: "Please enter second name",
            regex: "Incorrect input data format",
        },
    }
})
