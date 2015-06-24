package com.lab.epam.command.page.place;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.*;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
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
        PlaceRatingService placeRatingService = new PlaceRatingService();
        UserService userService = new UserService();

        HttpSession session = request.getSession();

        ResourceBundle resourceBandle = (ResourceBundle) session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        String language = locale.getLanguage();


        List<Place> places = null;
        List<Place> placeForWay;
        UserDataAboutTrip userDataAboutTrip = (UserDataAboutTrip) session.getAttribute("userDataTrip");
        placeForWay = (ArrayList<Place>) session.getAttribute("placeForWay");
        String dayNumberString = request.getParameter("dayNumber");
        String timePlaceString = request.getParameter("timePlace");

        Integer dayNumber = 0;
        Integer timePlace = 0;

        if (dayNumberString != null) {
            dayNumber = Integer.parseInt(dayNumberString);
        }
        //
        if (timePlaceString != null) {
            timePlace = Integer.parseInt(timePlaceString);
        }

        String placeId = request.getParameter("place_id");
        Place onePlaceForWay = null;
        Boolean isInWay = false;

        if (placeId != null && userDataAboutTrip != null && dayNumber != 0) {
            onePlaceForWay = servicePlace.getByPK(Integer.parseInt(placeId));
            loger.info("Get place is successfull");
            Map<Integer, List<Place>> map = userDataAboutTrip.getPlaceDay();
            loger.info("Get map is successfull");
            Set<Integer> keys = map.keySet();
            if (onePlaceForWay != null) {
                if (map.isEmpty() || !keys.contains(dayNumber)) {
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
                    if (timePlace != 0) {
                        onePlaceForWay.setPlace_time(timePlace);
                        placeForWay.add(onePlaceForWay);
                        loger.info("Add new place to placeForWay. Place is " + onePlaceForWay);
                    }
                }
            }
            map.put(dayNumber, placeForWay);
            loger.info("Put placeForWay to map");
            userDataAboutTrip.setPlaceDay(map);
            loger.info("Set map to userDataAboutTrip");
        }
        session.setAttribute("userDataTrip", userDataAboutTrip);

        String category = request.getParameter("category");
        if (category == null) {
            category = "";
        }
        if (category != null) {
            switch (category) {
                case "architecture":
                    places = servicePlace.getPlaceByCategory(1);
                    request.setAttribute("active_architecture", "active");
                    break;
                case "churches":
                    places = servicePlace.getPlaceByCategory(2);
                    request.setAttribute("active_churches", "active");
                    break;
                case "theatres":
                    places = servicePlace.getPlaceByCategory(3);
                    request.setAttribute("active_theatres", "active");
                    break;
                case "hotels":
                    places = servicePlace.getPlaceByCategory(4);
                    request.setAttribute("active_hotels", "active");
                    break;
                case "restaurants":
                    places = servicePlace.getPlaceByCategory(5);
                    request.setAttribute("active_restaurants", "active");
                    break;
                default:
                    places = servicePlace.getAll();
                    request.setAttribute("active_allplaces", "active");
                    break;
            }
        } else {
            places = servicePlace.getAll();
        }
        List<PlaceDescription> placeDescriptions = new ArrayList<>();
        List<PlaceImage> placeImages = new ArrayList<>();
        List<PlaceRating> placeRatings = new ArrayList<>();

        Comparator comparator = new Place.PlaceComparator();
        Collections.sort(places, comparator);
        Integer place_id;
        PlaceImage placeImage;
        PlaceDescription placeDescription;
        PlaceRating placeRating;

        String login = (String)session.getAttribute("login");
        User user = null;
        if (login != null){
            user = userService.geUserByLogin(login);
        }

        for (Place place : places) {
            place_id = place.getId();

            placeDescription = placeDescriptionService.getPlaceDescriptionByIdPlace(place_id, language);
            placeDescriptions.add(placeDescription);

            placeImage = placeImageService.getPlaceImageByPlaceId(place_id);
            if (placeImage == null || !isInFolder(placeImage.getReference(), request)) {
                placeImage = new PlaceImage(place_id, "default_building.jpg");
            }
            placeImages.add(placeImage);

            if (user != null){
                placeRating = placeRatingService.getPlaceRatingByPlaceAndUser(place_id,user.getId());
                if (placeRating == null){
                    placeRating = new PlaceRating(user.getId(),place_id,0);
                }
                placeRatings.add(placeRating);

            }

        }

        List<PlaceDescriptionAndPhoto> placesPageInfo = getPlaceDescriptionAndPhotoList(places, placeDescriptions, placeImages, placeRatings);
        request.setAttribute("category", category);
        request.setAttribute("places", placesPageInfo);
        request.setAttribute("userDataTrip", userDataAboutTrip);
        loger.info("Command Place.");
        request.setAttribute("active_places", "active");
        request.getRequestDispatcher("/views/pages/places.jsp").forward(request, response);
    }

    private List<PlaceDescriptionAndPhoto> getPlaceDescriptionAndPhotoList(List<Place> places, List<PlaceDescription> placeDescriptions, List<PlaceImage> placeImages, List<PlaceRating> placeRatings) {
        List<PlaceDescriptionAndPhoto> list = new ArrayList<>();
        for (PlaceDescription placeDescription : placeDescriptions) {
            for (Place place : places) {
                for (PlaceImage placeImage : placeImages) {
                    if (!placeRatings.isEmpty()){
                        for (PlaceRating placeRating : placeRatings) {
                            if (place.getId() == placeDescription.getPlace_id()) {
                                if (place.getId() == placeImage.getPlace_id()) {
                                    if (placeRating.getPlace_id() == place.getId()) {
                                        PlaceDescriptionAndPhoto item = new PlaceDescriptionAndPhoto();
                                        item.setId(place.getId());
                                        item.setImageReference(placeImage.getReference());
                                        item.setName(placeDescription.getName());
                                        item.setAdress(place.getAdress());
                                        item.setRating(placeRating.getRating());
                                        System.out.println(item.toString());
                                        list.add(item);
                                        // System.out.println(item.toString());
                                    }
                                }
                            }
                        }
                    } else {
                        if (place.getId() == placeDescription.getPlace_id()) {
                            if (place.getId() == placeImage.getPlace_id()) {
                                PlaceDescriptionAndPhoto item = new PlaceDescriptionAndPhoto();
                                item.setId(place.getId());
                                item.setImageReference(placeImage.getReference());
                                item.setName(placeDescription.getName());
                                item.setAdress(place.getAdress());
                                item.setRating(0);
                                System.out.println(item.toString());
                                list.add(item);
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

    private Boolean isInFolder(String fileName, HttpServletRequest request) {
        ClassLoader classLoader = getClass().getClassLoader();
        String realPath = request.getRealPath("/upload/photo/");
        File f = new File(realPath);
        String[] list = f.list();
        for (String file : list) {
            if (fileName.equals(file)) {
                return true;
            }
        }
        return false;
    }
}
