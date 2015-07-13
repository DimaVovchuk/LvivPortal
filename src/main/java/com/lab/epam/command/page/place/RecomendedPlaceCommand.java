package com.lab.epam.command.page.place;

import com.google.gson.Gson;
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
 * Created by Admin on 27.06.2015.
 */
public class RecomendedPlaceCommand  implements Command {
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

        String category = request.getParameter("category");
        if (category == null) {
            category = "";
        }
        if (category != null) {
            switch (category) {
                case "architecture":
                    places = servicePlace.getPlaceByCategoryRecomended(1);
                    request.setAttribute("active_architecture", "active");
                    break;
                case "churches":
                    places = servicePlace.getPlaceByCategoryRecomended(2);
                    request.setAttribute("active_churches", "active");
                    break;
                case "theatres":
                    places = servicePlace.getPlaceByCategoryRecomended(3);
                    request.setAttribute("active_theatres", "active");
                    break;
                case "hotels":
                    places = servicePlace.getPlaceByCategoryRecomended(4);
                    request.setAttribute("active_hotels", "active");
                    break;
                case "restaurants":
                    places = servicePlace.getPlaceByCategoryRecomended(5);
                    request.setAttribute("active_restaurants", "active");
                    break;
                default:
                    places = servicePlace.getAllPlaceRecomended();
                    request.setAttribute("active_allplaces", "active");
                    break;
            }
        } else {
            places = servicePlace.getAllPlaceRecomended();
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
        //request.setAttribute("category", category);
        //request.setAttribute("places", placesPageInfo);

        loger.info("Command RecomendedPlaceCommand.");
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
}
