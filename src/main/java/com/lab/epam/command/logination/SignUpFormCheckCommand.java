package com.lab.epam.command.logination;

import com.google.gson.Gson;
import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.User;
import com.lab.epam.md5.MD5Creator;
import com.lab.epam.service.UserService;
import com.lab.epam.smtp.SendEmail;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

public class SignUpFormCheckCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        UserService userService = new UserService();
        boolean check = false;
        if (login != null) check = userService.checkLogin(login);
        if (email != null) check = userService.checkEmail(email);
        if (phone != null) check = userService.checkPhone(phone);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(check));
    }
}