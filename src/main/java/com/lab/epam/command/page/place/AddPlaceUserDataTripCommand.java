package com.lab.epam.command.page.place;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.UserDataAboutTrip;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Admin on 23.06.2015.
 */
public class AddPlaceUserDataTripCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        PlaceService servicePlace = new PlaceService();
        UserService userService = new UserService();
        HttpSession session = request.getSession();
        List<Place> placeForWay;
        UserDataAboutTrip userDataAboutTrip = (UserDataAboutTrip) session.getAttribute("userDataTrip");
        placeForWay = (ArrayList<Place>) session.getAttribute("placeForWay");
        String dayNumberString = request.getParameter("dayNumber");
        String timePlaceString = request.getParameter("timePlace");

        Integer dayNumber = 0;
        Integer timePlace = 0;

        if (dayNumberString != null) {
            dayNumber = Integer.parseInt(dayNumberString);
        }
        //
        if (timePlaceString != null) {
            timePlace = Integer.parseInt(timePlaceString);
        }

        String placeId = request.getParameter("place_id");
        Place onePlaceForWay = null;
        Boolean isInWay = false;

        if (placeId != null && userDataAboutTrip != null && dayNumber != 0) {
            onePlaceForWay = servicePlace.getByPK(Integer.parseInt(placeId));
            loger.info("Get place is successfull");
            Map<Integer, List<Place>> map = userDataAboutTrip.getPlaceDay();
            loger.info("Get map is successfull");
            Set<Integer> keys = map.keySet();
            if (onePlaceForWay != null) {
                if (map.isEmpty() || !keys.contains(dayNumber)) {
                    placeForWay = new ArrayList<>();
                    loger.info("Create new List<Place>");
                    loger.info("Day is " + dayNumber);
                } else {
                    placeForWay = map.get(dayNumber);
                    loger.info("Get placeForWay");
                    for (Place place : placeForWay) {
                        if (place.getId() == onePlaceForWay.getId()) {
                            isInWay = true;
                        }
                    }
                }
                if (!isInWay) {
                    if (timePlace != 0) {
                        onePlaceForWay.setPlace_time(timePlace);
                        placeForWay.add(onePlaceForWay);
                        loger.info("Add new place to placeForWay. Place is " + onePlaceForWay);
                    }
                }
            }
            map.put(dayNumber, placeForWay);
            loger.info("Put placeForWay to map");
            userDataAboutTrip.setPlaceDay(map);
            loger.info("Set map to userDataAboutTrip");
        }
     //   System.out.println("userDataAboutTrip " + userDataAboutTrip);
        session.setAttribute("userDataTrip", userDataAboutTrip);
        request.getRequestDispatcher("portal?command=userPlace").forward(request, response);

    }

}
