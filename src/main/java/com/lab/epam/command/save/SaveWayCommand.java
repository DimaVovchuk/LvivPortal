package com.lab.epam.command.save;

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

        UserDataAboutTrip placeForWay;
        placeForWay = (UserDataAboutTrip)session.getAttribute("userDataTrip");

        if (placeForWay != null){
            Integer wayTime = placeForWay.getHoursCount();
            Map<Integer,List<Place>> placesDay = placeForWay.getPlaceDay();
            Integer wayDays = 0;
                if (placesDay != null){
                    wayDays = placesDay.size();
                    wayService.create(new Way(0, wayDays, wayTime));
                    loger.info("Create way is successfull");
                    Way way = wayService.getLastAdded();
                    Set<Integer> keys = placesDay.keySet();
                    Collection<List<Place>> values = placesDay.values();
                    for (Integer key : keys) {
                        List<Place> places = placesDay.get(key);
                        for (Place place: places){
                            if (!place.isVisible()){
                                servicePlace.create(place);
                                place = servicePlace.getPlaceByLongitudeLatitude(place.getLongitude(), place.getLatitude());
                                loger.info("Create castom place is successfull");
                            }
                            servicePlace.createPlaceWay(place.getId(), way.getId(),key);
                            loger.info("Create place_way is successfull");
                        }

                    }
                    loger.info("Create new way is successfull");

                }
        }else {
            loger.warn("You want create way without places");
        }
        loger.warn("You create new way in DB");
        response.sendRedirect("?command=userCabinet");

    }
}
