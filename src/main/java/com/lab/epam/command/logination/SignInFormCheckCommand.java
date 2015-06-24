package com.lab.epam.command.logination;

import com.google.gson.Gson;
import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.User;
import com.lab.epam.entity.UserImage;
import com.lab.epam.md5.MD5Creator;
import com.lab.epam.service.UserImageService;
import com.lab.epam.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInFormCheckCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        boolean check = false;
        UserService serviceUser = new UserService();
        User user = serviceUser.geUserByLogin(login);
        if (user.getPassword() != null && user.getPassword().equals(MD5Creator.getMD5(password + login))) check = true;
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(check));
    }
}
