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
    }
});

$("#sign-up-form").validate({
    rules: {
        first: {
            regex: /^[^<>$\(\)]*$/
        },
        last: {
            regex: /^[^<>$\(\)]*$/
        },
        companyname: {
            regex: /^[^<>$\(\)]*$/
        },
        login: {
            required: true,
            regex: /^[A-Za-z0-9_-]+$/,
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
        },
        confirm: {
            required: true,
            equalTo: "#password"
        },
        phone: {
            required: true,
            regex: /^[0-9\+\s\(\)]+$/,
            remote: {
                url: window.location.origin + '/portal?command=signUpFormCheck',
                type: 'post'
            }
        }
    }
});