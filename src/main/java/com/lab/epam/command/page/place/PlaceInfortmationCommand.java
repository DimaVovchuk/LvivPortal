package com.lab.epam.command.page.place;

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
 * Created by Admin on 13.06.2015.
 */
public class PlaceInfortmationCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        HttpSession session = request.getSession();
        loger.info("Message is " + message);

          Integer rating = 0;
//        String ratingString = request.getParameter("rating");
//
//        if (ratingString != null) {
//            rating = Integer.parseInt(ratingString);
//        }

        PlaceService servicePlace = new PlaceService();
        PlaceResponseService placeResponseService = new PlaceResponseService();
        PlaceRatingService placeRatingService = new PlaceRatingService();
        PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
        String txtSearch = request.getParameter("txtSearch");
        String place_idString = request.getParameter("place_id");
        Integer loginedUserID = (Integer) session.getAttribute("userID");

        request.setAttribute("place_id", place_idString);
        Integer place_id = 0;
        if (txtSearch != null && !txtSearch.isEmpty()){
            List<PlaceDescription> pl = placeDescriptionService.getAllPlaceBySearch(txtSearch);
            if (pl != null && !pl.isEmpty()) {
                PlaceDescription plD = pl.iterator().next();
                place_id = plD.getPlace_id();
            }
        } if (place_id == 0) {
            if (place_idString != null){
                place_id = Integer.parseInt(place_idString);
            }
        }
        Integer checkCustomEditID = servicePlace.getCheckCustomEditID(loginedUserID, place_id);
        session.setAttribute("checkCustomEditID", checkCustomEditID);

        loger.info("Place with id " + place_id);

        //String place_reference = request.getParameter("place_reference");
        // loger.info("place_reference is " + place_reference);

//        PlaceService servicePlace = new PlaceService();
//        PlaceResponseService placeResponseService = new PlaceResponseService();
//        PlaceRatingService placeRatingService = new PlaceRatingService();


        List <UserImageDescription> userImageDescription = new ArrayList<>();
        PlaceImageService placeImageService = new PlaceImageService();
        UserService userService = new UserService();
        UserImageService userImageService = new UserImageService();
//        String place_reference = null;
        PlaceRating place_rating = null;
        List<PlaceImage> imList = new ArrayList<>();
        if (place_id != null) {
            List<PlaceImage> fullImageList = placeImageService.getAllPlaceImageByPlaceId(place_id);
            if (fullImageList != null && !fullImageList.isEmpty()) {
                imList = fullImageList;
                for (int index = 0; index < imList.size(); index++) {
                    if (fullImageList.get(index) == null || !isInFolder(fullImageList.get(index).getReference(), request)) {
                        imList.remove(index);
                    }
                }
            } else{
                imList.add(new PlaceImage(place_id, "default_building.jpg"));
            }
        }

        List<Place> placeForWay;
        UserDataAboutTrip userDataAboutTrip = (UserDataAboutTrip) session.getAttribute("userDataTrip");
        placeForWay = (ArrayList<Place>) session.getAttribute("placeForWay");
        String dayNumberString = request.getParameter("dayNumber");
        Integer dayNumber = 0;
        if (dayNumberString != null) {
            dayNumber = Integer.parseInt(dayNumberString);
        }
        //

        String placeId = request.getParameter("place_id");
        Place onePlaceForWay = null;
        Boolean isInWay = false;

        if (placeId != null && userDataAboutTrip != null && dayNumber != 0) {
            onePlaceForWay = servicePlace.getByPK(Integer.parseInt(placeId));
            loger.info("Get place is successfull");
            Map<Integer, List<Place>> map = userDataAboutTrip.getPlaceDay();
            loger.info("Get map is successfull");
            Set<Integer> keys = map.keySet();
            if (onePlaceForWay != null || !keys.contains(dayNumber)) {
                if (map.isEmpty()) {
                    placeForWay = new ArrayList<>();
                    loger.info("Create new List<Place>");
                    loger.info("Day is " + dayNumber);
                } else {
                    placeForWay = map.get(dayNumber);
                    loger.info("Get placeForWay");
                    for (Place place : placeForWay) {
                        if (place.getId() == onePlaceForWay.getId()) {
                            isInWay = true;
                        }
                    }
                }
                if (!isInWay) {
                    placeForWay.add(onePlaceForWay);
                  //  loger.info("Add new place to placeForWay. Place is " + onePlaceForWay);
                }
            }
            map.put(dayNumber, placeForWay);
            loger.info("Put placeForWay to map");
            userDataAboutTrip.setPlaceDay(map);
            loger.info("Set map to userDataAboutTrip");
        }
        session.setAttribute("userDataTrip", userDataAboutTrip);

        String login = (String) session.getAttribute("login");
        String not_login = null;
        if (message != null && login == null) {
            not_login = "You are logout. Please login and than leave a comment";
        }
        request.setAttribute("not_login", not_login);

        if (login != null && message != null) {
            User user = userService.getUserByLogin(login);
            loger.info("User, who send comment has login " + login);
            Decoder decoder = new Decoder();
            message = decoder.decodeStringUtf8(message);
            if (user != null && !message.equals("")) {
                placeResponseService.create(new PlaceResponse(message, user.getId(), place_id));
                place_rating = placeRatingService.getPlaceRatingByPlaceAndUser(place_id, user.getId());
                if (place_rating != null) {
                    place_rating.setRating(rating);
                    placeRatingService.update(place_rating);
                } else {
                    placeRatingService.create(new PlaceRating(user.getId(), place_id, rating));
                }
            }
        }

        ResourceBundle resourceBandle = (ResourceBundle) session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        String language = locale.getLanguage();

        Place place = servicePlace.getByPK(place_id);

       // loger.info("Place with id " + place_id + " is " + place);

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
        placeDescription = placeDescriptionService.getPlaceDescriptionByIdPlace(place_id, language);

        if (placeDescription == null) {
            loger.info("Not place description for place_id " + place_id);
        } else {
            if (placeDescription.getPhone() != null) {
                infoPlacePhone = placeDescription.getPhone().split(";");
            }
            if (placeDescription.getPrice() != null) {
                infoPlacePrice = placeDescription.getPrice().split(";");
            }
        }

        placeImage = placeImageService.getPlaceImageByPlaceId(place_id);

        if (placeImage == null) {
            loger.info("Not place image for place_id " + place_id);
        }
        if (login != null) {
            User us = userService.getUserByLogin(login);
            placeRating = placeRatingService.getPlaceRatingByPlaceAndUser(place_id, us.getId());
            if (placeRating != null) {
                rating = placeRating.getRating();
            }
        }
        placeResponse = placeResponseService.getPlaceResponseByPlace(place_id);
        //loger.info("Place responses for place_id " + place_id + " is " + placeResponse);
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
                    if (user.getAvatar() != null) {
                        userImage = userImageService.getByPK(user.getAvatar());
                    } else {
                        userImage = new UserImage(user.getId(), "default-avatar.png");
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
                }
            }
            userImageDescription = createUserImageDescription(users, userImages, placeResponse);
        }

        //loger.info("Images " + userImages);

        request.setAttribute("place", place);
        request.setAttribute("placeDescription", placeDescription);
        request.setAttribute("placeImage", placeImage);
        request.setAttribute("userImageDescription", userImageDescription);
       // request.setAttribute("placeResponse", placeResponse);
       // request.setAttribute("userImages", userImages);
       // request.setAttribute("users", users);
        request.setAttribute("infoPlacePhone", infoPlacePhone);
        request.setAttribute("infoPlacePrice", infoPlacePrice);
        request.setAttribute("rating", rating);
//        loger.info("place " + place);
//        loger.info("infoPlacePrice " + infoPlacePrice);
//        loger.info("placeDescription " + placeDescription);
//        loger.info("placeImage " + placeImage);
//        loger.info("placeResponse " + placeResponse);
//        loger.info("userImages " + userImages);
//        loger.info("users " + users);
//        loger.info("infoPlacePhone " + infoPlacePhone);

        request.setAttribute("place_referenceList", imList);
        //loger.info("place_reference " + place_reference);

     //   request.setAttribute("place_reference", place_reference);
        //loger.info("place_reference " + place_reference);
      //  request.setAttribute("place_reference", place_reference);

        //loger.info("placeRatings " + placeRatings);
        request.setAttribute("placeRatings", placeRatings);

        //loger.info("Command Place Info.");
        request.getRequestDispatcher("/views/pages/info.jsp").forward(request, response);

    }

    private List<UserImageDescription> createUserImageDescription(List<User> users, List<UserImage> userImages, List<PlaceResponse> placeResponses){
        List<UserImageDescription> list = new ArrayList<>();
        for(User user: users){
            for (UserImage userImage: userImages){
                for (PlaceResponse placeResponse: placeResponses){
                    if (user.getId() == userImage.getUser_id() && user.getId() == placeResponse.getUser_id()){
                        UserImageDescription item = new UserImageDescription();
                        item.setLogin(user.getLogin());
                        item.setDescription(placeResponse.getDescription());
                        item.setReference(userImage.getReference());
                        item.setPlace_id(placeResponse.getPlace_id());
                        item.setResponse_id(placeResponse.getId());
                        list.add(item);
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
