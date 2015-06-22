package com.lab.epam.command.page.map;

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
        Map<Integer, List<Place>> placeDay = userDataTrip.getPlaceDay();
        List<Place> places = placeDay.get(1);
        Comparator<Place> placeComparator = new Comparator<Place>() {
            @Override
            public int compare(Place o1, Place o2) {
                int result = 0;
                Distance distance = new Distance();
                String obj1 =  ""+o1.getLatitude()+" "+o1.getLongitude()+"";
                String obj2 =  ""+o2.getLatitude()+" "+o2.getLongitude()+"";
                try {
                    result = distance.getDistance(obj1,obj2);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return result;
            }
        };
       // Collections.sort(places, placeComparator);
//        String obj1 =  ""+places.get(0).getLatitude()+" "+places.get(0).getLongitude()+"";
//        Distance distance = new Distance();
//        List<Place> placesSort = new ArrayList<>();
//        List<Integer> dist = new ArrayList<>();
//        for (int i = 1; i < places.size()-1; i++) {
//            String obj2 =  ""+places.get(i).getLatitude()+" "+places.get(i).getLongitude()+"";
//            try {
//                dist.add(distance.getDistance(obj1, obj2));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(dist);
        PlaceImageService placeImageService = new PlaceImageService();
        List<PlaceMarkerWithPhoto> placeMarkerWithPhotos = new ArrayList<>();
        PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
        ResourceBundle bundle = (ResourceBundle) session.getAttribute("bundle");

        for (Place place : places) {
            PlaceDescription placeDescription = placeDescriptionService.getPlaceDescriptionByIdPlace(place.getId(), bundle.getLocale().toString());
            PlaceImage placeImage = placeImageService.getPlaceImageByPlaceId(place.getId());
            if(placeImage.getReference() == null){
                placeImage.setReference("default_building.jpg");
            }
            placeMarkerWithPhotos.add(new PlaceMarkerWithPhoto(place.getId(), placeDescription.getName(), place.getLatitude(), place.getLongitude(), placeImage.getReference(), placeDescription.getDescription()));
        }
        request.setAttribute("wayPlaces", placeMarkerWithPhotos);
        loger.info("Command RoutesCommand.");
        request.getRequestDispatcher("/views/pages/routes.jsp").forward(request, response);
    }
}
