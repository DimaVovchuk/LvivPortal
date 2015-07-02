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
 * Created by Admin on 01.07.2015.
 */
public class RecomendedWayLoadCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {


        //String category = request.getParameter("category");
        loger.info("Way Loader.");
        //request.setAttribute("category", category);
        request.getRequestDispatcher("/views/pages/recomended-way.jsp").forward(request, response);
    }


}

