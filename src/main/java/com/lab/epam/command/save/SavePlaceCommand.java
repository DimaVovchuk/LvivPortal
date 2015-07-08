package com.lab.epam.command.save;

import com.google.gson.Gson;
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
 * Created by Admin on 22.06.2015.
 */
public class SavePlaceCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        UserService userService = new UserService();
        PlaceService placeService = new PlaceService();
        Integer is_saved = 0;

        String place_idString = request.getParameter("place_id");
        loger.info("Place id is " + place_idString);
        Integer place_id = 0;
        if (place_idString != null){
            place_id = Integer.parseInt(place_idString);
        }

        String login = (String)session.getAttribute("login");
        User user = null;
        if (login != null){
            user = userService.getUserByLogin(login);
        }

        if (user != null && place_id != 0){
            if (placeService.getPlaceByUserIdPlaceId(place_id, user.getId()) == null){
                placeService.createPlaceUser(place_id, user.getId());
                is_saved = 1;
            }
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(is_saved));
    }



}
