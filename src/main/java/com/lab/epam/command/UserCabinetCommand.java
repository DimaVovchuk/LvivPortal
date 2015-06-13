package com.lab.epam.command;

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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Admin on 13.06.2015.
 */
public class UserCabinetCommand implements Command{
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        UserService userservice = new UserService();
        UserImageService userImageService = new UserImageService();
        RoleService roleService = new RoleService();
        WayService wayService = new WayService();
        PlaceService placeService = new PlaceService();

        HttpSession session = request.getSession();
        String login = (String)session.getAttribute("login");

        User user = null;
        Role role = null;

        List<UserImage> userImage = new ArrayList<>();
        List<Way> ways = new ArrayList<>();
        List<Place> places = new ArrayList<>();
        String page = "/views/pages/index.jsp";
        loger.info("Login in session is " + login);
        if (login != null) {
            user = userservice.geUserByLogin(login);
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
        }
        if (role != null && (role.getRole().equalsIgnoreCase("guide") || role.getRole().equalsIgnoreCase("company"))){
            page = "/views/pages/companycabinet.jsp";
        }

        if (role != null && (role.getRole().equalsIgnoreCase("user") || role.getRole().equalsIgnoreCase("admin"))){
            page = "/views/pages/usercabinet.jsp";
        }

        loger.info("Command User Cabinet.");
        request.getRequestDispatcher(page).forward(request, response);
    }


}
