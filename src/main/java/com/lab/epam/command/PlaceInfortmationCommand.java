package com.lab.epam.command;

import com.lab.epam.entity.*;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Admin on 13.06.2015.
 */
public class PlaceInfortmationCommand implements Command{

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        String message = request.getParameter("Message");
        loger.info("Message is " + message);
        String email = request.getParameter("Email");
        loger.info("Email is " + email);
        String login = request.getParameter("Name");
        loger.info("Login is " + login);


        PlaceService servicePlace = new PlaceService();
        PlaceResponseService placeResponseService = new PlaceResponseService();

        PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
        PlaceImageService placeImageService = new PlaceImageService();
        UserService userService = new UserService();
        UserImageService userImageService = new UserImageService();

        HttpSession session = request.getSession();

        ResourceBundle resourceBandle = (ResourceBundle)session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        String language = locale.getLanguage();

        String place_idString = request.getParameter("place_id");
        Integer place_id = Integer.parseInt(place_idString);

        loger.info("Place with id " + place_id);

        Place place = servicePlace.getByPK(place_id);

        loger.info("Place with id " + place_id + " is " + place);

        PlaceDescription placeDescription;
        PlaceImage placeImage;
        User user;
        UserImage userImage;
        List<PlaceResponse> placeResponse;
        List<UserImage> userImages = new ArrayList<>();
        List<User> users = new ArrayList<>();
        Integer user_id;

        place_id = place.getId();
        String[] infoPlacePhone = null;
        String[] infoPlacePrice = null;
        placeDescription = placeDescriptionService.getPlaceDescriptionByIdPlace(place_id,language);

        if (placeDescription == null){
            loger.info("Not place description for place_id " + place_id);
        } else{
            if (placeDescription.getPhone() != null){
                infoPlacePhone = placeDescription.getPhone().split(";");
            }
            if (placeDescription.getPrice() != null){
                infoPlacePrice = placeDescription.getPrice().split(";");
            }
        }

        placeImage = placeImageService.getPlaceImageByPlaceId(place_id);

        if (placeImage == null){
            loger.info("Not place image for place_id " + place_id);
        }

        placeResponse = placeResponseService.getPlaceResponseByPlace(place_id);

        if (placeResponse == null){
            loger.info("Not place response for place_id " + place_id);
        } else{
            for (PlaceResponse placeRespons: placeResponse){
                loger.info("Place response " + placeRespons);
                user_id = placeRespons.getUser_id();
                user = userService.getByPK(user_id);
                loger.info("User " + user);
                if (user != null) {
                    users.add(user);
                    userImage = userImageService.getUserImageByUserIdOne(user.getId());
                    if (userImage != null){
                        userImages.add(userImage);
                    }
                }
            }
        }



        request.setAttribute("place", place);
        request.setAttribute("placeDescription", placeDescription);
        request.setAttribute("placeImage", placeImage);
        request.setAttribute("placeResponse", placeResponse);
        request.setAttribute("userImages", userImages);
        request.setAttribute("users", users);
        request.setAttribute("infoPlacePhone", infoPlacePhone);
        request.setAttribute("infoPlacePrice", infoPlacePrice);

        loger.info("Command Place Info.");
        request.getRequestDispatcher("/views/pages/info.jsp").forward(request, response);

    }
}
