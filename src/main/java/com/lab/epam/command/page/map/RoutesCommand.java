package com.lab.epam.command.page.map;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Place;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dima on 19-Jun-15.
 */
public class RoutesCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        PlaceService placeService = new PlaceService();
        List<Place> list = new ArrayList<>();
        list.add(placeService.getByPK(1));
        list.add(placeService.getByPK(2));
        list.add(placeService.getByPK(3));
        list.add(placeService.getByPK(4));
        request.setAttribute("wayPlaces",list);
        loger.info("Command RoutesCommand.");
        request.getRequestDispatcher("/views/pages/routes.jsp").forward(request, response);
    }
}
