package com.lab.epam.command.logination;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.User;
import com.lab.epam.entity.UserDataAboutTrip;
import com.lab.epam.entity.Way;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceService;
import com.lab.epam.service.UserService;
import com.lab.epam.service.WayService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Vasyl on 16.06.2015.
 */
public class SignOutCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    String command = "portal?command=index";

    public void execute(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (request.getParameter("no") == null){
            saveUserDataTrip(session);
        }
        session.invalidate();
        loger.info("Command Logout session - false.");
        response.sendRedirect("portal?command=index");
    }

    private void saveUserDataTrip(HttpSession session){

        PlaceService servicePlace = new PlaceService();
        WayService wayService = new WayService();
        UserService userService = new UserService();

        UserDataAboutTrip placeForWay;
        placeForWay = (UserDataAboutTrip)session.getAttribute("userDataTrip");
        String login = (String)session.getAttribute("login");
        User user = null;
        if (login != null){
            user = userService.getUserByLogin(login);
            loger.info("Get user by login " + login);
        }

        if (placeForWay != null){
            Date beginTrip = placeForWay.getBeginTrip();
            Date endTrip = placeForWay.getEndTrip();
            Map<Integer,List<Place>> placesDay = placeForWay.getPlaceDay();
            Integer wayDays = 0;
            if (placesDay != null && !placesDay.isEmpty()){
                wayDays = placeForWay.getDayCount();
                wayService.create(new Way(0, beginTrip, endTrip, wayDays));
                loger.info("Create way is successful");
                Way way = wayService.getLastAdded();
                if (user != null && way != null && wayDays > 0){
                    placeForWay.setWay_id(way.getId());
                    wayService.createUserWay(user.getId(), way.getId(), wayDays);
                }
                Set<Integer> keys = placesDay.keySet();
                Collection<List<Place>> values = placesDay.values();
                for (Integer key : keys) {
                    List<Place> places = placesDay.get(key);
                    for (Place place: places){
                           /* if (!place.getVisible()){
                                servicePlace.create(place);
                                place = servicePlace.getPlaceByLongitudeLatitude(place.getLongitude(), place.getLatitude());
                                loger.info("Create castom place is successfull");
                            }*/
                        servicePlace.createPlaceWay(place.getId(), way.getId(), key, place.getPlace_time());
                        loger.info("Create place_way is successful");
                    }

                }
                loger.info("Create new way is successful");
                placeForWay.setIsSaved(true);
            }
        }else {
            loger.warn("You want create way without places");
        }
        loger.info("You create new way in DB");
    }


}