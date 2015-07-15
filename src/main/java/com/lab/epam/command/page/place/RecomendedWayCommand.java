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
 * Created by Admin on 01.07.2015.
 */
public class RecomendedWayCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    private List<Way> ways = new ArrayList<>();
    private List<Place> places = new ArrayList<>();
    private List<PlaceDescription> placeDescriptions = new ArrayList<>();
    private List<WayRating> wayRatings = new ArrayList<>();
    private Map<Integer, List<PlaceDescription>> way_place = new HashMap<>();
    private PlaceService placeService = new PlaceService();
    private PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
    private Map<Integer, PlaceImage> wayPlaceImages = new HashMap<>();
    private PlaceImageService placeImageService = new PlaceImageService();
    private HttpServletRequest request;
    String language;


    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        loger.info("RecomendedWayCommand start");
        UserService userservice = new UserService();
        WayService wayService = new WayService();
        WayRatingService wayRatingService = new WayRatingService();
        List<WayPlaceImageRating> waysPlaceImageRating = null;
        this.request = request;
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        ResourceBundle resourceBandle = (ResourceBundle) session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        language = locale.getLanguage();

        User user = null;

        loger.info("Login in session is " + login);
        if (login != null) {
            user = userservice.getUserByLogin(login);
        }
        if (user == null) {
            loger.warn("No user with login " + login);
        }
        ways = wayService.getAllWayRecomended();


        if (ways != null && !ways.isEmpty()) {
            //way_place = getPlaceDescriptionByWay(ways);
            waysPlaceImageRating = new ArrayList<>();
            for (Way way : ways) {
                Map<Integer, List<PlaceDescription>> item = new HashMap<>();
                WayPlaceImageRating itemWayPlaceImage = new WayPlaceImageRating();
                if (way.getWay_days() > 1) {
                    for (int i = 1; i <= way.getWay_days(); i++) {
                        List<Place> placeList = placeService.getPlaceByWayIdDayNumber(way.getId(), i);
                        if (placeList != null) {
                            List<PlaceDescription> placeDecsription = getPlaceDescriptionByPlace(placeList);
                            item.put(i, placeDecsription);
                            PlaceImage plIm = getPlaceImageByPlace(placeList);
                            itemWayPlaceImage.setImageReference(plIm.getReference());
                        }
                    }
                } else {
                    List <Place> placeList = placeService.getPlaceByWayIdDayNumber(way.getId(), 1);
                    if (placeList != null){
                        List <PlaceDescription> placeDecsription = getPlaceDescriptionByPlace(placeList);
                        PlaceImage plIm = getPlaceImageByPlace(placeList);
                        itemWayPlaceImage.setImageReference(plIm.getReference());
                        item.put(1, placeDecsription);
                    }
                }
//                    List<Place> placeList = placeService.getPlaceByWayIdDayNumber(way.getId(), 1);
//                    List<PlaceDescription> placeDecsription = getPlaceDescriptionByPlace(placeList);
//                    PlaceImage plIm = getPlaceImageByPlace(placeList);
//                    itemWayPlaceImage.setImageReference(plIm.getReference());
//                    item.put(1, placeDecsription);
//                }
                itemWayPlaceImage.setPlace(item);
                itemWayPlaceImage.setId(way.getId());
                itemWayPlaceImage.setBeginDate(way.getBegin());
                itemWayPlaceImage.setEndDate(way.getEnd());
                itemWayPlaceImage.setName(way.getName());
                if (user != null && ways != null) {
                    WayRating wayRating = wayRatingService.getWayRatingByWayAndUser(way.getId(), user.getId());
                    if (wayRating == null) {
                        wayRating = new WayRating(user.getId(), way.getId(), 0);
                    }
                    itemWayPlaceImage.setRating(wayRating.getRating());
                }
                itemWayPlaceImage.setRating_way(way.getRating());
                waysPlaceImageRating.add(itemWayPlaceImage);
            }

        }


        Comparator comparator = new WayPlaceImageRating.WayRatingComparator();
        if (waysPlaceImageRating != null){
            Collections.sort(waysPlaceImageRating, comparator);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(waysPlaceImageRating));

    }


    private List<PlaceImage> getPlaceImageListByPlace(List<Place> places) {
        List<PlaceImage> placeImages = new ArrayList<>();
        Integer place_id;
        if (!places.isEmpty()) {
            for (Place place : places) {
                place_id = place.getId();
                PlaceImage placeImage = placeImageService.getPlaceImageByPlaceId(place_id);
                if (placeImage.getReference() == null || !isInFolder(placeImage.getReference())) {
                    placeImage = new PlaceImage(place_id, "default_building.jpg");
                }
                placeImages.add(placeImage);
            }
        }
        return placeImages;
    }

    private List<PlaceDescription> getPlaceDescriptionByPlace(List<Place> places) {
        Integer place_id;
        PlaceDescription placeDescription;
        List<PlaceDescription> placeDescriptions = new ArrayList<>();
        if (places != null && !places.isEmpty()) {
            for (Place place : places) {
                place_id = place.getId();
                placeDescription = placeDescriptionService.getPlaceDescriptionByIdPlace(place_id, language);
                placeDescriptions.add(placeDescription);
            }
        }
        return placeDescriptions;
    }

    private PlaceImage getPlaceImageByPlace(List<Place> places) {
        PlaceImage placeImage = null;
        Integer place_id;
        if (places != null && !places.isEmpty()) {
            for (Place place : places) {
                if (placeImage == null) {
                    place_id = place.getId();
                    placeImage = placeImageService.getPlaceImageByPlaceId(place_id);
                    if (placeImage.getReference() == null || !isInFolder(placeImage.getReference())) {
                        placeImage = new PlaceImage(place_id, "default_building.jpg");
                    }
                }
            }
        }
        return placeImage;
    }

    private Boolean isInFolder(String fileName) {
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
