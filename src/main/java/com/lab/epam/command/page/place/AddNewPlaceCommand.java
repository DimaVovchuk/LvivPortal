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
 * Created by Vasyl on 25.06.2015.
 */
public class AddNewPlaceCommand implements Command{
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loger.info("Command AddNewPlaceCommand.");
        request.getRequestDispatcher("/views/pages/admin-newplace.jsp").forward(request, response);
    }
}
