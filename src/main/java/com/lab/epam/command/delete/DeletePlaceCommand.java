package com.lab.epam.command.delete;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Place;
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
 * Created by Admin on 23.06.2015.
 */
public class DeletePlaceCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        UserService userService = new UserService();
        PlaceService placeService = new PlaceService();

        String yes = request.getParameter("yes");
        String no = request.getParameter("no");

        if (yes != null){
            String place_idString = request.getParameter("place_id_delete");
            loger.info("Place id is " + place_idString);
            Integer place_id = null;
            if (place_idString != null){
                place_id = Integer.parseInt(place_idString);
            }
            String login = (String)session.getAttribute("login");
            User user = null;
            if (login != null){
                user = userService.getUserByLogin(login);
                loger.info("User with login " + login + " is exist");
            }

            if (user != null && place_id != null){
                placeService.deletePlaceByUserIdPlaceId(user.getId(), place_id);
                loger.info("Place is deleted");
                Place place = placeService.getByPK(place_id);
                if (place != null && place.getCustom()){
                    placeService.delete(place);
                }
            }
        }
        request.getRequestDispatcher("portal?command=userPlace").forward(request, response);
    }
}
