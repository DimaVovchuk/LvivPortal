package com.lab.epam.command.page.user;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.User;
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
 * Created by Admin on 13.07.2015.
 */
public class DeleteWayRecommendedCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        UserService userservice = new UserService();
        WayService wayService = new WayService();
        String way_idString = request.getParameter("way_id");
        Integer way_id = null;
        if (way_idString != null){
            way_id = Integer.parseInt(way_idString);
            loger.info("Get way_id from request " + way_id);
        }

        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");

        User user = null;

        loger.info("Login in session is " + login);
        if (login != null) {
            user = userservice.getUserByLogin(login);
        }
        if (user == null) {
            loger.warn("No user with login " + login);
        }

        wayService.deleteWayIsRecommended(way_id);
        loger.info("Delete is successes is ");

        response.sendRedirect("?command=recomendedWay");

    }
}
