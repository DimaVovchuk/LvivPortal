package com.lab.epam.command.page.user;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.*;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by Admin on 13.06.2015.
 */
public class UserCabinetCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());


    private List<UserImage> userImage = new ArrayList<>();
    private List<Way> ways = new ArrayList<>();
    private List<Place> places = new ArrayList<>();
    private List<PlaceDescription> placeDescriptions = new ArrayList<>();
    private Map<Integer, List<PlaceDescription>> way_place = new HashMap<>();
    private PlaceService placeService = new PlaceService();
    private PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
    private Map<Integer,PlaceImage> wayPlaceImages = new HashMap<>();
    private PlaceImageService placeImageService = new PlaceImageService();
    String language;


    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        UserService userservice = new UserService();
        UserImageService userImageService = new UserImageService();
        RoleService roleService = new RoleService();
        WayService wayService = new WayService();


        HttpSession session = request.getSession();
        String login = (String)session.getAttribute("login");
        ResourceBundle resourceBandle = (ResourceBundle)session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        language = locale.getLanguage();

        User user = null;
        Role role = null;

        String page = "/views/page/index.jsp";
        loger.info("Login in session is " + login);
        if (login != null) {
            user = userservice.getUserByLogin(login);
        }
        if (user == null){
            loger.warn("No user with login " + login);
        }
        if (user != null) {
            Integer userId = user.getId();
            Integer roleId = user.getRoleID();
            userImage = userImageService.getUserImageByUserId(userId);
            ways = wayService.getWaysByUserId(userId);
            places = placeService.getPlaceByUserId(userId);
            role = roleService.getByPK(roleId);

            if (ways != null && !ways.isEmpty()){
                way_place = getPlaceDescriptionByWay(ways);
            }

            if (places != null && !places.isEmpty()) {
                placeDescriptions = getPlaceDescriptionByPlace(places);
            }
            loger.info("User role is " + role.getRole());

            request.setAttribute("places", places);
            request.setAttribute("userImage", userImage);
            request.setAttribute("ways", ways);
            request.setAttribute("user", user);
            request.setAttribute("placeDescription", placeDescriptions);
            request.setAttribute("way_place", way_place);
            request.setAttribute("wayPlaceImages", wayPlaceImages);
        }
        if (role != null && (role.getRole().equalsIgnoreCase("guide") || role.getRole().equalsIgnoreCase("company"))){
            page = "/views/pages/companycabinet.jsp";
        }

        if (role != null && (role.getRole().equalsIgnoreCase("user") || role.getRole().equalsIgnoreCase("admin"))){
            page = "/views/pages/user-cabinet.jsp";
        }

        //loger.info("Page is " + page);

        loger.info("Command User Cabinet.");
        request.getRequestDispatcher(page).forward(request, response);
    }


    private Map<Integer, List<PlaceDescription>> getPlaceDescriptionByWay(List<Way> ways){
        List<PlaceDescription> way_placeDescription_list = new ArrayList<>();
        List<Place> way_place_list = new ArrayList<>();
        Map<Integer, List<PlaceDescription>> way_place = new HashMap<>();
        Integer way_id;
        for (Way way : ways) {
            way_id = way.getId();
            way_place_list = placeService.getPlaceByWayId(way_id);
            if (way_place_list != null && !way_place_list.isEmpty()){
              //  loger.info(" way_place_list are " + way_place_list + " way_id" + way_id);
                way_placeDescription_list = getPlaceDescriptionByPlace(way_place_list);
               // loger.info(" way_placeDescription_list are " + way_placeDescription_list);
                way_place.put(way_id, way_placeDescription_list);
                wayPlaceImages.put(way_id, getPlaceImageByPlace(way_place_list));
            }

        }
        //loger.info(" way_place are " + way_place);
        //loger.info(" wayPlaceImages are " + wayPlaceImages);
        return way_place;
    }

    private List<PlaceDescription> getPlaceDescriptionByPlace(List<Place> places){
        Integer place_id;
        PlaceDescription placeDescription;
        List <PlaceDescription> placeDescriptions = new ArrayList<>();
        for (Place place : places) {
            place_id = place.getId();
            placeDescription = placeDescriptionService.getPlaceDescriptionByIdPlace(place_id, language);
            placeDescriptions.add(placeDescription);
        }
        return placeDescriptions;
    }

    private PlaceImage getPlaceImageByPlace(List<Place> places){
        PlaceImage placeImage = null;
        Integer place_id;
            for (Place place : places) {
                if (placeImage == null) {
                    place_id = place.getId();
                    placeImage = placeImageService.getPlaceImageByPlaceId(place_id);
                }
            }
        return placeImage;
        }
}


