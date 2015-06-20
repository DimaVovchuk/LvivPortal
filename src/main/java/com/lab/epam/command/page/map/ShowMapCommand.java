package com.lab.epam.command.page.map;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.PlaceDescription;
import com.lab.epam.entity.PlaceImage;
import com.lab.epam.entity.PlaceMarkerWithPhoto;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Vasyl on 09.06.2015.
 */
public class ShowMapCommand implements Command {
        private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        PlaceService placeService = new PlaceService();
        List<Place> all = placeService.getAll();
        PlaceImageService placeImageService = new PlaceImageService();

        List<PlaceMarkerWithPhoto> placeMarkerWithPhotos = new ArrayList<>();
        PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
        HttpSession session = request.getSession();
        ResourceBundle bundle = (ResourceBundle) session.getAttribute("bundle");

        for (Place place : all) {
            PlaceDescription placeDescription = placeDescriptionService.getPlaceDescriptionByIdPlace(place.getId(), bundle.getLocale().toString());
            PlaceImage placeImage = placeImageService.getPlaceImageByPlaceId(place.getId());
            if(placeImage.getReference() == null){
                placeImage.setReference("default_building.jpg");
            }
            placeMarkerWithPhotos.add(new PlaceMarkerWithPhoto(place.getId(), placeDescription.getName(), place.getLatitude(), place.getLongitude(), placeImage.getReference(), placeDescription.getDescription()));
        }
        session.setAttribute("language",bundle.getLocale().toString());
        request.setAttribute("places", placeMarkerWithPhotos);
        loger.info("Command ShowMapCommand.");
        request.getRequestDispatcher("/views/pages/map.jsp").forward(request, response);
    }
}
