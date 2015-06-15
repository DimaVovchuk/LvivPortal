package com.lab.epam.command;

import com.lab.epam.entity.Place;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Dima on 15-Jun-15.
 */
public class GeotegPhotoCommand implements Command{
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        PlaceService placeService = new PlaceService();
        List<Place> all = placeService.getAll();
//        PlaceImageService placeImageService = new PlaceImageService();
//        List<PlaceMarkerWithPhoto> placeMarkerWithPhotos = new ArrayList<>();
//        for (Place place : all) {
//            PlaceImage placeImageByPlaceId = placeImageService.getPlaceImageByPlaceId(place.getId());
//            placeMarkerWithPhotos.add(new PlaceMarkerWithPhoto("",place.getLatitude(),place.getLongitude(),placeImageByPlaceId.getReference()));
//        }
        //request.setAttribute("places",placeMarkerWithPhotos);
        request.setAttribute("places",all);
        loger.info("Command GeotegPhotoCommand.");
        request.getRequestDispatcher("/views/pages/geotegPhoto.jsp").forward(request, response);
    }
}
