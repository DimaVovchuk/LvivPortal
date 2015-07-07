package com.lab.epam.command.page.user;

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
 * Created by Admin on 24.06.2015.
 */
public class UserAllWayCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    private List<Way> ways = new ArrayList<>();
    private List<Place> places = new ArrayList<>();
    private List<PlaceDescription> placeDescriptions = new ArrayList<>();
    private Map<Integer, List<PlaceDescription>> way_place = new HashMap<>();
    private PlaceService placeService = new PlaceService();
    private PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
    private Map<Integer, PlaceImage> wayPlaceImages = new HashMap<>();
    private PlaceImageService placeImageService = new PlaceImageService();
    private HttpServletRequest request;
    String language;


    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        UserService userservice = new UserService();
        WayService wayService = new WayService();
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
        if (user != null) {
            Integer userId = user.getId();
            Integer roleId = user.getRoleID();
            ways = wayService.getWaysByUserId(userId);
            places = placeService.getPlaceByUserId(userId);

            if (ways != null && !ways.isEmpty()) {
                way_place = getPlaceDescriptionByWay(ways);
            }

            if (places != null && !places.isEmpty()) {
                placeDescriptions = getPlaceDescriptionByPlace(places);
            }
        }

        List<WayPlaceImage> waysPlaceImage = null;
        if(ways != null && !ways.isEmpty()){
            waysPlaceImage = getWayPlaceImageList(ways, way_place, wayPlaceImages);
        }
        request.setAttribute("places", places);
        request.setAttribute("waysPlaceImage", waysPlaceImage);
        request.setAttribute("user", user);

        loger.info("Command User All Ways.");
        request.getRequestDispatcher("/views/pages/userAllWays.jsp").forward(request, response);

    }

    private List<WayPlaceImage> getWayPlaceImageList(List<Way> ways,  Map<Integer, List<PlaceDescription>> way_place, Map<Integer,PlaceImage> wayPlaceImages){
        List<WayPlaceImage> list = new ArrayList<>();
        System.out.println("ways " + ways.size() + " way_place " + way_place.size() + " wayPlaceImages " + wayPlaceImages.size());
        if(ways != null && !ways.isEmpty()){
            for (Way way : ways) {
                WayPlaceImage item = new WayPlaceImage();
                item.setId(way.getId());
                System.out.println("beginn " + way.getBegin());
                item.setBeginDate(way.getBegin());
                System.out.println("end " + way.getEnd());
                item.setEndDate(way.getEnd());
                if (wayPlaceImages.size() <= way.getId()){
                    if (wayPlaceImages.get(way.getId()) != null){
                        item.setImageReference(wayPlaceImages.get(way.getId()).getReference());
                    }
                }
                if (way_place.size() <= way.getId()){
                    item.setPlace(way_place.get(way.getId()));
                }
                list.add(item);
            }
        }
        return list;
    }

    private Map<Integer, List<PlaceDescription>> getPlaceDescriptionByWay (List < Way > ways) {
        List<PlaceDescription> way_placeDescription_list = new ArrayList<>();
        List<Place> way_place_list = new ArrayList<>();
        Map<Integer, List<PlaceDescription>> way_place = new HashMap<>();
        Integer way_id;
        for (Way way : ways) {
            way_id = way.getId();
            way_place_list = placeService.getPlaceByWayId(way_id);
            if (way_place_list != null && !way_place_list.isEmpty()) {
                //      loger.info(" way_place_list are " + way_place_list + " way_id" + way_id);
                way_placeDescription_list = getPlaceDescriptionByPlace(way_place_list);
                //        loger.info(" way_placeDescription_list are " + way_placeDescription_list);
                way_place.put(way_id, way_placeDescription_list);
                wayPlaceImages.put(way_id, getPlaceImageByPlace(way_place_list));
            }

        }
        //  loger.info(" way_place are " + way_place);
        //    loger.info(" wayPlaceImages are " + wayPlaceImages);
        return way_place;
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

    private List<PlaceDescription> getPlaceDescriptionByPlace (List < Place > places) {
        Integer place_id;
        PlaceDescription placeDescription;
        List<PlaceDescription> placeDescriptions = new ArrayList<>();
        if (!places.isEmpty()) {
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
        if (!places.isEmpty()) {
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
