package com.lab.epam.command;

import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dima on 11-Jun-15.
 */
public class PlaceCommand implements Command{
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        loger.info("Command Place.");
        request.getRequestDispatcher("/views/pages/places.jsp").forward(request, response);
    }
}
