$.validator.addMethod(
    "regex",
    function (value, element, regex) {
        return regex.test(value);
    }
);

$("#sign-in-form").validate({
    rules: {
        login: "required",
        password: "required"
    },
    messages: {
        login: {
            required: "Please enter login"
        },
        password: {
            required: "Please enter password"
        }
    }
});

$("#sign-up-form").validate({
    rules: {
        first: {
            required: true,
            regex: /^[^<>$\(\)]+$/
        },
        last: {
            required: true,
            regex: /^[^<>$\(\)]+$/
        },
        login: {
            required: true,
            regex: /^[A-Za-z0-9_-]+$/
        },
        email: {
            required: true,
            email: true
        },
        password: {
            required: true,
        },
        confirm: {
            required: true,
            equalTo: "#password"
        },
        phone: {
            required: true,
            regex: /^[0-9\+\s\(\)]+$/
        }
    },
    messages: {
        first: {
            required: "Please enter first name",
            regex: "Special characters are not allowed"
        },
        last: {
            required: "Please enter last name",
            regex: "Special characters are not allowed"
        },
        login: {
            required: "Please enter login",
            regex: "Only latin letters, dashes and underscores are allowed"
        },
        email: {
            required: "Please enter E-mail address",
            email: "Wrong E-mail address format"
        },
        password: {
            required: "Please enter password"
        },
        confirm: {
            required: "Please confirm your password",
            equalTo: "Password and confirmation do not match"
        },
        phone: {
            required: "Please enter phone number",
            regex: "Wrong phone number format"
        }
    }
});
