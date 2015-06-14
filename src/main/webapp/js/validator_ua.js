$.validator.addMethod(
    "regex",
    function(value, element, regex) {
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
            required: "Будь ласка, введіть логін"
        },
        password: {
            required: "Будь ласка, введіть пароль"
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
            required: true
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
            required: "Будь ласка, введіть ім'я",
            regex: "Спеціальні символи не допускаються"
        },
        last: {
            required: "Будь ласка, введіть прізвище",
            regex: "Спеціальні символи не допускаються"
        },
        login: {
            required: "Будь ласка, введіть логін",
            regex: "Допускаються тільки латинські букви, тире і нижнє підкреслення"
        },
        email: {
            required: "Будь ласка, введіть адресу електронної пошти",
            email: "Невірний формат електронної пошти"
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
            regex: "Невірний формат номеру телефону"
        }
    }
});
