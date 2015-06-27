package com.lab.epam.command.page.delete;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.UserDataAboutTrip;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 26.06.2015.
 */
public class DeletePlaceFromTripCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserDataAboutTrip userDataTrip = (UserDataAboutTrip)session.getAttribute("userDataTrip");
        Map<Integer, List<Place>> placeDay = userDataTrip.getPlaceDay();
        List<Place> places = new ArrayList<>();
        String dayString = request.getParameter("day");
        String place_idString = request.getParameter("place_id");
        Integer place_id = null;
        Integer day = null;
        if (dayString != null){
            day = Integer.parseInt(dayString);

        }
        if (place_idString != null){
            place_id = Integer.parseInt(place_idString);
        }
        if (placeDay != null && !placeDay.isEmpty() && day != null){
            places = placeDay.get(day);
        }
        if (places != null && !places.isEmpty() && place_id != null){
            for (int i = 0; i < places.size(); i++) {
                if (places.get(i).getId().equals(place_id)){
                    places.remove(places.get(i));
                }
            }
        }
        response.sendRedirect("/portal?command=userWays");

    }
}
