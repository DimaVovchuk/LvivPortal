package com.lab.epam.command.save;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Place;
import com.lab.epam.service.PlaceService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Vasyl on 22.06.2015.
 */
public class SaveEditPlaceCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer savePlaceID = (Integer) session.getAttribute("editPlaceID");
        String newAdress = request.getParameter("placeAdress");
        String newPlaceNameUA = request.getParameter("placeNameUA");
        String newPlaceNameEN = request.getParameter("placeNameEN");
        String newPlaceDescriptionUA = request.getParameter("placeDescriptionUA");
        String newPlaceDescriptionEN = request.getParameter("placeDescriptionEN");
        String newPlacePriceUA = request.getParameter("placeDescriptionUA");
        String newPlacePriceEN = request.getParameter("placePriceEN");
        Integer newPlaceRating = Integer.valueOf(request.getParameter("newPlaceRating"));
        String newPlaceLatitude = request.getParameter("placeLatitude");
        String newPlaceLongitude = request.getParameter("placeLongitude");
        Integer newPlaceTime = Integer.valueOf(request.getParameter("place_time"));
        Integer newCategory = Integer.valueOf(request.getParameter("newCategory"));
        Boolean newVisible = new Boolean(request.getParameter("newVisible"));
        Boolean newState = new Boolean(request.getParameter("newState"));

        PlaceService placeService = new PlaceService();
        Place newPlaceData = new Place();
        newPlaceData.setId(savePlaceID);
        newPlaceData.setAdress(newAdress);
        newPlaceData.setLatitude(newPlaceLatitude);
        newPlaceData.setLongitude(newPlaceLongitude);
        newPlaceData.setRating(newPlaceRating);
        newPlaceData.setCategory_id(newCategory);
        newPlaceData.setPlace_time(newPlaceTime);
        newPlaceData.setVisible(newVisible);
        newPlaceData.setDeleted(newState);
        placeService.update(newPlaceData);


        request.setAttribute("editPlace",newPlaceData);

        System.out.println("savePlaceID " + savePlaceID);
        System.out.println("newAdress " + newAdress);
        System.out.println("newplaceNameUA " + newPlaceNameUA);
        System.out.println("newplaceNameEN " + newPlaceNameEN);
        System.out.println("newplaceDescriptionUA " + newPlaceDescriptionUA);
        System.out.println("newplaceDescriptionEN " + newPlaceDescriptionEN);
        System.out.println("newplacePriceUA " + newPlacePriceUA);
        System.out.println("newplacePriceEN " + newPlacePriceEN);
        System.out.println("newPlaceLatitude " + newPlaceLatitude);
        System.out.println("ewPlaceLongitude " + newPlaceLongitude);
        System.out.println("newPlaceTime " + newPlaceTime);
        System.out.println("newPlaceRating " + newPlaceRating);
        System.out.println("newCategory " + newCategory);
        System.out.println("newState " + newState);
        System.out.println("newVisible " + newVisible);

        response.sendRedirect("/portal?command=editPlace");
    }
}
