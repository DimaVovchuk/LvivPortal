package com.lab.epam.command.page.user;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.*;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by Oleguk on 16.06.2015.
 */
public class EditProfileCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    private List<UserImage> userImage = new ArrayList<>();
    String language;


    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        UserService userservice = new UserService();
        UserImageService userImageService = new UserImageService();

        HttpSession session = request.getSession();
        String login = (String)session.getAttribute("login");
        ResourceBundle resourceBandle = (ResourceBundle)session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        language = locale.getLanguage();

        User user = null;
        String page = "/views/page/editProfile.jsp";
        loger.info("Login in session is " + login);

        if (login != null) {
            user = userservice.geUserByLogin(login);
        }
        if (user == null){
            loger.warn("No user with login " + login);
        }
        if (user != null) {

            userImage = userImageService.getUserImageByUserId(user.getId());

            request.setAttribute("name", user.getName());
            request.setAttribute("surname", user.getSurname());
            request.setAttribute("login", user.getLogin());
            request.setAttribute("mail", user.getMail());
            request.setAttribute("phone", user.getPhone());
            request.setAttribute("about", user.getAbout());
            request.setAttribute("password", user.getPassword());
            request.setAttribute("avatar", user.getAvatar());
            request.setAttribute("id", user.getId());
        }

        loger.info("Command Edit Profile.");
        request.getRequestDispatcher(page).forward(request, response);
    }
}
