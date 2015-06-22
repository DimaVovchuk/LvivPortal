package com.lab.epam.command.logination;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.User;
import com.lab.epam.entity.UserImage;
import com.lab.epam.helper.ClassName;
import com.lab.epam.md5.MD5Creator;
import com.lab.epam.service.UserImageService;
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
        session = request.getSession();
        if (user.getPassword()!= null && user.getPassword().equals(MD5Creator.getMD5(password + login))) {
            Integer userID = user.getId();
            session.setAttribute("login", login);
            session.setAttribute("usedID", userID);
            session.setAttribute("role", user.getRoleID());

            UserImageService userImageService = new UserImageService();
            UserImage userImagee = userImageService.getByPK(user.getAvatar());
            String avatar = null;
            if(userImagee !=null) {
                avatar = userImagee.getReference();
            }
            session.setAttribute("avatar", avatar);


            loger.info("User " + login + " signing in ");
            request.getRequestDispatcher("/views/pages/user-cabinet.jsp").forward(request, response);
        } else {
            session.setAttribute("loginError", 1);
            loger.info("login or password is incorrect");
            response.sendRedirect("");
        }

    }
}
