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
            required: "Будь ласка, введіть логін"
        },
        password: {
            required: "Будь ласка, введіть пароль",
            remote: "Невірний логін або пароль"
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
            required: true
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
    },
    messages: {
        first: {
            regex: "Спеціальні символи не допускаються"
        },
        last: {
            regex: "Спеціальні символи не допускаються"
        },
        companyname: {
            regex: "Спеціальні символи не допускаються"
        },
        login: {
            required: "Будь ласка, введіть логін",
            regex: "Допускаються тільки латинські букви, тире і нижнє підкреслення",
            remote: "Цей логін вже використовується"
        },
        email: {
            required: "Будь ласка, введіть адресу електронної пошти",
            email: "Невірний формат електронної пошти",
            remote: "Ця ардреса електронної пошти вже використовується"
        },
        password: {
            required: "Будь ласка, введіть пароль"
        },
        confirm: {
            required: "Будь ласка, підтвердіть пароль",
            equalTo: "Пароль і підтвердження не співпадають"
        },
        phone: {
            required: "Будь ласка, введіть номер телефону",
            regex: "Невірний формат номеру телефону",
            remote: "Цей номер телефону вже використовується"
        }
    }
});

$('#reset-send-email-form').validate({
    rules: {
        email: "email"
    },
    messages: {
        email: {
            email: "Невірний формат електронної пошти"
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
            required: "Будь ласка, введіть пароль"
        },
        confirm: {
            required: "Будь ласка, підтвердіть пароль",
            equalTo: "Пароль і підтвердження не співпадають"
        }
    }
});