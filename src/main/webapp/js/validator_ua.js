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
            required: "���� �����, ������ ����"
        },
        password: {
            required: "���� �����, ������ ������"
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
            required: "���� �����, ������ ��'�",
            regex: "��������� ������� �� ������������"
        },
        last: {
            required: "���� �����, ������ �������",
            regex: "��������� ������� �� ������������"
        },
        login: {
            required: "���� �����, ������ ����",
            regex: "������������ ����� �������� �����, ���� � ���� �����������"
        },
        email: {
            required: "���� �����, ������ ������ ���������� �����",
            email: "������� ������ ���������� �����"
        },
        password: {
            required: "���� �����, ������ ������"
        },
        confirm: {
            required: "���� �����, ��������� ������",
            equalTo: "������ � ������������ �� ����������"
        },
        phone: {
            required: "���� �����, ������ ����� ��������",
            regex: "������� ������ ������ ��������"
        }
    }
});
