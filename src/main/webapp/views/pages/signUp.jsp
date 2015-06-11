<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SingUpFrom</title>
</head>
<body>
<form method="POST" action="signUp" >
    name<input type="text" name="name">
    surname<input type="text" name="surname">
    login<input type="text" name="login">
    email<input type="text" name="email">
    password<input type="text" name="password">
    phone<input type="text" name="phone">
    <input type="hidden" name="command" value="signUp">
    <input type="submit" value="Submit"/>
</form>

</body>
</html>
