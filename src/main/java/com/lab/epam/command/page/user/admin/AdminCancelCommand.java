package com.lab.epam.command.page.user.admin;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.Way;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceService;
import com.lab.epam.service.WayService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dima on 10-Jul-15.
 */
public class AdminCancelCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String deletedPlaceId = request.getParameter("id");
        PlaceService placeService = new PlaceService();
        WayService wayService = new WayService();

        if (deletedPlaceId != null && !deletedPlaceId.equals("")) {
            Integer objectID = Integer.valueOf(deletedPlaceId);

            if (page.equals("custom")) {
                Place place = placeService.getByPK(objectID);
                place.setIs_recommended(false);
                placeService.update(place);
            }

            if (page.equals("recom")) {
                Place place = placeService.getByPK(objectID);
                place.setIs_recommended(false);
                placeService.update(place);
            }

            if (page.equals("confirmRecomWay")) {
                Way way = wayService.getByPK(objectID);
                way.setRecomended(true);
                way.setIs_recommended(false);
                System.out.println("confirmRecomWay " + way);
                wayService.update(way);
            }

            if (page.equals("cancelRecomWay")) {
                Way way = wayService.getByPK(objectID);
                way.setIs_recommended(false);
                System.out.println("cancelRecomWay " + way);
                wayService.update(way);
            }

            if (page.equals("editPlace")) {
                Place place = placeService.getByPK(objectID);
                place.setDeleted(true);
                placeService.update(place);
            }

            if (page.equals("restore")) {
                Place place = placeService.getByPK(objectID);
                place.setDeleted(false);
                placeService.update(place);
            }
        }

        loger.info("Command AdminCancelCommand");
    }
}
