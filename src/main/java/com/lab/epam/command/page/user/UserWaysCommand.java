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
 * Created by Admin on 17.06.2015.
 */
public class UserWaysCommand implements Command {

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

        //Get the maintenance user trip
        UserDataAboutTrip userDataAboutTrip = (UserDataAboutTrip) session.getAttribute("userDataTrip");
        Integer dayCount;
        Map<Integer, List<Place>> mapPlaceMaintenance = null;
        Map<Integer, List<PlaceDescription>> mapPlaceDescriptionMaintenance = new HashMap<>();
        Map<Integer, List<PlaceImage>> mapPlaceImageMaintenance = new HashMap<>();
        List<DayPlaceImage> daysPlaceImage = null;


        if (userDataAboutTrip != null){
            dayCount = userDataAboutTrip.getDayCount();
            mapPlaceMaintenance = userDataAboutTrip.getPlaceDay();
            if(!mapPlaceMaintenance.isEmpty()){
                Set<Integer> keys = mapPlaceMaintenance.keySet();
                for (Integer key : keys) {
                    List<PlaceDescription> placeDescription = getPlaceDescriptionByPlace(mapPlaceMaintenance.get(key));
                    mapPlaceDescriptionMaintenance.put(key, placeDescription);
                    List<PlaceImage> placeImage = getPlaceImageListByPlace(mapPlaceMaintenance.get(key));
                    mapPlaceImageMaintenance.put(key, placeImage);
                }
            }
        }

        if(mapPlaceMaintenance != null && !mapPlaceDescriptionMaintenance.isEmpty() && !mapPlaceImageMaintenance.isEmpty()) {
            daysPlaceImage = getDayPlaceImageList(mapPlaceDescriptionMaintenance, mapPlaceImageMaintenance);
        }

        // Get user data about way from DB
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

        List<WayPlaceImage> waysPlaceImage = getWayPlaceImageList(ways, way_place, wayPlaceImages);
        request.setAttribute("places", places);
            //request.setAttribute("ways", ways);
        request.setAttribute("waysPlaceImage", waysPlaceImage);
        request.setAttribute("user", user);
        request.setAttribute("daysPlaceImage", daysPlaceImage);
           //request.setAttribute("placeDescription", placeDescriptions);
            //request.setAttribute("way_place", way_place);
           // request.setAttribute("wayPlaceImages", wayPlaceImages);

            loger.info("Command User Ways.");
            request.getRequestDispatcher("/views/pages/userWay.jsp").forward(request, response);
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
                }
            }
        }
        return placeImage;
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

    private List<WayPlaceImage> getWayPlaceImageList(List<Way> ways,  Map<Integer, List<PlaceDescription>> way_place, Map<Integer,PlaceImage> wayPlaceImages){
        List<WayPlaceImage> list = new ArrayList<>();
        for (Way way : ways) {
            WayPlaceImage item = new WayPlaceImage();
            item.setId(way.getId());
            item.setImageReference(wayPlaceImages.get(way.getId()).getReference());
            item.setPlace(way_place.get(way.getId()));
            list.add(item);
        }
        return list;
    }

    private List<DayPlaceImage> getDayPlaceImageList(Map<Integer, List<PlaceDescription>> place, Map<Integer,List<PlaceImage>> placeImages){
        List<DayPlaceImage> list = new ArrayList<>();
        Set<Integer> keys = place.keySet();
        for (Integer key : keys) {
            DayPlaceImage item = new DayPlaceImage();
            item.setDay(key);
            List<ImageDescription> imageDescriptionList = new ArrayList<>();
            List<PlaceDescription> placeList = place.get(key);
            List<PlaceImage> imageList = placeImages.get(key);
            for (PlaceDescription placeDescription: placeList){
                for (PlaceImage image: imageList){
                    if(image.getPlace_id() == placeDescription.getPlace_id()) {
                        ImageDescription imageDescriptionItem = new ImageDescription();
                        imageDescriptionItem.setDescription(placeDescription.getDescription());
                        imageDescriptionItem.setImageReference(image.getReference());
                        imageDescriptionItem.setLocale(placeDescription.getLocale());
                        imageDescriptionItem.setName(placeDescription.getName());
                        imageDescriptionItem.setPlace_id(placeDescription.getPlace_id());
                        imageDescriptionItem.setPrice(placeDescription.getPrice());
                        imageDescriptionList.add(imageDescriptionItem);
                    }
                }
            }
            item.setPlaceImage(imageDescriptionList);
            list.add(item);
        }
        return list;
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


