package com.lab.epam.command.page.delete;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.User;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceResponseService;
import com.lab.epam.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Admin on 11.07.2015.
 */
public class DeletePlaceResponseCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String place_idString = request.getParameter("place_id");
        Integer place_id = 0;
        System.out.println(" place_id " + place_idString);
        String response_idString = request.getParameter("response_id");
        System.out.println(" response_id " + response_idString);
        Integer response_id = 0;

        PlaceResponseService placeResponseService = new PlaceResponseService();
        UserService userService = new UserService();

        if (place_idString != null){
            place_id = Integer.parseInt(place_idString);
        }

        if (response_idString != null){
            response_id = Integer.parseInt(response_idString);
        }

        if (response_id != 0){
            placeResponseService.deleteResponseByUserIdPlaceId(response_id);
        }
        loger.info("Command DeletePlaceResponseCommand");

        response.sendRedirect("/portal?command=placeInformation&place_id=" + place_id);



    }
}
