package com.lab.epam.command.page.place;

import com.google.gson.Gson;
import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.*;
import com.lab.epam.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class PlaceJSONCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        Integer dayNumber = 0;
        if (dayNumberString != null) {
            dayNumber = Integer.parseInt(dayNumberString);
        }

        String placeId = request.getParameter("place_id");
        Place onePlaceForWay = null;
        Boolean isInWay = false;

        if (placeId != null && userDataAboutTrip != null && dayNumber != 0) {
            onePlaceForWay = servicePlace.getByPK(Integer.parseInt(placeId));
            Map<Integer, List<Place>> map = userDataAboutTrip.getPlaceDay();
            Set<Integer> keys = map.keySet();
            if (onePlaceForWay != null || !keys.contains(dayNumber)) {
                if (map.isEmpty()) {
                    placeForWay = new ArrayList<>();
                } else {
                    placeForWay = map.get(dayNumber);
                    for (Place place : placeForWay) {
                        if (place.getId() == onePlaceForWay.getId()) {
                            isInWay = true;
                        }
                    }
                }
                if (!isInWay) {
                    placeForWay.add(onePlaceForWay);
                }
            }
            map.put(dayNumber, placeForWay);
            userDataAboutTrip.setPlaceDay(map);
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
        PlaceRating placeRating;
        List<PlaceRating> placeRatings = new ArrayList<>();

        String login = (String)session.getAttribute("login");
        User user = null;
        if (login != null){
            user = userService.getUserByLogin(login);
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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(placesPageInfo));
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
                                        //  System.out.println(item.toString());
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
                                //   System.out.println(item.toString());
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
