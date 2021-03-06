$.validator.addMethod(
    "regex",
    function (value, element, regex) {
        return regex.test(value);
    }
);

$("#sign-in-form").validate({
    rules: {
        login: "required",
        password: {
            required: true,
            remote: {
                url: window.location.origin + '/portal?command=signInFormCheck',
                type: 'post',
                data: {
                    login: function () {
                        return $('#login-in').val()
                    }
                }
            }
        }
    },
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
    rules: {
        first: {
            regex: /^[^<>\/{}\s?!;]+$/
        },
        last: {
            regex: /^[^<>\/{}\s?!;]+$/
        },
        companyname: {
            regex: /^[^<>\/{}\s?!;]+$/
        },
        login: {
            required: true,
            regex: /^[^<>\/{}\s?!;]+$/,
            remote: {
                url: window.location.origin + '/portal?command=signUpFormCheck',
                type: 'post'
            }
        },
        email: {
            required: true,
            email: true,
            remote: {
                url: window.location.origin + '/portal?command=signUpFormCheck',
                type: 'post'
            }
        },
        password: {
            required: true,
            regex: /((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,15})/
        },
        confirm: {
            required: true,
            equalTo: "#password"
        },
        phone: {
            required: true,
            regex: /((^(8-?|\+?7-?|\+38-?|38-?|)?(\(?\d{3}\)?)-?(\d-?){6}\d$)|^(\d-?){6}\d$)/,
            remote: {
                url: window.location.origin + '/portal?command=signUpFormCheck',
                type: 'post'
            }
        }
    },
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
            required: "Please enter password",
            regex: "Password should be minimum 8 symbols with letters and numbers"
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

$('#reset-send-email-form').validate({
    rules: {
        email: "email"
    },
    messages: {
        email: {
            email: "Wrong E-mail address format"
        }
    }
});

$('#reset-confirm-form').validate({
    rules: {
        password: {
            required: true
        },
        confirm: {
            required: true,
            equalTo: "#resetpassword"
        }
    },
    messages: {
        password: {
            required: "Please enter password"
        },
        confirm: {
            required: "Please confirm your password",
            equalTo: "Password and confirmation do not match"
        }
    }
});