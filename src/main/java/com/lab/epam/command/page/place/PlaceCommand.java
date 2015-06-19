package com.lab.epam.command.page.place;

import com.lab.epam.command.controller.Command;
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
public class PlaceCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        PlaceService servicePlace = new PlaceService();

        PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
        PlaceImageService placeImageService = new PlaceImageService();

        HttpSession session = request.getSession();

        ResourceBundle resourceBandle = (ResourceBundle) session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        String language = locale.getLanguage();


        List<Place> places = null;
        List<Place> placeForWay;
        UserDataAboutTrip userDataAboutTrip = (UserDataAboutTrip) session.getAttribute("userDataTrip");
        placeForWay = (ArrayList<Place>) session.getAttribute("placeForWay");
        String dayNumberString = request.getParameter("dayNumber");
        Integer dayNumber = 0;
        if (dayNumberString != null) {
            dayNumber = Integer.parseInt(dayNumberString);
        }
        //

        String placeId = request.getParameter("place_id");
        Place onePlaceForWay = null;
        Boolean isInWay = false;

        if (placeId != null && userDataAboutTrip != null && dayNumber != 0) {
            onePlaceForWay = servicePlace.getByPK(Integer.parseInt(placeId));
            loger.info("Get place is successfull");
            Map<Integer, List<Place>> map = userDataAboutTrip.getPlaceDay();
            loger.info("Get map is successfull");
            Set<Integer> keys = map.keySet();
            if (onePlaceForWay != null || !keys.contains(dayNumber)) {
                if (map.isEmpty()) {
                    placeForWay = new ArrayList<>();
                    loger.info("Create new List<Place>");
                    loger.info("Day is " + dayNumber);
                } else {
                    placeForWay = map.get(dayNumber);
                    loger.info("Get placeForWay");
                    for (Place place : placeForWay) {
                        if (place.getId() == onePlaceForWay.getId()) {
                            isInWay = true;
                        }
                    }
                }
                if (!isInWay) {
                    placeForWay.add(onePlaceForWay);
                    loger.info("Add new place to placeForWay. Place is " + onePlaceForWay);
                }
            }
            map.put(dayNumber, placeForWay);
            loger.info("Put placeForWay to map");
            userDataAboutTrip.setPlaceDay(map);
            loger.info("Set map to userDataAboutTrip");
        }
        System.out.println("userDataTrip " + userDataAboutTrip);
        session.setAttribute("userDataTrip", userDataAboutTrip);

        String category = request.getParameter("category");
        if (category == null) {
            category = "";
        }
        if (category != null) {
            switch (category) {
                case "sights":
                    places = servicePlace.getPlaceByCategory(1);
                    break;
                case "churches":
                    places = servicePlace.getPlaceByCategory(2);
                    break;
                case "theatres":
                    places = servicePlace.getPlaceByCategory(3);
                    break;
                case "hotels":
                    places = servicePlace.getPlaceByCategory(4);
                    break;
                case "restaurants":
                    places = servicePlace.getPlaceByCategory(5);
                    break;
                default:
                    places = servicePlace.getAll();
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
        for (Place place : places) {
            place_id = place.getId();

            placeDescription = placeDescriptionService.getPlaceDescriptionByIdPlace(place_id, language);
            placeDescriptions.add(placeDescription);
            placeImage = placeImageService.getPlaceImageByPlaceId(place_id);
            if (placeImage == null) {
                placeImage = new PlaceImage(place_id, "default_building.jpg");
            }
            placeImages.add(placeImage);
        }

        List<PlaceDescriptionAndPhoto> placesPageInfo = getPlaceDescriptionAndPhotoList(places, placeDescriptions, placeImages);
        //request.setAttribute("places", places);
        loger.info("places = " + places);
        //request.setAttribute("placeDescriptions", placeDescriptions);
        loger.info("placeDescriptions = " + placeDescriptions);
        loger.info("placeImages = " + placeImages);
        //request.setAttribute("placeImages", placeImages);
        request.setAttribute("category", category);
        request.setAttribute("places", placesPageInfo);
        loger.info("Command Place.");
        request.getRequestDispatcher("/views/pages/places.jsp").forward(request, response);
    }

    private List<PlaceDescriptionAndPhoto> getPlaceDescriptionAndPhotoList(List<Place> places, List<PlaceDescription> placeDescriptions, List<PlaceImage> placeImages) {
        List<PlaceDescriptionAndPhoto> list = new ArrayList<>();
        for (PlaceDescription placeDescription : placeDescriptions) {
            for (Place place : places) {
                for (PlaceImage placeImage : placeImages) {
                    if (place.getId() == placeDescription.getPlace_id()) {
                        if (place.getId() == placeImage.getPlace_id()) {
                            PlaceDescriptionAndPhoto item = new PlaceDescriptionAndPhoto();
                            item.setId(place.getId());
                            item.setImageReference(placeImage.getReference());
                            item.setName(placeDescription.getName());
                            item.setAdress(place.getAdress());
                            System.out.println(item.toString());
                            list.add(item);
                        }
                    }
                }
            }
        }
        return list;
    }

}
