package com.lab.epam.command.page.place;

import com.lab.epam.command.controller.Command;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 03.07.2015.
 */
public class RecommendedPlaceCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String place_id = request.getParameter("place_id");
        loger.info("In RecommendedPlaceCommand");
        //request.getRequestDispatcher("/views/pages/addCustomPlace.jsp").forward(request, response);
    }
}
