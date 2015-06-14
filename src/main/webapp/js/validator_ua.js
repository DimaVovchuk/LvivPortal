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
            regex: /[A-Z]/,
        },
        last: {
            required: true,
            regex: /[A-Z]/,
        }
    },
    messages: {
        first: {
            required: "����1",
            regex: "����2",
        },
        last: "Test",
    }
})
