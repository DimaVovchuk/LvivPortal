package com.lab.epam.command.page.place;

import com.google.gson.Gson;
import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Place;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 05.07.2015.
 */
public class RecommendPlaceCommand  implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        loger.info("Command RecommendPlaceCommand");
        PlaceService servicePlace = new PlaceService();
        String place_idString = request.getParameter("place_id");
        Integer place_id = null;
        Integer recommend_result = 0;
        if (place_idString != null){
            place_id = Integer.parseInt(place_idString);
        }

        if (place_id != null && place_id > 0){
            Place place = servicePlace.getByPK(place_id);
            if (!place.getRecomended()){
                servicePlace.setPlaceIsRecommended(place_id);
                recommend_result = 1;
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(recommend_result));
    }
}
