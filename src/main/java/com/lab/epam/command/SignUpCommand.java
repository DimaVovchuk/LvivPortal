package com.lab.epam.command;

import com.lab.epam.entity.User;
import com.lab.epam.helper.ClassName;
import com.lab.epam.md5.MD5Creator;
import com.lab.epam.service.UserService;
import com.lab.epam.smtp.SendEmail;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vasyl on 11.06.2015.
 */
public class SignUpCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    private static final String CHECK_NAME = "^[^<>/{}\\s?!;]+$";
    private static final String CHECK_SURNAME = "^[^<>/{}\\s?!;]+$";
    private static final String CHECK_LOGIN = "^[^<>/{}\\s?!;]+$";
    private static final String CHECK_EMAIL = "(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})";
    private static final String CHECK_PASSWORD = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,15})";
    private static final String CHECK_PHONE = "([0-9]{6,15})";

    private static boolean checkData(String testString, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(testString);
        boolean matches = !m.matches();
        return matches;
    }

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        loger.info("Command SignUpCommand.");
        boolean errorFlag = false;
        String name = request.getParameter("first");
        String surname = request.getParameter("last");
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        User user = new User();
        user.setRating(0);
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);
        user.setMail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setStatus(1);
        user.setRoleID(2);

        UserService userService = new UserService();
        boolean checkEmail = userService.checkEmail(email);
        boolean checkPhone = userService.checkEmail(phone);
        boolean checkLogin = userService.checkEmail(login);

        if (checkData(name, CHECK_NAME)) {
            session.setAttribute("loginError", 1);
            errorFlag = true;
            loger.warn("Name is pattern error");
        }

        if (checkData(surname, CHECK_SURNAME)) {
            session.setAttribute("loginError", 1);
            errorFlag = true;
            loger.warn("Surname is pattern error");
        }

        if (checkData(login, CHECK_LOGIN)) {
            session.setAttribute("loginError", 1);
            errorFlag = true;
            loger.warn("Login is pattern error");
        }

        if (checkData(email, CHECK_EMAIL)) {
            session.setAttribute("loginError", 1);
            errorFlag = true;
            loger.warn("Emain is pattern error");
        }

        if (checkData(password, CHECK_PASSWORD)) {
            session.setAttribute("loginError", 1);
            errorFlag = true;
            loger.warn("Password is pattern error");
        }

        if (checkData(phone, CHECK_PHONE)) {
            session.setAttribute("loginError", 1);
            errorFlag = true;
            loger.warn("Phone is pattern error");
        }

        if (login == "") {
            session.setAttribute("loginError", 1);
            errorFlag = true;
            loger.warn("Login is empty");
        }

        if (phone == "") {
            session.setAttribute("phoneError", 1);
            errorFlag = true;
            loger.warn("Phone is empty");
        }
        if (email == "") {
            session.setAttribute("emailError", 1);
            errorFlag = true;
            loger.warn("Email is empty");
        }

        if (!checkEmail) {
            session.setAttribute("emailError", 1);
            errorFlag = true;
            loger.warn("Such email is exist");
        }

        if (!checkPhone) {
            session.setAttribute("phoneError", 1);
            errorFlag = true;
            loger.warn("Such phone is exist");
        }

        if (!checkLogin) {
            session.setAttribute("loginError", 1);
            errorFlag = true;
            loger.warn("Such login is exist");
        }

        if (errorFlag) {
        } else {
            try {
                userService.create(user);
                ResourceBundle bundle = (ResourceBundle) session.getAttribute("bundle");
                String md5phone = MD5Creator.getMD5(phone);
                String s = new String(bundle.getString("confirm.message").getBytes("ISO-8859-1"), "windows-1251") + " -> <a href = 'http://localhost:8080/portal?command=confirm&user=" + login + "&param=" + md5phone + "'> http://localhost:8080/portal?command=confirm&user=" + login + "&param=" + md5phone + " <a>";
                SendEmail.sender("Lviv Portal", s, email);
                loger.info("New user was added");
                request.getRequestDispatcher("/views/pages/dashboard.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                loger.info("Adding new user was failed");
                loger.error(e.getMessage());
            }
        }
    }
}
