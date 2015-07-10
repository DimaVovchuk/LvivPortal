package com.lab.epam.command.page.user.admin;

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
 * Created by Vasyl on 10.07.2015.
 */
public class ConfirmRecommendedWayCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    private List<Place> wayPlaces = new ArrayList<>();
    private PlaceService placeService = new PlaceService();
    private PlaceImageService placeImageService = new PlaceImageService();
    private PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
    private String language;
    private HttpServletRequest request;
    private WayService wayService = new WayService();
    PlaceImage image = null;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loger.info("ConfirmRecommendedWayCommand start.");
        HttpSession session = request.getSession();
        List<Way> wayList = new ArrayList<>();
        ResourceBundle resourceBandle = (ResourceBundle) session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        language = locale.getLanguage();
        this.request = request;
        List<PlaceDescriptionAndPhoto> placesPageInfo = new ArrayList<>();

        wayList = wayService.getAllConfirmRecommendedWay();//???

        UserDataAboutTrip userDataTrip = new UserDataAboutTrip();
        Map<Way, Map<Integer, List<PlaceDescriptionAndPhoto>>> allWaysPlaseInfo = new HashMap<>();

        if (wayList != null && !wayList.isEmpty()) {
            for (Way oneWay : wayList) {
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
                allWaysPlaseInfo.put(oneWay, placesMap);
            }
        } else {
            request.setAttribute("allWayInfo", allWaysPlaseInfo);
        }

        request.setAttribute("allWayInfo", allWaysPlaseInfo);
        request.getRequestDispatcher("/views/pages/confirmRecommendedWay.jsp").forward(request, response);
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
