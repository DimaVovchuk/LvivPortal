$("#sign-in-form").validate({
    messages: {
        login: {
            required: "Please enter login"
        },
        password: {
            required: "Please enter password",
            remote: "Wrong login or password"
        }
    }
});

$("#sign-up-form").validate({
    messages: {
        first: {
            regex: "Special characters are not allowed"
        },
        last: {
            regex: "Special characters are not allowed"
        },
        companyname: {
            regex: "Special characters are not allowed"
        },
        login: {
            required: "Please enter login",
            regex: "Only latin letters, dashes and underscores are allowed",
            remote: "This login is already in use"
        },
        email: {
            required: "Please enter E-mail address",
            email: "Wrong E-mail address format",
            remote: "This E-mail address is already in use"
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
            regex: "Wrong phone number format",
            remote: "This phone number is already in use"
        }
    }
});
