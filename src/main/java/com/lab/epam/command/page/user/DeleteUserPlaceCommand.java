package com.lab.epam.command.page.user;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.User;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceService;
import com.lab.epam.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Admin on 18.06.2015.
 */
public class DeleteUserPlaceCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        UserService userservice = new UserService();
        PlaceService placeService = new PlaceService();
        String place_idString = request.getParameter("place_id");
        Integer place_id = Integer.parseInt(place_idString);
        loger.info("Get place_id from request " + place_id);

        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");

        User user = null;

        loger.info("Login in session is " + login);
        if (login != null) {
            user = userservice.getUserByLogin(login);
        }
        if (user == null) {
            loger.warn("No user with login " + login);
        }

        placeService.deletePlaceByUserIdPlaceId(user.getId(), place_id);
        loger.info("Delete place is successes");

        response.sendRedirect("?command=userPlace");

    }

}