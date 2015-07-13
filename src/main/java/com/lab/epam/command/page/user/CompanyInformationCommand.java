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
 * Created by Vasyl on 27.06.2015.
 */
public class CompanyInformationCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    private List<Place> places = new ArrayList<>();
    private UserService userService = new UserService();
    private PlaceService placeService = new PlaceService();
    private UserImageService userImageService = new UserImageService();
    private PlaceImageService placeImageService = new PlaceImageService();
    private UserRatingService userRatingService = new UserRatingService();
    private PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
    private List<Place> wayPlaces = new ArrayList<>();
    private String language;
    private HttpServletRequest request;
    private WayService wayService = new WayService();
    PlaceImage image = null;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Way> userWayList = null;
        ResourceBundle resourceBandle = (ResourceBundle) session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        language = locale.getLanguage();
        this.request = request;
        List<PlaceDescriptionAndPhoto> placesPageInfo = new ArrayList<>();

        String login = (String) session.getAttribute("login");
        Integer company_id = null;
        String userID = request.getParameter("id");
        if (userID != null) {
            Integer id = Integer.valueOf(userID);
            User userData = userService.getByPK(id);
            UserImage avatar = userImageService.getByPK(userData.getAvatar());
            List<UserImage> userGalery = userImageService.getUserImageByUserId(id);

            places = placeService.getPlaceByUserId(id);
            if (places != null && !places.isEmpty()) {
                for (Place place : places) {
                    placesPageInfo.add(getPlaceDescriptionAndPhotoList(place));
                }
            }

            userWayList = wayService.getWaysByUserId(id);
            UserDataAboutTrip userDataTrip = new UserDataAboutTrip();
            Map<Way, Map<Integer, List<PlaceDescriptionAndPhoto>>> allWaysPlaseInfo = new HashMap<>();

            if (userWayList != null && !userWayList.isEmpty()) {
                for (Way oneWay : userWayList) {
                   Map<Integer, List<PlaceDescriptionAndPhoto>> placesMap = new HashMap<>();
                    wayPlaces = placeService.getPlaceByWayId(oneWay.getId());
                    List<PlaceDescriptionAndPhoto> wayPlacesPageInfo = new ArrayList<>();
                    userDataTrip.setDayCount(oneWay.getWay_days());
                    for (int i = 1; i <= userDataTrip.getDayCount(); i++) {
                        List<Place> places = placeService.getPlaceByWayIdDayNumber(oneWay.getId(), i);
                        List<PlaceDescriptionAndPhoto> wayPlacesPageInfoList = new ArrayList<>();
                        if (places != null && !places.isEmpty()) {
                            for (Place place : places) {
                                wayPlacesPageInfoList.add(getPlaceDescriptionAndPhotoList(place));
                        }
                            placesMap.put(i, wayPlacesPageInfoList);
                        }
                    }
                    allWaysPlaseInfo.put(oneWay,placesMap);
                }
            } else {
                request.setAttribute("allWayInfo", allWaysPlaseInfo);
            }

            Integer companyRatingByUser = 0;
            if (userID != null && login != null){
                User user = userService.getUserByLogin(login);
                company_id = Integer.valueOf(userID);
                if (user != null){
                    UserRating userRating = userRatingService.getUseRatingByCompanyAndUser(company_id, user.getId());
                    if (userRating != null){
                        companyRatingByUser = userRating.getRating();
                    }
                }

            }

            request.setAttribute("placesInfo", placesPageInfo);
            request.setAttribute("userInfo", userData);
            request.setAttribute("avatar", avatar);
            request.setAttribute("userGalery", userGalery);
            request.setAttribute("allWayInfo", allWaysPlaseInfo);
            request.setAttribute("companyRatingByUser", companyRatingByUser);
            request.setAttribute("company_id", company_id);
        }
        loger.info("Command CompanyInformationCommand");

        request.getRequestDispatcher("/views/pages/company-page.jsp").forward(request, response);
    }

    private PlaceDescriptionAndPhoto getPlaceDescriptionAndPhotoList(Place place) {
        Integer currentPlaceID = null;
        PlaceDescriptionAndPhoto item = new PlaceDescriptionAndPhoto();
        currentPlaceID = place.getId();
        item.setId(currentPlaceID);
        image = placeImageService.getPlaceImageByPlaceId(currentPlaceID);
        if (image == null || !isInFolder(image.getReference())) {
            image = new PlaceImage(currentPlaceID, "default_building.jpg");
        }
        item.setImageReference(image.getReference());
        item.setName(placeDescriptionService.getPlaceDescriptionByIdPlace(currentPlaceID, language).getName());
        item.setAdress(placeDescriptionService.getPlaceDescriptionByIdPlace(currentPlaceID, language).getAdress());
        item.setRating(place.getRating());
        return item;
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
