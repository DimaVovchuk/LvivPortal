package com.lab.epam.command.page.user;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.*;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceDescriptionService;
import com.lab.epam.service.PlaceImageService;
import com.lab.epam.service.PlaceService;
import com.lab.epam.service.UserService;
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
public class UserPlaceCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    private List<Place> places = new ArrayList<>();
    private List<PlaceDescription> placeDescriptions = new ArrayList<>();
    private List<PlaceImage> placeImage = new ArrayList<>();
    private PlaceService placeService = new PlaceService();
    private PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
    private PlaceImageService placeImageService = new PlaceImageService();
    private HttpServletRequest request;
    String language;

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        UserService userservice = new UserService();
        HttpSession session = request.getSession();
        String login = (String)session.getAttribute("login");
        ResourceBundle resourceBandle = (ResourceBundle)session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        language = locale.getLanguage();
        this.request = request;

        User user = null;

        loger.info("Login in session is " + login);
        if (login != null) {
            user = userservice.getUserByLogin(login);
        }
        if (user == null){
            loger.warn("No user with login " + login);
        }
        if (user != null) {
            Integer userId = user.getId();
            //Integer roleId = user.getRoleID();
            places = placeService.getPlaceByUserId(userId);
            if (places != null && !places.isEmpty()) {
                placeDescriptions = getPlaceDescriptionByPlace(places);
                placeImage = getPlaceImageByPlace(places);
            }
        }
        List<PlaceDescriptionAndPhoto> placesPageInfo = null;
        if(places != null && !places.isEmpty()){
            placesPageInfo = getPlaceDescriptionAndPhotoList(places, placeDescriptions, placeImage);
        }
       // request.setAttribute("places", places);
       // request.setAttribute("placeImages", placeImage);
        //request.setAttribute("placeDescriptions", placeDescriptions);
        //request.setAttribute("placesPageInfo", placesPageInfo);
        request.setAttribute("places", placesPageInfo);
        loger.info("Command User Place.");
        request.getRequestDispatcher("/views/pages/userPlace.jsp").forward(request, response);

    }

    private List<PlaceDescription> getPlaceDescriptionByPlace(List<Place> places){
        Integer place_id;
        PlaceDescription placeDescription;
        List <PlaceDescription> placeDescriptions = new ArrayList<>();
        for (Place place : places) {
            place_id = place.getId();
            placeDescription = placeDescriptionService.getPlaceDescriptionByIdPlace(place_id, language);
            placeDescriptions.add(placeDescription);
        }
        System.out.println("placeDescriptions size is " + placeDescriptions.size());
        return placeDescriptions;
    }

    private List<PlaceImage> getPlaceImageByPlace(List<Place> places){
        List <PlaceImage> placeImage = new ArrayList<>();
        PlaceImage image;
        Integer place_id;
        for (Place place : places) {
                place_id = place.getId();
                image = placeImageService.getPlaceImageByPlaceId(place_id);
                if (image == null || !isInFolder(image.getReference())) {
                    image = new PlaceImage(place_id, "default_building.jpg");
                }
                placeImage.add(image);
        }
        System.out.println("placeImage size is " + placeImage.size());
        return placeImage;
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
                            item.setAdress(placeDescription.getAdress());
                            // System.out.println(item.toString());
                            list.add(item);
                        }
                    }
                }
            }
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
