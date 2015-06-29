package com.lab.epam.command.page.user;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.*;
import com.lab.epam.service.*;

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
 * Created by Vasyl on 27.06.2015.
 */
public class CompanyInformationCommand implements Command{
    private List<Place> places = new ArrayList<>();
    private List<PlaceDescription> placeDescriptions = new ArrayList<>();
    private List<PlaceImage> placeImage = new ArrayList<>();
    private UserService userService = new UserService();
    private PlaceService placeService = new PlaceService();
    private UserImageService userImageService = new UserImageService();
    private PlaceImageService placeImageService = new PlaceImageService();
    private PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
    private  String language;
    private HttpServletRequest request;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ResourceBundle resourceBandle = (ResourceBundle)session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        language = locale.getLanguage();
        this.request = request;


        String userID = request.getParameter("id");
        if(userID !=null) {
            Integer id = Integer.valueOf(userID);
            User userData = userService.getByPK(id);
            UserImage avatar = userImageService.getUserImageByUserIdOne(id);
            List<UserImage> userGalery = userImageService.getUserImageByUserId(id);

            places = placeService.getPlaceByUserId(id);
            if (places != null && !places.isEmpty()) {
                placeDescriptions = getPlaceDescriptionByPlace(places);
                placeImage = getPlaceImageByPlace(places);
            }
            List<PlaceDescriptionAndPhoto> placesPageInfo = null;
            if(places != null && !places.isEmpty()){
                placesPageInfo = getPlaceDescriptionAndPhotoList(places, placeDescriptions, placeImage);
            }
            request.setAttribute("placesInfo", placesPageInfo);
            session.setAttribute("userInfo", userData);
            session.setAttribute("avatar", avatar);
            session.setAttribute("userGalery",userGalery);
        }
        request.getRequestDispatcher("/views/pages/company-page.jsp").forward(request, response);
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
                            item.setAdress(place.getAdress());
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