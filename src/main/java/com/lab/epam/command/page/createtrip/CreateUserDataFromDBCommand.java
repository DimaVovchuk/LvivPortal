package com.lab.epam.command.page.createtrip;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.UserDataAboutTrip;
import com.lab.epam.entity.Way;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceService;
import com.lab.epam.service.WayService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 25.06.2015.
 */
public class CreateUserDataFromDBCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDataAboutTrip userDataTrip = new UserDataAboutTrip();
        WayService wayService = new WayService();
        PlaceService placeService = new PlaceService();
        HttpSession session = request.getSession();
        String way_idString = request.getParameter("way_id");
        Integer way_id;
        Map<Integer, List<Place>> placesMap = new HashMap<>();
        if (way_idString != null) {
            way_id = Integer.parseInt(way_idString);
            userDataTrip.setWay_id(way_id);
            Way way = wayService.getByPK(way_id);
            if (way.getBegin() != null) {
                userDataTrip.setBeginTrip(way.getBegin());
            }
            if (way.getEnd() != null) {
                userDataTrip.setEndTrip(way.getEnd());
            }

            if (way.getRecomended()) {
                userDataTrip.setRecommended(true);
            }

            userDataTrip.setIsSaved(true);
            userDataTrip.setIsFull(true);
            userDataTrip.setDayCount(way.getWay_days());

            for (int i = 1; i <= userDataTrip.getDayCount(); i++) {
                List<Place> place = placeService.getPlaceByWayIdDayNumber(way_id, i);
                if (place != null && !place.isEmpty()) {
                    placesMap.put(i, place);
                }
            }
            userDataTrip.setPlaceDay(placesMap);
            for (int i = 1; i <= userDataTrip.getPlaceDay().size(); i++) {
                userDataTrip.getSortFlag().put(i, true);
            }
            loger.info("userDataTrip is created");
        } else {
            loger.warn("Any way_id");
        }
        System.out.println("userDataTrip " + userDataTrip);
        loger.warn("Command CreateUserDataFromDBCommand");
        session.setAttribute("userDataTrip", userDataTrip);

        response.sendRedirect("portal?command=showMap");


    }

}
