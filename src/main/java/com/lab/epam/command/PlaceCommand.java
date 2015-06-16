package com.lab.epam.command;

import com.lab.epam.entity.*;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceDescriptionService;
import com.lab.epam.service.PlaceImageService;
import com.lab.epam.service.PlaceService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by Dima on 11-Jun-15.
 */
public class PlaceCommand implements Command{
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        PlaceService servicePlace = new PlaceService();

        PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
        PlaceImageService placeImageService = new PlaceImageService();

        HttpSession session = request.getSession();

        ResourceBundle resourceBandle = (ResourceBundle)session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        String language = locale.getLanguage();


        List<Place> places = null;

        String category = request.getParameter("category");
        if (category != null){
            switch(category){
                case "sights": places = servicePlace.getPlaceByCategory(1);
                    break;
                case "churches": places = servicePlace.getPlaceByCategory(2);
                    break;
                case "theatres": places = servicePlace.getPlaceByCategory(3);
                    break;
                case "hotels": places = servicePlace.getPlaceByCategory(4);
                    break;
                case "restaurants": places = servicePlace.getPlaceByCategory(5);
                    break;
                default: servicePlace.getAll();
                    break;
            }
        } else {
            places = servicePlace.getAll();
        }
        List<PlaceDescription> placeDescriptions = new ArrayList<>();
        List<PlaceImage> placeImages = new ArrayList<>();

        Comparator comparator = new Place.PlaceComparator();
        Collections.sort(places, comparator);
        Integer place_id;
        PlaceImage placeImage;
        PlaceDescription placeDescription;
        for (Place place: places){
            place_id = place.getId();
            placeDescription = placeDescriptionService.getPlaceDescriptionByIdPlace(place_id,language);
            placeDescriptions.add(placeDescription);
            placeImage = placeImageService.getPlaceImageByPlaceId(place_id);
            if (placeImage == null) {
                placeImage = new PlaceImage(place_id,"default_building.jpg");
            }
            placeImages.add(placeImage);
        }

        request.setAttribute("places", places);
        loger.info("places = " + places);
        request.setAttribute("placeDescriptions", placeDescriptions);
        loger.info("placeDescriptions = " + placeDescriptions);
        loger.info("placeImages = " + placeImages);
        request.setAttribute("placeImages", placeImages);

        loger.info("Command Place.");
        request.getRequestDispatcher("/views/pages/places.jsp").forward(request, response);
    }
}
