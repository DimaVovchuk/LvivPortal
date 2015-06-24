package com.lab.epam.command.save;

import com.lab.epam.command.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vasyl on 22.06.2015.
 */
public class SaveEditPlaceCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newAdress = request.getParameter("placeAdress");
        String newPlaceLatitude = request.getParameter("placeLatitude");
        String newPlaceLongitude = request.getParameter("placeLongitude");
        Integer newPlaceTime = Integer.valueOf(request.getParameter("place_time"));
        String newPlaceRating = request.getParameter("newplaceRating");
        String newCategory = request.getParameter("newCategory");
        String newVisible = request.getParameter("newVisible");
        String newState = request.getParameter("newState");

        System.out.println("newAdress " + newAdress);
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
