package com.lab.epam.command.logination;

import com.google.gson.Gson;
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
        User user = serviceUser.getUserByLogin(login);
        session = request.getSession();
        Integer userStatus = user.getStatus();
        if (userStatus != 2 && user.getPassword() != null && user.getPassword().equals(MD5Creator.getMD5(password + login))) {
            Integer userID = user.getId();
            session.setAttribute("login", login);
            session.setAttribute("userID", userID);
            session.setAttribute("role", user.getRoleID());

            String avatarReference = null;
            Integer userAvatarID = user.getAvatar();

            if (userAvatarID != null && userAvatarID != 0) {
                UserImageService userImageService = new UserImageService();
                UserImage userImagee = userImageService.getByPK(userAvatarID);
                avatarReference = userImagee.getReference();
            }

            if (avatarReference != null) {
                session.setAttribute("avatarReference", avatarReference);
                loger.info("Set user avatar reference");
            } else{
                session.setAttribute("avatarReference", "user.png");
                loger.info("Set default avatar reference");
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson("success"));
            loger.info("Command SignInCommand");
        } else {
            session.setAttribute("loginError", 1);
            loger.info("login or password is incorrect");
            response.sendRedirect("");
        }

    }
}
