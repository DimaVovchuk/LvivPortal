package com.lab.epam.command.save;

import com.lab.epam.command.controller.Command;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.User;
import com.lab.epam.entity.UserDataAboutTrip;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceService;
import com.lab.epam.service.UserImageService;
import com.lab.epam.service.UserService;
import com.lab.epam.service.WayService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Oleguk on 18.06.2015.
 */
public class SaveProfileCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        loger.info("Command Update Profile.");

        HttpSession session = request.getSession();
        UserService userservice = new UserService();
        UserImageService userImageService = new UserImageService();
        Integer id = (Integer)session.getAttribute("id");
        User user = null;

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String login = request.getParameter("login");
        String mail = request.getParameter("mail");
        String phone = request.getParameter("phone");
        String about = request.getParameter("about");
        String password = request.getParameter("password");
        String avatar = request.getParameter("avatar");

        String page = "/views/pages/editProfile.jsp";
        String errorMsg = null;

        if (id != null) {
            user = userservice.getByPK(id);
        }
        if (user == null){
            loger.warn("No user with id " + id);
        }
        else {
            if (!(user.getName().equals(name) && user.getSurname().equals(surname) && user.getLogin().equals(login) && user.getMail().equals(mail)
                    && user.getPhone().equals(phone) && user.getAbout().equals(about) && user.getPassword().equals(password) && user.getAvatar().equals(avatar))) {
                name = request.getParameter("name");
                surname = request.getParameter("surname");
                login = request.getParameter("login");
                mail = request.getParameter("mail");
                phone = request.getParameter("phone");
                about = request.getParameter("about");
                password = request.getParameter("password");
                avatar = request.getParameter("avatar");

                user.setName(name);
                user.setSurname(surname);
                user.setLogin(login);
                user.setMail(mail);
                user.setPhone(phone);
                user.setAbout(about);
                user.setPassword(password);
                user.setAvatar(avatar);
            }
            else {
                errorMsg = "You have not changed any field";
            }
        }

        if (!(errorMsg == null || errorMsg.isEmpty() || errorMsg == "")) {
            loger.info("User profile of \'" + user.getName() + "\' is updated!");
            try {
                userservice.update(user);
            } catch (PersistException e) {
                e.printStackTrace();
            }
            request.setAttribute("name", user.getName());
            request.setAttribute("surname", user.getSurname());
            request.setAttribute("login", user.getLogin());
            request.setAttribute("mail", user.getMail());
            request.setAttribute("phone", user.getPhone());
            request.setAttribute("about", user.getAbout());
            request.setAttribute("password", user.getPassword());
            request.setAttribute("avatar", user.getAvatar());
            request.setAttribute("msg", errorMsg);
            request.getRequestDispatcher(page).forward(request, response);
        }
        else {
            request.setAttribute("msg", errorMsg);
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
