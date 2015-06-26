package com.lab.epam.command.page.map;

import com.google.gson.Gson;
import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.*;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceDescriptionService;
import com.lab.epam.service.PlaceImageService;
import com.lab.epam.workWithMap.Distance;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by Dima on 19-Jun-15.
 */
public class RoutesCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDataAboutTrip userDataTrip = (UserDataAboutTrip) session.getAttribute("userDataTrip");
        if (userDataTrip != null) {
            Map<Integer, List<Place>> placeDay = userDataTrip.getPlaceDay();
            if (!placeDay.isEmpty()) {
                List<Place> places = placeDay.get(1);
                String obj1 = "" + places.get(0).getLatitude() + " " + places.get(0).getLongitude() + "";
                Distance distance = new Distance();
                List<Double> dist = new ArrayList<>();
                for (int i = 0; i < places.size(); i++) {
                    String obj2 = "" + places.get(i).getLatitude() + " " + places.get(i).getLongitude() + "";
                    try {
                        dist.add(distance.getDistance(obj1, obj2));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                TreeMap<Double, Place> sorted = new TreeMap<>();
                sorted.put(0.0, places.get(0));
                int j = 1;
                for (int i = 0; i < dist.size(); i++) {
                    sorted.put(dist.get(i), places.get(j));
                    j++;
                }
                Collection<Place> values = sorted.values();
                List<Place> list = new ArrayList<>();
                list.addAll(values);


                PlaceImageService placeImageService = new PlaceImageService();
                List<PlaceMarkerWithPhoto> placeMarkerWithPhotos = new ArrayList<>();
                PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
                ResourceBundle bundle = (ResourceBundle) session.getAttribute("bundle");

                for (Place place : values) {
                    PlaceDescription placeDescription = placeDescriptionService.getPlaceDescriptionByIdPlace(place.getId(), bundle.getLocale().toString());
                    PlaceImage placeImage = placeImageService.getPlaceImageByPlaceId(place.getId());
                    placeMarkerWithPhotos.add(new PlaceMarkerWithPhoto(place.getId(), placeDescription.getName(), place.getLatitude(), place.getLongitude(), placeImage.getReference(), placeDescription.getDescription()));
                }
                session.setAttribute("language", bundle.getLocale().toString());
//                request.setAttribute("wayPlaces", placeMarkerWithPhotos);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new Gson().toJson(placeMarkerWithPhotos));
            } else {
                //request.setAttribute("error", "Choose some places");
            }

            //loger.info("Command RoutesCommand.");
            //request.getRequestDispatcher("/views/pages/routes.jsp").forward(request, response);
        }


//    public int getFirstPlace(List<Place> places) throws IOException, JSONException {
//        int index = 0;
//        double dist = 0;
//        String o1 = "" + places.get(0).getLatitude() + " " + places.get(0).getLongitude() + "";
//        String o2 = "" + places.get(1).getLatitude() + " " + places.get(1).getLongitude() + "";
//        Distance distance = new Distance();
//        double maxMinDist = distance.getDistance(o1, o2);
//        for (int i = 0; i < places.size(); i++) {
//            o1 = "" + places.get(i).getLatitude() + " " + places.get(i).getLongitude() + "";
//            for (int j = 1; j < places.size(); i++) {
//                o2 = "" + places.get(i).getLatitude() + " " + places.get(i).getLongitude() + "";
//                dist += distance.getDistance(o1, o2);
//            }
//
//            if (maxMinDist > dist) {
//                index = i;
//                maxMinDist = dist;
//                dist = 0;
//            } else {
//                dist = 0;
//            }
//        }
//
//        return index;
//    }
    }
}
