package com.lab.epam.command.page.user.admin;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.PlaceDescription;
import com.lab.epam.entity.PlaceImage;
import com.lab.epam.entity.PlaceRating;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceDescriptionService;
import com.lab.epam.service.PlaceImageService;
import com.lab.epam.service.PlaceRatingService;
import com.lab.epam.service.PlaceService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Vasyl on 21.06.2015.
 */
public class EditPlaceCommand implements Command{
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String editPlaceIDSring = request.getParameter("editPlaceID");
        String editPlaceIDSring = "1";
        HttpSession session = request.getSession();
        loger.info("start EditPlaceCommand");

        if(editPlaceIDSring !=null){
            Integer editPlaceID = Integer.valueOf(editPlaceIDSring);
            PlaceService placeService = new PlaceService();
            loger.info("edit place id is " + editPlaceID);
            //get place
            Place place = placeService.getByPK(editPlaceID);
            session.setAttribute("editPlace", place);
            loger.info("edit place is: " + place.toString());

            //get all place image
            PlaceImageService placeImageService = new PlaceImageService();
            List<PlaceImage> placeImageList = placeImageService.getAllPlaceImageByPlaceId(editPlaceID);
            session.setAttribute("placeImageList", placeImageList);
            loger.info("edit place fotos are " + placeImageList.toString());

            //get place description
            PlaceDescriptionService  placeDescriptionService = new PlaceDescriptionService();
            List<PlaceDescription> placeDescriptionList = placeDescriptionService.getPlaceDescriptionByIdPlace(editPlaceID);
            session.setAttribute("placeDescriptionList", placeDescriptionList);
            loger.info("edit place description are " + placeDescriptionList);

            //get place rating
            PlaceRatingService placeRatingService = new PlaceRatingService();
            PlaceRating placeRating= placeRatingService.getByPK(editPlaceID);
            session.setAttribute("placeRating", placeRating);
            loger.info("edit place rating is " + placeRating);
        }
        response.setContentType("text/html; charset=windows-1251");
        request.getRequestDispatcher("/views/pages/edit_place.jsp").forward(request, response);
    }
}
