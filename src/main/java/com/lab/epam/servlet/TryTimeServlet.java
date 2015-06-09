package com.lab.epam.servlet;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import netscape.javascript.JSObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 08.06.2015.
 */
public class TryTimeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyCL9BI_9U8ba_Zf_ldHd9KrYFtBtK7cTzI");
        GeocodingResult[] resultsFrom = new GeocodingResult[0];
        try {
            resultsFrom = GeocodingApi.geocode(context, "Lviv, Svobody Prospect").await();
        } catch (Exception e) {
            e.printStackTrace();
        }
      //  JSObject win = JSObject.getWindow(this);


        LatLng southwestFrom = resultsFrom[0].geometry.bounds.southwest;
        request.setAttribute("from_x", southwestFrom.lat);
        request.setAttribute("from_y", southwestFrom.lng);
        request.setAttribute("time", 0);
        request.getRequestDispatcher("views/triptime.jsp").forward(request,
                response);
    }
}
