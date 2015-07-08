package com.lab.epam.command.save;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.*;
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
import java.util.*;

/**
 * Created by Admin on 18.06.2015.
 */
public class SaveWayCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        PlaceService servicePlace = new PlaceService();
        WayService wayService = new WayService();
        UserService userService = new UserService();

        UserDataAboutTrip placeForWay;
        Boolean isFull = false;
        placeForWay = (UserDataAboutTrip)session.getAttribute("userDataTrip");
        String login = (String)session.getAttribute("login");
        String name = request.getParameter("name");
        name = Decoder.decodeStringUtf8(name);
        System.out.println("name " + name);
        User user = null;
        if (login != null){
            user = userService.getUserByLogin(login);
        }

        if (placeForWay != null){
            Date beginTrip = placeForWay.getBeginTrip();
            Date endTrip = placeForWay.getEndTrip();
            Map<Integer,List<Place>> placesDay = placeForWay.getPlaceDay();
            Integer wayDays = 0;
                if (placesDay != null && !placesDay.isEmpty()){
                    wayDays = placeForWay.getDayCount();
                    Way wayNew = new Way(0, beginTrip, endTrip, wayDays);
                    if (name != null){
                        wayNew.setName(name);
                    }else {
                        name = "way";
                        wayNew.setName(name);
                    }
                    Set<Integer> keys = placesDay.keySet();
                    Collection<List<Place>> values = placesDay.values();
                    for (Integer key : keys) {
                        List<Place> places = placesDay.get(key);
                        if (!places.isEmpty()){
                            isFull = true;
                            break;
                        }
                    }
                    if (user != null){
                        Integer way_id = wayService.createUserWay(wayNew, placesDay, user.getId(), isFull);
                        if (way_id != null){
                            placeForWay.setWay_id(way_id);
                            placeForWay.setIsSaved(true);
                        }
                    }
                }
        }else {
            loger.warn("You want create way without places");
        }
        loger.info("You create new way in DB");
        placeForWay.setIsFull(isFull);
      //  request.setAttribute("isFull", isFull);
        session.setAttribute("userDataTrip", placeForWay);
        response.sendRedirect("portal?command=userWays");


    }
}
