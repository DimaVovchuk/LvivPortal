package com.lab.epam.command.page.map;

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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by Dima on 19-Jun-15.
 */
public class RoutesCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDataAboutTrip userDataTrip = (UserDataAboutTrip) session.getAttribute("userDataTrip");
        Map<Integer, List<Place>> placeDay = userDataTrip.getPlaceDay();
        List<Place> places = placeDay.get(1);
        Comparator<Place> placeComparator = new Comparator<Place>() {
            @Override
            public int compare(Place o1, Place o2) {
                double earthRadius = 6371000; //meters
                double dLat = Math.toRadians(Double.parseDouble(o1.getLatitude()) - Double.parseDouble(o2.getLatitude()));
                double dLng = Math.toRadians(Double.parseDouble(o1.getLongitude()) - Double.parseDouble(o2.getLongitude()));
                double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                        Math.cos(Math.toRadians(Double.parseDouble(o1.getLatitude()))) * Math.cos(Math.toRadians(Double.parseDouble(o1.getLatitude()))) *
                                Math.sin(dLng / 2) * Math.sin(dLng / 2);
                double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                float dist = (float) (earthRadius * c);
                int result = 0;
                if (dist < 0) {
                    result = 1;
                }
                if (dist > 0) {
                    result = -1;
                }
                if (dist == 0) {
                    result = 0;
                }
                return result;
            }
        };
        System.out.println(places);
        Collections.sort(places,placeComparator);
        System.out.println(places);
        request.setAttribute("wayPlaces", places);
        loger.info("Command RoutesCommand.");
        request.getRequestDispatcher("/views/pages/routes.jsp").forward(request, response);
    }
}
