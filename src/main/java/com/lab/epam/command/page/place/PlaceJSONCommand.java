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
        Integer place_id;
        PlaceImage placeImage;
        PlaceDescription placeDescription;
        PlaceDescription placeD;
        PlaceRating placeRating;
        List<PlaceRating> placeRatings = new ArrayList<>();
        List<PlaceDescription> placeDescriptions = new ArrayList<>();
        List<PlaceDescription> placeDs = new ArrayList<>();
        List<PlaceDescription> placeDesc = new ArrayList<>();
        List<PlaceDescription> placeDescPeriod = new ArrayList<>();
        List<PlaceImage> placeImages = new ArrayList<>();

        HttpSession session = request.getSession();

        ResourceBundle resourceBandle = (ResourceBundle) session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        String language = locale.getLanguage();

        String login = (String) session.getAttribute("login");
        User user = null;
        if (login != null) {
            user = userService.getUserByLogin(login);
        }

        List<Place> places = new ArrayList<>();
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
        String searchString = request.getParameter("txtSearch");
        Integer countPlace = 0;
        Map<Integer, List<PlaceDescription>> results = new HashMap<>();
        if(searchString != null && !searchString.equals("")){
            searchString = searchString.trim();
            searchString = searchString.toLowerCase();
            placeDesc = placeDescriptionService.getAllPlaceBySearch(searchString);
            if (placeDesc == null || placeDesc.isEmpty()  || !placeDesc.iterator().next().getName().equalsIgnoreCase(searchString)){
                placeDesc.clear();
                searchString = searchString.toLowerCase();
            String[] searchParth = searchString.split(" ");
            placeDs = placeDescriptionService.getPlaceByLanguege(language);//getAllPlaceBySearch(searchString);
                if(user !=null){
                    List<Place> custom = servicePlace.getAllVisbleUserCustomPlace(user.getId());
                    if(custom != null && !custom.isEmpty()){
                        for (Place plCustom: custom){
                            PlaceDescription plDCustom = placeDescriptionService.getPlaceDescriptionByIdPlace(plCustom.getId(),language);
                            if (plDCustom != null){
                                placeDs.add(plDCustom);
                            }
                        }
                    }
                }
            if (placeDs != null && !placeDs.isEmpty()) {
                for (PlaceDescription place : placeDs) {
                    String name = place.getName().replaceAll("\"", "");
                    name = name.toLowerCase();
                    String[] nameParth = name.split(" ");
                    Integer count = 0;
                    for (String parth : nameParth) {
                        for (String res : searchParth) {
                            if (parth.contains(res)) {
                                count++;
                            }
                        }
                    }
                    if (count >= searchParth.length) {
                        if (countPlace < 10) {
                            placeDesc.add(place);
                            countPlace++;
                        }
                    }

                }
                if (placeDesc.isEmpty()) {
                    Integer fail = 0;
                    for (int i = 1; i <= 3; i++) {
                        results.put(i, new ArrayList<PlaceDescription>());
                    }
                    for (PlaceDescription place : placeDs) {
                        String name = place.getName().replaceAll("\"", "");
                        name = name.toLowerCase();
                        fail = distanse(name, searchString);
                        if (fail <= 3) {
                            List<PlaceDescription> pl = results.get(fail);
                            if (pl.size() < 10) {
                                pl.add(place);
                            }
                            results.put(fail, pl);

                        }
                    }
                    if (!results.isEmpty()) {
                        for (int i = 1; i <= 15; i++) {
                            List<PlaceDescription> pl = results.get(i);
                            if (pl != null && !pl.isEmpty()) {
                                placeDesc = pl;
                                break;
                            }
                        }
                    }
                }
            }
            }
            if (placeDesc != null && !placeDesc.isEmpty()){
                for (PlaceDescription placeDescript: placeDesc){
                    place_id = placeDescript.getPlace_id();
                    placeDescript = placeDescriptionService.getPlaceDescriptionByIdPlace(place_id, language);
                    placeDescriptions.add(placeDescript);

                    Place place = servicePlace.getByPK(place_id);
                    places.add(place);
                    placeImage = placeImageService.getPlaceImageByPlaceId(place_id);
                    if (placeImage == null || !isInFolder(placeImage.getReference(), request)) {
                        placeImage = new PlaceImage(place_id, "default_building.jpg");
                    }
                    placeImages.add(placeImage);
                    if (user != null) {
                        placeRating = placeRatingService.getPlaceRatingByPlaceAndUser(place_id, user.getId());
                        if (placeRating == null) {
                            placeRating = new PlaceRating(user.getId(), place_id, 0);
                        }
                        placeRatings.add(placeRating);

                    }
                }
            }

        } else {

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
                        places = servicePlace.getAllPlaceVisible();
                        break;
                }
            } else {
                places = servicePlace.getAllPlaceVisible();
            }

            Comparator comparator = new Place.PlaceComparator();
            Collections.sort(places, comparator);

            for (Place place : places) {
                place_id = place.getId();
                placeDescription = placeDescriptionService.getPlaceDescriptionByIdPlace(place_id, language);
                placeDescriptions.add(placeDescription);
                placeImage = placeImageService.getPlaceImageByPlaceId(place_id);
                if (placeImage == null || !isInFolder(placeImage.getReference(), request)) {
                    placeImage = new PlaceImage(place_id, "default_building.jpg");
                }
                placeImages.add(placeImage);
                if (user != null) {
                    placeRating = placeRatingService.getPlaceRatingByPlaceAndUser(place_id, user.getId());
                    if (placeRating == null) {
                        placeRating = new PlaceRating(user.getId(), place_id, 0);
                    }
                    placeRatings.add(placeRating);

                }

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
                                        item.setAdress(placeDescription.getAdress());
                                        item.setRating(placeRating.getRating());
                                        list.add(item);
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
                                item.setAdress(placeDescription.getAdress());
                                item.setRating(0);
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

    private Integer distanse(String s1, String s2){
        Integer m = s1.length();
        Integer n = s2.length();
        char[] charS2 = s2.toCharArray();
        Integer count = 0;
        if (charS2.length >= 2){
            for (int i = 0; i < charS2.length - 1; i++){
                String s = new StringBuilder().append(charS2[i]).append(charS2[i + 1]).toString();;
                if (s1.contains(s)){
                    count++;
                }
            }
        }
        Integer distance = n - count;
        return distance;
    }

}

