package com.lab.epam.servlet;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dima on 07-Jun-15.
 */
public class TryMapServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyCL9BI_9U8ba_Zf_ldHd9KrYFtBtK7cTzI");
        GeocodingResult[] resultsFrom = new GeocodingResult[0];
        GeocodingResult[] resultsTo = new GeocodingResult[0];
        try {
            resultsFrom = GeocodingApi.geocode(context, "Lviv, Svobody Prospect").await();
            resultsTo = GeocodingApi.geocode(context, "Stryiii").await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LatLng southwestFrom = resultsFrom[0].geometry.bounds.southwest;
        LatLng southwestTo = resultsTo[0].geometry.bounds.southwest;
        request.setAttribute("from_x", southwestFrom.lat);
        request.setAttribute("from_y", southwestFrom.lng);
        request.setAttribute("to_x",southwestTo.lat);
        request.setAttribute("to_y",southwestTo.lng);
        request.getRequestDispatcher("views/map.jsp").forward(request,
                response);
    }
}