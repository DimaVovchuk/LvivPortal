package com.lab.epam.command.page.place;

import com.google.gson.Gson;
import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.PlaceDescriptionAndPhoto;
import com.lab.epam.entity.PlaceImage;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceDescriptionService;
import com.lab.epam.service.PlaceImageService;
import com.lab.epam.service.PlaceService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Vasyl on 08.07.2015.
 */
public class ConfirmRecommendedPlaceJSON implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    private List<Place> places = new ArrayList<>();
    private PlaceService placeService = new PlaceService();
    private PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
    private PlaceImageService placeImageService = new PlaceImageService();
    private HttpServletRequest request;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loger.info("ConfirmRecommendedPlaceJSON start");
        HttpSession session = request.getSession();
        ResourceBundle resourceBandle = (ResourceBundle) session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        String language = locale.getLanguage();
        this.request = request;

        PlaceImage image = null;
        places = placeService.getAllConfirmRecommendedPlace();
        List<PlaceDescriptionAndPhoto> list = new ArrayList<>();
        if (places != null && !places.isEmpty()) {
            for (Place place : places) {
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
                list.add(item);
            }
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(list));
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
