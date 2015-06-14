package com.lab.epam.command;

import com.lab.epam.entity.User;
import com.lab.epam.helper.ClassName;
import com.lab.epam.md5.MD5Creator;
import com.lab.epam.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Vasyl on 12.06.2015.
 */
public class SignInCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if (login == null) {
            session.setAttribute("loginError", 1);
            loger.info("login or password is incorrect");
            response.sendRedirect("");
            return;
        }
        UserService serviceUser = new UserService();
        User user = serviceUser.geUserByLogin(login);
        if (user.getPassword()!= null && user.getPassword().equals(MD5Creator.getMD5(password + login))) {
            session.setAttribute("login", login);
            session.setAttribute("role", user.getRoleID());
            loger.info("User " + login + " signing in ");
            request.getRequestDispatcher("/views/pages/usercabinet.jsp").forward(request, response);
        } else {
            session.setAttribute("loginError", 1);
            loger.info("login or password is incorrect");
            response.sendRedirect("");
        }

    }
}
