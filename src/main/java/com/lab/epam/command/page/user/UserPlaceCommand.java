package com.lab.epam.command.page.user;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.PlaceDescription;
import com.lab.epam.entity.PlaceImage;
import com.lab.epam.entity.User;
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
    String language;

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        UserService userservice = new UserService();
        HttpSession session = request.getSession();
        String login = (String)session.getAttribute("login");
        ResourceBundle resourceBandle = (ResourceBundle)session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        language = locale.getLanguage();

        User user = null;

        loger.info("Login in session is " + login);
        if (login != null) {
            user = userservice.geUserByLogin(login);
        }
        if (user == null){
            loger.warn("No user with login " + login);
        }
        if (user != null) {
            Integer userId = user.getId();
            Integer roleId = user.getRoleID();
            places = placeService.getPlaceByUserId(userId);

            if (places != null && !places.isEmpty()) {
                placeDescriptions = getPlaceDescriptionByPlace(places);
                placeImage = getPlaceImageByPlace(places);
            }
        }

        request.setAttribute("places", places);
        request.setAttribute("placeImages", placeImage);
        request.setAttribute("placeDescriptions", placeDescriptions);
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
        return placeDescriptions;
    }

    private List<PlaceImage> getPlaceImageByPlace(List<Place> places){
        List <PlaceImage> placeImage = new ArrayList<>();
        Integer place_id;
        for (Place place : places) {
                place_id = place.getId();
            if (placeImageService.getPlaceImageByPlaceId(place_id) != null){
                placeImage.add(placeImageService.getPlaceImageByPlaceId(place_id));
            }
        }
        return placeImage;
    }

}
