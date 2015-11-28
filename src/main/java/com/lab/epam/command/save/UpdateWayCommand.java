package com.lab.epam.command.save;

import com.google.gson.Gson;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Admin on 26.06.2015.
 */
public class UpdateWayCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WayService wayService = new WayService();
        Integer updateResult = 3;
        PlaceService placeService = new PlaceService();
        HttpSession session = request.getSession();
        UserDataAboutTrip userDataTrip = (UserDataAboutTrip)session.getAttribute("userDataTrip");
        UserDataAboutTrip userDataTripNew = null;
        UserService userService = new UserService();
        PlaceService servicePlace = new PlaceService();
        String login = (String)session.getAttribute("login");
        User user = null;
        if (login != null){
            user = userService.getUserByLogin(login);
        }



//get old userDataTrip
        UserDataAboutTrip userDataTripOld = new UserDataAboutTrip();
        Integer way_id = 0;

        if (userDataTrip != null){
            way_id = userDataTrip.getWay_id();
        }

        Map<Integer, List<Place>> placesMap = new HashMap<>();
        if (way_id != 0){
            Way way = wayService.getByPK(way_id);
            userDataTripOld.setWay_id(way_id);

            userDataTripOld.setIsSaved(true);
            userDataTripOld.setDayCount(way.getWay_days());

            for (int i = 1; i <= userDataTripOld.getDayCount(); i++){
                List<Place> place = placeService.getPlaceByWayIdDayNumber(way_id, i);
                if (place != null && !place.isEmpty()){
                    placesMap.put(i, place);
                }
            }
            userDataTripOld.setPlaceDay(placesMap);
            loger.info("userDataTripOld is created");
        } else{
            loger.warn("Any way_id");
        }
System.out.println("***************************************userDataTripOld " + userDataTripOld);
        if (userDataTrip != null && userDataTripOld != null && user != null){
           if (!userDataTrip.getDayCount().equals(userDataTripOld.getDayCount())){
               wayService.updateWayDay(user.getId(), way_id, userDataTrip.getDayCount());
               updateResult = 4;
           }
//            if (userDataTrip.getBeginTrip() != null && !userDataTrip.getBeginTrip().equals(userDataTripOld.getBeginTrip())){
//                wayService.updateWayBeginDate(way_id, userDataTrip.getBeginTrip());
//                updateResult = 4;
//            }
//            if (userDataTrip.getBeginTrip() != null && !userDataTrip.getEndTrip().equals(userDataTripOld.getEndTrip())) {
//                wayService.updateWayEndDate(way_id, userDataTrip.getEndTrip());
//                updateResult = 4;
//            }

            Map<Integer, List<Place>> placesOld = userDataTripOld.getPlaceDay();
            Map<Integer, List<Place>> places = userDataTrip.getPlaceDay();
            if (placesOld != null && !placesOld.isEmpty() && places != null && !places.isEmpty()){
                Set<Integer> keys = places.keySet();
                System.out.println("*********************************************************"+keys);
                for (Integer day: keys){
                    if (placesOld.get(day) != null){
                        List<Place> pNew = places.get(day);
                        List<Place> pOld = placesOld.get(day);
                        for (Place placeNew: pNew){
                            if(!pOld.contains(placeNew)){
//                                if (!placeNew.getVisible()) {
//                                    servicePlace.create(placeNew);
//                                    placeNew = servicePlace.getPlaceByLongitudeLatitude(placeNew.getLongitude(), placeNew.getLatitude());
//                                    loger.info("Create castom place is successful");
//                                }
                                servicePlace.createPlaceWay(placeNew.getId(), way_id, day, placeNew.getPlace_time());
                            }
                        }
                        for (Place placeOld: pOld){
                            if(!pNew.contains(placeOld)){
                                servicePlace.deletePlaceByWayIdPlaceId(way_id, placeOld.getId(), day);
                            }
                        }
                    }else{
                        List<Place> p = places.get(day);
                        for (Place place: p){
//                            if (!place.getVisible()) {
//                                servicePlace.create(place);
//                                place = servicePlace.getPlaceByLongitudeLatitude(place.getLongitude(), place.getLatitude());
//                                loger.info("Create castom place is successful");
//                            }
                            servicePlace.createPlaceWay(place.getId(), way_id, day, place.getPlace_time());
                        }
                    }
                }
                updateResult = 4;
            }
            if (userDataTrip.getDayCount() < userDataTripOld.getDayCount()) {
                Integer daySubst = userDataTripOld.getDayCount() - userDataTrip.getDayCount();
                for (int i = 1; i <= daySubst; i++) {
                    servicePlace.deletePlaceByWayIdDayNumber(way_id, i);
                }
                updateResult = 4;
            }
        }
        //response.sendRedirect("portal?command=userWays");
        loger.info("Command UpdateWayCommand");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(updateResult));

    }

}
