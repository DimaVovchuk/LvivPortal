package com.lab.epam.command.page.place;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Decoder;
import com.lab.epam.entity.PlaceDescription;
import com.lab.epam.service.PlaceDescriptionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Admin on 02.07.2015.
 */
public class PlaceSearchCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();

        HttpSession session = request.getSession();

        ResourceBundle resourceBandle = (ResourceBundle) session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        String language = locale.getLanguage();
        String searchResult = "";

        String searchString = request.getParameter("search");
        searchString = Decoder.decodeStringUtf8(searchString);
        System.out.println("searchString " + searchString);
        List<PlaceDescription> placeDescriptions = new ArrayList<>();

        if (searchString != null) {
            placeDescriptions = placeDescriptionService.getAllPlaceBySearch(searchString);
            if (placeDescriptions != null && !placeDescriptions.isEmpty()){
                for (PlaceDescription place: placeDescriptions){
                    searchResult += place.getName() + "\n";
                }
            }
        }
        response.setContentType("application/txt");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(searchResult);
    }
}
