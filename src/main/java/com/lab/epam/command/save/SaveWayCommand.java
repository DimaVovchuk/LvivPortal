package com.lab.epam.command.save;

import com.google.gson.Gson;
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
        loger.info("Command SaveWayCommand");

        HttpSession session = request.getSession();

        PlaceService servicePlace = new PlaceService();
        WayService wayService = new WayService();
        UserService userService = new UserService();

        UserDataAboutTrip placeForWay;
        Boolean isFull = false;
        Boolean isEquals = false;
        Boolean onePlace = false;
        Integer indicatorSaved = 0;
        placeForWay = (UserDataAboutTrip)session.getAttribute("userDataTrip");
        String login = (String)session.getAttribute("login");
        String name = request.getParameter("name");
        User user = null;
        if (login != null){
            user = userService.getUserByLogin(login);
        }
        List <Way> userWaysCountDay = new ArrayList<>();
        Integer placeCount = 0;



        //loger.info("placeForWay " + placeForWay);
        if (placeForWay != null){
            if (user != null){
                List <Way> userWays = wayService.getWaysByUserId(user.getId());
                //loger.info("userWays " + userWays);
                if (userWays != null && !userWays.isEmpty()){

                    for (Way userWay: userWays){
                       // loger.info("userWay.getWay_days() " + userWay.getWay_days());
                        //loger.info("placeForWay.getDayCount() " + placeForWay.getDayCount());
                        if (userWay.getWay_days().equals(placeForWay.getDayCount())){
                            userWaysCountDay.add(userWay);
                            //loger.info("Days are equels " + userWay.getWay_days());
                        }
                    }
                }
            }

            List <Way> userWaysCountPlace = new ArrayList<>();
            Map<Integer,List<Place>> placesDayCountPlace = placeForWay.getPlaceDay();

            if (placesDayCountPlace.size() == 1 && placesDayCountPlace.get(1).size() == 1){
                onePlace = true;
                indicatorSaved = 1;
                isFull = true;
            }

            Integer placesDayCurrent = 0;
            if (!userWaysCountDay.isEmpty() && !placesDayCountPlace.isEmpty()){
                isFull = true;
                for (Way userWay: userWaysCountDay){
                    placeCount = 0;
                    placesDayCurrent = 0;
                    for (int i = 1; i <= userWay.getWay_days(); i++) {
                        List<Place> place = servicePlace.getPlaceByWayIdDayNumber(userWay.getId(), i);
                        if (place != null){
                            placeCount += place.size();
                        }
                        if (placesDayCountPlace.get(i) != null && !placesDayCountPlace.get(i).isEmpty()){
                            placesDayCurrent += placesDayCountPlace.get(i).size();
                        }
                        if (placeCount != placesDayCurrent){
                            //loger.info("Plays in day are differend " + placeCount);
                            break;
                        }
                    }

                    if (placeCount == placesDayCurrent){
                        userWaysCountPlace.add(userWay);
                    }
                }
            }
            Integer equelsDay = 0;
            Integer countDayPlace = 0;
            if (!userWaysCountPlace.isEmpty()){
                for (Way userWay: userWaysCountPlace){
                   // equelsDay = 0;
                    for (int i = 1; i <= userWay.getWay_days(); i++) {
                        List<Place> place = servicePlace.getPlaceByWayIdDayNumber(userWay.getId(), i);
                        List<Place> placesDay = placesDayCountPlace.get(i);
                        if (placesDay != null && !placesDay.isEmpty()){
                            //countDayPlace++;
                        }
                        Integer countEquels = 0;
                        if (place != null) {
                            for (Place pl : place) {
                                if (placesDay.contains(pl)) {
                                    loger.info("You have equels place DB in " + pl.getId() + " in way " + userWay.getId());
                                    countEquels++;
                                }
                            }

                            if (countEquels == place.size()) {
                                loger.info("You have equels day DB in " + userWay.getId());
                                equelsDay++;
                            }
                            countEquels = 0;
                        }
                    }
                    if (equelsDay == placesDayCountPlace.size()){
                        isEquals = true;
                        indicatorSaved = 2;
                        loger.info("You have equels way in DB");
                    }
                    equelsDay = 0;
                }
            }



            if (!isEquals && !onePlace) {
                Date beginTrip = placeForWay.getBeginTrip();
                Date endTrip = placeForWay.getEndTrip();
                Map<Integer, List<Place>> placesDay = placeForWay.getPlaceDay();
                Integer wayDays = 0;
                if (placesDay != null && !placesDay.isEmpty()) {
                    wayDays = placeForWay.getDayCount();
                    Way wayNew = new Way(0, beginTrip, endTrip, wayDays);
                    if (name != null) {
                        wayNew.setName(name);
                    } else {
                        Way wayForName = wayService.getLastAdded();
                        Integer nameId = wayForName.getId() + 1;
                        name = "way" + nameId;
                        wayNew.setName(name);
                    }
                    Set<Integer> keys = placesDay.keySet();
                    Collection<List<Place>> values = placesDay.values();
                    for (Integer key : keys) {
                        List<Place> places = placesDay.get(key);
                        if (!places.isEmpty()) {
                            isFull = true;
                            break;
                        }
                    }
                    if (user != null) {
                        Integer way_id = wayService.createUserWay(wayNew, placesDay, user.getId(), isFull);
                        if (way_id != null) {
                            placeForWay.setWay_id(way_id);
                            placeForWay.setIsSaved(true);
                            placeForWay.setRecommended(false);
                            indicatorSaved = 0;
                            loger.info("You create new way in DB");
                        }
                    }
                }
            }
        } else {
            loger.warn("You want create way without places");
        }
        placeForWay.setIsFull(isFull);
        session.setAttribute("userDataTrip", placeForWay);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(indicatorSaved));
        //response.sendRedirect("portal?command=userWays");


    }
}
