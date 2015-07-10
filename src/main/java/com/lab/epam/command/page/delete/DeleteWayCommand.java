package com.lab.epam.command.page.delete;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.User;
import com.lab.epam.entity.UserDataAboutTrip;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.UserService;
import com.lab.epam.service.WayService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Admin on 07.07.2015.
 */
public class DeleteWayCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String way_idString = request.getParameter("way_id");
        Integer way_id = 0;
        HttpSession sesion = request.getSession();
        String login = (String)sesion.getAttribute("login");
        UserDataAboutTrip userDataTrip = (UserDataAboutTrip) sesion.getAttribute("userDataTrip");
        Integer user_id = 0;

        WayService wayService = new WayService();
        UserService userService = new UserService();

        if (way_idString != null){
            way_id = Integer.parseInt(way_idString);
        }

        if (login != null){
            User user = userService.getUserByLogin(login);
            if (user != null){
                user_id = user.getId();
            }
        }

        if (way_id != 0 && user_id != 0){
            wayService.deleteWaysByUserIdWayId(user_id, way_id);
        }
        if (userDataTrip != null){
            if (userDataTrip.getWay_id() != null && userDataTrip.getWay_id().equals(way_id)){
                    userDataTrip.setIsSaved(false);
                    userDataTrip.setIsFull(true);
            }
        }
        sesion.setAttribute("userDataTrip", userDataTrip);
        response.sendRedirect("/portal?command=userAllWay");



    }
}
