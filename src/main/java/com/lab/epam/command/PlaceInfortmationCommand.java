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

        String message = request.getParameter("message");
        loger.info("Message is " + message);

        Integer rating = 0;
        String ratingString = request.getParameter("rating");

        if (ratingString != null){
            rating = Integer.parseInt(ratingString);
        }

        String place_idString = request.getParameter("place_id");
        request.setAttribute("place_id", place_idString);
        Integer place_id = Integer.parseInt(place_idString);
        loger.info("Place with id " + place_id);

        //String place_reference = request.getParameter("place_reference");
       // loger.info("place_reference is " + place_reference);

        PlaceService servicePlace = new PlaceService();
        PlaceResponseService placeResponseService = new PlaceResponseService();
        PlaceRatingService placeRatingService = new PlaceRatingService();

        PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
        PlaceImageService placeImageService = new PlaceImageService();
        UserService userService = new UserService();
        UserImageService userImageService = new UserImageService();
        String place_reference = null;
        PlaceRating place_rating = null;

        if (place_id != null) {
            PlaceImage im = placeImageService.getPlaceImageByPlaceId(place_id);
            place_reference = im.getReference();
            loger.info("place_reference is " + place_reference);
        }

        HttpSession session = request.getSession();

        String login = (String)session.getAttribute("login");
        String not_login = null;
        if (message != null && login == null){
            not_login = "You are logout. Please login and than leave a comment";
        }
        request.setAttribute("not_login", not_login);

        if (login != null && message != null){
            User user = userService.geUserByLogin(login);
            loger.info("User, who send comment has login " + login);
            if (user != null){
                placeResponseService.create(new PlaceResponse(message, user.getId(),place_id));
                place_rating = placeRatingService.getPlaceRatingByPlaceAndUser(place_id,user.getId());
                if (place_rating != null){
                    place_rating.setRating(rating);
                    placeRatingService.update(place_rating);
                }else {
                    placeRatingService.create(new PlaceRating(user.getId(),place_id,rating));
                }
            }
        }

        ResourceBundle resourceBandle = (ResourceBundle)session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        String language = locale.getLanguage();

        Place place = servicePlace.getByPK(place_id);

        loger.info("Place with id " + place_id + " is " + place);

        PlaceDescription placeDescription;
        PlaceImage placeImage;
        User user;
        UserImage userImage = null;
        PlaceRating placeRating = null;
        List<PlaceResponse> placeResponse;
        List<PlaceRating> placeRatings = new ArrayList<>();
        List<UserImage> userImages = new ArrayList<>();
        List<User> users = new ArrayList<>();
        Integer user_id;
        Boolean isInUserArray = false;
        Boolean isInUserImageArray = false;
        Boolean isInPlaceRating = false;

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
        loger.info("Place responses for place_id " + place_id + " is " + placeResponse);
        if (placeResponse == null){
            loger.info("Not place response for place_id " + place_id);
        } else{
            for (PlaceResponse placeRespons: placeResponse) {
                loger.info("Place response " + placeRespons);
                user_id = placeRespons.getUser_id();
                user = userService.getByPK(user_id);
                loger.info("User " + user);
                if (user != null) {
                    if (users != null) {
                        for (User us : users) {
                            if (us.getId() == user.getId()) {
                                isInUserArray = true;
                            }
                        }
                        if (!isInUserArray) {
                            users.add(user);
                        }

                    }

                    userImage = userImageService.getUserImageByUserIdOne(user.getId());
                }
                if (userImage != null) {
                    for (UserImage im : userImages) {
                        if (im.getId() == userImage.getId()) {
                            isInUserImageArray = true;
                        }
                    }
                    if (!isInUserImageArray) {
                        userImages.add(userImage);
                    }
                }
                placeRating = placeRatingService.getPlaceRatingByPlaceAndUser(place_id, user.getId());
                if (placeRating != null) {
                    if (placeRatings != null) {
                        for (PlaceRating plrat : placeRatings) {
                            if (plrat.getId() == placeRating.getId()) {
                                isInPlaceRating = true;
                            }
                        }
                        if (!isInPlaceRating) {
                            placeRatings.add(placeRating);
                        }
                    }
                }
            }
        }

        loger.info("Images " + userImages);

        request.setAttribute("place", place);
        loger.info("place " + place);
        request.setAttribute("placeDescription", placeDescription);
        loger.info("placeDescription " + placeDescription);
        request.setAttribute("placeImage", placeImage);
        loger.info("placeImage " + placeImage);
        request.setAttribute("placeResponse", placeResponse);
        loger.info("placeResponse " + placeResponse);
        request.setAttribute("userImages", userImages);
        loger.info("userImages " + userImages);
        request.setAttribute("users", users);
        loger.info("users " + users);
        request.setAttribute("infoPlacePhone", infoPlacePhone);
        loger.info("infoPlacePhone " + infoPlacePhone);
        request.setAttribute("infoPlacePrice", infoPlacePrice);
        loger.info("infoPlacePrice " + infoPlacePrice);
        request.setAttribute("place_reference", place_reference);
        loger.info("place_reference " + place_reference);
        request.setAttribute("place_reference", place_reference);
        loger.info("placeRatings " + placeRatings);
        request.setAttribute("placeRatings", placeRatings);

        loger.info("Command Place Info.");
        request.getRequestDispatcher("/views/pages/info.jsp").forward(request, response);

    }
}
