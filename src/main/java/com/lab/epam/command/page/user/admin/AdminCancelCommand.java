package com.lab.epam.command.page.user.admin;

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

/**
 * Created by Dima on 10-Jul-15.
 */
public class AdminCancelCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String deletedPlaceId = request.getParameter("id");
        PlaceService placeService=new PlaceService();
        System.out.println("deletedPlaceId " + deletedPlaceId);
        if(deletedPlaceId!=null && deletedPlaceId!="") {
            Integer placeID = Integer.valueOf(deletedPlaceId);
            Place place = placeService.getByPK(placeID);
            if (page.equals("custom")) {
              System.out.println("custom id " + deletedPlaceId);
                place.setIs_recommended(false);
                System.out.println(place);
                placeService.update(place);
            }
            if (page.equals("recom")) {


                System.out.println("recom id " + deletedPlaceId);
            }
            if (page.equals("recomWay")) {


                System.out.println("recomWay id " + deletedPlaceId);
            }
            if (page.equals("editPlace")) {
//                System.out.println("editPlace id " + deletedPlaceId);
                place.setDeleted(true);
                placeService.update(place);
            }
        }
        if(page.equals("custom")){
            loger.info("custom id " + deletedPlaceId);
        }
        if(page.equals("recom")){
            loger.info("recom id " + deletedPlaceId);
        }
        if(page.equals("recomWay")){
            loger.info("recomWay id " + deletedPlaceId);
        }
        if(page.equals("editPlace")){
            loger.info("editPlace id " + deletedPlaceId);
        }

        loger.info("Command AdminCancelCommand");
    }
}
